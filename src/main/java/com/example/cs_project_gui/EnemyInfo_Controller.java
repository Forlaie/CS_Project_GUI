package com.example.cs_project_gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EnemyInfo_Controller implements Initializable {
    @FXML
    private Button backButton;
    @FXML
    private TableView<Enemy> tableView;
    @FXML
    private TableColumn<Enemy, String> nameColumn;
    @FXML
    private TableColumn<Enemy, String> descriptionColumn;

    // initialize tableviews when loaded
    @Override
    public void initialize(URL location, ResourceBundle resources){
        nameColumn.setCellValueFactory(new PropertyValueFactory<Enemy, String>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Enemy, String>("description"));
        tableView.setItems(Enemy.getObservablePossibleEnemies());
    }

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
