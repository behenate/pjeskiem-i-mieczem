package com.pjeskiem_i_mieczem;

import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LeaderboardGui extends VBox {
    final private TableView<Player> tableView;
    public LeaderboardGui(){
//      Setup sizes
        this.setPrefWidth(Config.windowWidth);
        this.setPrefHeight(Config.windowWidth);
        int buttonWidth = (int) (Config.windowWidth*0.3);
        int buttonHeight = (int)(Config.windowHeight*0.1);
        this.tableView = new TableView<>();
        tableView.setEditable(true);


//      Setup gui elemets
        Text titleText = new Text("Ranking");
        titleText.setFont(Font.font("Z003",50));

//      Setup table
        TableColumn<Player, String> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Player, String> column2 = new TableColumn<>("Score");
        column2.setCellValueFactory(new PropertyValueFactory<>("score"));

// Set the column resize policy to constrained resize policy
        tableView.setMaxSize(500, 500);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.getColumns().addAll(column1, column2);


        ImageButton menuButton = new ImageButton("Wróć do menu", buttonWidth, buttonHeight, "buttons/button_0.png");

//      Setup events
        menuButton.setOnAction((event)->{
            System.out.println("Go go to menu!");
        });

        VBox container = new VBox(titleText, tableView, menuButton);
        container.setSpacing(10);
        container.setAlignment(Pos.CENTER);
        this.getChildren().add(container);
        this.setAlignment(Pos.CENTER);
    }
}
