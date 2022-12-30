package com.example.cs_project_gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Died_Controller {
    @FXML
    private Button PSButton;
    @FXML
    private Button BButton;

    @FXML
    protected void clickPS() throws IOException {
        Main.player.died("S");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("homeScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) PSButton.getScene().getWindow();
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void clickB() throws IOException {
        Main.player.died("B");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("homeScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) BButton.getScene().getWindow();
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }
}
