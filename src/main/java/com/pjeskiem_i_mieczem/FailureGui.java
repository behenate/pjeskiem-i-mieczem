package com.pjeskiem_i_mieczem;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class FailureGui extends VBox {
    private final LeaderboardGui leaderboardGui;

    public FailureGui(LeaderboardGui leaderboard, Application app){
//      Setup size
        this.leaderboardGui = leaderboard;
        this.setPrefWidth(Config.windowWidth);
        this.setPrefHeight(Config.windowWidth);
        int buttonWidth = (int) (Config.windowWidth*0.2);
        int buttonHeight = (int)(Config.windowHeight*0.1);

//      Setup labels
        Label titleText = new Label("Przegraaaaaałeś");
        Label scoreLabel = new Label("Twój wynik to: "+Application.player.getGold());
        titleText.setFont(Font.font("Z003", FontWeight.BOLD, 50));
        scoreLabel.setFont(Font.font("Z003", FontWeight.BOLD, 25));

//      Setup buttons
        //jakiś błąd z zapisem do rankingu (!!!)
        ImageButton saveButton = new ImageButton("Zapisz wynik", buttonWidth, buttonHeight, "buttons/button6.gif");
        saveButton.setOnAction((event)->{
            leaderboardGui.saveToLeaderboard();
        });
        ImageButton menuButton = new ImageButton("Zacznij od nowa", buttonWidth, buttonHeight, "buttons/button6.gif");
        menuButton.setOnAction((event)->{
            app.goToTheStart();
        });
        HBox buttons = new HBox(15);
        buttons.getChildren().addAll(saveButton, menuButton);
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
        BackgroundImage image = new BackgroundImage(new Image("backgrounds/failure.gif"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize);
        this.setBackground(new Background(image));
    }
}
