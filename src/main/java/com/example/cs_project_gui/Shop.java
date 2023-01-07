package com.example.cs_project_gui;

import javafx.scene.control.Label;
public class Shop {
    // confirm purchase is valid, then purchase selected items or display error messages accordingly
    public static void purchaseItem(Label messageLabel, Label coinsLabel) {
        boolean valid = true;
        boolean buySomething = false;
        for (Potion potion : Item.getObservablePotions()){
            if (potion.getCheckBox().isSelected() && Integer.parseInt(potion.getTextField().getText()) > 0){
                buySomething = true;
                break;
            }
        }
        if (buySomething){
            for (Potion potion : Item.getObservablePotions()){
                if (potion.getCheckBox().isSelected()){
                    try {
                        int buy = Integer.parseInt(potion.getTextField().getText());
                        if (potion.getCost()*buy > Main.player.getCoins()){
                            messageLabel.setText("Sorry, you have tried to buy more than you can afford");
                            valid = false;
                            break;
                        }
                        else{
                            Main.player.useCoins(potion.getCost()*buy);
                            Main.player.getInventory().merge(potion, buy, Integer::sum);
                        }
                    } catch (NumberFormatException e) {
                        messageLabel.setText("Please input integers only");
                        valid = false;
                        break;
                    }
                }
            }
            if (valid){
                messageLabel.setText("Successfully bought potions!");
                coinsLabel.setText("Coins: " + Main.player.getCoins());
            }
        }
        else{
            messageLabel.setText("Please buy at least one item");
        }
    }
}
