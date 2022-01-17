package com.pjeskiem_i_mieczem;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CityViewGui extends VBox {
    public CityViewGui(){
//      Setup sizes
        this.setPrefWidth(Config.windowWidth);
        this.setPrefHeight(Config.windowWidth);
        int buttonWidth = (int) (Config.windowWidth*0.3);
        int buttonHeight = (int)(Config.windowHeight*0.1);

//      Setup gui elements
        Text titleText = new Text("Witaj w pjeskowie!");
        titleText.setFont(Font.font("Z003",50));
        ImageButton arenaButton = new ImageButton("Idź na arenę", buttonWidth, buttonHeight,
                "buttons/wooden_arrow_left.png");
        ImageButton chillButton = new ImageButton("Idź na suczki", buttonWidth, buttonHeight,
                "buttons/wooden_arrow_right.png");
        ImageButton exitButton = new ImageButton("Exit", buttonWidth, buttonHeight,
                "buttons/wooden_arrow_left.png");
        VBox leftButtons = new VBox(arenaButton, exitButton);
        leftButtons.setSpacing(15);
        HBox buttons = new HBox(leftButtons, chillButton);
        buttons.setSpacing(0);
        VBox container = new VBox(titleText, buttons);
        container.setSpacing(10);
        container.setAlignment(Pos.CENTER);
        this.getChildren().add(container);
        this.setAlignment(Pos.CENTER);
    }
}
