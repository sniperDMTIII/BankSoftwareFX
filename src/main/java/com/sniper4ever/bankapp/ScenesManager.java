package com.sniper4ever.bankapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class ScenesManager {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchFXML(ActionEvent event, String filename, String title) {
        try {
            root = FXMLLoader.load(getClass().getResource(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Yesbank - "+ title);
        stage.setScene(scene);
        stage.show();


    }


    public void switchlogin(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("login.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("YesBank - Main menu");
        stage.setScene(scene);
        stage.show();


    }
    @FXML
    public void switchcreate(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("create.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("YesBank - Créer un compte");
        stage.setScene(scene);
        stage.show();

    }
}
