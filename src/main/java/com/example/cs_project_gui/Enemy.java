package com.example.cs_project_gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Enemy {
    protected String name;
    protected int health;
    protected int maxHealth;
    protected int attack;
    protected String description;
    //protected ImageView image;
    protected Label hLabel = new Label();
    protected ProgressBar hBar = new ProgressBar();
    protected VBox infoVBox;
    private static Enemy[] possibleEnemies = {
            new Enemy("Enemy", 10*Floor.floorLevel, Floor.floorLevel, """
            Enemies are people who have been corrupted by \nthe pollution"""),
            new Vampire("Vampire", 15*Floor.floorLevel, 3*Floor.floorLevel, """
                    Vampires are creatures that suck your blood
                    They steal your hp and heal themselves
                    (scaled from your hp)"""),
            new Golem("Golem", 20*Floor.floorLevel, 2*Floor.floorLevel, 5*Floor.floorLevel, """
                    Golems are creatures made of rock and stone that \nhave become sentient due to the pollution
                    They have strong defence, so attacks will deal less \ndamage than usual
                    (scaled from golem defence)"""),
            new Troll("Troll", 5*Floor.floorLevel, 2*Floor.floorLevel, """
                    Trolls are mischievous mythical creatures that love \nto play tricks
                    Trolls will steal an item from your inventory when \nthey die, so equip any items you want to keep safe \nand drink any potions you want to use asap"""),
            new Dragon("Dragon", 50*Floor.floorLevel, 10*Floor.floorLevel, 10*Floor.floorLevel, """
                    Dragons are extremely powerful creatures
                    Dragons have lots of health, attack, and defence, so \nthey're difficult to defeat
                    However, once defeated, you can gain stat bonuses \nto your health, defence or attack""")
    };

    public Enemy(String name, int health, int attack, String description){
        this.name = name;
        this.health = health;
        maxHealth = health;
        this.attack = attack;
        this.description = description;
//        InputStream stream = new FileInputStream("D:\images\elephant.jpg");
//        Image image = new Image(stream);
//        ImageView imageView = new ImageView();
//        //Setting image to the image view
//        imageView.setImage(image)
        //image = new ImageView("Enemy_Hilichurl.png");
        hLabel.setText(this.health + "/" + maxHealth);
        hBar.setProgress((double) this.health/maxHealth);
        Label nameLabel = new Label(this.name);
        HBox h = new HBox(5);
        h.getChildren().addAll(hBar, hLabel);
        infoVBox = new VBox();
        infoVBox.getChildren().addAll(nameLabel, h);
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

    public Label gethLabel(){
        return hLabel;
    }

    public ProgressBar gethBar(){
        return hBar;
    }

    public VBox getInfoVBox(){
        return infoVBox;
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
    public void Fbattle(TextArea YTInfo, TextArea ETInfo, ProgressBar healthBar, Label healthLabel, Label floorLabel, VBox enemyVBox){
        health -= Main.player.getAttack();
        Main.YT.setValue(Main.YT.getValue() + "You have dealt " + Main.player.getAttack() + " damage to " + name + "\n");
        if (health <= 0){
            enemyVBox.getChildren().remove(infoVBox);
            Fdied(YTInfo, ETInfo, healthBar, healthLabel, floorLabel);
        }
        hLabel.setText(this.health + "/" + maxHealth);
        hBar.setProgress((double) this.health/maxHealth);
    }

    // dying in a floor (drop random item)
    public void Fdied(TextArea YTInfo, TextArea ETInfo, ProgressBar healthBar, Label healthLabel, Label floorLabel){
        Main.ET.setValue(Main.ET.getValue() + name + " has died\n");
        Item item = Item.generateRandomDrop();
        Main.player.defeatedMonster(item, YTInfo, ETInfo, healthBar, healthLabel, floorLabel);
        Main.ET.setValue(Main.ET.getValue() + name + " dropped " + item.getName() + "\n");
        Main.floor.addDeadEnemy(this);
    }

    // battling in a dungeon
    public void Dbattle(TextArea YTInfo, TextArea ETInfo, ProgressBar healthBar, Label healthLabel, Label floorLabel, VBox enemyVBox){
        health -= Main.player.getAttack();
        Main.YT.setValue(Main.YT.getValue() + "You have dealt " + Main.player.getAttack() + " damage to " + name + "\n");
        if (health <= 0){
            enemyVBox.getChildren().remove(infoVBox);
            Ddied(YTInfo, ETInfo, healthBar, healthLabel, floorLabel);
        }
        hLabel.setText(this.health + "/" + maxHealth);
        hBar.setProgress((double) this.health/maxHealth);
    }

    // when enemy dies in a dungeon, only drop enemy materials
    public void Ddied(TextArea YTInfo, TextArea ETInfo, ProgressBar healthBar, Label healthLabel, Label floorLabel){
        Main.ET.setValue(Main.ET.getValue() + name + " has died\n");
        Main.player.defeatedMonster(Item.materialDrops[0], YTInfo, ETInfo, healthBar, healthLabel, floorLabel);
        Main.ET.setValue(Main.ET.getValue() + name + " dropped " + Item.materialDrops[0].getName() + "\n");
        Main.dungeon.addDeadEnemy(this);
    }
}
