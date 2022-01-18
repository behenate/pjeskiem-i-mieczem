package com.pjeskiem_i_mieczem;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.*;
import java.util.Scanner;

public class LeaderboardGui extends VBox {
    public LeaderboardGui(){
//      Setup sizes
        this.setPrefWidth(Config.windowWidth);
        this.setPrefHeight(Config.windowWidth);
        int buttonWidth = (int) (Config.windowWidth*0.3);
        int buttonHeight = (int)(Config.windowHeight*0.1);


//      Setup gui elemets
        Text titleText = new Text("Ranking");
        titleText.setFont(Font.font("Z003",50));

        HBox table = this.uploadTable();

        ImageButton menuButton = new ImageButton("Wróć do menu", buttonWidth, buttonHeight, "buttons/button_0.png");

//      Setup events
        menuButton.setOnAction((event)->{
            System.out.println("Go go to menu!");
        });

        VBox container = new VBox(titleText, table, menuButton);
        container.setSpacing(10);
        container.setAlignment(Pos.CENTER);
        this.getChildren().add(container);
        this.setAlignment(Pos.CENTER);
    }


    public HBox uploadTable(){
        VBox scores = new VBox();
        scores.setAlignment(Pos.TOP_RIGHT);
        VBox names = new VBox();
        names.setAlignment(Pos.TOP_LEFT);
        try {
            String line;
            String[] array;
            Scanner scanner = new Scanner(new File("src/main/resources/leaderboard/scoreData.txt"));
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                array = line.split(";");
                Label name = new Label(array[0]);
                name.setFont(Font.font("Z003",20));
                Label score = new Label(array[1]);
                score.setFont(Font.font("Z003",20));
                names.getChildren().add(name);
                scores.getChildren().add(score);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        HBox table = new HBox(names, scores);
        table.setSpacing(15);
        table.setAlignment(Pos.TOP_CENTER);
        table.setMinHeight(400);
        table.setMaxHeight(500);
        return table;
    }


    public void saveToLeaderboard(Player player) {
        try{
            FileWriter file = new FileWriter("src/main/resources/leaderboard/scoreData.txt", true);
            BufferedWriter save = new BufferedWriter(file);
            save.write(player.name+";"+player.gold);
            save.close();
        } catch (IOException e) {
        e.printStackTrace();
    }
    }


}
