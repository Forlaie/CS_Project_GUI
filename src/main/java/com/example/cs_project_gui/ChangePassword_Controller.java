package com.example.cs_project_gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class ChangePassword_Controller {
    @FXML
    private Button backButton;
    @FXML
    private PasswordField oldPasswordField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Button submitButton;
    @FXML
    private Label messageLabel;

    @FXML
    protected void clickSubmit() throws FileNotFoundException, NoSuchAlgorithmException {
        String oldPassword = oldPasswordField.getText();
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String hashedOldPassword = Main.hashPassword(oldPassword);
        if (!Main.player.getPassword().equals(hashedOldPassword)){
            messageLabel.setText("Original password does not match. Please try again");
        }
        else if (newPassword.equals("")){
            messageLabel.setText("Please have at least one character in your new password.");
        }
        else if (!newPassword.equals(confirmPassword)){
            messageLabel.setText("Your new password does not match the confirmation. Please try again");
        }
        else{
            String hashedNewPassword = Main.hashPassword(newPassword);
            Main.player.setPassword(hashedNewPassword);
            messageLabel.setText("Successfully changed password!");
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
