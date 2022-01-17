package com.pjeskiem_i_mieczem;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CreateCharacterGui extends VBox {

    private Image classImage;
    private ImageView classImageView = new ImageView();
    private final VBox statsBox = new VBox();
    private final Label classNameLabel = new Label();
    private int current_class = 0;



    public CreateCharacterGui(){
        Player hunterPreset = new Hunter();
        Player warriorPreset = new Warrior();
        Player magePreset = new Mage();
        Player[] classPresets = {warriorPreset, hunterPreset, magePreset};

        //        Setup sizes
        this.setPrefWidth(Config.windowWidth);
        this.setPrefHeight(Config.windowWidth);
        int arrowButtonSize = (int) (Config.windowWidth*0.03);
        int navigationButtonWidth = (int)(Config.windowWidth*0.1);
        int navigationButtonHeight = (int)(Config.windowHeight*0.05);
        int classImageSize = (int)(Config.windowHeight*0.3);
        int prefWidth = (int)(Config.windowWidth*0.2);

//        Setup gui elemets
        Text titleText = new Text("STWÓRZ \n \t PJESA");
        titleText.setFont(Font.font("Z003",30));

        Label characterNameLabel = new Label("Wybierz imje pjesa: ");
        TextField characterNameField = new TextField();

        ImageButton prevClassButton = new ImageButton("", arrowButtonSize, arrowButtonSize, "buttons/arrow_left.png");
        ImageButton nextClassButton = new ImageButton("", arrowButtonSize, arrowButtonSize, "buttons/arrow_right.png");
        ImageButton continueButton = new ImageButton("Graj Pjesem",navigationButtonWidth,navigationButtonHeight, "buttons/button_0.png" );

//        Apply sizes
        classImageView.setFitWidth(classImageSize);
        classImageView.setFitHeight(classImageSize);
        characterNameField.setMaxWidth(prefWidth);
        classNameLabel.setPrefWidth(prefWidth - 2*arrowButtonSize);
        characterNameField.setFont(Font.font(Config.windowWidth*0.012));

//        Setup events
        prevClassButton.setOnAction((event)->{
            System.out.println("Previous Class!");
            current_class = (current_class - 1) % classPresets.length;
            setCharacter(classPresets[Math.abs(current_class)]);
        });
        nextClassButton.setOnAction((event)->{
            System.out.println("Next Class!");
            current_class = (current_class + 1) % classPresets.length;
            setCharacter(classPresets[Math.abs(current_class)]);
        });
        continueButton.setOnAction((event)->{
            System.out.println("Continue");
        });

//        Load the first character
        setCharacter(classPresets[0]);

//        Add the gui elements to containers and base class
        HBox buttonsAndClassnameContainer = new HBox(prevClassButton, classNameLabel, nextClassButton);
        statsBox.getChildren().addAll(buttonsAndClassnameContainer);
        VBox container = new VBox(titleText, characterNameLabel, characterNameField, buttonsAndClassnameContainer, classImageView,statsBox, continueButton);

        //      Setup alignments
        container.setAlignment(Pos.CENTER);
        statsBox.setAlignment(Pos.CENTER);
        statsBox.setMaxWidth(prefWidth);
        buttonsAndClassnameContainer.setAlignment(Pos.CENTER);
        characterNameField.setAlignment(Pos.CENTER);
        classNameLabel.setAlignment(Pos.CENTER);
        this.setAlignment(Pos.CENTER);

        this.getChildren().add(container);
    }
    private void setCharacter(Player character){
        classImageView.setImage(character.image);
        classNameLabel.setText(character.className);
        statsBox.getChildren().clear();
        statsBox.getChildren().add(character.getStatsView());
    }
}
