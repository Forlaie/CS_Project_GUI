package com.example.cs_project_gui;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

// child of Enemy class (inheritance)
public class Vampire extends Enemy{
    // inherits constructor and instance variables of Enemy class
    public Vampire(String name, int health, int attack, String description) {
        super(name, health, attack, description);
    }

    // override floor battle function from parent class (Enemy)
    // takes into account vampire sucking blood (stealing player health)
    public void Fbattle(TextArea YTInfo, TextArea ETInfo, ProgressBar healthBar, Label healthLabel, Label floorLabel, VBox enemyVBox){
        health -= Main.player.getAttack();
        Main.YT.setValue(Main.YT.getValue() + "You have dealt " + Main.player.getAttack() + " damage to " + name + "\n");
        int suckedBlood = (int) (Main.player.getHealth()*0.05);
        health += suckedBlood;
        Main.ET.setValue(Main.ET.getValue() + "Vampire sucked " + suckedBlood + " hp " + "from you\n");
        Main.player.setHealth(Main.player.getHealth() - suckedBlood);
        if (health <= 0){
            enemyVBox.getChildren().remove(infoVBox);
            Fdied(YTInfo, ETInfo, healthBar, healthLabel, floorLabel);
        }
        hLabel.setText(this.health + "/" + maxHealth);
        hBar.setProgress((double) this.health/maxHealth);
    }

    // override dungeon battle function from parent class (Enemy)
    // takes into account vampire sucking blood (stealing player health)
    public void Dbattle(TextArea YTInfo, TextArea ETInfo, ProgressBar healthBar, Label healthLabel, Label floorLabel, VBox enemyVBox){
        health -= Main.player.getAttack();
        Main.YT.setValue(Main.YT.getValue() + "You have dealt " + Main.player.getAttack() + " damage to " + name + "\n");
        int suckedBlood = (int) (Main.player.getHealth()*0.05);
        health += suckedBlood;
        Main.player.setHealth(Main.player.getHealth() - suckedBlood);
        if (health <= 0){
            enemyVBox.getChildren().remove(infoVBox);
            Ddied(YTInfo, ETInfo, healthBar, healthLabel, floorLabel);
        }
        hLabel.setText(this.health + "/" + maxHealth);
        hBar.setProgress((double) this.health/maxHealth);
    }

    // when vampire dies in a dungeon, only drop vampire materials
    public void Ddied(TextArea YTInfo, TextArea ETInfo, ProgressBar healthBar, Label healthLabel, Label floorLabel){
        Main.ET.setValue(Main.ET.getValue() + name + " has died\n");
        Main.player.defeatedMonster(Item.materialDrops[1], YTInfo, ETInfo, healthBar, healthLabel, floorLabel);
        Main.ET.setValue(Main.ET.getValue() + name + " dropped " + Item.materialDrops[1].getName() + "\n");
        Main.dungeon.addDeadEnemy(this);
    }
}
