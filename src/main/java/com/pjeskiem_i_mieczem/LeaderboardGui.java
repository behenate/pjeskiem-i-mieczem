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

//      Setup table
        TableColumn<String, String> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<String, String> column2 = new TableColumn<>("Score");
        column2.setCellValueFactory(new PropertyValueFactory<>("score"));

//      Set the column resize policy to constrained resize policy
        HBox table = this.uploadTable();
        table.setMaxWidth(200);
        table.setAlignment(Pos.CENTER);

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
        scores.setAlignment(Pos.CENTER_RIGHT);
        VBox names = new VBox();
        int dataQuantity = 12;
        for(int i=1; i<dataQuantity; i++){
            Label name = new Label("gracz "+i);
            name.setFont(Font.font("Z003",20));
            names.getChildren().add(name);

            Label score = new Label(""+i+"pkt");
            score.setFont(Font.font("Z003",20));
            scores.getChildren().add(score);
        }
        HBox table = new HBox(names, scores);
        table.setSpacing(15);
        return table;
    }


    //public void addnewData(Player player){
    //    tableView.getItems().add();
    //}


}
