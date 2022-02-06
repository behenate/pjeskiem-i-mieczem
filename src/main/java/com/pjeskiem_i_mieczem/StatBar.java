package com.pjeskiem_i_mieczem;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.layout.HBox;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;


// Class showing how much of some thing is left
public class StatBar extends HBox{
    StackPane bar = new StackPane();
    private final int width;
    private final int height;
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
        this.height = height;
        this.showText = showText;
        bar.setMinHeight(height);
        bar.setMaxHeight(height);
        bar.setStyle("-fx-background-color:" + colour );
        textLabel.setAlignment(Pos.CENTER);
        textLabel.setStyle("-fx-font-size: " + (int)(height*0.9));
        bar.getChildren().addAll(textLabel);
        textLabel.textOverrunProperty().setValue(OverrunStyle.CLIP);
        bar.setAlignment(Pos.CENTER);
        this.setAlignment(Pos.CENTER);
        this.maxValue=maxValue;
        updateBar(currentValue);
        this.setStyle("-fx-background-color: " + backgroundColour);
        this.setMinWidth(this.width);

        this.setMaxWidth(this.width);
        this.getChildren().add(bar);
    }
//    Updates bar width based on current parameter value
    public void updateBar(double currentValue){
        bar.setPrefWidth(Math.min(1, currentValue/maxValue)* this.width);
        if (showText){
            textLabel.setText(Math.round(currentValue) + "/"+ Math.round(maxValue));
            textLabel.setStyle("-fx-font-size: " + (int)(height*0.9)* Math.min(1, (currentValue*2)/maxValue));
        }
    }
}
