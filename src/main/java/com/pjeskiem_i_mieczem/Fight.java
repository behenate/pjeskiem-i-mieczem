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
    private Application app;
    public Fight(Application app, FightGui gui, Player p1, Player p2){
        this.gui = gui;
        this.p1 = p1;
        this.p2 = p2;
        this.app = app;
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
                current_player_idx = (current_player_idx + 1)%2;
                other_player.currentHp.setValue(Math.max(other_player.currentHp.getValue(),0));
                Platform.runLater(()->{
                    gui.updateFightGui();
                });
                if (other_player.currentHp.getValue() == 0){
                    Thread.sleep(1000);
                    if (other_player == Application.player){
                        Platform.runLater(() -> app.goToFailureGui());
                    }
                    else {
                        Platform.runLater(() -> app.goToVictoryGui());
                    }
                    break;
                }
                System.out.println("Stary niedzwiedz mocno spi");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
