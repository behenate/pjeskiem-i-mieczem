package com.pjeskiem_i_mieczem;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class FailureGui extends VBox {
    private final LeaderboardGui leaderboardGui;

    public FailureGui(LeaderboardGui leaderboard, Application app){
//      Setup size and background
        this.leaderboardGui = leaderboard;
        this.setPrefWidth(Config.windowWidth);
        this.setPrefHeight(Config.windowWidth);
        int buttonWidth = (int) (Config.windowWidth*0.2);
        int buttonHeight = (int)(Config.windowHeight*0.1);
        Tools.setBack(this, "backgrounds/failure.gif");

//      Setup labels
        Label titleText = new Label("Przegraaaałeś");
        Label scoreLabel = new Label("Twój wynik to: " + Application.player.getGold());

//      Setup buttons and their actions
        ImageButton saveButton = new ImageButton("Zapisz wynik", buttonWidth, buttonHeight, "buttons/button6.gif");
        saveButton.setOnAction((event)->{
            leaderboardGui.saveToLeaderboard();
            app.goToTheStart();
        });
        ImageButton menuButton = new ImageButton("Zacznij od nowa", buttonWidth, buttonHeight, "buttons/button6.gif");
        menuButton.setOnAction((event)->{
            app.goToTheStart();
        });
        HBox buttons = new HBox(15);
        buttons.getChildren().addAll(saveButton, menuButton);
        buttons.setAlignment(Pos.CENTER);

        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().addAll(titleText, scoreLabel, buttons);
    }
}
