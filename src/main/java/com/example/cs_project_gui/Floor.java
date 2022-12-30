package com.example.cs_project_gui;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Floor {
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<Enemy> deadEnemies = new ArrayList<Enemy>();
    public static int floorLevel;

    // constructor to create a new floor
    // also updates static variable floorLevel that keeps track of what floor player is on
    // generates random enemies, except for floor 10 which is the LORE ENEMY
    public Floor(){
        floorLevel += 1;
        if (floorLevel != 10){
            generateEnemies();
        }
        else{
            enemies.add(new Reaper("**********", 100, 10, "*** **** ****"));
        }
    }

    // overload constructor to set the floor using the save file
    public Floor(ArrayList<String> enemyNames){
        setFloorEnemies(enemyNames);
    }

    // sets the floor enemies from the save file
    public void setFloorEnemies(ArrayList<String> enemyNames){
        for (String name : enemyNames){
            switch(name) {
                case "Enemy" -> enemies.add(new Enemy("Enemy", 10+Floor.floorLevel, 1+Floor.floorLevel, """
            Enemies are people who have been corrupted by the pollution"""));
                case "Vampire" -> enemies.add(new Vampire("Vampire", 15+Floor.floorLevel, 3+Floor.floorLevel, """
                    Vampires are creatures that suck your blood
                    They steal your hp and heal themselves
                    (scaled according to how much hp you have)"""));
                case "Golem" -> enemies.add(new Golem("Golem", 20+Floor.floorLevel, 2+Floor.floorLevel, 5+Floor.floorLevel, """
                    Golems are creatures made of rock and stone that have become sentient due to the pollution
                    They have strong defence, so attacks will deal less damage than usual
                    (scaled according to how much defence the golem has)"""));
                case "Troll" -> enemies.add(new Troll("Troll", 5+Floor.floorLevel, 2+Floor.floorLevel, """
                    Trolls are mischievous mythical creatures that love to play tricks
                    Trolls will steal an item from your inventory when they die, so equip any items you want to keep safe"""));
                case "Dragon" -> enemies.add(new Dragon("Dragon", 50+Floor.floorLevel, 10+Floor.floorLevel, 10+Floor.floorLevel, """
                    Dragons are extremely powerful creatures
                    Dragons have lots of health, attack, and defence, so they're difficult to defeat
                    However, once defeated, they drop special dragon armour that can't be found anywhere else
                    These items have higher stats than all other items"""));
                case "**********" -> enemies.add(new Reaper("**********", 100, 10, "*** **** ****"));
            }
        }
    }

    // generate random enemies using the static method created in Enemy class
    public void generateEnemies(){
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
    public void floorCleared(TextArea YTInfo, TextArea ETInfo, ProgressBar healthBar, Label healthLabel, Label floorLabel, HBox fightHBox, HBox doneHBox) throws FileNotFoundException {
        floorLabel.setText("Floor " + floorLevel + " cleared!");
        for (Potion potion : Main.player.getPotionsInUse()){
            potion.endOfEffect();
        }
        Main.player.clearPotionsInUse();
        fightHBox.setVisible(false);
        doneHBox.setVisible(true);
    }
}
