package com.example.cs_project_gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import java.util.ArrayList;

public class Dungeon {
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<Enemy> deadEnemies = new ArrayList<Enemy>();

    public static String enemyType;
    public static String difficultyLevel;
    private final int difficulty;

    // constructor to create a dungeon
    public Dungeon(int difficulty){
        Main.YT = new SimpleStringProperty("");
        Main.ET = new SimpleStringProperty("");
        this.difficulty = difficulty;
        switch (enemyType) {
            case "Enemy" -> enemyDungeon();
            case "Vampire" -> vampireDungeon();
            case "Golem" -> golemDungeon();
        }
    }

    // creates the correct enemy dungeon based on difficulty chosen
    public void enemyDungeon(){
        for (int i = 0; i < 4+difficulty; i++){
            enemies.add(new Enemy("Enemy", 10*difficulty, difficulty, """
                    Enemies are people who have been corrupted by the pollution
            """));
        }
    }

    // creates the correct vampire dungeon based on difficulty chosen
    public void vampireDungeon(){
        for (int i = 0; i < 4+difficulty; i++){
            enemies.add(new Vampire("Vampire", 15*difficulty, 3*difficulty, """
                    Vampires are creatures that suck your blood
                    They steal your hp and heal themselves
                    (scaled according to how much hp you have)
                    """));
        }
    }

    // creates the correct golem dungeon based on difficulty chosen
    public void golemDungeon(){
        for (int i = 0; i < 4+difficulty; i++){
            enemies.add(new Golem("Golem", 20*difficulty, 2*difficulty, 5*difficulty, """
                    Golems are creatures made of rock and stone that have become sentient due to the pollution
                    They have strong defence, so attacks will deal less damage than usual
                    (scaled according to how much defence the golem has)
                    """));
        }
    }

    // get all the enemies inside the dungeon
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    // checks it all enemies in the dungeon are dead
    public boolean getAllEnemiesDead(){
        return (enemies.size() == 0);
    }

    // add a dead enemy to the deadEnemies list
    public void addDeadEnemy(Enemy enemy) {
        deadEnemies.add(enemy);
    }

    // removes all dead enemies from the dungeon
    public void updateEnemies(){
        enemies.removeAll(deadEnemies);
    }

    // runs when dungeon is successfully cleared
    // gets rid of the potions in effect (ends buff)
    public void dungeonCleared(TextArea YTInfo, TextArea ETInfo, ProgressBar healthBar, Label healthLabel, Label dungeonLabel, HBox fightHBox, HBox doneHBox){
        dungeonLabel.setText("Dungeon cleared!");
        for (Potion potion : Main.player.getPotionsInUse()){
            potion.endOfEffect();
        }
        Main.player.clearPotionsInUse();
        fightHBox.setVisible(false);
        doneHBox.setVisible(true);
    }
}
