package com.pjeskiem_i_mieczem;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.robot.Robot;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;

import java.io.FileOutputStream;
import java.io.IOException;

public class CityViewGui extends VBox {
    GridPane grid = new GridPane();
    Application app;
    Text gold;
    StatBar healthBar;
    StatBar expBar;

    public CityViewGui(Application app){
        this.app = app;
        makeGrid();
        this.getChildren().addAll(grid);
        this.setBack();
    }

    private void makeGrid(){
//      Setup sizes
        this.setPrefWidth(Config.windowWidth);
        this.setPrefHeight(Config.windowHeight);
        int buttonWidth = (int) (Config.windowWidth*0.13);
        int buttonHeight = (int)(Config.windowHeight*0.05);

//      setup grid
        for(int i = 0; i < 30; i++){
            Label label = new Label(" ");
            grid.add(label, i , i, 1, 1);
            grid.getRowConstraints().add(new RowConstraints(Config.windowHeight*0.025*16/9));
            grid.getColumnConstraints().add(new ColumnConstraints(Config.windowWidth*0.025));
        }

//      Setup gui elements
        Text titleText = new Text("Witaj w pjeskowie!");
        titleText.setFont(Font.font("Z003",50));

        if(Application.player != null){
            gold = new Text("Twój stan konta to: "+Application.player.getGold()+" $$");
            gold.setFont(Font.font("Z003",20));
            healthBar = new StatBar("#f7573e", true,(int)(Config.windowWidth*0.18),
                    (int)(Config.windowHeight*0.02), (float) Application.player.maxHp.getValue(),
                    (float) Application.player.hp.getValue(), "#f2afa5");
            expBar = new StatBar("#ebcf34", true, (int)(Config.windowWidth*0.18),
                    (int)(Config.windowHeight*0.02), (float) Application.player.expToNextLevel,
                    (float) Application.player.exp.getValue(), "#f0e6b1");

            VBox statsContainer = new VBox(gold,healthBar, expBar);
            statsContainer.setSpacing(10);
            statsContainer.setAlignment(Pos.CENTER);
            grid.add(statsContainer, 16, 5, 1, 1);
        }

//      Setup buttons and their sizes
        ImageButton arenaButton = new ImageButton("Idź na arenę", buttonWidth, buttonHeight,
                "buttons/arrow_left.gif");
        ImageButton trainingButton = new ImageButton("Idź na trening", buttonWidth, buttonHeight,
                "buttons/arrow_right.gif");
        ImageButton menuButton = new ImageButton("Idź do menu", buttonWidth, buttonHeight,
                "buttons/arrow_left.gif");

        Popup cashPopup = new Popup();
        cashPopup.getContent().addAll(new ImageButton("10 $$", 100, 60, "buttons/dymek.gif"));

        Robot robot = new Robot();

        Runnable showPopup = ()->{
            cashPopup.setX(robot.getMouseX()+40);
            cashPopup.setY(robot.getMouseY()-30);
            cashPopup.show(app.stage);
        };
        Runnable hidePopup = cashPopup::hide;

        ImageButton chillButton = new ImageButton("Idź na suczki", buttonWidth, buttonHeight,
                "buttons/arrow_right.gif", showPopup, hidePopup);

        arenaButton.setMinWidth(buttonWidth);
        chillButton.setMinWidth(buttonWidth);
        trainingButton.setMinWidth(buttonWidth);
        menuButton.setMinWidth(buttonWidth);

//      Setup buttons actions
        arenaButton.setOnAction((event)->{
            app.goToTheArena();
        });
        chillButton.setOnAction((event)->{
            this.chillOut();
            this.gold.setText("Twój stan konta to: "+Application.player.getGold()+" $$");
            this.healthBar.updateBar((float) Application.player.hp.getValue());

        });
        trainingButton.setOnAction((event)->{
            app.goToTheTraining();
        });

        menuButton.setOnAction((event)->{
            try {
                FileOutputStream writer = new FileOutputStream("src/main/resources/leaderboard/save");
                writer.write(("").getBytes());
                writer.close();
            }catch(IOException ex){
                ex.printStackTrace();
            }
            Application.player.saveProgress();
            app.goToTheStart();
        });
        //grid.setGridLinesVisible(true);
//      add elements to grid
        grid.add(titleText, 14, 1, 1, 2);
        grid.add(arenaButton, 6, 11, 2, 1);
        grid.add(menuButton, 6, 13, 2, 2);
        grid.add(trainingButton, 11, 11, 1, 2);
        grid.add(chillButton, 11, 13, 1, 1);
        chillButton.setTranslateX(-5);
        trainingButton.setTranslateX(-4);
    }

    private void chillOut(){
        int boost = 10;
        if(Application.player.hp.getValue() < Application.player.maxHp.getValue()){
            Application.player.gold -= 10;
            Application.player.hp.setValue(Math.min(Application.player.maxHp.getValue(), Application.player.hp.getValue()+boost));
        }
    }
    private void setBack(){
        BackgroundSize backgroundSize = new BackgroundSize(900,
                700,
                true,
                true,
                true,
                false);
        BackgroundImage image = new BackgroundImage(new Image("backgrounds/cityView.gif"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize);
        this.setBackground(new Background(image));
    }
}
