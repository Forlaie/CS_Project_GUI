package com.example.cs_project_gui;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;

// child of Enemy class (inheritance)
public class Golem extends Enemy{
    private int defence;

    // inherits constructor and instance variables of Enemy class, plus its own instance variable of defence
    public Golem(String name, int health, int attack, int defence, String description) {
        super(name, health, attack, description);
        this.defence = defence;
    }

    // override floor battle function from parent class (Enemy)
    // takes into account golem defence when calculating player damage
    public void Fbattle(TextArea YTInfo, TextArea ETInfo, ProgressBar healthBar, Label healthLabel, Label floorLabel){
        int playerDamage = (int) (Main.player.getAttack() - Main.player.getAttack()*(defence/100.0));
        YTInfo.appendText("You have dealt " + playerDamage + " damage to " + name + "\n");
        health -= playerDamage;
        if (health <= 0){
            Fdied(YTInfo, ETInfo, healthBar, healthLabel, floorLabel);
        }
    }

    // override dungeon battle function from parent class (Enemy)
    // takes into account golem defence when calculating player damage
    public void Dbattle(TextArea YTInfo, TextArea ETInfo, ProgressBar healthBar, Label healthLabel, Label floorLabel){
        int playerDamage = (int) (Main.player.getAttack() - Main.player.getAttack()*(defence/100.0));
        System.out.println("You have dealt " + playerDamage + " damage to " + name);
        health -= playerDamage;
        if (health <= 0){
            Ddied(YTInfo, ETInfo, healthBar, healthLabel, floorLabel);
        }
    }

    // when golem dies in a dungeon, only drop golem materials
    public void Ddied(TextArea YTInfo, TextArea ETInfo, ProgressBar healthBar, Label healthLabel, Label floorLabel){
        ETInfo.appendText(name + " has died\n");
        Main.player.defeatedMonster(Item.materialDrops[2], YTInfo, ETInfo, healthBar, healthLabel, floorLabel);
        ETInfo.appendText(name + " dropped " + Item.materialDrops[2].getName() + "\n");
        Main.dungeon.addDeadEnemy(this);
    }
}
