package com.example.cs_project_gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstScreen_Controller {
    @FXML
    private Button signupButton;
    @FXML
    private Button loginButton;

    @FXML
    protected void clickSignup() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("signup.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) signupButton.getScene().getWindow();
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void clickLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }
}