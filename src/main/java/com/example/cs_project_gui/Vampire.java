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
    public void battle(TextArea YTInfo, TextArea ETInfo, ProgressBar healthBar, Label healthLabel, Label floorLabel){
        health -= Main.player.getAttack();
        YTInfo.appendText("You have dealt " + Main.player.getAttack() + " damage to " + name + "\n");
        int suckedBlood = (int) (Main.player.getHealth()*0.05);
        health += suckedBlood;
        ETInfo.appendText("Vampire sucked " + suckedBlood + " hp " + "from you\n");
        Main.player.setHealth(Main.player.getHealth() - suckedBlood);
        if (health <= 0){
            died(YTInfo, ETInfo, healthBar, healthLabel, floorLabel);
        }
    }

    // override dungeon battle function from parent class (Enemy)
    // takes into account vampire sucking blood (stealing player health)
//    public void battle(Player player, Dungeon dungeon){
//        health -= player.getAttack();
//        System.out.println("You have dealt " + player.getAttack() + " damage to " + name + "\n");
//        int suckedBlood = (int) (player.getHealth()*0.05);
//        health += suckedBlood;
//        System.out.println("Vampire sucked " + suckedBlood + " hp from you\n");
//        player.setHealth(player.getHealth() - suckedBlood);
//        if (health <= 0){
//            died(player, dungeon);
//        }
//    }

    // when vampire dies in a dungeon, only drop vampire materials
//    public void died(Player player, Dungeon dungeon){
//        System.out.println(name + " has died");
//        player.defeatedMonster(Item.materialDrops[1]);
//        System.out.println(name + " dropped " + Item.materialDrops[1].getName());
//        dungeon.addDeadEnemy(this);
//    }
}
