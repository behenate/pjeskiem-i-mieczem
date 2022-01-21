package com.pjeskiem_i_mieczem;
import javafx.application.Platform;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Fight implements Runnable{
    private FightGui gui;
    private Player p1;
    private Player p2;
    public Fight(FightGui gui, Player p1, Player p2){
        this.gui = gui;
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public void run() {
        Player[] players = {p1,p2};
        int current_player_idx = 0;
        while(true){
            try {
                Player current_player = players[current_player_idx];
                Player other_player = players[(current_player_idx+1)%2];
                Thread.sleep(1000);
                other_player.takeDamage(current_player);
                Platform.runLater(()->{
                    gui.updateFightGui();
                });
                System.out.println("Stary niedzwiedz mocno spi");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
