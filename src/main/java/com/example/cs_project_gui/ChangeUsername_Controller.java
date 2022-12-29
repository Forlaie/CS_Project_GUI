package com.example.cs_project_gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class ChangeUsername_Controller {
    @FXML
    private Button backButton;
    @FXML
    private TextField textField;
    @FXML
    private Button submitButton;
    @FXML
    private Label messageLabel;

    @FXML
    protected void clickSubmit() throws FileNotFoundException, NoSuchAlgorithmException {
        String username = textField.getText();
        if (username.equals("")){
            messageLabel.setText("Please have at least one character in your username.");
        }
        else{
            Main.player.setUsername(username);
            messageLabel.setText("Successfully changed username to " + username + "!");
            Main.putInfoIntoLoginInfoFile();
        }
    }

    @FXML
    protected void clickBack() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(Main.previousScreen));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }
}
