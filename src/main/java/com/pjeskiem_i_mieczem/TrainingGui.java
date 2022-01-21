package com.pjeskiem_i_mieczem;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class TrainingGui extends VBox {
    private final int plusButtonWidth = (int)(Config.windowWidth*0.024);
    private final int plusButtonHeight = (int)(Config.windowHeight*0.04);
    private int availablePoints;
    private final ImageButton cityButton;
    private boolean flag = false;
    private Label availablePointsLabel;

    public TrainingGui(int pointsValue, Application app){
        this.availablePoints = pointsValue;
//      Setup sizes
        this.setPrefWidth(Config.windowWidth);
        this.setPrefHeight(Config.windowWidth);
        int imageSize = (int)(Config.windowHeight*0.3);

//      Setup gui elements
        Text titleText = new Text("Ulepsz swoją postać " + Application.player.name);
        titleText.setFont(Font.font("Z003",30));

        ImageView playerImageView = Application.player.imageView;
        playerImageView.setFitWidth(imageSize);
        playerImageView.setFitHeight(imageSize);

        int cityButtonWidth = (int) (Config.windowWidth * 0.2);
        int cityButtonHeight = (int) (Config.windowWidth * 0.05);
        cityButton = new ImageButton("Wróć do miasta", cityButtonWidth, cityButtonHeight, "buttons/button_0.png" );
        availablePointsLabel = new Label("Masz do wykorzystania: "+this.availablePoints+" punktów");
        availablePointsLabel.setFont(Font.font("Z003",25));

        VBox allStats = allStatsBox();

//      Create a container and add everything
        VBox container = new VBox(20);
        container.setAlignment(Pos.CENTER);
        container.getChildren().addAll(titleText, playerImageView, availablePointsLabel, allStats);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(container);


//      Setup action of going to the city (without changing window)
        cityButton.setOnAction((event)->{
            app.goToTheCity();
        });
    }

    private HBox singleStatsBox(Statistic stat){
        HBox box = new HBox(10);
//      Setup labels
        Label nameLabel = new Label(stat.getName());
        Label valueLabel = new Label((int)stat.getValue()+"");
        nameLabel.setMinWidth(150);
        valueLabel.setMinWidth(40);
        nameLabel.setFont(Font.font(16));
        valueLabel.setFont(Font.font(16));

//      Setup button and its action
        ImageButton improveButton = new ImageButton("", plusButtonWidth, plusButtonHeight, "buttons/plus_icon.png" );
        improveButton.setOnAction((event)->{
            if(this.availablePoints > 0) {
                stat.setValue(stat.getValue()+1);
                valueLabel.setText((int)stat.getValue() + "");
                this.availablePoints--;
                this.availablePointsLabel.setText("Masz do wykorzystania: " + this.availablePoints + " punktów");
                if(this.availablePoints == 0 && !this.flag){
                    this.flag = true;
                    this.getChildren().add(cityButton);
                }
            }
        });
//      Add everything to a container
        box.getChildren().addAll(nameLabel, valueLabel, improveButton);
        box.setAlignment(Pos.CENTER);
        return box;
    }

    private VBox allStatsBox(){
        VBox box = new VBox(15);
        HBox statHp = singleStatsBox(Application.player.hp);
        HBox statStrength = singleStatsBox(Application.player.strength);
        HBox statIntelligence = singleStatsBox(Application.player.intelligence);
        HBox statDexterity = singleStatsBox(Application.player.dexterity);
        HBox statEndurance = singleStatsBox(Application.player.endurance);
        HBox statLuck = singleStatsBox(Application.player.luck);

        box.getChildren().addAll(statHp, statStrength, statIntelligence,
                statDexterity, statEndurance, statLuck);
        return box;
    }


}
