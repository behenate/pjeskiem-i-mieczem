package com.pjeskiem_i_mieczem;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Player {
    protected String name;
    protected String className;
    protected Statistic hp;
    protected Statistic endurance;
    protected Statistic strength;
    protected Statistic dexterity;
    protected Statistic intelligence;
    protected Statistic luck;
    protected String imagePath;
    protected Image classImage;
    protected ImageView classImageView;
    public Player(String className, Integer endurance, Integer strength, Integer dexterity,
                  Integer intelligence, Integer luck, String imagePath){
        this.className = className;
        this.endurance = new Statistic("Wytrzymałość",endurance);
        this.strength = new Statistic("Siła",strength);
        this.dexterity = new Statistic("Zręczność", dexterity);
        this.intelligence = new Statistic("Intencja", intelligence);
        this.luck = new Statistic("Szczęście", luck);
        this.imagePath = imagePath;
        this.hp = new Statistic("Hp", this.endurance.getValue()*10 );
        setImagePath(imagePath);
    }
    public void setImagePath(String imagePath){
        this.classImage = new Image(imagePath);
        this.classImageView = new ImageView(this.classImage);
    }
    public void setName(String name){
        this.name = name;
    }
    public Node getStatsView(){
        return new VBox(
                hp.getLabel(),
                strength.getLabel(),
                dexterity.getLabel(),
                endurance.getLabel(),
                luck.getLabel()
        );
    }
}
