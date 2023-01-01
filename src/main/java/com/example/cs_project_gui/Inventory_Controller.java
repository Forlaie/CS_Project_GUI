package com.example.cs_project_gui;

import javafx.beans.property.SimpleIntegerProperty;
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

public class Inventory_Controller implements Initializable {
    @FXML
    private Button backButton;
    @FXML
    private TableView<Item> equippedTable;
    @FXML
    private TableColumn<Item, String> EnameColumn;
    @FXML
    private TableColumn<Item, String> EstatsColumn;
    @FXML
    private TableView<Item> materialTable;
    @FXML
    private TableColumn<Item, String> MnameColumn;
    @FXML
    private TableColumn<Item, Integer> MamountColumn;
    @FXML
    private TableView<Potion> inventoryTable;
    @FXML
    private TableColumn<Potion, String> InameColumn;
    @FXML
    private TableColumn<Potion, Integer> IamountColumn;

    // initialize tableviews when loaded
    @Override
    public void initialize(URL location, ResourceBundle resources){
        EnameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        EstatsColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("stats"));
        equippedTable.setItems(Main.player.getObservableEquipped());

        MnameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        MamountColumn.setCellValueFactory(p -> {
            int intValue = Main.player.getMaterials().get(p.getValue());
            return new SimpleIntegerProperty(intValue).asObject();
        });
        materialTable.setItems(Main.player.getObservableMaterials());

        InameColumn.setCellValueFactory(new PropertyValueFactory<Potion, String>("name"));
        IamountColumn.setCellValueFactory(p -> {
            int intValue = Main.player.getInventory().get(p.getValue());
            return new SimpleIntegerProperty(intValue).asObject();
        });
        inventoryTable.setItems(Main.player.getObservableInventory());
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
