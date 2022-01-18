package com.pjeskiem_i_mieczem;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.concurrent.atomic.AtomicInteger;

public class TrainingGui extends VBox {
    private final Player player;
    private final int plusButtonWidth = (int)(Config.windowWidth*0.024);
    private final int plusButtonHeight = (int)(Config.windowHeight*0.04);
    private int availablePoints;
    private String[] stats = new String[6];
    private final ImageButton cityButton;
    private boolean flag = false;
    private Label availablePointsLabel;

    public TrainingGui(Player player, int pointsValue){
        this.player = player;
        this.availablePoints = pointsValue;
//      Setup sizes
        this.setPrefWidth(Config.windowWidth);
        this.setPrefHeight(Config.windowWidth);
        int imageSize = (int)(Config.windowHeight*0.3);

//      Setup gui elements
        Text titleText = new Text("Ulepsz swoją postać "+player.name);
        titleText.setFont(Font.font("Z003",30));

        ImageView playerImageView = player.imageView;
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
            if(stats[0] != null) player.hp.setValue(Integer.parseInt(stats[0]));
            if(stats[1] != null) player.strength.setValue(Integer.parseInt(stats[1]));
            if(stats[2] != null) player.intelligence.setValue(Integer.parseInt(stats[2]));
            if(stats[3] != null) player.dexterity.setValue(Integer.parseInt(stats[3]));
            if(stats[4] != null) player.endurance.setValue(Integer.parseInt(stats[4]));
            if(stats[5] != null) player.luck.setValue(Integer.parseInt(stats[5]));
        });
    }

    private HBox singleStatsBox(Statistic stat, int index){
        HBox box = new HBox(10);
//      Setup labels
        Label nameLabel = new Label(stat.getName());
        Label valueLabel = new Label(stat.getValue()+"");
        nameLabel.setMinWidth(150);
        valueLabel.setMinWidth(40);
        nameLabel.setFont(Font.font(16));
        valueLabel.setFont(Font.font(16));

//      Setup button and its action
        ImageButton improveButton = new ImageButton("", plusButtonWidth, plusButtonHeight, "buttons/plus_icon.png" );
        AtomicInteger newValue = new AtomicInteger(stat.getValue());
        improveButton.setOnAction((event)->{
            if(this.availablePoints > 0) {
                newValue.addAndGet(1);
                valueLabel.setText(newValue + "");
                stats[index] = valueLabel.getText();
                this.availablePoints--;
                this.availablePointsLabel.setText("Masz do wykorzystania: "+this.availablePoints+" punktów");
                // muszę tę opcję dopracować jeszcze bo nie działa tak jak chcę
            }else if(this.availablePoints == 0 && !this.flag){
                this.flag = true;
                this.getChildren().add(cityButton);
            }
        });
//      Add everything to a container
        box.getChildren().addAll(nameLabel, valueLabel, improveButton);
        box.setAlignment(Pos.CENTER);
        return box;
    }

    private VBox allStatsBox(){
        VBox box = new VBox(15);
        HBox statHp = singleStatsBox(player.hp, 0);
        HBox statStrength = singleStatsBox(player.strength, 1);
        HBox statIntelligence = singleStatsBox(player.intelligence, 2);
        HBox statDexterity = singleStatsBox(player.dexterity, 3);
        HBox statEndurance = singleStatsBox(player.endurance, 4);
        HBox statLuck = singleStatsBox(player.luck, 5);

        box.getChildren().addAll(statHp, statStrength, statIntelligence,
                statDexterity, statEndurance, statLuck);
        return box;
    }


}
