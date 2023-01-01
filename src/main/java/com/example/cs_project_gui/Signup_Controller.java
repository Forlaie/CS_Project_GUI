package com.example.cs_project_gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Signup_Controller {
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private PasswordField confirmTextField;
    @FXML
    private Button signupButton;
    @FXML
    private Button backButton;
    @FXML
    private Label messageLabel;

    @FXML
    protected void clickSignup() throws IOException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String confirm = confirmTextField.getText();
        if (username.equals("") || password.equals("")){
            messageLabel.setText("Please enter your username and password");
        }
        else{
            if (password.equals(confirm)){
                try{
                    String hashedPassword = Main.hashPassword(password);
                    Main.player = new Player(username, hashedPassword);
                    Main.putInfoIntoLoginInfoFile();
                    Main.putInfoIntoPlayerInfoFile();
                    Main.floor = new Floor();
                    Main.putInfoIntoFloorFile();
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("homeScreen.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                    Stage stage = (Stage) signupButton.getScene().getWindow();
                    stage.setTitle("Wen Ymar Elad");
                    stage.setScene(scene);
                    stage.show();
                } catch (FileNotFoundException e) {
                    System.out.println("Can't write to files");
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                messageLabel.setText("Your passwords don't match up. Please try again");
            }
        }
    }

    @FXML
    protected void clickBack() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("firstScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }
}
