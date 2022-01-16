package com.pjeskiem_i_mieczem;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player {
    protected String playerName;
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
        this.endurance = new Statistic("Hp",endurance);
        this.strength = new Statistic("Siła",strength);
        this.dexterity = new Statistic("Zręczność", dexterity);
        this.intelligence = new Statistic("Intencja", intelligence);
        this.luck = new Statistic("Szczęście", luck);
        this.imagePath = imagePath;
        setImagePath(imagePath);
    }
    public void setImagePath(String imagePath){
        this.classImage = new Image(imagePath);
        this.classImageView = new ImageView(this.classImage);
    }
    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }
}
