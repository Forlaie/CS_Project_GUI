package com.example.cs_project_gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Player {
    //lombok
    private String username;
    private String password;
    private int health;
    private int maxHealth;
    private int defence;
    private int attack;
    private int level;
    private int xp;
    private int coins;
//    private boolean isDead;
    public Item[] equipped = {
            new Item("Sword", 0, 0, 10, 10, """
                    The sword is a sturdy and reliable weapon for any warrior
                    +10 atk"""),
            new Item("Shield", 5, 0, 0, 10, """
                    The shield is an essential for any warrior to keep themselves safe and protect what they need to protect
                    +5 hp, +10 def"""),
            new Item("Armour", 0, 20, 0, 30, """
                    Proper armour keeps your vitals safe
                    +10 hp, +20 def, +5 atk""")
    };
    LinkedHashMap<Item, Integer> materials = new LinkedHashMap<Item, Integer>();
    LinkedHashMap<Potion, Integer> inventory = new LinkedHashMap<Potion, Integer>();
    ArrayList<Potion> potionsInUse = new ArrayList<Potion>();

    // constructor for creating a new player
    public Player(String username, String password){
        this.username = username;
        this.password = password;
        health = 95;
        maxHealth = 100;
        health += equipped[1].getHealth();
        defence = 0;
        defence += equipped[2].getDefence();
        attack = 10;
        attack += equipped[0].getAttack();
        level = 1;
        xp = 0;
        coins = 0;
//        isDead = false;
    }

    // constructor for recreating player from save file
    public Player(String username, String password, int health, int maxHealth, int defence, int attack, int level, int xp, int coins, int[] materialQuantities, int[] potionQuantities, int[] swordInfo, int[] shieldInfo, int[] armourInfo){
        this.username = username;
        this.password = password;
        this.health = health;
        this.maxHealth = maxHealth;
        this.defence = defence;
        this.attack = attack;
        this.level = level;
        this.xp = xp;
        this.coins = coins;
        loadMaterialInfo(materialQuantities);
        loadPotionInfo(potionQuantities);
        loadWeaponInfo(swordInfo, shieldInfo, armourInfo);
//        isDead = false;
    }

    // updates equipped weapon stats from save file information
    private void loadWeaponInfo(int[] swordInfo, int[] shieldInfo, int[] armourInfo) {
        equipped[0].setHealth(swordInfo[0]);
        equipped[0].setDefence(swordInfo[1]);
        equipped[0].setAttack(swordInfo[2]);
        equipped[1].setHealth(shieldInfo[0]);
        equipped[1].setDefence(shieldInfo[1]);
        equipped[1].setAttack(shieldInfo[2]);
        equipped[2].setHealth(armourInfo[0]);
        equipped[2].setDefence(armourInfo[1]);
        equipped[2].setAttack(armourInfo[2]);
    }

    // updates inventory with potions that player had according to save file information
    private void loadPotionInfo(int[] potionQuantities) {
        if (potionQuantities[0] != 0){
            inventory.put(Item.potions[0], potionQuantities[0]);
        }
        if (potionQuantities[1] != 0){
            inventory.put(Item.potions[1], potionQuantities[1]);
        }
        if (potionQuantities[2] != 0){
            inventory.put(Item.potions[2], potionQuantities[2]);
        }
    }

    // updates inventory with materials that player had according to save file information
    private void loadMaterialInfo(int[] materialQuantities) {
        if (materialQuantities[0] != 0){
            materials.put(Item.materialDrops[0], materialQuantities[0]);
        }
        if (materialQuantities[1] != 0){
            materials.put(Item.materialDrops[1], materialQuantities[1]);
        }
        if (materialQuantities[2] != 0){
            materials.put(Item.materialDrops[2], materialQuantities[2]);
        }
        if (materialQuantities[3] != 0){
            materials.put(Item.weaponDrops[0], materialQuantities[3]);
        }
        if (materialQuantities[4] != 0){
            materials.put(Item.weaponDrops[1], materialQuantities[4]);
        }
        if (materialQuantities[5] != 0){
            materials.put(Item.weaponDrops[2], materialQuantities[5]);
        }
    }

    // get player username
    public String getUsername() {
        return username;
    }

    // change player username
    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    // get player health
    public int getHealth(){
        return health;
    }
    public int getMaxHealth(){
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth){
        this.maxHealth = maxHealth;
    }

    // set player health
    public void setHealth(int health){
        this.health = health;
    }

    // get player defence
    public int getDefence() {
        return defence;
    }

    // get player attack
    public int getAttack(){
        return attack;
    }

    // get player level
    public int getLevel() {
        return level;
    }

    // get player xp
    public int getXP() {
        return xp;
    }

    public int getCoins(){
        return coins;
    }

    public ObservableList<Item> getObservableEquipped(){
        ObservableList<Item> observableEquipped = FXCollections.observableArrayList();
        observableEquipped.addAll(equipped);
        return observableEquipped;
    }

    public ObservableList<Potion> getObservableInventory(){
        ObservableList<Potion> observableInventory = FXCollections.observableArrayList();
        observableInventory.addAll(inventory.keySet());
        return observableInventory;
    }

    public ObservableList<Item> getObservableMaterials(){
        ObservableList<Item> observableMaterials = FXCollections.observableArrayList();
        observableMaterials.addAll(materials.keySet());
        return observableMaterials;
    }

    public ObservableList<Item> getObservableSwordMaterials(){
        ObservableList<Item> observableSwordMaterials = FXCollections.observableArrayList();
        if (materials.get(Item.weaponDrops[0]) != null){
            observableSwordMaterials.add(Item.weaponDrops[0]);
        }
        if (materials.get(Item.materialDrops[1]) != null){
            observableSwordMaterials.add(Item.materialDrops[1]);
        }
        return observableSwordMaterials;
    }

    public ObservableList<Item> getObservableShieldMaterials(){
        ObservableList<Item> observableShieldMaterials = FXCollections.observableArrayList();
        if (materials.get(Item.weaponDrops[1]) != null){
            observableShieldMaterials.add(Item.weaponDrops[1]);
        }
        if (materials.get(Item.materialDrops[0]) != null){
            observableShieldMaterials.add(Item.materialDrops[0]);
        }
        return observableShieldMaterials;
    }

    public ObservableList<Item> getObservableArmourMaterials(){
        ObservableList<Item> observableArmourMaterials = FXCollections.observableArrayList();
        if (materials.get(Item.weaponDrops[2]) != null){
            observableArmourMaterials.add(Item.weaponDrops[2]);
        }
        if (materials.get(Item.materialDrops[2]) != null){
            observableArmourMaterials.add(Item.materialDrops[2]);
        }
        return observableArmourMaterials;
    }

    public void useCoins(int used){
        coins -= used;
    }

    // get whether player is dead or not
//    public boolean getIsDead(){
//        return isDead;
//    }

    // get player equipped items
    public Item[] getEquipped() {
        return equipped;
    }

    // get player materials inventory
    public LinkedHashMap<Item, Integer> getMaterials(){
        return materials;
    }

    // get player inventory
    public LinkedHashMap<Potion, Integer> getInventory(){
        return inventory;
    }

    // get the amount of enemy material player has
    public int getEnemyMaterials(){
        if (materials.get(Item.materialDrops[0]) != null){
            return materials.get(Item.materialDrops[0]);
        }
        else{
            return 0;
        }
    }

    // get the amount of vampire material player has
    public int getVampireMaterials(){
        if (materials.get(Item.materialDrops[1]) != null){
            return materials.get(Item.materialDrops[1]);
        }
        else{
            return 0;
        }
    }

    // get the amount of golem material player has
    public int getGolemMaterials(){
        if (materials.get(Item.materialDrops[2]) != null){
            return materials.get(Item.materialDrops[2]);
        }
        else{
            return 0;
        }
    }

    // get the amount of swords player has
    public int getSwords(){
        if (materials.get(Item.weaponDrops[0]) != null){
            return materials.get(Item.weaponDrops[0]);
        }
        else{
            return 0;
        }
    }

    // get the amount of shields player has
    public int getShields(){
        if (materials.get(Item.weaponDrops[1]) != null){
            return materials.get(Item.weaponDrops[1]);
        }
        else{
            return 0;
        }
    }

    // get the amount of armours player has
    public int getArmours(){
        if (materials.get(Item.weaponDrops[2]) != null){
            return materials.get(Item.weaponDrops[2]);
        }
        else{
            return 0;
        }
    }

    public void setDefence(int defence){
        this.defence = defence;
    }
    public void setAttack(int attack){
        this.attack = attack;
    }
    private void setLevel(int level) {
        this.level = level;
    }
    private void setXP(int xp) {
        this.xp = xp;
    }
    private void setCoins(int coins) {
        this.coins = coins;
    }
    private void clearInventory() {
        inventory.clear();
    }
    private void clearMaterials() {
        materials.clear();
    }

    // update coins after selling an item
    public void soldItem(int profit) {
        coins += profit;
    }

    // upgrade an item
    public void upgradeItem(int itemChoice, Label messageLabel) throws IOException {
        switch (itemChoice){
            case 1 -> equipped[0].upgradeItem(messageLabel);
            case 2 -> equipped[1].upgradeItem(messageLabel);
            case 3 -> equipped[2].upgradeItem(messageLabel);
        }
    }

    // update materials inventory accordingly after upgrading item
    public void useVampireMaterial(int used) {
        materials.replace(Item.materialDrops[1], materials.get(Item.materialDrops[1])-used);
        if (materials.get(Item.materialDrops[1]) == 0){
            materials.remove(Item.materialDrops[1]);
        }
    }
    public void useSword(int used) {
        materials.replace(Item.weaponDrops[0], materials.get(Item.weaponDrops[0])-used);
        if (materials.get(Item.weaponDrops[0]) == 0){
            materials.remove(Item.weaponDrops[0]);
        }
    }
    public void useEnemyMaterial(int used) {
        materials.replace(Item.materialDrops[0], materials.get(Item.materialDrops[0])-used);
        if (materials.get(Item.materialDrops[0]) == 0){
            materials.remove(Item.materialDrops[0]);
        }
    }
    public void useArmour(int used) {
        materials.replace(Item.weaponDrops[2], materials.get(Item.weaponDrops[2])-used);
        if (materials.get(Item.weaponDrops[2]) == 0){
            materials.remove(Item.weaponDrops[2]);
        }
    }
    public void useGolemMaterial(int used) {
        materials.replace(Item.materialDrops[2], materials.get(Item.materialDrops[2])-used);
        if (materials.get(Item.materialDrops[2]) == 0){
            materials.remove(Item.materialDrops[2]);
        }
    }
    public void useShield(int used) {
        materials.replace(Item.weaponDrops[1], materials.get(Item.weaponDrops[1])-used);
        if (materials.get(Item.weaponDrops[1]) == 0){
            materials.remove(Item.weaponDrops[1]);
        }
    }

    // updates player's stats after leveling up items
    public void setEquipped(int index, int health, int defence, int attack){
        equipped[index].setHealth(equipped[index].getHealth()+health);
        equipped[index].setDefence(equipped[index].getDefence()+defence);
        equipped[index].setAttack(equipped[index].getAttack()+attack);
        Main.player.setHealth(Main.player.getHealth()+health);
        Main.player.setDefence(Main.player.getDefence()+defence);
        Main.player.setAttack(Main.player.getAttack()+attack);
    }

    // drink a health potion
    // increases health by the potion amount
    public void drinkHealthPotion(Label currentHealthLabel, Label numOfHealthPotionsLabel, TextField useTextField, Label messageLabel){
        try {
            int use = Integer.parseInt(useTextField.getText());
            int own;
            if (inventory.get(Item.potions[0]) == null){
                own = 0;
            }
            else{
                own = inventory.get(Item.potions[0]);
            }
            if (use > own){
                messageLabel.setText("You don't have that many health potions. Please try again");
            }
            else if (use == 0){
                messageLabel.setText("Please use at least 1 health potion");
            }
            else{
                if (use == 1){
                    messageLabel.setText("Successfully used " + use + " health potion!");
                }
                else{
                    messageLabel.setText("Successfully used " + use + " health potions!");
                }
                health += Item.potions[0].getHealth()*use;
                inventory.replace(Item.potions[0], own-use);
                if (inventory.get(Item.potions[0]) == 0){
                    inventory.remove(Item.potions[0]);
                }
                Item.potions[0].usePotion(use);
                currentHealthLabel.setText("Current health: " + health + "/" + maxHealth);
                if (inventory.get(Item.potions[0]) == null){
                    numOfHealthPotionsLabel.setText("Health potions: 0");
                }
                else{
                    numOfHealthPotionsLabel.setText("Health potions: " + inventory.get(Item.potions[0]));
                }
            }
        } catch (NumberFormatException e) {
            messageLabel.setText("Please input an integer");
        }
    }

    // use a potion during floor/dungeon
    public void usePotion(Label messageLabel) throws IOException {
        boolean valid = true;
        boolean useSomething = false;
        for (Potion potion : getObservableInventory()){
            if (potion.getCheckBox().isSelected()){
                try {
                    int use = Integer.parseInt(potion.getTextField().getText());
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
            for (Potion potion : getObservableInventory()){
                if (potion.getCheckBox().isSelected()){
                    try {
                        int use = Integer.parseInt(potion.getTextField().getText());
                        if (use > inventory.get(potion)){
                            messageLabel.setText("Sorry, you have tried to use more potions that you own");
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
                for (Potion potion : getObservableInventory()){
                    if (potion.getCheckBox().isSelected()){
                        int use = Integer.parseInt(potion.getTextField().getText());
                        inventory.replace(potion, inventory.get(potion)-use);
                        if (inventory.get(potion) == 0){
                            inventory.remove(potion);
                        }
                        potion.usePotion(use);
                        potionsInUse.add(potion);
                    }
                }
                messageLabel.setText("Successfully used potions!");
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("drinkPotion.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                Stage stage = (Stage) messageLabel.getScene().getWindow();
                stage.setTitle("Wen Ymar Elad");
                stage.setScene(scene);
                stage.show();
            }
        }
        else{
            messageLabel.setText("Please use at least one potion");
        }
    }

    // get all the potions that are in use (all buffs that are currently being applied)
    public ArrayList<Potion> getPotionsInUse(){
        return potionsInUse;
    }

    // clear all buffs from potions (after completing the floor/dungeon)
    public void clearPotionsInUse(){
        potionsInUse.clear();
    }

    // remove an item from inventory (troll uses it to steal a potion from player)
    public void removeItem(Potion potion){
        inventory.replace(potion, inventory.get(potion)-1);
        if (inventory.get(potion) == 0){
            inventory.remove(potion);
        }
    }

    // called when xp reaches the required amount to level uo
    // increases stats
    public void levelUp(TextArea YTInfo, TextArea ETInfo, ProgressBar healthBar, Label healthLabel, Label floorLabel){
        Main.YT.setValue(Main.YT.getValue() + "Level up!\n");
        xp = xp - level*10;
        level += 1;
        maxHealth += 10;
        health = maxHealth;
        attack += 5;
        defence += 5;
        coins += 20;
    }

    // defeat a monster that drops an item
    public void defeatedMonster(Item item, TextArea YTInfo, TextArea ETInfo, ProgressBar healthBar, Label healthLabel, Label floorLabel){
        Main.YT.setValue(Main.YT.getValue() + "Gained " + (10+Floor.floorLevel) + " xp!\n");
        xp += 10+Floor.floorLevel;
        if (xp >= level*10){
            levelUp(YTInfo, ETInfo, healthBar, healthLabel, floorLabel);
        }
        materials.merge(item, 1, Integer::sum);
        coins += 10+Floor.floorLevel;
    }

    // defeat a monster that doesn't drop an item
    public void defeatedMonster(TextArea YTInfo, TextArea ETInfo, ProgressBar healthBar, Label healthLabel, Label floorLabel){
        Main.YT.setValue(Main.YT.getValue() + "Gained " + (10+Floor.floorLevel) + " xp!\n");
        xp += 10+Floor.floorLevel;
        if (xp >= level*10){
            levelUp(YTInfo, ETInfo, healthBar, healthLabel, floorLabel);
        }
        coins += 10+Floor.floorLevel;
    }

    // floor battle function
    public void Fbattle(TextArea YTInfo, TextArea ETInfo, ProgressBar healthBar, Label healthLabel, Label floorLabel, HBox fightHBox, HBox doneHBox, VBox enemyVBox) throws IOException {
        // print out what enemies did on their turn
        ArrayList<Enemy> enemies = Main.floor.getEnemies();
        for (Enemy enemy : enemies){
            // takes player defence into account when calculating damage taken
            int damage = enemy.getAttack()*(100/defence);
            health -= damage;
            Main.ET.setValue(Main.ET.getValue() + enemy.getName() + " has dealt " + damage + " damage\n");
            // check if player has died
            if (health <= 0){
                healthBar.setProgress(0);
                healthLabel.setText("0/" + maxHealth);
                //Thread.sleep(1000);
                //died();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("died.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                Stage stage = (Stage) healthLabel.getScene().getWindow();
                stage.setTitle("Wen Ymar Elad");
                stage.setScene(scene);
                stage.show();
            }
            else{
                healthBar.setProgress((double) health/maxHealth);
                healthLabel.setText(health + "/" + maxHealth);
                //Thread.sleep(1000);
            }
        }
        // battle each enemy
        for (Enemy enemy : enemies){
            enemy.Fbattle(YTInfo, ETInfo, healthBar, healthLabel, floorLabel, enemyVBox);
        }
        healthBar.setProgress((double) health/maxHealth);
        healthLabel.setText(health + "/" + maxHealth);
        // update the enemies on the floor (remove dead enemies)
        Main.floor.updateEnemies();
        if (Main.floor.getAllEnemiesDead()){
            Main.floor.floorCleared(YTInfo, ETInfo, healthBar, healthLabel, floorLabel, fightHBox, doneHBox);
        }
    }

    // dungeon battle function
    public void Dbattle(TextArea YTInfo, TextArea ETInfo, ProgressBar healthBar, Label healthLabel, Label dungeonLabel, HBox fightHBox, HBox doneHBox, VBox enemyVBox) throws IOException {
        // print out what enemies did on their turn
        ArrayList<Enemy> enemies = Main.dungeon.getEnemies();
        for (Enemy enemy : enemies){
            // takes player defence into account when calculating damage taken
            int damage = enemy.getAttack()*(100/defence);
            health -= damage;
            Main.ET.setValue(Main.ET.getValue() + enemy.getName() + " has dealt " + damage + " damage\n");
            if (health <= 0){
                healthBar.setProgress(0);
                healthLabel.setText("0/" + maxHealth);
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("died.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                Stage stage = (Stage) healthLabel.getScene().getWindow();
                stage.setTitle("Wen Ymar Elad");
                stage.setScene(scene);
                stage.show();
            }
            else{
                healthBar.setProgress((double) health/maxHealth);
                healthLabel.setText(health + "/" + maxHealth);
            }
        }

        // battle each enemy
        for (Enemy enemy : enemies){
            enemy.Dbattle(YTInfo, ETInfo, healthBar, healthLabel, dungeonLabel, enemyVBox);
        }
        // update the enemies on the dungeon (remove dead enemies)
        healthBar.setProgress((double) health/maxHealth);
        healthLabel.setText(health + "/" + maxHealth);
        Main.dungeon.updateEnemies();
        if (Main.dungeon.getAllEnemiesDead()){
            Main.dungeon.dungeonCleared(YTInfo, ETInfo, healthBar, healthLabel, dungeonLabel, fightHBox, doneHBox);
        }
        }


    // function for when player dies
    public void died(String choice) throws FileNotFoundException {
        // restart from previous save
        if (choice.equals("S")) {
            try {
                Scanner fileInput = new Scanner(new File("loginInfo.txt"));
                String username = fileInput.nextLine();
                String password = fileInput.nextLine();
                fileInput = new Scanner(new File("playerInfo.txt"));
                // reset player information to previous save (e.g. stats, inventory, etc)
                int health = Integer.parseInt(fileInput.nextLine());
                int maxHealth = Integer.parseInt(fileInput.nextLine());
                int defence = Integer.parseInt(fileInput.nextLine());
                int attack = Integer.parseInt(fileInput.nextLine());
                int level = Integer.parseInt(fileInput.nextLine());
                int xp = Integer.parseInt(fileInput.nextLine());
                int coins = Integer.parseInt(fileInput.nextLine());
                int[] materialQuantities = new int[6];
                for (int i = 0; i < 6; i++){
                    int quantity = Integer.parseInt(fileInput.nextLine());
                    materialQuantities[i] = quantity;
                }
                int[] potionQuantities = new int[3];
                for (int i = 0; i < 3; i++){
                    int quantity = Integer.parseInt(fileInput.nextLine());
                    potionQuantities[i] = quantity;
                }
                int[] swordInfo = new int[3];
                for (int i = 0; i < 3; i++){
                    int stat = Integer.parseInt(fileInput.nextLine());
                    swordInfo[i] = stat;
                }
                int[] shieldInfo = new int[3];
                for (int i = 0; i < 3; i++){
                    int stat = Integer.parseInt(fileInput.nextLine());
                    shieldInfo[i] = stat;
                }
                int[] armourInfo = new int[3];
                for (int i = 0; i < 3; i++){
                    int stat = Integer.parseInt(fileInput.nextLine());
                    armourInfo[i] = stat;
                }
                Main.player.setUsername(username);
                Main.player.setPassword(password);
                Main.player.setHealth(health);
                Main.player.setMaxHealth(maxHealth);
                Main.player.setDefence(defence);
                Main.player.setAttack(attack);
                Main.player.setLevel(level);
                Main.player.setXP(xp);
                Main.player.setCoins(coins);
                Main.player.loadMaterialInfo(materialQuantities);
                Main.player.loadPotionInfo(potionQuantities);
                Main.player.loadWeaponInfo(swordInfo, shieldInfo, armourInfo);
                try {
                    // reset floor to previous floor (floor level, floor enemies)
                    fileInput = new Scanner(new File("floorInfo.txt"));
                    if (fileInput.hasNextLine()) {
                        Floor.floorLevel = Integer.parseInt(fileInput.nextLine());
                        ArrayList<String> enemyNames = new ArrayList<String>();
                        while (fileInput.hasNextLine()){
                            enemyNames.add(fileInput.nextLine());
                        }
                        Main.floor.setFloorEnemies(enemyNames);
                        fileInput.close();
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Couldn't open floor file");
                }

            } catch (FileNotFoundException e) {
                System.out.println("Couldn't open player file");
            }
        }
        // reset from very beginning
        else{
            // reset player to base stats, empty inventory, start from the first floor again
            // also SHOW LORE AGAIN
            Floor.floorLevel = 0;
            Main.floor = new Floor();
            Main.player.setHealth(100);
            Main.player.setDefence(20);
            Main.player.setAttack(20);
            Main.player.setLevel(1);
            Main.player.setXP(0);
            Main.player.setCoins(0);
            int[] materialQuantities = {0, 0, 0, 0, 0, 0};
            Main.player.loadMaterialInfo(materialQuantities);
            int[] potionQuantities = {0, 0, 0};
            Main.player.loadPotionInfo(potionQuantities);
            int[] swordInfo = {0, 0, 10};
            int[] shieldInfo = {5, 0, 0};
            int[] armourInfo = {0, 20, 0};
            Main.player.loadWeaponInfo(swordInfo, shieldInfo, armourInfo);
            Main.player.clearMaterials();
            Main.player.clearInventory();
            Main.putInfoIntoPlayerInfoFile();
            Main.putInfoIntoFloorFile();
        }
    }
}