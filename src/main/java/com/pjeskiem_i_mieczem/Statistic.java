package com.pjeskiem_i_mieczem;


import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class Statistic{
    private String name;
    private double value;
    public Statistic(String name, double value){
        this.name = name;
        this.value = value;
    }
    public double getValue(){
        return value;
    }

    public String getName(){return name;}

    public void setValue(double value){
        this.value = value;
    }

    public HBox getLabel(){
        Label nameLabel = new Label(this.name+":");
        nameLabel.setPrefWidth(Config.windowWidth*0.15);
        Label valueLabel = new Label(""+ (int) this.value);
        nameLabel.setFont(Font.font(16));
        valueLabel.setFont(Font.font(16));
        return new HBox(nameLabel, valueLabel);
    }
}
