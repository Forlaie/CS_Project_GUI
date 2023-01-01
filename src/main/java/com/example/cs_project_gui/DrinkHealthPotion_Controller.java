package com.example.cs_project_gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DrinkHealthPotion_Controller implements Initializable {
    @FXML
    private Button backButton;
    @FXML
    private Label currentHealthLabel;
    @FXML
    private Label numOfHealthPotionsLabel;
    @FXML
    private TextField useTextField;
    @FXML
    private Button submitButton;
    @FXML
    private Label messageLabel;

    // initialize labels when loaded
    @Override
    public void initialize(URL location, ResourceBundle resources){
        currentHealthLabel.setText("Current health: " + Main.player.getHealth() + "/" + Main.player.getMaxHealth());
        if (Main.player.getInventory().get(Item.potions[0]) == null){
            numOfHealthPotionsLabel.setText("Health potions: 0");
        }
        else{
            numOfHealthPotionsLabel.setText("Health potions: " + Main.player.getInventory().get(Item.potions[0]));
        }
    }

    // use amount of health potions selected
    @FXML
    protected void clickSubmit(){
        Main.player.drinkHealthPotion(currentHealthLabel, numOfHealthPotionsLabel, useTextField, messageLabel);
    }

    // go back to previous screen
    @FXML
    protected void clickBack() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("homeScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }
}
