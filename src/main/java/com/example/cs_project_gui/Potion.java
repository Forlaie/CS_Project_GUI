package com.example.cs_project_gui;

// child of Item class (inheritance)
public class Potion extends Item{
    // inherits constructor and instance variables of Item class
    public Potion(String name, int health, int defence, int attack, int cost, String description) {
        super(name, health, defence, attack, cost, description);
    }

    public Potion(String name, int cost, String description) {
        super(name, cost, description);
    }

    // resets player stats back to normal after buff is over
    public void endOfEffect(){
        switch (name){
            case "Defence potion" -> Main.player.setDefence(Main.player.getDefence()-defence);
            case "Attack potion" -> Main.player.setAttack(Main.player.getAttack()-attack);
        }
    }

    public void usePotion(int use){
        switch (name){
            case "Health potion" -> Main.player.setHealth(Math.min(Main.player.getHealth() + health * use, Main.player.getMaxHealth()));
            case "Defence potion" -> Main.player.setDefence(Main.player.getDefence()+defence);
            case "Attack potion" -> Main.player.setAttack(Main.player.getAttack()+attack);
        }
    }
}

