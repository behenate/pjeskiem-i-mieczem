package com.pjeskiem_i_mieczem;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class LeaderboardGui extends VBox {
    public LeaderboardGui(Application app){
//      Setup sizes
        this.setPrefWidth(Config.windowWidth);
        this.setPrefHeight(Config.windowWidth);
        int buttonWidth = (int) (Config.windowWidth*0.3);
        int buttonHeight = (int)(Config.windowHeight*0.1);
        Tools.setBack(this, "backgrounds/leaderboard.gif");

//      Setup gui elemets
        Text titleText = new Text("Ranking \n");
        titleText.setFont(Font.font("Z003",50));
        HBox table = this.uploadTable();
        ImageButton menuButton = new ImageButton("Wróć do menu", buttonWidth, buttonHeight, "buttons/button6.gif");

//      Setup button's action
        menuButton.setOnAction((event)->{
            app.goToTheStart();
        });

        VBox container = new VBox(titleText, table, menuButton);
        container.setSpacing(10);
        container.setAlignment(Pos.CENTER);
        this.getChildren().add(container);
        this.setAlignment(Pos.CENTER);
    }

//  Uploading table from a file with all saved scores
    public HBox uploadTable(){
//      Setup gui elemets
        VBox scores = new VBox();
        scores.setAlignment(Pos.TOP_RIGHT);
        VBox names = new VBox();
        names.setAlignment(Pos.TOP_LEFT);

//      Reeading from file
        ArrayList<String[]> namesScores = new ArrayList<>();
        try {
            String line;
            String[] array = null;
            Scanner scanner = new Scanner(new File("src/main/resources/leaderboard/scoreData.txt"));
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                array = line.split(";");
                namesScores.add(array);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//      Sorting scores ascending
        namesScores.sort(Comparator.comparingDouble(a -> Double.parseDouble(a[1])));
        Collections.reverse(namesScores);

//      Placing and formatting scores on the leaderboard
        for (String[] elem:namesScores) {
            Label name = new Label(elem[0]);
            Label score = new Label(elem[1]);
            names.getChildren().add(name);
            scores.getChildren().add(score);
        }

        HBox table = new HBox(names, scores);
        table.setSpacing(15);
        table.setAlignment(Pos.TOP_CENTER);
        table.setMinHeight(400);
        table.setMaxHeight(500);
        return table;
    }


//  Function for saving player's score when dead
    public void saveToLeaderboard() {
        try{
            FileWriter file = new FileWriter("src/main/resources/leaderboard/scoreData.txt", true);
            BufferedWriter save = new BufferedWriter(file);
            save.write(Application.player.name+";"+Application.player.gold);
            save.newLine();
            save.close();
        } catch (IOException e) {
        e.printStackTrace();
        }
    }
}
