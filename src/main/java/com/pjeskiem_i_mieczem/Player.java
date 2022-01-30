package com.pjeskiem_i_mieczem;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.*;

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
        this.endurance = new Statistic("Wytrzymałość", endurance);
        this.strength = new Statistic("Siła", strength);
        this.dexterity = new Statistic("Zręczność", dexterity);
        this.intelligence = new Statistic("Inteligencja", intelligence);
        this.luck = new Statistic("Szczęście", luck);
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
        System.out.println(parts[0] + "_attacking" + parts[1]);
        this.attackingImage = new Image(parts[0] + "_attacking." + parts[1]);
    }

    public Image getAttackImage() {
        return this.attackingImage;
    }

    public void setImagePath(String imagePath) {
        this.idleImage = new Image(imagePath);
        this.imageView = new ImageView(this.idleImage);
        setAttackImage(imagePath);
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public VBox getPlayerCard(int width) {
        Label playerNameLabel = new Label(name);
        playerNameLabel.setFont(Font.font(29));
        StatBar healthBar = new StatBar("#f7573e", true, width - 20, (int) (Config.windowHeight * 0.05), (float) maxHp.getValue(), (float) hp.getValue());
        Node statCard = getStatsView();
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

    public void takeDamage(Player other, float multiplier) {
        this.hp.setValue(this.hp.getValue() - 1);
    }

    public void checkLevelUp() {
        if (this.exp.getValue() >= this.expToNextLevel) {
            this.level += 1;
            this.skillPoints += 4;
            this.expModifier = this.expModifier + 0.1;
            this.expToNextLevel *= this.expModifier;
        }
    }

    public abstract void recalculateHp();

    public void recalculateHp(int hpMultiplier) {
        double oldHp = this.maxHp.getValue();
        double newHp = this.endurance.getValue() * hpMultiplier;
        this.maxHp.setValue(newHp);
        this.hp.setValue(this.hp.getValue() + newHp - oldHp);
    }


    public void saveProgress() {
        try {
            FileOutputStream fileOut = new FileOutputStream("src/main/resources/leaderboard/save");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            objectOut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

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
    public void flipImageView(){
        this.imageView.setScaleX(this.imageView.getScaleX()*(-1));
    }
}
