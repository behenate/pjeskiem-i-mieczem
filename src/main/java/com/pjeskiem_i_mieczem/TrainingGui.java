package com.pjeskiem_i_mieczem;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.scene.robot.Robot;



public class TrainingGui extends VBox {
    private final int plusButtonWidth = (int)(Config.windowWidth*0.024);
    private final int plusButtonHeight = (int)(Config.windowHeight*0.04);
    private final Label availablePointsLabel;
    private final Application app;
    private final Robot robot = new Robot();
    public TrainingGui(Application app){
        this.app = app;
//      Setup sizes
        this.setPrefWidth(Config.windowWidth);
        this.setPrefHeight(Config.windowWidth);
        int imageSize = (int)(Config.windowHeight*0.3);
        this.setBack();
//      Setup gui elements
        Text titleText = new Text("Ulepsz swoją postać " + Application.player.name);

        ImageView playerImageView = Application.player.imageView;
        playerImageView.setFitWidth(imageSize);
        playerImageView.setFitHeight(imageSize);

        int cityButtonWidth = (int) (Config.windowWidth * 0.2);
        int cityButtonHeight = (int) (Config.windowWidth * 0.05);
        ImageButton cityButton = new ImageButton("Wróć do miasta", cityButtonWidth, cityButtonHeight, "buttons/button6.gif");
        cityButton.setTranslateY(20);
        availablePointsLabel = new Label("Masz do wykorzystania: "+Application.player.skillPoints+" punktów");
        availablePointsLabel.setTranslateY(-Config.windowHeight*0.03);
        availablePointsLabel.setFont(Font.font("Z003",25));

        VBox allStats = allStatsBox();

//      Create a container and add everything
        VBox container = new VBox(8);
        container.setAlignment(Pos.CENTER);
        container.getChildren().addAll(titleText, playerImageView, availablePointsLabel, allStats, cityButton);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(container);

//      Setup action of going to the city (without changing window)
        cityButton.setOnAction((event)->{
            Application.player.recalculateHp();
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
        Popup popup = new Popup();
        popup.getContent().addAll(new ImageButton(stat.getDescription(), stat.getDescription().length()*8, 100, "buttons/infoPopupLong.gif"));
        popup.getContent().get(0).setStyle("-fx-font-size: 15");
        Runnable showPopup = () -> {
            popup.setX(robot.getMouseX()+20);
            popup.setY(robot.getMouseY()-50);
            popup.show(app.stage);
        };
//      Setup button and its action
        ImageButton improveButton = new ImageButton("", plusButtonWidth, plusButtonHeight, "buttons/plus_icon.gif", showPopup, popup::hide );
        improveButton.setOnAction((event)->{
            if(Application.player.skillPoints > 0) {
                stat.setValue(stat.getValue() + 1);
                valueLabel.setText((int) stat.getValue() + "");
                Application.player.skillPoints--;
                this.availablePointsLabel.setText("Masz do wykorzystania: " + Application.player.skillPoints + " punktów");
            }
        });
//      Add everything to a container
        box.getChildren().addAll(nameLabel, valueLabel, improveButton);
        box.setAlignment(Pos.CENTER);
        return box;
    }

    private VBox allStatsBox(){
        VBox box = new VBox(2);
        HBox statStrength = singleStatsBox(Application.player.strength);
        HBox statIntelligence = singleStatsBox(Application.player.intelligence);
        HBox statDexterity = singleStatsBox(Application.player.dexterity);
        HBox statEndurance = singleStatsBox(Application.player.endurance);
        HBox statLuck = singleStatsBox(Application.player.luck);

        box.getChildren().addAll(statStrength, statIntelligence,
                statDexterity, statEndurance, statLuck);
        return box;
    }

    private void setBack(){
        BackgroundSize backgroundSize = new BackgroundSize(900,
                700,
                true,
                true,
                true,
                false);
        BackgroundImage image = new BackgroundImage(new Image("backgrounds/training.gif"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize);
        this.setBackground(new Background(image));
    }
}
