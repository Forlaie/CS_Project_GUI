package com.example.cs_project_gui;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main extends Application {
    public static Player player;
    public static String previousScreen;
    public static Floor floor;
    public static Dungeon dungeon;
    public static SimpleStringProperty YT;
    public static SimpleStringProperty ET;
    public static boolean newUser;

    // very first method that is called when program runs
    // load firstScreen screen
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("firstScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Wen Ymar Elad");
        stage.setScene(scene);
        stage.show();
    }

    // launch program
    public static void main(String[] args) {
        launch();
    }

    // static method to save player info into player file
    public static void putInfoIntoPlayerInfoFile() throws FileNotFoundException {
        PrintWriter playerOutput = new PrintWriter("playerInfo.txt");
        playerOutput.println(player.getHealth());
        playerOutput.println(player.getMaxHealth());
        playerOutput.println(player.getDefence());
        playerOutput.println(player.getAttack());
        playerOutput.println(player.getLevel());
        playerOutput.println(player.getXP());
        playerOutput.println(player.getCoins());
        if (player.getMaterials().containsKey(Item.materialDrops[0])){
            playerOutput.println(player.getMaterials().get(Item.materialDrops[0]));
        }
        else{
            playerOutput.println(0);
        }
        if (player.getMaterials().containsKey(Item.materialDrops[1])){
            playerOutput.println(player.getMaterials().get(Item.materialDrops[1]));
        }
        else{
            playerOutput.println(0);
        }
        if (player.getMaterials().containsKey(Item.materialDrops[2])){
            playerOutput.println(player.getMaterials().get(Item.materialDrops[2]));
        }
        else{
            playerOutput.println(0);
        }
        if (player.getMaterials().containsKey(Item.weaponDrops[0])){
            playerOutput.println(player.getMaterials().get(Item.weaponDrops[0]));
        }
        else{
            playerOutput.println(0);
        }
        if (player.getMaterials().containsKey(Item.weaponDrops[1])){
            playerOutput.println(player.getMaterials().get(Item.weaponDrops[1]));
        }
        else{
            playerOutput.println(0);
        }
        if (player.getMaterials().containsKey(Item.weaponDrops[2])){
            playerOutput.println(player.getMaterials().get(Item.weaponDrops[2]));
        }
        else{
            playerOutput.println(0);
        }
        if (player.getInventory().containsKey(Item.potions[0])){
            playerOutput.println(player.getInventory().get(Item.potions[0]));
        }
        else{
            playerOutput.println(0);
        }
        if (player.getInventory().containsKey(Item.potions[1])){
            playerOutput.println(player.getInventory().get(Item.potions[1]));
        }
        else{
            playerOutput.println(0);
        }
        if (player.getInventory().containsKey(Item.potions[2])){
            playerOutput.println(player.getInventory().get(Item.potions[2]));
        }
        else{
            playerOutput.println(0);
        }
        for (int i = 0; i < 3; i++){
            playerOutput.println(player.getEquipped()[i].getHealth());
            playerOutput.println(player.getEquipped()[i].getDefence());
            playerOutput.println(player.getEquipped()[i].getAttack());
        }
        playerOutput.close();
    }

    // static method to save login info into login file
    public static void putInfoIntoLoginInfoFile() throws FileNotFoundException, NoSuchAlgorithmException {
        PrintWriter pw = new PrintWriter("loginInfo.txt");
        pw.println(player.getUsername());
        pw.println(player.getPassword());
        pw.close();
    }

    // static method to save floor info into floor file
    public static void putInfoIntoFloorFile() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("floorInfo.txt");
        pw.println(Floor.floorLevel);
        for (Enemy enemy : floor.getEnemies()){
            pw.println(enemy.getName());
        }
        pw.close();
    }

    // hash password to keep it safe and secure from HACKERS (are you impressed ;D)
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] bytePassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder hashedPassword = new StringBuilder();
        for (byte aByte : bytePassword) {
            hashedPassword.append(String.format("%02X", aByte));
        }
        return hashedPassword.toString();
    }
}