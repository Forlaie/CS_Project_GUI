package com.example.cs_project_gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Floor_Controller implements Initializable {
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menu;
    @FXML
    private MenuItem gameInfo;
    @FXML
    private MenuItem enemyInfo;
    @FXML
    private MenuItem itemInfo;
    @FXML
    private MenuItem saveAndExit;
    @FXML
    private Menu profile;
    @FXML
    private MenuItem checkProfile;
    @FXML
    private MenuItem changeUsername;
    @FXML
    private MenuItem changePassword;
    @FXML
    private Menu inventory;
    @FXML
    private MenuItem checkInventory;
    @FXML
    private MenuItem drinkPotion;
    @FXML
    private Button fightButton;
    @FXML
    private Button fleeButton;
    @FXML
    private TextArea YTInfo;
    @FXML
    private TextArea ETInfo;
    @FXML
    private ProgressBar healthBar;
    @FXML
    private Label healthLabel;
    @FXML
    private Label floorLabel;
    @FXML
    private HBox fightHBox;
    @FXML
    private Button exitFloorButton;
    @FXML
    private Button continueToNextFloorButton;
    @FXML
    private HBox doneHBox;
    @FXML
    private VBox enemyVBox;
    @Override
    public void initialize(URL location, ResourceBundle resources){
        healthLabel.setText(Main.player.getHealth() + "/" + Main.player.getMaxHealth());
        healthBar.setProgress((double) Main.player.getHealth()/Main.player.getMaxHealth());
        YTInfo.textProperty().bind(Main.YT);
        ETInfo.textProperty().bind(Main.ET);
        for (Enemy enemy : Main.floor.getEnemies()){
            enemyVBox.getChildren().add(enemy.getInfoVBox());
        }
        if (Main.floor.getAllEnemiesDead()){
            floorLabel.setText("Floor " + Floor.floorLevel + " cleared!");
            fightHBox.setVisible(false);
//            fightButton.setDefaultButton(false);
//            fleeButton.setCancelButton(false);
            doneHBox.setVisible(true);
//            exitFloorButton.setCancelButton(true);
//            continueToNextFloorButton.setDefaultButton(true);
        }
        else{
            floorLabel.setText("Floor " + Floor.floorLevel);
            fightHBox.setVisible(true);
//            fightButton.setDefaultButton(true);
//            fleeButton.setCancelButton(true);
            doneHBox.setVisible(false);
//            exitFloorButton.setCancelButton(false);
//            continueToNextFloorButton.setDefaultButton(false);
        }
    }
    @FXML
    protected void clickGameInfo() throws IOException {
        Main.previousScreen = "floor.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("gameInfo.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void clickEnemyInfo() throws IOException {
        Main.previousScreen = "floor.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("enemyInfo.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void clickItemInfo() throws IOException {
        Main.previousScreen = "floor.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("itemInfo.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void clickChangeUsername() throws IOException {
        Main.previousScreen = "floor.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("changeUsername.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void clickChangePassword() throws IOException {
        Main.previousScreen = "floor.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("changePassword.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void clickSaveAndExit() throws IOException {
        Main.previousScreen = "floor.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("saveAndExit.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void clickCheckInventory() throws IOException {
        Main.previousScreen = "floor.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("inventory.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void clickCheckProfile() throws IOException {
        Main.previousScreen = "floor.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("profile.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void clickDrinkPotion() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("drinkPotion.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void clickFlee() throws IOException {
        try {
            Scanner loginInput = new Scanner(new File("loginInfo.txt"));
            String fileUsername = loginInput.nextLine();
            String filePassword = loginInput.nextLine();
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
            Main.player = new Player(fileUsername, filePassword, health, maxHealth, defence, attack, level, xp, coins, materialQuantities, potionQuantities, swordInfo, shieldInfo, armourInfo);
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
            Stage stage = (Stage) menuBar.getScene().getWindow();
            stage.setTitle("Wen Ymar Elad");
            stage.setScene(scene);
            stage.show();
        } catch (FileNotFoundException e) {
            System.out.println("Can't read files");
        }
    }

    @FXML
    protected void clickFight() throws IOException {
        Main.player.Fbattle(YTInfo, ETInfo, healthBar, healthLabel, floorLabel, fightHBox, doneHBox, enemyVBox);
//        Thread.sleep(1000);
//        Main.player.FbattleE(YTInfo, ETInfo, healthBar, healthLabel, floorLabel);
    }

    @FXML
    protected void clickExitFloor() throws IOException {
        Main.putInfoIntoPlayerInfoFile();
        Main.floor = new Floor();
        Main.putInfoIntoFloorFile();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("homeScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void clickContinueToNextFloor() throws IOException {
        Main.putInfoIntoPlayerInfoFile();
        Main.floor = new Floor();
        Main.putInfoIntoFloorFile();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("floor.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }
}
