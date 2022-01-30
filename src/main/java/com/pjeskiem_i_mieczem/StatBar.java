package com.pjeskiem_i_mieczem;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import javafx.scene.layout.StackPane;



// Class showing how much of some thing is left
public class StatBar extends HBox{
    StackPane bar = new StackPane();
    private final int width;
    double maxValue;
    private final Label textLabel = new Label();
    private final boolean showText;
//    Bar without background
    public StatBar(String colour, Boolean showText, int width, int height, double maxValue, double currentValue) {
        this(colour, showText,width, height,maxValue, currentValue,"FFFFFF00");

    }

//    Bar with progress text
    public StatBar(String colour, Boolean showText, int width, int height, double maxValue, double currentValue, String backgroundColour){
        this.width = width;
        this.showText = showText;
        bar.setMinHeight(height);
        bar.setMaxHeight(height);
        bar.setStyle("-fx-background-color:" + colour );
        textLabel.setAlignment(Pos.CENTER);
        bar.getChildren().addAll(textLabel);

        this.maxValue=maxValue;
        updateBar(currentValue);
        this.setStyle("-fx-background-color: " + backgroundColour);
        this.setMinWidth(width);
        this.getChildren().add(bar);
    }
    public void updateBar(double currentValue){
        bar.setPrefWidth(Math.min(1, currentValue/maxValue)*width);
        if (showText)
            textLabel.setText(Math.round(currentValue) + "/"+ Math.round(maxValue));
    }
}
