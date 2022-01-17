package com.pjeskiem_i_mieczem;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Player {
    protected String name;
    protected String className;
    protected Statistic hp;
    protected int gold;
    protected Statistic endurance;
    protected Statistic strength;
    protected Statistic dexterity;
    protected Statistic intelligence;
    protected Statistic luck;
    protected String imagePath;
    protected Image image;
    protected ImageView imageView;
    public Player(String className, Integer endurance, Integer strength, Integer dexterity,
                  Integer intelligence, Integer luck, String imagePath){
        this.className = className;
        this.endurance = new Statistic("Wytrzymałość",endurance);
        this.strength = new Statistic("Siła",strength);
        this.dexterity = new Statistic("Zręczność", dexterity);
        this.intelligence = new Statistic("Inteligencja", intelligence);
        this.luck = new Statistic("Szczęście", luck);
        this.imagePath = imagePath;
<<<<<<< HEAD
        this.gold = 500;
        this.hp = new Statistic("Hp", this.endurance.getValue()*10 );
=======
        this.hp = new Statistic("Temp HP", this.endurance.getValue()*1);
>>>>>>> 8ea06017c9c4a06aa059a7e6d0c95e5d927d9053
        setImagePath(imagePath);
    }
//  minimalistic constructor to test sth
    public Player(String name, int gold){
        this.name = name;
        this.gold = gold;
    }
    public void setImagePath(String imagePath){
        this.image = new Image(imagePath);
        this.imageView = new ImageView(this.image);
    }
    public void setName(String name){
        this.name = name;
    }
    public Node getStatsView(){
        return new VBox(
                hp.getLabel(),
                strength.getLabel(),
                intelligence.getLabel(),
                dexterity.getLabel(),
                endurance.getLabel(),
                luck.getLabel()
        );
    }
    public VBox getPlayerCard(int width){
        Label playerNameLabel = new Label(name);
        playerNameLabel.setFont(Font.font(29));
        StatBar healthBar = new StatBar("#f7573e", true,width-20, (int)(Config.windowHeight*0.05), hp.getValue(), 5);
        Node statCard = getStatsView();
        imageView.setFitWidth(width*0.7);
        imageView.setFitHeight(width*0.7);
        VBox playerCard = new VBox(playerNameLabel, imageView, healthBar, statCard);
        playerCard.setAlignment(Pos.CENTER);
        return playerCard;
    }
}
