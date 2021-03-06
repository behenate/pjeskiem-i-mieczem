package com.pjeskiem_i_mieczem;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.io.*;

public class WelcomeScreenGui extends HBox {
    public WelcomeScreenGui(Application app){

//        Setup sizes
        this.setPrefWidth(Config.windowWidth);
        this.setPrefHeight(Config.windowWidth);
        int buttonWidth = (int) (Config.windowWidth*0.3);
        int buttonHeight = (int)(Config.windowHeight*0.1);
        Tools.setBack(this, "backgrounds/welcomeScreen.gif");

//      Setup gui elemets
        Text titleText = new Text("PJESKIEM I \n \t MIECZEM \n");
        titleText.setFont(Font.font("Z003",50));
        ImageButton startButton = new ImageButton("Nowa gra", buttonWidth, buttonHeight, "buttons/button6.gif");
        ImageButton loadButton = new ImageButton("Wczytaj grę", buttonWidth, buttonHeight, "buttons/button6.gif");
        ImageButton leaderboardButton = new ImageButton("Hala sławy", buttonWidth, buttonHeight, "buttons/button6.gif");
        ImageButton exitButton = new ImageButton("Wyjdź :((", buttonWidth, buttonHeight, "buttons/button6.gif");

//      Setup buttons' events
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

//      Add the gui elements to a container
        VBox container = new VBox(titleText, startButton, loadButton, leaderboardButton, exitButton);
        container.setAlignment(Pos.CENTER);
        this.getChildren().add(container);
        this.setAlignment(Pos.CENTER);
    }

//  Function for reloading player form a file
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
}
