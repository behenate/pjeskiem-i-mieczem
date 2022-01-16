package com.pjeskiem_i_mieczem;

import javafx.scene.control.Label;

public class Statistic{
    private String name;
    private int value;
    public Statistic(String name, int value){
        this.name = name;
        this.value = value;
    }
    public int value(){
        return value;
    }
    public void setValue(int value){
        this.value = value;
    }
    public Label getLabel(){
        return new Label(this.name+":\t"+this.value);
    }
}
