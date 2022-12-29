package com.example.cs_project_gui;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;

// child of Enemy class (inheritance)
public class Troll extends Enemy{

    // inherits constructor and instance variables of Enemy class
    public Troll(String name, int health, int attack, String description) {
        super(name, health, attack, description);
    }

    // when troll dies, it steals something from player inventory (if there's something there)
    public String steal(){
        int index = (int) (Math.random()*Main.player.getInventory().size());
        List<Potion> keyList = new ArrayList<>(Main.player.getInventory().keySet());
        Potion potion = keyList.get(index);
        switch (potion.getName()){
            case "Health potion" -> potion = Item.potions[0];
            case "Attack potion" -> potion = Item.potions[1];
            case "Defence potion" -> potion = Item.potions[2];
        }
        Main.player.removeItem(potion);
        return potion.getName();
    }

    // override died function in Enemy class to include stealing
    public void died(TextArea YTInfo, TextArea ETInfo){
        Main.player.defeatedMonster(YTInfo);
        if (!Main.player.getInventory().isEmpty()){
            String itemName = steal();
            System.out.println(name + " steals " + itemName + " before dying");
        }
        else{
            System.out.println(name + " has died");
        }
        Main.floor.addDeadEnemy(this);
    }
}
