package com.example.cs_project_gui;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
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

public class DrinkPotion_Controller implements Initializable {
    @FXML
    private Button backButton;
    @FXML
    private TableView<Potion> tableView;
    @FXML
    private TableColumn<Potion, CheckBox> selectColumn;
    @FXML
    private TableColumn<Potion, String> nameColumn;
    @FXML
    private TableColumn<Potion, Integer> amountColumn;
    @FXML
    private TableColumn<Potion, TextField> useColumn;
    @FXML
    private Button submitButton;
    @FXML
    private Label messageLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        selectColumn.setCellValueFactory(new PropertyValueFactory<Potion, CheckBox>("checkBox"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Potion, String>("name"));
        //amountColumn.setCellValueFactory(new PropertyValueFactory<Potion, Integer>("amount"));
        amountColumn.setCellValueFactory(p -> {
            p.getValue().getCheckBox().setSelected(false);
            p.getValue().getTextField().clear();
            p.getValue().getTextField().setDisable(true);
            int intValue = Main.player.getInventory().get(p.getValue());
            ObservableValue<Integer> obsInt = new SimpleIntegerProperty(intValue).asObject();
            return obsInt;
        });
        useColumn.setCellValueFactory(new PropertyValueFactory<Potion, TextField>("textField"));
        tableView.setItems(Main.player.getObservableInventory());
    }

    @FXML
    protected void clickBack() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("floor.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void clickSubmit() throws IOException {
        Main.player.usePotion(messageLabel);
    }
}
