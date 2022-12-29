package com.example.cs_project_gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SaveAndExit_Controller {
    @FXML
    private Button yesButton;
    @FXML
    private Button noButton;

    @FXML
    protected void clickYes() {
        Platform.exit();
    }

    @FXML
    protected void clickNo() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(Main.previousScreen));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) yesButton.getScene().getWindow();
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }
}
