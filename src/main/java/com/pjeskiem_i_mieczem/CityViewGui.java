package com.pjeskiem_i_mieczem;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CityViewGui extends VBox {
    GridPane grid = new GridPane();
    Player player;

    public CityViewGui(Player player){
        this.player = player;
        makeGrid();
        this.getChildren().addAll(grid);
        this.setBack();
    }

    private void makeGrid(){
//      Setup sizes
        this.setPrefWidth(Config.windowWidth);
        this.setPrefHeight(Config.windowHeight);
        int buttonWidth = (int) (Config.windowWidth*0.1);
        int buttonHeight = (int)(Config.windowHeight*0.05);

//      setup grid
        for(int i = 0; i < 30; i++){
            Label label = new Label(" ");
            grid.add(label, i , i, 1, 1);
            grid.getRowConstraints().add(new RowConstraints(Config.windowHeight*0.05*16/9));
            grid.getColumnConstraints().add(new ColumnConstraints(Config.windowWidth*0.05));
        }

//      Setup gui elements
        Text titleText = new Text("Witaj w pjeskowie!");
        titleText.setFont(Font.font("Z003",50));
        if(player != null){
            Text gold = new Text("Twój stan konta to: "+player.getGold()+" $$");
            gold.setFont(Font.font("Z003",20));
            grid.add(gold, 15, 10, 1, 1);
        }

//      Setup buttons
        ImageButton arenaButton = new ImageButton("Idź na arenę", buttonWidth, buttonHeight,
                "buttons/wooden_arrow_left.png");
        ImageButton chillButton = new ImageButton("Idź na suczki", buttonWidth, buttonHeight,
                "buttons/wooden_arrow_right.png");
        ImageButton trainingButton = new ImageButton("Idź na trening", buttonWidth, buttonHeight,
                "buttons/wooden_arrow_right.png");
        ImageButton exitButton = new ImageButton("Exit", buttonWidth, buttonHeight,
                "buttons/wooden_arrow_left.png");
        arenaButton.setMinWidth(buttonWidth);
        chillButton.setMinWidth(buttonWidth);
        trainingButton.setMinWidth(buttonWidth);
        exitButton.setMinWidth(buttonWidth);

        //grid.setGridLinesVisible(true);
//      add elements to grid
        grid.add(titleText, 7, 0, 1, 2);
        grid.add(arenaButton, 3, 5, 1, 1);
        grid.add(exitButton, 3, 6, 1, 1);
        grid.add(trainingButton, 5, 5, 1, 2);
        grid.add(chillButton, 5, 6, 1, 1);
    }

    private void setBack(){
        BackgroundSize backgroundSize = new BackgroundSize(900,
                700,
                true,
                true,
                true,
                false);
        BackgroundImage image = new BackgroundImage(new Image("backgrounds/cityView.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize);
        this.setBackground(new Background(image));
    }
}
