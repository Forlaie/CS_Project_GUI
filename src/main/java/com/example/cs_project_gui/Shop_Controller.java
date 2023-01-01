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

public class Shop_Controller implements Initializable {
    @FXML
    private Button backButton;
    @FXML
    private TableView<Potion> tableView;
    @FXML
    private TableColumn<Potion, CheckBox> selectColumn;
    @FXML
    private TableColumn<Potion, String> nameColumn;
    @FXML
    private TableColumn<Potion, String> descriptionColumn;
    @FXML
    private TableColumn<Potion, Integer> costColumn;
    @FXML
    private TableColumn<Potion, TextField> buyAmountColumn;
    @FXML
    private Button submitButton;
    @FXML
    private Label messageLabel;
    @FXML
    private Label coinsLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        selectColumn.setCellValueFactory(new PropertyValueFactory<Potion, CheckBox>("checkBox"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Potion, String>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Potion, String>("description"));
        costColumn.setCellValueFactory(p -> {
            p.getValue().getCheckBox().setSelected(false);
            p.getValue().getTextField().clear();
            p.getValue().getTextField().setDisable(true);
            int intValue = p.getValue().getCost();
            ObservableValue<Integer> obsInt = new SimpleIntegerProperty(intValue).asObject();
            return obsInt;
        });
        costColumn.setCellValueFactory(new PropertyValueFactory<Potion, Integer>("cost"));
        buyAmountColumn.setCellValueFactory(new PropertyValueFactory<Potion, TextField>("textField"));
        tableView.setItems(Item.getObservablePotions());
        coinsLabel.setText("Coins: " + Main.player.getCoins());
    }

    @FXML
    protected void clickSubmit() throws IOException {
        Shop.purchaseItem(messageLabel, coinsLabel);
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
