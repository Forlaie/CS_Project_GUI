package com.example.cs_project_gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DungeonCreation_Controller implements Initializable {
    @FXML
    private MenuButton typeMenuButton;
    @FXML
    private MenuItem enemyMenuItem;
    @FXML
    private MenuItem vampireMenuItem;
    @FXML
    private MenuItem golemMenuItem;
    @FXML
    private MenuButton difficultyMenuButton;
    @FXML
    private MenuItem easyMenuItem;
    @FXML
    private MenuItem mediumMenuItem;
    @FXML
    private MenuItem hardMenuItem;
    @FXML
    private Button enterDungeonButton;
    @FXML
    private Label dungeonTypeLabel;
    @FXML
    private Label dungeonDifficultyLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        enemyMenuItem.setOnAction(e -> {
            typeMenuButton.setText("Enemy");
            dungeonTypeLabel.setText("Enemy dungeon\nOnly drops enemy materials");
            difficultyMenuButton.setDisable(false);
        });
        vampireMenuItem.setOnAction(e -> {
            typeMenuButton.setText("Vampire");
            dungeonTypeLabel.setText("Vampire dungeon\nOnly drops vampire materials");
            difficultyMenuButton.setDisable(false);
        });
        golemMenuItem.setOnAction(e -> {
            typeMenuButton.setText("Golem");
            dungeonTypeLabel.setText("Golem dungeon\nOnly drops golem materials");
            difficultyMenuButton.setDisable(false);
        });
        easyMenuItem.setOnAction(e -> {
            difficultyMenuButton.setText("Easy");
            dungeonDifficultyLabel.setText("5 monsters\nNormal stats");
            enterDungeonButton.setDisable(false);
        });
        mediumMenuItem.setOnAction(e -> {
            difficultyMenuButton.setText("Medium");
            dungeonDifficultyLabel.setText("6 monsters\nStats are doubled");
            enterDungeonButton.setDisable(false);
        });
        hardMenuItem.setOnAction(e -> {
            difficultyMenuButton.setText("Hard");
            dungeonDifficultyLabel.setText("7 monsters\nStats are tripled");
            enterDungeonButton.setDisable(false);
        });
    }

    @FXML
    protected void clickEnterDungeon() throws IOException {
        Dungeon.enemyType = typeMenuButton.getText();
        Dungeon.difficultyLevel = difficultyMenuButton.getText();
        switch (Dungeon.difficultyLevel) {
            case "Easy" -> Main.dungeon = new Dungeon(1);
            case "Medium" -> Main.dungeon = new Dungeon(2);
            case "Hard" -> Main.dungeon = new Dungeon(3);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dungeon.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) typeMenuButton.getScene().getWindow();
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }
}
