package com.pjeskiem_i_mieczem;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class VictoryGui extends VBox{
    public VictoryGui(Application main){
//      Setup size
        this.setPrefWidth(Config.windowWidth);
        this.setPrefHeight(Config.windowHeight);
        int buttonWidth = (int) (Config.windowWidth*0.3);
        int buttonHeight = (int)(Config.windowHeight*0.1);

//      Setup labels
        Label titleText = new Label("Brawo! \n Wygrałeeś!");
        Label scoreLabel = new Label("Twój aktualny stan konta to: "+Application.player.getGold());
        // Tu ma byc exp wg moich planów
        //Label expLabel = new Label("Masz: "+player.getGold());
        titleText.setFont(Font.font("Z003", FontWeight.BOLD, 45));
        scoreLabel.setFont(Font.font("Z003", FontWeight.BOLD, 25));
        titleText.setTextFill(Color.WHITESMOKE);
        scoreLabel.setTextFill(Color.WHITESMOKE);

//      Setup buttons
        ImageButton cityButton = new ImageButton("Przejdź do miasta", buttonWidth, buttonHeight, "buttons/button_1.png");
        cityButton.setOnAction((event)->{
            main.goToTheCity();
        });
        HBox buttons = new HBox(15);
        buttons.getChildren().addAll(cityButton);
        buttons.setAlignment(Pos.CENTER);

        this.setBack();
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().addAll(titleText, scoreLabel, buttons);
    }

    private void setBack(){
        BackgroundSize backgroundSize = new BackgroundSize(900,
                700,
                true,
                true,
                true,
                false);
        BackgroundImage image = new BackgroundImage(new Image("backgrounds/victory.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize);
        this.setBackground(new Background(image));
    }
}
