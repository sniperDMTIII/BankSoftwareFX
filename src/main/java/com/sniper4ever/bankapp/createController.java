package com.sniper4ever.bankapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class createController {


    @FXML
    private String defemail;
    @FXML
    private String defpass;
    @FXML
    private String defusername;
    @FXML
    private String deftype;
    private Double defvalue;

    @FXML
    private Label showuser, showemail, showpass, showtype, showvalue;

    @FXML
    private Label errormsg;

    @FXML
    private TextField email;
    @FXML
    private TextField username, value;
    @FXML
    private PasswordField pass, pass2;


    @FXML
    private AnchorPane page1, page2, page3, finalpage;

    @FXML
    private RadioButton Buttonc, Buttone, Buttonp;

    @FXML
    private Button next1, next2;

    private boolean check;

    ScenesManager scenesManager = new ScenesManager();

    public void switchpage2(ActionEvent e) {
        check = checkpage1();

        if (check == true) {

            page1.setVisible(false);
            page2.setVisible(true);

            System.out.println(email.getText());
        }
    }


    public boolean checkpage1() {
        if (!email.getText().isEmpty() && !pass.getText().isEmpty() && !pass2.getText().isEmpty()) {
            if (email.getText().contains(".") && pass.getText().equals(pass2.getText()) && email.getText().contains("@")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean checkpage2() {
        if (!username.getText().isEmpty() && !value.getText().isEmpty()) {
            if (Buttone.isSelected() || Buttonc.isSelected() ||Buttonp.isSelected()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void changeOpacity(KeyEvent e) {
        check = checkpage1();
        if (check == true) {
            next1.setOpacity(1);
        } else next1.setOpacity(0.5);

    }

    public void changeOpacity2(KeyEvent e) {
        check = checkpage2();
        if (check == true) {
            next2.setOpacity(1);
        } else next2.setOpacity(0.5);

    }

    public void switchpage3(ActionEvent e) {
        defemail = String.valueOf(email.getText());
        defpass = String.valueOf(pass.getText());
        defusername = String.valueOf(username.getText());
        defvalue = Double.valueOf(String.valueOf(value.getText()));

        check = checkpage2();

        if (check == true) {
            if (Buttone.isSelected()) {
                printresult("Epargne");
            } else if (Buttonp.isSelected()) {
                printresult("Professionel");
            } else if (Buttonc.isSelected()) {
                printresult("Courant");

            }


        }
    }

    public void printresult(String vtype) {
        defemail = String.valueOf(email.getText());
        defpass = String.valueOf(pass.getText());
        defusername = String.valueOf(username.getText());
        deftype = vtype;
        defvalue = Double.valueOf(String.valueOf(value.getText()));

        showuser.setText(defusername);
        showemail.setText(defemail);
        showpass.setText( defpass);
        showtype.setText( deftype);
        showvalue.setText(defvalue + "USD");

        page2.setVisible(false);
        page3.setVisible(true);


    }


    public String returntype() {
        if (Buttone.isSelected()) {
            System.out.println("Compte Epargne");
            return "Épargne";
        } else if (Buttonp.isSelected()) {
            System.out.println("Compte Professionel");
            return "Professionel";
        } else if (Buttonc.isSelected()) {
            System.out.println("Compte Courant");
            return "Courant";
        }
        return null;
    }






    public void createaccount(ActionEvent e) {
        defemail = String.valueOf(email.getText());
        defpass = String.valueOf(pass.getText());
        defusername = String.valueOf(username.getText());
        deftype = returntype();
        defvalue = Double.valueOf(String.valueOf(value.getText()));

        if ( !defusername.isEmpty()  && !deftype.isEmpty() && !value.getText().isEmpty()) {
            String id = AccountManager.generateRandomString(8);
            AccountManager.newAccount(id, defusername, defpass, defvalue, deftype, defemail);
            page3.setVisible(false);
            finalpage.setVisible(true);





    }

//    public void checkEmail(KeyEvent e) {
//        email.textProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue.contains(".")) {
//                erroremail.setVisible(false);
//                erroremail.setText("email valide!");
//                System.out.println("email valide");
//            } else {
//                erroremail.setVisible(true);
//                erroremail.setText("Veuillez entrez un email valide");
//                System.out.println();
            }



    public void backlogin(ActionEvent e) {
        scenesManager.switchFXML(e, "login.fxml", "Se connecter");

    }

    public void back1(ActionEvent e) {
        page2.setVisible(false);
        page1.setVisible(true);

    }

    public void back2(ActionEvent e) {
        page2.setVisible(true);
        page3.setVisible(false);

    }

}
