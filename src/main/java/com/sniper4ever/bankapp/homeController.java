package com.sniper4ever.bankapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class homeController {

    @FXML
    Label textuser;
    @FXML
    Label textid;
    @FXML
    Label textvalue;
    @FXML
    Label texttype;
    @FXML
    Label test;

    ScenesManager scenesManager = new ScenesManager();



    public void showvariable(String user, String id, Double value, String choix) {

        System.out.println("Home " + user + textuser);
        System.out.println("Home " + id + textid);
        System.out.println("Home " + value + textvalue);

        textvalue.setText(value + " USD");
        textid.setText(id);
        textuser.setText(user);
        texttype.setText("C: " + choix);


    }

 public void refresh(ActionEvent e) {

     textvalue.setText(String.valueOf("500"));
     textid.setText("meilleur id");
     textuser.setText(" je suis l'user");



 }

 public void back(ActionEvent e) {
        scenesManager.switchFXML(e,"login.fxml", "Se connecter");



 }

    public void delete(ActionEvent e) {
        AccountManager.clearallAccounts("Account.dat");
        back(e);



    }


}
