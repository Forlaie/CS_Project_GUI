package com.example.cs_project_gui;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

// child of Enemy class (inheritance)
public class Dragon extends Enemy{
    private int defence;

    // inherits constructor and instance variables of Enemy class, plus its own instance variable of defence
    public Dragon(String name, int health, int attack, int defence, String description) {
        super(name, health, attack, description);
        this.defence = defence;
    }

    // override floor battle function from parent class (Enemy)
    // takes into account dragon defence when calculating player damage
    public void Fbattle(TextArea YTInfo, TextArea ETInfo, ProgressBar healthBar, Label healthLabel, Label floorLabel, VBox enemyVBox){
        int playerDamage = (int) (Main.player.getAttack() - Main.player.getAttack()*(defence/100.0));
        Main.YT.setValue(Main.YT.getValue() + "You have dealt " + playerDamage + " damage to " + name + "\n");
        //YTInfo.appendText("You have dealt " + playerDamage + " damage to " + name + "\n");
        health -= playerDamage;
        if (health <= 0){
            Fdied(YTInfo, ETInfo, healthBar, healthLabel, floorLabel);
            enemyVBox.getChildren().remove(infoVBox);
        }
        hLabel.setText(this.health + "/" + maxHealth);
        hBar.setProgress((double) this.health/maxHealth);
    }

    // when dragon dies, instead of dropping items, it permanently increases one of your stats by a random amount (1-10)
    public void Fdied(TextArea YTInfo, TextArea ETInfo, ProgressBar healthBar, Label healthLabel, Label floorLabel){
        Main.ET.setValue(Main.ET.getValue() + name + " has died\n");
        int stat = (int) (Math.random()*3);
        int value = (int) (Math.random()*10)+1;
        switch (stat){
            case 0 -> {
                Main.YT.setValue(Main.YT.getValue() + "\nHealth stat increased by " + value + "!\n");
                Main.player.setHealth(Main.player.getHealth()+value);
            }
            case 1 -> {
                Main.YT.setValue(Main.YT.getValue() + "Defence stat increased by " + value + "!\n");
                Main.player.setDefence(Main.player.getDefence()+value);
            }
            case 2 -> {
                Main.YT.setValue(Main.YT.getValue() + "Attack stat increased by " + value + "!\n");
                Main.player.setAttack(Main.player.getAttack()+value);
            }
        }
        Main.floor.addDeadEnemy(this);
    }
}
