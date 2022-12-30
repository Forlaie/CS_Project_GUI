package com.example.cs_project_gui;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;

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
    public void battle(TextArea YTInfo, TextArea ETInfo, ProgressBar healthBar, Label healthLabel, Label floorLabel){
        int playerDamage = (int) (Main.player.getAttack() - Main.player.getAttack()*(defence/100.0));
        YTInfo.appendText("You have dealt " + playerDamage + " damage to " + name + "\n");
        health -= playerDamage;
        if (health <= 0){
            died(YTInfo, ETInfo, healthBar, healthLabel, floorLabel);
        }
    }

    // when dragon dies, instead of dropping items, it permanently increases one of your stats by a random amount (1-10)
    public void died(TextArea YTInfo, TextArea ETInfo, ProgressBar healthBar, Label healthLabel, Label floorLabel){
        ETInfo.appendText(name + " has died\n");
        int stat = (int) (Math.random()*3);
        int value = (int) (Math.random()*10)+1;
        switch (stat){
            case 0 -> {
                YTInfo.appendText("\nHealth stat increased by " + value + "!\n\n");
                Main.player.setHealth(Main.player.getHealth()+value);
            }
            case 1 -> {
                YTInfo.appendText("\nDefence stat increased by " + value + "!\n\n");
                Main.player.setDefence(Main.player.getDefence()+value);
            }
            case 2 -> {
                YTInfo.appendText("\nAttack stat increased by " + value + "!\n\n");
                Main.player.setAttack(Main.player.getAttack()+value);
            }
        }
        Main.floor.addDeadEnemy(this);
    }
}
