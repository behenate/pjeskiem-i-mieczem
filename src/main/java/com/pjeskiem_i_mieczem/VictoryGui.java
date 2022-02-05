package com.pjeskiem_i_mieczem;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;


public class VictoryGui extends VBox{
    public VictoryGui(Application main){
//      Setup size
        this.setPrefWidth(Config.windowWidth);
        this.setPrefHeight(Config.windowHeight);
        int buttonWidth = (int) (Config.windowWidth*0.3);
        int buttonHeight = (int)(Config.windowHeight*0.1);
        Tools.setBack(this, "backgrounds/victory.gif");

//      Setup labels
        Label titleText = new Label("Brawo! \n Wygrałeeś!");
        Label scoreLabel = new Label("Twój aktualny stan konta to: "+Application.player.getGold());
        titleText.setStyle("-fx-font-size: 50");
        scoreLabel.setStyle("-fx-font-size: 30");


//      Setup buttons
        ImageButton cityButton = new ImageButton("Przejdź do miasta", buttonWidth, buttonHeight, "buttons/button6.gif");
        cityButton.setOnAction((event)->{
            main.goToTheCity();
        });
        HBox buttons = new HBox(15);
        buttons.getChildren().addAll(cityButton);
        buttons.setAlignment(Pos.CENTER);

        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().addAll(titleText, scoreLabel, buttons);
    }
}
