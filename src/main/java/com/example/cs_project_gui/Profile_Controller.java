package com.example.cs_project_gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Profile_Controller implements Initializable {
    @FXML
    private Button backButton;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label levelLabel;
    @FXML
    private ProgressBar xpBar;
    @FXML
    private Label xpLabel;
    @FXML
    private Label coinLabel;
    @FXML
    private Label healthLabel;
    @FXML
    private Label defenceLabel;
    @FXML
    private Label attackLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        usernameLabel.setText("Username: " + Main.player.getUsername());
        levelLabel.setText("Level: " + Main.player.getLevel());
        xpBar.setProgress(Main.player.getXP()/(Main.player.getLevel()*10.0));
        xpLabel.setText(Main.player.getXP() + "/" + (Main.player.getLevel()*10) + " XP");
        coinLabel.setText("Coins: " + Main.player.getCoins());
        healthLabel.setText("Health: " + Main.player.getHealth());
        defenceLabel.setText("Defence: " + Main.player.getDefence());
        attackLabel.setText("Attack: " + Main.player.getAttack());
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
