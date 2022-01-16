package com.pjeskiem_i_mieczem;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player {
    protected String playerName;
    protected String className;
    protected Integer hp;
    protected Integer endurance;
    protected Integer strength;
    protected Integer dexterity;
    protected Integer intelligence;
    protected Integer luck;
    protected String imagePath;
    protected Image classImage;
    protected ImageView classImageView;
    public Player(String className, Integer endurance, Integer strength, Integer dexterity,
                  Integer intelligence, Integer luck, String imagePath){
        this.className = className;
        this.endurance = endurance;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.luck = luck;
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
