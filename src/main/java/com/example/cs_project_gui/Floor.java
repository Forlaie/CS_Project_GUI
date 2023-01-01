package com.example.cs_project_gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Floor {
    private final ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private final ArrayList<Enemy> deadEnemies = new ArrayList<Enemy>();
    public static int floorLevel;

    // constructor to create a new floor
    // also updates static variable floorLevel that keeps track of what floor player is on
    // generates random enemies, except for floor 10 which is the LORE ENEMY
    public Floor() throws FileNotFoundException {
        Main.YT = new SimpleStringProperty("");
        Main.ET = new SimpleStringProperty("");
        floorLevel += 1;
        if (floorLevel != 10){
            generateEnemies();
        }
        else{
            enemies.add(new Reaper("**********", 100, 10, "*** **** ****"));
        }
    }

    // overload constructor to set the floor using the save file
    public Floor(ArrayList<String> enemyNames) throws FileNotFoundException {
        Main.YT = new SimpleStringProperty("");
        Main.ET = new SimpleStringProperty("");
        setFloorEnemies(enemyNames);
    }

    // sets the floor enemies from the save file
    public void setFloorEnemies(ArrayList<String> enemyNames) throws FileNotFoundException {
        for (String name : enemyNames){
            switch(name) {
                case "Enemy" -> enemies.add(new Enemy("Enemy", 10*Floor.floorLevel, Floor.floorLevel, """
            Enemies are people who have been corrupted by \nthe pollution"""));
                case "Vampire" -> enemies.add(new Vampire("Vampire", 15*Floor.floorLevel, 3*Floor.floorLevel, """
                    Vampires are creatures that suck your blood
                    They steal your hp and heal themselves
                    (scaled from your hp)"""));
                case "Golem" -> enemies.add(new Golem("Golem", 20*Floor.floorLevel, 2*Floor.floorLevel, 5*Floor.floorLevel, """
                    Golems are creatures made of rock and stone that \nhave become sentient due to the pollution
                    They have strong defence, so attacks will deal less \ndamage than usual
                    (scaled from golem defence)"""));
                case "Troll" -> enemies.add(new Troll("Troll", 5*Floor.floorLevel, 2*Floor.floorLevel, """
                    Trolls are mischievous mythical creatures that love \nto play tricks
                    Trolls will steal an item from your inventory when \nthey die, so equip any items you want to keep safe \nand drink any potions you want to use asap"""));
                case "Dragon" -> enemies.add(new Dragon("Dragon", 50*Floor.floorLevel, 10*Floor.floorLevel, 10*Floor.floorLevel, """
                    Dragons are extremely powerful creatures
                    Dragons have lots of health, attack, and defence, so \nthey're difficult to defeat
                    However, once defeated, you can gain stat bonuses \nto your health, defence or attack"""));
                case "**********" -> enemies.add(new Reaper("**********", 100, 10, "*** **** ****"));
            }
        }
    }

    // generate random enemies using the static method created in Enemy class
    public void generateEnemies() {
        for (int i = 0; i < floorLevel; i++){
            enemies.add(Enemy.generateRandomEnemy());
        }
    }

    // get if all enemies in the floor are dead
    public boolean getAllEnemiesDead(){
        return (enemies.size() == 0);
    }

    // get the list of enemies in the floor
    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }

    // add a dead enemy to the deadEnemies list
    public void addDeadEnemy(Enemy enemy){
        deadEnemies.add(enemy);
    }

    // removes all dead enemies from the dungeon
    public void updateEnemies(){
        enemies.removeAll(deadEnemies);
    }

    // runs when floor is successfully cleared
    // gets rid of the potions in effect (ends buff)
    public void floorCleared(TextArea YTInfo, TextArea ETInfo, ProgressBar healthBar, Label healthLabel, Label floorLabel, HBox fightHBox, HBox doneHBox) {
        floorLabel.setText("Floor " + floorLevel + " cleared!");
        for (Potion potion : Main.player.getPotionsInUse()){
            potion.endOfEffect();
        }
        Main.player.clearPotionsInUse();
        fightHBox.setVisible(false);
        doneHBox.setVisible(true);
    }
}
