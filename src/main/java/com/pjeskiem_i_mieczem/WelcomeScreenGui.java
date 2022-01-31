package com.pjeskiem_i_mieczem;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.*;

// Class containing the Gui of the welcome screen
public class WelcomeScreenGui extends HBox {
    public WelcomeScreenGui(Application app){

//        Setup sizes
        this.setPrefWidth(Config.windowWidth);
        this.setPrefHeight(Config.windowWidth);
        int buttonWidth = (int) (Config.windowWidth*0.3);
        int buttonHeight = (int)(Config.windowHeight*0.1);
        setBack();

//      Setup gui elemets
        Text titleText = new Text("PJESKIEM I \n \t MIECZEM \n");
        titleText.setFont(Font.font("Z003",50));
        ImageButton startButton = new ImageButton("New game", buttonWidth, buttonHeight, "buttons/button6.gif");
        ImageButton loadButton = new ImageButton("Load game", buttonWidth, buttonHeight, "buttons/button6.gif");
        ImageButton leaderboardButton = new ImageButton("Leaderboard", buttonWidth, buttonHeight, "buttons/button6.gif");
        ImageButton exitButton = new ImageButton("Exit", buttonWidth, buttonHeight, "buttons/button6.gif");

//        Setup events
        startButton.setOnAction((event)->{
            try {
                FileOutputStream writer = new FileOutputStream("src/main/resources/leaderboard/save");
                writer.write(("").getBytes());
                writer.close();
            }catch(IOException ex){
                ex.printStackTrace();
            }
            app.goToCreateCharacterGui();
        });
        loadButton.setOnAction((event)->{
            String filePath = "src/main/resources/leaderboard/save";
            File newFile = new File(filePath);
            if(newFile.length() > 0) {
                try {
                    Application.player = reloadPlayer(filePath);
                    app.goToTheCity();
                }catch(NullPointerException ex){
                    ex.printStackTrace();
                    System.exit(22);
                }
            }
        });
        leaderboardButton.setOnAction((event)->{
            app.goToTheLeaderboard();
        });
        exitButton.setOnAction((event)->{
            System.exit(0);
        });

//        Add the gui elements to containers and base class
        VBox container = new VBox(titleText, startButton, loadButton, leaderboardButton, exitButton);
        container.setAlignment(Pos.CENTER);
        this.getChildren().add(container);
        this.setAlignment(Pos.CENTER);
    }

    public Player reloadPlayer(String filePath) {
        try {
            FileInputStream file = new FileInputStream(filePath);
            ObjectInputStream objectIn = new ObjectInputStream(file);
            Player player = (Player) objectIn.readObject();
            objectIn.close();
            player.setImages();
            return player;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private void setBack(){
        BackgroundSize backgroundSize = new BackgroundSize(900,
                700,
                true,
                true,
                true,
                false);
        BackgroundImage image = new BackgroundImage(new Image("backgrounds/welcomeScreen.gif"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize);
        this.setBackground(new Background(image));
    }
}
