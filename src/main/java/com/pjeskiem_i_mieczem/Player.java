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
    protected Statistic currentHp;
    protected int gold;
    protected Statistic endurance;
    protected Statistic strength;
    protected Statistic dexterity;
    protected Statistic intelligence;
    protected Statistic luck;
    protected Statistic exp;
    protected Statistic expModifier;
    protected int level;
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
        this.expModifier = new Statistic("ExpModifier", 1.3);
        this.imagePath = imagePath;
        this.gold = 500;
        this.level = 1;
        this.exp = new Statistic("Exp", 100);
        this.hp = new Statistic("Hp", this.endurance.getValue()*10 );
        this.currentHp = new Statistic("Temp HP", this.endurance.getValue()*10);
        setImagePath(imagePath);
    }

//  minimalistic constructor to test sth
    public Player(String name, int gold){
        this.name = name;
        this.gold = gold;
        this.hp = new Statistic("Hp", 123);
        this.endurance = new Statistic("Wytrzymałość",10);
        this.strength = new Statistic("Siła",18);
        this.dexterity = new Statistic("Zręczność", 22);
        this.intelligence = new Statistic("Inteligencja", 9);
        this.luck = new Statistic("Szczęście", 77);
        setImagePath("zloty.jpg");
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
                currentHp.getLabel(),
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
        StatBar healthBar = new StatBar("#f7573e", true,width-20, (int)(Config.windowHeight*0.05), (float) hp.getValue(), (float) currentHp.getValue());
        Node statCard = getStatsView();
        imageView.setFitWidth(width*0.7);
        imageView.setFitHeight(width*0.7);
        VBox playerCard = new VBox(playerNameLabel, imageView, healthBar, statCard);
        playerCard.setAlignment(Pos.CENTER);
        return playerCard;
    }

    public int getGold(){
        return this.gold;
    }
    public void takeDamage(Player other){
        this.currentHp.setValue(this.currentHp.getValue()-1);
    }
    public int getDamage(){
        return 1;
    }
}
