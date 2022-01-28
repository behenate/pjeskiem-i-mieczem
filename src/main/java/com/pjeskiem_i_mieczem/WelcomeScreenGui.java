package com.pjeskiem_i_mieczem;

import com.pjeskiem_i_mieczem.Config;
import com.pjeskiem_i_mieczem.ImageButton;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

// Class containing the Gui of the welcome screen
public class WelcomeScreenGui extends HBox {
    public WelcomeScreenGui(Application app){

//        Setup sizes
        this.setPrefWidth(Config.windowWidth);
        this.setPrefHeight(Config.windowWidth);
        int buttonWidth = (int) (Config.windowWidth*0.3);
        int buttonHeight = (int)(Config.windowHeight*0.1);

//      Setup gui elemets
        Text titleText = new Text("PJESKIEM I \n \t MIECZEM \n");
        titleText.setFont(Font.font("Z003",50));
        ImageButton startButton = new ImageButton("Start", buttonWidth, buttonHeight, "buttons/button_0.png");

        ImageButton leaderboardButton = new ImageButton("Leaderboard", buttonWidth, buttonHeight, "buttons/button_0.png");
        ImageButton exitButton = new ImageButton("Exit", buttonWidth, buttonHeight, "buttons/button_0.png");

//        Setup events
        startButton.setOnAction((event)->{
            app.goToCreateCharacterGui();
        });
        leaderboardButton.setOnAction((event)->{
            app.goToTheLeaderboard();
        });
        exitButton.setOnAction((event)->{
            System.exit(0);
        });

//        Add the gui elements to containers and base class
        VBox container = new VBox(titleText, startButton, leaderboardButton, exitButton);
        container.setAlignment(Pos.CENTER);
        this.getChildren().add(container);
        this.setAlignment(Pos.CENTER);
    }
}
