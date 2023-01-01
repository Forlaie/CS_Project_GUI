package com.example.cs_project_gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class Item {
    protected String name;
    protected int health;
    protected int defence;
    protected int attack;
    protected int cost;
    protected String description;
    protected CheckBox checkBox;
    protected TextField textField;

    public static final Item[] weaponDrops = {
            new Item("Sword", 0, 0, 10, 10, """
                    The sword is a sturdy and reliable weapon for any \nwarrior
                    +10 atk"""),
            new Item("Shield", 5, 0, 0, 10, """
                    The shield is an essential for any warrior to protect \nthemselves and others
                    +5 hp"""),
            new Item("Armour", 0, 20, 0, 30, """
                    Proper armour keeps your vitals safe
                    +20 def""")
    };

    // stores all the material drops and their info (used to generate random drops after defeating enemy)
    public static final Item[] materialDrops = {
            new Item("Enemy material", 1, """
                    Enemies drop this
                    Used to upgrade shield stats"""),
            new Item("Vampire material", 1, """
                    Vampires drop this
                    Used to upgrade sword stats"""),
            new Item("Golem material", 1, """
                    Golems drop this
                    Used to upgrade armour stats""")
    };

    // stores all the potions and their info (useful for hashmaps later)
    public static final Potion[] potions = {
            new Potion("Health potion", 50, 0, 0, 50, """
                    Health potion heals you by 50hp points
                    Costs 50 coins"""),
            new Potion("Attack potion", 0, 0, 30, 50, """
                    Attack potion buffs your attack by 30 points
                    Lasts for one floor/dungeon
                    Costs 50 coins"""),
            new Potion("Defence potion", 0, 30, 0, 50, """
                    Defence potion buffs your defence by 30 points
                    Lasts for one floor/dungeon
                    Costs 50 coins""")
    };

    public Item(String name, int health, int defence, int attack, int cost, String description){
        this.name = name;
        this.health = health;
        this.defence = defence;
        this.attack = attack;
        this.cost = cost;
        this.description = description;
        textField = new TextField();
        textField.setDisable(true);
        checkBox = new CheckBox();
        checkBox.setOnAction(e -> {
            textField.setDisable(!checkBox.isSelected());
            textField.clear();
        });
    }

    public Item(String name, int cost, String description){
        this.name = name;
        this.cost = cost;
        this.description = description;
        textField = new TextField();
        textField.setDisable(true);
        checkBox = new CheckBox();
        checkBox.setOnAction(e -> {
            textField.setDisable(!checkBox.isSelected());
            textField.clear();
        });
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getHealth() {
        return health;
    }

    public int getDefence() {
        return defence;
    }

    public int getAttack() {
        return attack;
    }

    public static ObservableList<Item> getObservableWeaponDrops(){
        ObservableList<Item> observableWeaponDrops = FXCollections.observableArrayList();
        observableWeaponDrops.addAll(weaponDrops);
        return observableWeaponDrops;
    }

    public static ObservableList<Item> getObservableMaterialDrops(){
        ObservableList<Item> observableMaterialDrops = FXCollections.observableArrayList();
        observableMaterialDrops.addAll(materialDrops);
        return observableMaterialDrops;
    }

    public static ObservableList<Potion> getObservablePotions(){
        ObservableList<Potion> observablePotions = FXCollections.observableArrayList();
        observablePotions.addAll(potions);
        return observablePotions;
    }

    // get item cost
    public int getCost(){
        return cost;
    }

    // set item stat points (from save file)
    public void setHealth(int health) {
        this.health = health;
    }
    public void setDefence(int defence){
        this.defence = defence;
    }
    public void setAttack(int attack){
        this.attack = attack;
    }
    public TextField getTextField(){
        return textField;
    }
    public CheckBox getCheckBox(){
        return checkBox;
    }

    public static Item generateRandomDrop(){
        int index;
        int getRandomDrop = (int) (Math.random()*101)+1;
        index = (int) (Math.random() * 3);
        if (getRandomDrop >= 90){
            return weaponDrops[index];
        }
        else{
            return materialDrops[index];
        }
    }

    // upgrade item
    public void upgradeItem(Label messageLabel) throws IOException {
        // find what item player wants to upgrade and what they want to use for it
        // update inventory, coins, or display error messages accordingly
        switch (this.name){
            case "Sword" -> {
                boolean valid = true;
                int cost = 0;
                boolean useSomething = false;
                for (Item item : Main.player.getObservableSwordMaterials()){
                    if (item.getCheckBox().isSelected()){
                        try {
                            int use = Integer.parseInt(item.getTextField().getText());
                            if (use > 0){
                                useSomething = true;
                                break;
                            }
                        } catch (NumberFormatException e) {
                            //throw new RuntimeException(e);
                        }
                    }
                }
                if (useSomething){
                    for (Item item : Main.player.getObservableSwordMaterials()){
                        if (item.getCheckBox().isSelected()){
                            try {
                                int use = Integer.parseInt(item.getTextField().getText());
                                cost += use * item.getCost();
                                if (use > Main.player.getMaterials().get(item)){
                                    messageLabel.setText("Sorry, you have tried to use more than you own");
                                    valid = false;
                                    break;
                                }
                                else if (Main.player.getCoins() < cost){
                                    messageLabel.setText("Sorry, you don't have enough coins.");
                                    valid = false;
                                    break;
                                }
                            } catch (NumberFormatException e) {
                                messageLabel.setText("Please input integers only");
                                valid = false;
                                break;
                            }
                        }
                    }
                    if (valid){
                        for (Item item : Main.player.getObservableSwordMaterials()){
                            if (item.getCheckBox().isSelected()){
                                int use = Integer.parseInt(item.getTextField().getText());
                                cost = use * item.getCost();
                                switch (item.getName()){
                                    case "Sword" -> {
                                        Main.player.useSword(use);
                                        Main.player.useCoins(cost);
                                        Main.player.setEquipped(0, use, use, 10*use);
                                    }
                                    case "Vampire material" -> {
                                        Main.player.useVampireMaterial(use);
                                        Main.player.useCoins(cost);
                                        Main.player.setEquipped(0, 0, 0, use);
                                    }
                                }
                            }
                        }
                        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("smithery.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                        Stage stage = (Stage) messageLabel.getScene().getWindow();
                        stage.setTitle("Wen Ymar Elad");
                        stage.setScene(scene);
                        stage.show();
//                        messageLabel.setText("Successfully upgraded sword!");
                    }
                }
                else{
                    messageLabel.setText("Please use at least one material to level up sword");
                }
            }
            case "Shield" -> {
                boolean valid = true;
                int cost = 0;
                boolean useSomething = false;
                for (Item item : Main.player.getObservableShieldMaterials()){
                    if (item.getCheckBox().isSelected()){
                        try {
                            int use = Integer.parseInt(item.getTextField().getText());
                            if (use > 0){
                                useSomething = true;
                                break;
                            }
                        } catch (NumberFormatException e) {
                            //throw new RuntimeException(e);
                        }
                    }
                }
                if (useSomething){
                    for (Item item : Main.player.getObservableShieldMaterials()){
                        if (item.getCheckBox().isSelected()){
                            try {
                                int use = Integer.parseInt(item.getTextField().getText());
                                cost += use * item.getCost();
                                if (use > Main.player.getMaterials().get(item)){
                                    messageLabel.setText("Sorry, you have tried to use more than you own");
                                    valid = false;
                                    break;
                                }
                                else if (Main.player.getCoins() < cost){
                                    messageLabel.setText("Sorry, you don't have enough coins.");
                                    valid = false;
                                    break;
                                }
                            } catch (NumberFormatException e) {
                                messageLabel.setText("Please input integers only");
                                valid = false;
                                break;
                            }
                        }
                    }
                    if (valid){
                        for (Item item : Main.player.getObservableShieldMaterials()){
                            if (item.getCheckBox().isSelected()){
                                int use = Integer.parseInt(item.getTextField().getText());
                                cost = use * item.getCost();
                                switch (item.getName()){
                                    case "Shield" -> {
                                        Main.player.useShield(use);
                                        Main.player.useCoins(cost);
                                        Main.player.setEquipped(1, 5*use, use, use);
                                    }
                                    case "Enemy material" -> {
                                        Main.player.useEnemyMaterial(use);
                                        Main.player.useCoins(cost);
                                        Main.player.setEquipped(1, use, 0, 0);
                                    }
                                }
                            }
                        }
                        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("smithery.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                        Stage stage = (Stage) messageLabel.getScene().getWindow();
                        stage.setTitle("Wen Ymar Elad");
                        stage.setScene(scene);
                        stage.show();
//                        messageLabel.setText("Successfully upgraded shield!");
                    }
                }
                else{
                    messageLabel.setText("Please use at least one material to level up shield");
                }
            }
            case "Armour" -> {
                boolean valid = true;
                int cost = 0;
                boolean useSomething = false;
                for (Item item : Main.player.getObservableArmourMaterials()){
                    if (item.getCheckBox().isSelected()){
                        try {
                            int use = Integer.parseInt(item.getTextField().getText());
                            if (use > 0){
                                useSomething = true;
                                break;
                            }
                        } catch (NumberFormatException e) {
                            //throw new RuntimeException(e);
                        }
                    }
                }
                if (useSomething){
                    for (Item item : Main.player.getObservableArmourMaterials()){
                        if (item.getCheckBox().isSelected()){
                            try {
                                int use = Integer.parseInt(item.getTextField().getText());
                                cost += use * item.getCost();
                                if (use > Main.player.getMaterials().get(item)){
                                    messageLabel.setText("Sorry, you have tried to use more than you own");
                                    valid = false;
                                    break;
                                }
                                else if (Main.player.getCoins() < cost){
                                    messageLabel.setText("Sorry, you don't have enough coins.");
                                    valid = false;
                                    break;
                                }
                            } catch (NumberFormatException e) {
                                messageLabel.setText("Please input integers only");
                                valid = false;
                                break;
                            }
                        }
                    }
                    if (valid){
                        for (Item item : Main.player.getObservableArmourMaterials()){
                            if (item.getCheckBox().isSelected()){
                                int use = Integer.parseInt(item.getTextField().getText());
                                cost = use * item.getCost();
                                switch (item.getName()){
                                    case "Armour" -> {
                                        Main.player.useArmour(use);
                                        Main.player.useCoins(cost);
                                        Main.player.setEquipped(2, use, 20*use, use);
                                    }
                                    case "Golem material" -> {
                                        Main.player.useGolemMaterial(use);
                                        Main.player.useCoins(cost);
                                        Main.player.setEquipped(2, 0, use, 0);
                                    }
                                }
                            }
                        }
                        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("smithery.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                        Stage stage = (Stage) messageLabel.getScene().getWindow();
                        stage.setTitle("Wen Ymar Elad");
                        stage.setScene(scene);
                        stage.show();
//                        messageLabel.setText("Successfully upgraded armour!");
                    }
                }
                else{
                    messageLabel.setText("Please use at least one item to level up armour");
                }
            }
        }
    }
}
