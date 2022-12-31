package com.example.cs_project_gui;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;

// child of Enemy class (inheritance)
public class Vampire extends Enemy{
    // inherits constructor and instance variables of Enemy class
    public Vampire(String name, int health, int attack, String description) {
        super(name, health, attack, description);
    }

    // override floor battle function from parent class (Enemy)
    // takes into account vampire sucking blood (stealing player health)
    public void Fbattle(TextArea YTInfo, TextArea ETInfo, ProgressBar healthBar, Label healthLabel, Label floorLabel){
        health -= Main.player.getAttack();
        YTInfo.appendText("You have dealt " + Main.player.getAttack() + " damage to " + name + "\n");
        int suckedBlood = (int) (Main.player.getHealth()*0.05);
        health += suckedBlood;
        ETInfo.appendText("Vampire sucked " + suckedBlood + " hp " + "from you\n");
        Main.player.setHealth(Main.player.getHealth() - suckedBlood);
        if (health <= 0){
            Fdied(YTInfo, ETInfo, healthBar, healthLabel, floorLabel);
        }
    }

    // override dungeon battle function from parent class (Enemy)
    // takes into account vampire sucking blood (stealing player health)
    public void Dbattle(TextArea YTInfo, TextArea ETInfo, ProgressBar healthBar, Label healthLabel, Label floorLabel){
        health -= Main.player.getAttack();
        YTInfo.appendText("You have dealt " + Main.player.getAttack() + " damage to " + name + "\n");
        int suckedBlood = (int) (Main.player.getHealth()*0.05);
        health += suckedBlood;
        System.out.println("Vampire sucked " + suckedBlood + " hp from you\n");
        Main.player.setHealth(Main.player.getHealth() - suckedBlood);
        if (health <= 0){
            Ddied(YTInfo, ETInfo, healthBar, healthLabel, floorLabel);
        }
    }

    // when vampire dies in a dungeon, only drop vampire materials
    public void Ddied(TextArea YTInfo, TextArea ETInfo, ProgressBar healthBar, Label healthLabel, Label floorLabel){
        ETInfo.appendText(name + " has died\n");
        Main.player.defeatedMonster(Item.materialDrops[1], YTInfo, ETInfo, healthBar, healthLabel, floorLabel);
        ETInfo.appendText(name + " dropped " + Item.materialDrops[1].getName() + "\n");
        Main.dungeon.addDeadEnemy(this);
    }
}
