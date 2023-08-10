package com.sniper4ever.bankapp;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class AccountManager implements Serializable {
    String owner;
    String pass;

    String type;

    String id;

    String email;
    double value;

    private static long serialVersionUID = 123456789L;


    public AccountManager(String id, String owner, String pass, double value, String type, String email) {
        this.owner = owner;
        this.pass = pass;
        this.value = value;
        this.type = type;
        this.id = id;
        this.email = email;

    }

    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }

        return randomString.toString();
    }

    public static void newAccount(String id, String owner, String pass, double value, String type, String email) {

            try {

                Map<String, AccountManager> accounts;

                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Account.dat"));
                accounts = (Map<String, AccountManager>) ois.readObject();
                System.out.println("NOCHECK " + accounts);
                ois.close();


                if (!accounts.containsKey(email)) {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Account.dat"));
                    accounts.putIfAbsent(email.toLowerCase(Locale.ROOT), new AccountManager(id, owner, pass, value, type, email.toLowerCase()));
                    System.out.println("Le compte " + email + " a été créer avec succès !");
                    System.out.println("Nocheck3" + accounts);
                    oos.writeObject(accounts);
                    oos.close();
                } else {
                    System.out.println("L'email de ce compte existe deja");
                    System.out.println(accounts);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }



    public static boolean searchAccount(String email) {

        try {

        Map<String, AccountManager> accounts;

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Account.dat"));
        accounts = (Map<String, AccountManager>) ois.readObject();
        System.out.println("NOCHECK " + accounts);
        ois.close();

        if (accounts.containsKey(email)) {
            return true;
        } else return false;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPass(String email) {
        try {
            Map<String, AccountManager> accounts;

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Account.dat"));
            accounts = (Map<String, AccountManager>) ois.readObject();
            ois.close();

            AccountManager accountManager = accounts.get(email);
            String passManager = accounts.get(email).pass;
            if (accountManager != null) {
                return passManager; // Retourne le mot de passe associé à l'email
            } else {
                return "Email non trouvé";
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getUser(String email) {
        try {
            Map<String, AccountManager> accounts;

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Account.dat"));
            accounts = (Map<String, AccountManager>) ois.readObject();
            ois.close();

            AccountManager accountManager = accounts.get(email);
            String userManager = accounts.get(email).owner;
            if (accountManager != null) {
                return userManager; // Retourne le mot de passe associé à l'email
            } else {
                return "Email non trouvé";
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getID(String email) {
        try {
            Map<String, AccountManager> accounts;

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Account.dat"));
            accounts = (Map<String, AccountManager>) ois.readObject();
            ois.close();

            AccountManager accountManager = accounts.get(email);
            String idManager = accounts.get(email).id;
            if (accountManager != null) {
                return idManager; // Retourne le mot de passe associé à l'email
            } else {
                return "Email non trouvé";
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    public static Double getValue(String email) {
        try {
            Map<String, AccountManager> accounts;
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Account.dat"));
            accounts = (Map<String, AccountManager>) ois.readObject();
            ois.close();


            AccountManager accountManager = accounts.get(email);
            Double valueManager = accounts.get(email).value;
            if (accountManager != null) {
                return valueManager; // Retourne le mot de passe associé à l'email
            } else {
                return null;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getThis(String email) {
        try {
            Map<String, AccountManager> accounts;

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Account.dat"));
            accounts = (Map<String, AccountManager>) ois.readObject();
            ois.close();

            AccountManager accountManager = accounts.get(email);
            String typeManager = accounts.get(email).type;
            if (accountManager != null) {
                return typeManager; // Retourne le mot de passe associé à l'email
            } else {
                return null;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public static void clearallAccounts(String filename) {
        try {
            File file = new File(filename);

            if (file.exists()) {
                Map<String, AccountManager> emptyMap = new HashMap<>();
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(emptyMap);
                oos.close();
                System.out.println("HashMap data in Account.dat has been cleared.");
            } else {
                System.out.println("Account.dat does not exist. No data cleared.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addUserEmail(String key, String email)
    {
        try {
            File accountFile = new File("Account.dat");
            File usersFile = new File("Users.dat");

            if (!usersFile.exists()) {
                usersFile.createNewFile();
            }

            if (accountFile.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(accountFile));
                Map<String, AccountManager> loadedAccountMap = (Map<String, AccountManager>) ois.readObject();
                ois.close();

                if (loadedAccountMap.containsKey(key)) {
                    Map<String, String> userMap = new HashMap<>();
                    if (usersFile.exists()) {
                        ObjectInputStream usersOis = new ObjectInputStream(new FileInputStream(usersFile));
                        userMap = (Map<String, String>) usersOis.readObject();
                        usersOis.close();
                    }

                    if (!userMap.containsKey(key) && !userMap.containsValue(email)) {
                        userMap.put(key, email);
                        ObjectOutputStream usersOos = new ObjectOutputStream(new FileOutputStream(usersFile));
                        usersOos.writeObject(userMap);
                        usersOos.close();
                        System.out.println("User email added to Users.dat: Key = " + key + ", Email = " + email);
                    } else {
                        System.out.println("User email already exists for this key in Users.dat.");
                    }
                } else {
                    System.out.println("Key not found in Account.dat.");
                }
            } else {
                System.out.println("Account.dat does not exist.");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}








