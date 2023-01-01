package com.example.cs_project_gui;

import javafx.beans.property.SimpleIntegerProperty;
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

public class Smithery_Controller implements Initializable {
    @FXML
    private Button backButton;
    @FXML
    private TableView<Item> swordTable;
    @FXML
    private TableColumn<Item, CheckBox> SselectColumn;
    @FXML
    private TableColumn<Item, String> SnameColumn;
    @FXML
    private TableColumn<Item, Integer> ScostColumn;
    @FXML
    private TableColumn<Item, Integer> SamountColumn;
    @FXML
    private TableColumn<Item, TextField> SuseColumn;
    @FXML
    private Button SlevelupButton;
    @FXML
    private Label SmessageLabel;
    @FXML
    private Label ScoinsLabel;
    @FXML
    private TableView<Item> shieldTable;
    @FXML
    private TableColumn<Item, CheckBox> HselectColumn;
    @FXML
    private TableColumn<Item, String> HnameColumn;
    @FXML
    private TableColumn<Item, Integer> HcostColumn;
    @FXML
    private TableColumn<Item, Integer> HamountColumn;
    @FXML
    private TableColumn<Item, TextField> HuseColumn;
    @FXML
    private Button HlevelupButton;
    @FXML
    private Label HmessageLabel;
    @FXML
    private Label HcoinsLabel;
    @FXML
    private TableView<Item> armourTable;
    @FXML
    private TableColumn<Item, CheckBox> AselectColumn;
    @FXML
    private TableColumn<Item, String> AnameColumn;
    @FXML
    private TableColumn<Item, Integer> AcostColumn;
    @FXML
    private TableColumn<Item, Integer> AamountColumn;
    @FXML
    private TableColumn<Item, TextField> AuseColumn;
    @FXML
    private Button AlevelupButton;
    @FXML
    private Label AmessageLabel;
    @FXML
    private Label AcoinsLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        SselectColumn.setCellValueFactory(new PropertyValueFactory<Item, CheckBox>("checkBox"));
        SnameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        ScostColumn.setCellValueFactory(new PropertyValueFactory<Item, Integer>("cost"));
        SamountColumn.setCellValueFactory(p -> {
            p.getValue().getCheckBox().setSelected(false);
            p.getValue().getTextField().clear();
            p.getValue().getTextField().setDisable(true);
            int intValue = Main.player.getMaterials().get(p.getValue());
            return new SimpleIntegerProperty(intValue).asObject();
        });
        SuseColumn.setCellValueFactory(new PropertyValueFactory<Item, TextField>("textField"));
        swordTable.setItems(Main.player.getObservableSwordMaterials());
        ScoinsLabel.setText("Coins: " + Main.player.getCoins());

        HselectColumn.setCellValueFactory(new PropertyValueFactory<Item, CheckBox>("checkBox"));
        HnameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        HcostColumn.setCellValueFactory(new PropertyValueFactory<Item, Integer>("cost"));
        HamountColumn.setCellValueFactory(p -> {
            p.getValue().getCheckBox().setSelected(false);
            p.getValue().getTextField().clear();
            p.getValue().getTextField().setDisable(true);
            int intValue = Main.player.getMaterials().get(p.getValue());
            return new SimpleIntegerProperty(intValue).asObject();
        });
        HuseColumn.setCellValueFactory(new PropertyValueFactory<Item, TextField>("textField"));
        shieldTable.setItems(Main.player.getObservableShieldMaterials());
        HcoinsLabel.setText("Coins: " + Main.player.getCoins());

        AselectColumn.setCellValueFactory(new PropertyValueFactory<Item, CheckBox>("checkBox"));
        AnameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        AcostColumn.setCellValueFactory(new PropertyValueFactory<Item, Integer>("cost"));
        AamountColumn.setCellValueFactory(p -> {
            p.getValue().getCheckBox().setSelected(false);
            p.getValue().getTextField().clear();
            p.getValue().getTextField().setDisable(true);
            int intValue = Main.player.getMaterials().get(p.getValue());
            return new SimpleIntegerProperty(intValue).asObject();
        });
        AuseColumn.setCellValueFactory(new PropertyValueFactory<Item, TextField>("textField"));
        armourTable.setItems(Main.player.getObservableArmourMaterials());
        AcoinsLabel.setText("Coins: " + Main.player.getCoins());
    }

    @FXML
    protected void clickSLevelup() throws IOException {
        Main.player.upgradeItem(1, SmessageLabel);
    }

    @FXML
    protected void clickHLevelup() throws IOException {
        Main.player.upgradeItem(2, HmessageLabel);
    }

    @FXML
    protected void clickALevelup() throws IOException {
        Main.player.upgradeItem(3, AmessageLabel);
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
