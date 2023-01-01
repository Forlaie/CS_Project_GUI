package com.example.cs_project_gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class GameInfo_Controller {
    @FXML
    private Button backButton;

    // go back to previous screen
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
