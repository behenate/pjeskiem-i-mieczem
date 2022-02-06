package com.pjeskiem_i_mieczem;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class FightGui extends VBox {
    private final HBox cardsContainer = new HBox();
    private final Player player2;

    public FightGui(Player characterDos){
        this.player2 = characterDos;

//        Setup gui elements and background
        Text titleText = new Text("Bitwa!");
        titleText.setFont(Font.font("Monotype Corsiva", 50));
        Tools.setBack(this, "backgrounds/arena.gif");

        updateFightGui();
        cardsContainer.setAlignment(Pos.CENTER);
        cardsContainer.setSpacing(Config.windowWidth*0.25);
        this.getChildren().addAll(titleText, cardsContainer);
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(Config.windowHeight*0.1);
    }

//    Function for updating players during the fight
    public void updateFightGui(){
        VBox p1Card = Application.player.getPlayerCard((int)(Config.windowWidth*0.25));
        VBox p2Card = player2.getPlayerCard((int)(Config.windowWidth*0.25));
        p1Card.setMaxWidth(Config.windowWidth*0.25);
        p2Card.setMaxWidth(Config.windowWidth*0.25);
        p2Card.setTranslateX(-Config.windowWidth*0.05);
        cardsContainer.getChildren().clear();
        cardsContainer.getChildren().addAll(p1Card, p2Card);
    }
}
