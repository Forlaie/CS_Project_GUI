package com.example.cs_project_gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeScreen_Controller implements Initializable {
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
    private MenuItem drinkHealthPotion;
    @FXML
    private Button floorButton;
    @FXML
    private Button dungeonButton;
    @FXML
    private Button shopButton;
    @FXML
    private Button smitheryButton;
    private static boolean FT = true;

    // load tutorial if first time
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (Main.newUser && FT){
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("newHS.fxml"));
            Scene scene;
            try {
                scene = new Scene(fxmlLoader.load(), 600, 400);
                stage.setTitle("Tutorial");
                stage.setScene(scene);
                stage.show();
                FT = false;
            } catch (IOException ignored) {}
        }
    }

    // load gameInfo screen
    // set homeScreen as previous screen
    @FXML
    protected void clickGameInfo() throws IOException {
        Main.previousScreen = "homeScreen.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("gameInfo.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }

    // load enemyInfo screen
    // set homeScreen as previous screen
    @FXML
    protected void clickEnemyInfo() throws IOException {
        Main.previousScreen = "homeScreen.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("enemyInfo.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }

    // load itemInfo screen
    // set homeScreen as previous screen
    @FXML
    protected void clickItemInfo() throws IOException {
        Main.previousScreen = "homeScreen.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("itemInfo.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }

    // load changeUsername screen
    // set homeScreen as previous screen
    @FXML
    protected void clickChangeUsername() throws IOException {
        Main.previousScreen = "homeScreen.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("changeUsername.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }

    // load changeUsername screen
    // set homeScreen as previous screen
    @FXML
    protected void clickChangePassword() throws IOException {
        Main.previousScreen = "homeScreen.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("changePassword.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }

    // load saveAndExit screen
    // set homeScreen as previous screen
    @FXML
    protected void clickSaveAndExit() throws IOException {
        Main.previousScreen = "homeScreen.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("saveAndExit.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }

    // load inventory screen
    // set homeScreen as previous screen
    @FXML
    protected void clickCheckInventory() throws IOException {
        Main.previousScreen = "homeScreen.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("inventory.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }

    // load profile screen
    // set homeScreen as previous screen
    @FXML
    protected void clickCheckProfile() throws IOException {
        Main.previousScreen = "homeScreen.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("profile.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }

    // load drinkHealthPotion screen
    // set homeScreen as previous screen
    @FXML
    protected void clickDrinkHealthPotion() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("drinkHealthPotion.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }

    // load floor screen
    @FXML
    protected void clickFloor() throws IOException {
        Main.YT.setValue("");
        Main.ET.setValue("");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("floor.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }

    // load dungeon screen
    @FXML
    protected void clickDungeon() throws IOException {
        Main.YT.setValue("");
        Main.ET.setValue("");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dungeonCreation.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }

    // load shop screen
    @FXML
    protected void clickShop() throws IOException {
        Main.previousScreen = "homeScreen.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("shop.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }

    // load smithery screen
    @FXML
    protected void clickSmithery() throws IOException {
        Main.previousScreen = "homeScreen.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("smithery.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }
}
