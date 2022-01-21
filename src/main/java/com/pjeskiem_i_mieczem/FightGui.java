package com.pjeskiem_i_mieczem;

import com.pjeskiem_i_mieczem.Config;
import com.pjeskiem_i_mieczem.Player;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class FightGui extends VBox {
    private HBox cardsContainer = new HBox();
    private final Player player1;
    private final Player player2;
    public FightGui(Player characterUno, Player characterDos){
        this.player1 = characterUno;
        this.player2 = characterDos;
        Text titleText = new Text("Bitwa!");
        titleText.setFont(Font.font("Monotype Corsiva", 50));
        updateFightGui();
        cardsContainer.setAlignment(Pos.CENTER);
        cardsContainer.setSpacing(Config.windowWidth*0.15);
        this.getChildren().addAll(titleText, cardsContainer, new Button("Halo"));
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(Config.windowHeight*0.05);
    }

    public void updateFightGui(){
        VBox p1Card = player1.getPlayerCard((int)(Config.windowWidth*0.3));
        VBox p2Card = player2.getPlayerCard((int)(Config.windowWidth*0.3));
        p1Card.setMaxWidth(Config.windowWidth*0.2);
        p2Card.setMaxWidth(Config.windowWidth*0.2);
        cardsContainer.getChildren().clear();
        cardsContainer.getChildren().addAll(p1Card, p2Card);
    }

}
