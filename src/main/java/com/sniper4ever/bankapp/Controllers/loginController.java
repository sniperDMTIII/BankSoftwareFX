package com.sniper4ever.bankapp.Controllers;

import com.sniper4ever.bankapp.AccountManager;
import com.sniper4ever.bankapp.Managers.ScenesManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class loginController {

    @FXML private Label errormessage;
    private String username;


    @FXML private TextField emailfield;

    @FXML private AnchorPane page1, page2, errorpane;

    @FXML private PasswordField pass;


    private boolean accountexist;






    ScenesManager scenesManager = new ScenesManager();
    @FXML
    public void createAccount(ActionEvent e) {
        System.out.println("Création du compte");
        scenesManager.switchFXML(e,"create.fxml","Création de compte");




    }
    public void switchlogin2() {

        accountexist = AccountManager.searchAccount(emailfield.getText());

        if (accountexist == true) {
            System.out.println("Le compte existe bien");
            System.out.println("Le mot de passe est: " + AccountManager.getPass(emailfield.getText()));
            page2.setVisible(true);
            page1.setVisible(false);
        } else {
            System.out.println("Le compte n'existe pas");
            System.out.println(AccountManager.searchAccount(emailfield.getText()));
            System.out.println(accountexist);
            errorpane.setVisible(false);
            System.out.println("lol");
            errorpane.setVisible(true);
            errormessage.setText("⚠ Le compte spécifié n'existe pas");

        }





    }


    public void Mainmenu(ActionEvent e) {
    String vusername = AccountManager.getUser(emailfield.getText());
    String vid = AccountManager.getID(emailfield.getText());
    Double vvalue= AccountManager.getValue(emailfield.getText());
    String vtype = AccountManager.getThis(emailfield.getText());
    Parent root;
    Scene scene;
    Stage stage;


        if (pass.getText().equals(AccountManager.getPass(emailfield.getText()))) {
            System.out.println(vusername);
            System.out.println(vid);
            System.out.println(vvalue);

            page2.setVisible(false);
            page1.setVisible(true);

            //home.setTexter();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
            try {
                root = loader.load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            homeController home = loader.getController();
            home.showvariable(vusername, vid, vvalue, vtype);

            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setTitle("YesBank - Main menu");
            stage.setScene(scene);
            stage.show();


            System.out.println("Mot de passe correct");

        } else {
            errorpane.setVisible(true);
            System.out.println(AccountManager.getPass(emailfield.getText()));
            System.out.println(pass.getText());
            errormessage.setText("⚠ Mot de passe incorrect !");

        }
    }
    public void backpage1() {
        page2.setVisible(false);
        page1.setVisible(true);


    }

}
