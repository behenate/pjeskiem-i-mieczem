package com.pjeskiem_i_mieczem;
import javafx.application.Platform;

import java.util.concurrent.ThreadLocalRandom;
import java.io.FileOutputStream;
import java.io.IOException;

public class Fight implements Runnable{
    private FightGui gui;
    private Player p2;
    private Application app;

    public Fight(Application app, FightGui gui, Player p2){
        this.gui = gui;
        this.p2 = p2;
        this.app = app;
    }

    @Override
    public void run() {
//        Two players that will fight
        Player[] players = {Application.player,p2};
        int current_player_idx = ThreadLocalRandom.current().nextInt(0, 1 + 1);
//        Flip enemy image
        p2.flipImageView();
//        Alternate between players, take damage from each based on stats until one loses
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(true){
            try {
                Player current_player = players[current_player_idx];
                Player other_player = players[(current_player_idx+1)%2];

//                Wait 1 second between each move
                Thread.sleep(1000);
                current_player.playAttackAnimation(400);
                other_player.takeDamage(current_player);
                current_player_idx = (current_player_idx + 1)%2;

                other_player.hp.setValue(Math.max(other_player.hp.getValue(),0));
                Platform.runLater(()-> gui.updateFightGui());
                if (other_player.hp.getValue() == 0){
                    Thread.sleep(1000);
                    if (other_player == Application.player){
                        this.clearFile();
                        Platform.runLater(() -> app.goToFailureGui());
                    }
                    else {
                        double wonExp = p2.exp.getValue() * (Math.random() * 9.9 + 0.1) / (double) 100;
                        wonExp = (int)(wonExp*Config.expModifier);
                        Application.player.exp.setValue(Application.player.exp.getValue() + wonExp);
                        Application.player.checkLevelUp();
                        double wonGold = (p2.gold * (Math.random() * 24 + 1) / (double) 100)*Config.goldModifier;
                        Application.player.addGold(wonGold);

                        this.clearFile();
                        Application.player.saveProgress();
                        Platform.runLater(() -> app.goToVictoryGui());
                    }
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
//    Function to clear the save file
    private void clearFile() {
        try {
            FileOutputStream writer = new FileOutputStream("src/main/resources/leaderboard/save");
            writer.write(("").getBytes());
            writer.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
