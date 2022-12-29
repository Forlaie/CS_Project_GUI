package com.example.cs_project_gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemInfo_Controller implements Initializable {
    @FXML
    private Button backButton;
    @FXML
    private TableView<Item> weaponTable;
    @FXML
    private TableColumn<Item, String> WnameColumn;
    @FXML
    private TableColumn<Item, String> WdescriptionColumn;
    @FXML
    private TableView<Item> materialsTable;
    @FXML
    private TableColumn<Item, String> MnameColumn;
    @FXML
    private TableColumn<Item, String> MdescriptionColumn;
    @FXML
    private TableView<Potion> potionsTable;
    @FXML
    private TableColumn<Potion, String> PnameColumn;
    @FXML
    private TableColumn<Potion, String> PdescriptionColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        WnameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        WdescriptionColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("description"));
        weaponTable.setItems(Item.getObservableWeaponDrops());

        MnameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        MdescriptionColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("description"));
        materialsTable.setItems(Item.getObservableMaterialDrops());

        PnameColumn.setCellValueFactory(new PropertyValueFactory<Potion, String>("name"));
        PdescriptionColumn.setCellValueFactory(new PropertyValueFactory<Potion, String>("description"));
        potionsTable.setItems(Item.getObservablePotions());
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
