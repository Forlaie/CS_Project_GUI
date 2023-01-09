package com.example.cs_project_gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class Login_Controller {
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Button loginButton;
    @FXML
    private Button backButton;
    @FXML
    private Label messageLabel;

    // confirm username and password entered are correct
    // load home screen if correct, display error messages if not
    @FXML
    protected void clickLogin() throws IOException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        if (username.equals("") || password.equals("")){
            messageLabel.setText("Please enter your username and password");
        }
        else{
            try {
                Scanner loginInput = new Scanner(new File("loginInfo.txt"));
                if (!loginInput.hasNextLine()) {
                    messageLabel.setText("You don't have an account. Please sign up");
                } else {
                    String hashedPassword = Main.hashPassword(password);
                    String fileUsername = loginInput.nextLine();
                    String filePassword = loginInput.nextLine();
                    if (!(fileUsername.equals(username) && filePassword.equals(hashedPassword))) {
                        messageLabel.setText("Your username or password is incorrect. Please try again");
                    } else {
                        Scanner playerInput = new Scanner(new File("playerInfo.txt"));
                        int health = Integer.parseInt(playerInput.nextLine());
                        int maxHealth = Integer.parseInt(playerInput.nextLine());
                        int defence = Integer.parseInt(playerInput.nextLine());
                        int attack = Integer.parseInt(playerInput.nextLine());
                        int level = Integer.parseInt(playerInput.nextLine());
                        int xp = Integer.parseInt(playerInput.nextLine());
                        int coins = Integer.parseInt(playerInput.nextLine());
                        int[] materialQuantities = new int[6];
                        for (int i = 0; i < 6; i++){
                            int quantity = Integer.parseInt(playerInput.nextLine());
                            materialQuantities[i] = quantity;
                        }
                        int[] potionQuantities = new int[3];
                        for (int i = 0; i < 3; i++){
                            int quantity = Integer.parseInt(playerInput.nextLine());
                            potionQuantities[i] = quantity;
                        }
                        int[] swordInfo = new int[3];
                        for (int i = 0; i < 3; i++){
                            int stat = Integer.parseInt(playerInput.nextLine());
                            swordInfo[i] = stat;
                        }
                        int[] shieldInfo = new int[3];
                        for (int i = 0; i < 3; i++){
                            int stat = Integer.parseInt(playerInput.nextLine());
                            shieldInfo[i] = stat;
                        }
                        int[] armourInfo = new int[3];
                        for (int i = 0; i < 3; i++){
                            int stat = Integer.parseInt(playerInput.nextLine());
                            armourInfo[i] = stat;
                        }
                        Main.newUser = false;
                        Main.player = new Player(fileUsername, filePassword, health, maxHealth, defence, attack, level, xp, coins, materialQuantities, potionQuantities, swordInfo, shieldInfo, armourInfo);
                        //messageLabel.setText("Successful login!");
                        Scanner floorInput = new Scanner(new File("floorInfo.txt"));
                        Floor.floorLevel = Integer.parseInt(floorInput.nextLine());
                        ArrayList<String> enemyNames = new ArrayList<String>();
                        while (floorInput.hasNextLine()){
                            enemyNames.add(floorInput.nextLine());
                        }
                        Main.floor = new Floor(enemyNames);
                        floorInput.close();

                        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("homeScreen.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                        Stage stage = (Stage) loginButton.getScene().getWindow();
                        stage.setTitle("Wen Ymar Elad");
                        stage.setScene(scene);
                        stage.show();
                    }
                }
            }
            catch (FileNotFoundException e) {
                System.out.println("Can't read files");
            } catch (NoSuchAlgorithmException ignored) {}
        }
    }

    // go back to previous screen
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
