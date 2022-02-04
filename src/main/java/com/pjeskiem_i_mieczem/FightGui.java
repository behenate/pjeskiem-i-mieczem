package com.pjeskiem_i_mieczem;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class FightGui extends VBox {
    private HBox cardsContainer = new HBox();
    private final Player player2;
    public FightGui(Player characterDos){
        this.player2 = characterDos;
        Text titleText = new Text("Bitwa!");
        titleText.setFont(Font.font("Monotype Corsiva", 50));

        cardsContainer.setAlignment(Pos.CENTER);
        cardsContainer.setSpacing(Config.windowWidth*0.25);

        this.getChildren().addAll(titleText, cardsContainer);
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(Config.windowHeight*0.1);
        this.setBack();
        updateFightGui();
    }

    public void updateFightGui(){
        VBox p1Card = Application.player.getPlayerCard((int)(Config.windowWidth*0.25));
        VBox p2Card = player2.getPlayerCard((int)(Config.windowWidth*0.25));
        p1Card.setMaxWidth(Config.windowWidth*0.25);
        p2Card.setMaxWidth(Config.windowWidth*0.25);
        p2Card.setTranslateX(-Config.windowWidth*0.05);
        cardsContainer.getChildren().clear();
        cardsContainer.getChildren().addAll(p1Card, p2Card);
    }

    private void setBack(){
        BackgroundSize backgroundSize = new BackgroundSize(900,
                700,
                true,
                true,
                true,
                false);
        BackgroundImage image = new BackgroundImage(new Image("backgrounds/arena.gif"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize);
        this.setBackground(new Background(image));
    }
}
