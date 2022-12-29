package com.example.cs_project_gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;

public class Enemy {
    protected String name;
    protected int health;
    protected int attack;
    protected String description;
    private static Enemy[] possibleEnemies = {
            new Enemy("Enemy", 10*Floor.floorLevel, Floor.floorLevel, """
            Enemies are people who have been corrupted by the pollution"""),
            new Vampire("Vampire", 15*Floor.floorLevel, 3*Floor.floorLevel, """
                    Vampires are creatures that suck your blood
                    They steal your hp and heal themselves
                    (scaled from your hp)"""),
            new Golem("Golem", 20*Floor.floorLevel, 2*Floor.floorLevel, 5*Floor.floorLevel, """
                    Golems are creatures made of rock and stone that have become sentient due to the pollution
                    They have strong defence, so attacks will deal less damage than usual
                    (scaled from golem defence)"""),
            new Troll("Troll", 5*Floor.floorLevel, 2*Floor.floorLevel, """
                    Trolls are mischievous mythical creatures that love to play tricks
                    Trolls will steal an item from your inventory when they die, so equip any items you want to keep safe"""),
            new Dragon("Dragon", 50*Floor.floorLevel, 10*Floor.floorLevel, 10*Floor.floorLevel, """
                    Dragons are extremely powerful creatures
                    Dragons have lots of health, attack, and defence, so they're difficult to defeat
                    However, once defeated, you can gain stat bonuses to your health, defence or attack""")
    };

    public Enemy(String name, int health, int attack, String description){
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.description = description;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getHealth(){
        return health;
    }

    public int getAttack(){
        return attack;
    }

    public static ObservableList<Enemy> getObservablePossibleEnemies(){
        ObservableList<Enemy> observablePossibleEnemies = FXCollections.observableArrayList();
        observablePossibleEnemies.addAll(possibleEnemies);
        return observablePossibleEnemies;
    }

    public static Enemy generateRandomEnemy(){
        possibleEnemies = new Enemy[]{
                new Enemy("Enemy", 10 * Floor.floorLevel, Floor.floorLevel, """
                        Enemies are people who have been corrupted by the pollution"""),
                new Vampire("Vampire", 15 * Floor.floorLevel, 3 * Floor.floorLevel, """
                        Vampires are creatures that suck your blood
                        They steal your hp and heal themselves
                        (scaled from your hp)"""),
                new Golem("Golem", 20 * Floor.floorLevel, 2 * Floor.floorLevel, 5 * Floor.floorLevel, """
                        Golems are creatures made of rock and stone that have become sentient due to the pollution
                        They have strong defence, so attacks will deal less damage than usual
                        (scaled from golem defence)"""),
                new Troll("Troll", 5 * Floor.floorLevel, 2 * Floor.floorLevel, """
                        Trolls are mischievous mythical creatures that love to play tricks
                        Trolls will steal an item from your inventory when they die, so equip any items you want to keep safe"""),
                new Dragon("Dragon", 50 * Floor.floorLevel, 10 * Floor.floorLevel, 10 * Floor.floorLevel, """
                        Dragons are extremely powerful creatures
                        Dragons have lots of health, attack, and defence, so they're difficult to defeat
                        However, once defeated, you can gain stat bonuses to your health, defence or attack""")
        };
        int index;
        if (Floor.floorLevel <= 5){
            index = (int) (Math.random() * possibleEnemies.length - 1);
        }
        else{
            index = (int) (Math.random() * possibleEnemies.length);
        }
        return possibleEnemies[index];
    }

    // battling in a floor
    public void battle(TextArea YTInfo, TextArea ETInfo){
        health -= Main.player.getAttack();
        YTInfo.appendText("You have dealt " + Main.player.getAttack() + " damage to " + name + "\n");
        if (health <= 0){
            died(YTInfo, ETInfo);
        }
    }

    // dying in a floor (drop random item)
    public void died(TextArea YTInfo, TextArea ETInfo){
        ETInfo.appendText(name + " has died\n");
        Item item = Item.generateRandomDrop();
        Main.player.defeatedMonster(item, YTInfo);
        ETInfo.appendText(name + " dropped " + item.getName() + "\n");
        Main.floor.addDeadEnemy(this);
    }

    // battling in a dungeon
//    public void battle(Player player, Dungeon dungeon){
//        health -= player.getAttack();
//        System.out.println("You have dealt " + player.getAttack() + " damage to " + name);
//        if (health <= 0){
//            died(player, dungeon);
//        }
//    }

    // when enemy dies in a dungeon, only drop enemy materials
//    public void died(Player player, Dungeon dungeon){
//        System.out.println(name + " has died");
//        player.defeatedMonster(Item.materialDrops[0]);
//        System.out.println(name + " dropped " + Item.materialDrops[0].getName());
//        dungeon.addDeadEnemy(this);
//    }
}
