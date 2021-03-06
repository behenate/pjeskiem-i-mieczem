package com.pjeskiem_i_mieczem;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.io.*;

//  General class for a character (also enemy)
public abstract class Player implements Serializable {
    protected String name;
    protected String className;
    protected Statistic maxHp;
    protected Statistic hp;
    protected int gold;
    protected Statistic endurance;
    protected Statistic strength;
    protected Statistic dexterity;
    protected Statistic intelligence;
    protected Statistic luck;
    protected Statistic exp;
    protected double expModifier;
    protected int level;
    protected String imagePath;
    protected Image idleImage;
    protected ImageView imageView;
    protected int skillPoints;
    protected int expToNextLevel;
    private Image attackingImage;

    public Player(String className, Integer endurance, Integer strength, Integer dexterity,
                  Integer intelligence, Integer luck, String imagePath) {
        this.className = className;
//        Sets up the common statistics
        this.endurance = new Statistic("Wytrzymałość", endurance);
        this.strength = new Statistic("Siła", strength);
        this.dexterity = new Statistic("Zręczność", dexterity);
        this.intelligence = new Statistic("Inteligencja", intelligence);
        this.luck = new Statistic("Szczęście", luck);

//        Default values of exp/gold etc
        this.expModifier = 1.2;
        this.expToNextLevel = (int) (this.expModifier * 100);
        this.imagePath = imagePath;
        this.gold = 500;
        this.level = 1;
        this.exp = new Statistic("Exp", 100);
        this.skillPoints = 0;
        setImagePath(imagePath);
    }

    //    Loading assumes that attack animation has the same name and path as the idle animation, but has _attacking added to name
    public void setAttackImage(String idleImagePath) {
        String[] parts = idleImagePath.split("\\.");
        //System.out.println(parts[0] + "_attacking" + parts[1]);
        this.attackingImage = new Image(parts[0] + "_attacking." + parts[1]);
    }

//    (re)sets the image paths for animations
    public void setImagePath(String imagePath) {
        this.idleImage = new Image(imagePath);
        this.imageView = new ImageView(this.idleImage);
        setAttackImage(imagePath);
    }

    public void setName(String name) {
        this.name = name;
    }

//    Creates a simple summary view of player stats
    public Node getStatsView() {
        return new VBox(
                maxHp.getLabel(),
                strength.getLabel(),
                intelligence.getLabel(),
                dexterity.getLabel(),
                endurance.getLabel(),
                luck.getLabel()
        );
    }

//    Creates a player card with all fight-relevant basic stats and image
    public VBox getPlayerCard(int width) {
        Label playerNameLabel = new Label(name);

        playerNameLabel.setStyle("-fx-font-size: 50; -fx-text-fill: #070000; -fx-background-color: rgba(255,255,255, 0.9)");
        StatBar healthBar = new StatBar("#f7573e", true, width - 10 ,
                (int) (Config.windowHeight * 0.05), (float) maxHp.getValue(), (float) hp.getValue(),"#f2afa5");
        Node statCard = getStatsView();
        statCard.setTranslateX(width*0.1);
        imageView.setFitWidth(width * 0.7);
        imageView.setFitHeight(width * 0.7);
        VBox playerCard = new VBox(playerNameLabel, imageView, healthBar, statCard);
        playerCard.setAlignment(Pos.CENTER);
        return playerCard;
    }

    public int getGold() {
        return this.gold;
    }

    public void addGold(double value) {
        this.gold += value;
    }

//    Every class should have a takeDamage method
    abstract public void takeDamage(Player other);

//    Checks level and increases it when levelup requirements are met
    public void checkLevelUp() {
        while (this.exp.getValue() >= this.expToNextLevel) {
            this.level += 1;
            this.skillPoints += 4;
            this.expModifier = this.expModifier + Config.expModifierOffset;
            this.expToNextLevel = (int) (this.expToNextLevel + this.expToNextLevel * (this.expModifier-1) * Config.expCurveModifier);
        }
    }

    public abstract void recalculateHp();

//    Recalculates the character Hp based on endurance stat and passed hpMultiplier (5 for mage, 7 for hunter etd.)
    public void recalculateHp(int hpMultiplier) {
        double oldHp = this.maxHp.getValue();
        double newHp = this.endurance.getValue() * hpMultiplier;
        this.maxHp.setValue(newHp);
        this.hp.setValue(this.hp.getValue() + newHp - oldHp);
    }


//    Function that writes the character progress to a file
    public void saveProgress() {
        try {
            FileOutputStream fileOut = new FileOutputStream("src/main/resources/leaderboard/save");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            this.imageView = null;
            this.idleImage = null;
            this.attackingImage = null;
            objectOut.writeObject(this);
            objectOut.close();
            this.setImages();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

//    Sets images for default classes
    public void setImages(){
        if(this instanceof Mage){
            setImagePath("characters/mage.gif");
        }else if(this instanceof Warrior){
            setImagePath("characters/warrior.gif");
        }else if(this instanceof Hunter){
            setImagePath("characters/hunter.gif");
        }
    }

//    Changes the animation gif to attack animation
    public void playAttackAnimation(int duration) {
        Thread animationThread = new Thread(() -> {
            try {
                Platform.runLater(() -> this.imageView.setImage(this.attackingImage));
                Thread.sleep(duration);
                Platform.runLater(() -> this.imageView.setImage(this.idleImage));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        animationThread.start();
    }

    public double getDamage() {
        return 1;
    }
//    Flips the image view (useful when the Player instance is an enemy and in dodge animation)
    public void flipImageView(){
        this.imageView.setScaleX(this.imageView.getScaleX()*(-1));
    }
}
