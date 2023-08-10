package com.sniper4ever.bankapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BankApp extends Application {

    public static Map<String, AccountManager> readHashMapFromFile(String filename, Map<String, AccountManager> existingMap) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            Map<String, AccountManager> loadedMap = (Map<String, AccountManager>) objectInputStream.readObject();

            existingMap.putAll(loadedMap);

            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return existingMap;
    }


    public static void main(String[] args) {

        launch(args);

        File usersFile = new File("Users.dat");

        if (!usersFile.exists()) {
            try {
                usersFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(System.getProperty("user.dir"));


        //String id = AccountManager.generateRandomString(8);
        //AccountManager.newAccount(id,"loic", "daniel12", 400, "c");
//        AccountManager.addUserEmail(key, email);

        Map<String, AccountManager> existingAccountMap = new HashMap<>();
        // Peupler existingAccountMap avec des données existantes si nécessaire

        Map<String, AccountManager> loadedAccountMap = readHashMapFromFile("Account.dat", existingAccountMap);

        // Afficher ou manipuler le contenu de la HashMap dans la console
        for (Map.Entry<String, AccountManager> entry : loadedAccountMap.entrySet()) {
            String key = entry.getKey();
            AccountManager value = entry.getValue();
            System.out.println("Key: " + key + ", Value: " + value + " Owner: " + value.owner + "Email : " + value.email);
        }

        //AccountManager.clearallAccounts("Account.dat");

        //Map<String, AccountManager> loadedUsersMap = readHashMapFromFile("Users.dat", existingAccountMap);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez un email");
        String email = scanner.next();
        System.out.println("Entrez une key");
        String key = scanner.next();



    }

    @Override
    public void start(Stage stage) throws Exception {
try {
    Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
    Scene scene = new Scene(root);
    scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

    Image icon = new Image("logo_bank.png");

    stage.getIcons().add(icon);
    stage.setTitle("YesBank - Se connecter");
    stage.setResizable(false);

    stage.setScene(scene);
    stage.show();
} catch (Exception e) {
    e.printStackTrace();
}



    }
}