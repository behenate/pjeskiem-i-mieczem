package com.pjeskiem_i_mieczem;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

// Class showing how much of some thing is left
public class StatBar extends HBox {
    StackPane bar = new StackPane();
    private int width;
    double maxValue;
    private Label textLabel = new Label();
    private boolean showText;
//    Bar without text

//    Bar with progress text
    public StatBar(String colour, Boolean showText, int width, int height, double maxValue, double currentValue){
        this.width = width;
        this.showText = showText;
        bar.setMinHeight(height);
        bar.setStyle("-fx-background-color:" + colour);
        textLabel.setAlignment(Pos.CENTER);
        bar.getChildren().addAll(textLabel);

        this.maxValue=maxValue;
        updateBar(currentValue);
        this.setMinWidth(width);
        this.getChildren().add(bar);
    }
    public void updateBar(double currentValue){
        bar.setPrefWidth(Math.min(1, currentValue/maxValue)*width);
        if (showText)
            textLabel.setText(Math.round(currentValue) + "/"+ Math.round(maxValue));
    }
}
