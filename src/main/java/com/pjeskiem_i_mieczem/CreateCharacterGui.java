package com.pjeskiem_i_mieczem;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.Objects;

public class CreateCharacterGui extends VBox {
    private Image classImage;
    private ImageView classImageView = new ImageView();
    private final VBox statsBox = new VBox();
    private final Label classNameLabel = new Label();
    private int current_class = 0;

    public CreateCharacterGui(Application app){
        Player hunterPreset = new Hunter();
        Player warriorPreset = new Warrior();
        Player magePreset = new Mage();
        Player[] classPresets = {warriorPreset, hunterPreset, magePreset};

        //        Setup sizes
        this.setPrefWidth(Config.windowWidth);
        this.setPrefHeight(Config.windowWidth);
        int arrowButtonSize = (int) (Config.windowWidth*0.03);
        int navigationButtonWidth = (int)(Config.windowWidth*0.15);
        int navigationButtonHeight = (int)(Config.windowHeight*0.05);
        int classImageSize = (int)(Config.windowHeight*0.3);
        int prefWidth = (int)(Config.windowWidth*0.2);

//        Setup gui elemets

        Label characterNameLabel = new Label("Wybierz imje pjesa ");
        TextField characterNameField = new TextField();
        Tools.addTextLimiter(characterNameField, 12);
        characterNameField.setText("Pjeseł");

        ImageButton prevClassButton = new ImageButton("", arrowButtonSize, arrowButtonSize, "buttons/mini_arrow_left.gif");
        ImageButton nextClassButton = new ImageButton("", arrowButtonSize, arrowButtonSize, "buttons/mini_arrow_right.gif");
        ImageButton continueButton = new ImageButton("Graj Pjesem",navigationButtonWidth,navigationButtonHeight, "buttons/button6.gif" );

//        Setup events
        prevClassButton.setOnAction((event)->{
            current_class = (current_class - 1) % classPresets.length;
            setCharacter(classPresets[Math.abs(current_class)]);
        });
        nextClassButton.setOnAction((event)->{
            current_class = (current_class + 1) % classPresets.length;
            setCharacter(classPresets[Math.abs(current_class)]);
        });
//      creating a new player
        continueButton.setOnAction((event)->{
            Player newPlayer = classPresets[Math.abs(current_class)];
            newPlayer.setName(characterNameField.getText());
            if (Objects.equals(newPlayer.name, "Bernard") || Objects.equals(newPlayer.name, "bernard")){
                newPlayer.endurance.setValue(100);
                newPlayer.luck.setValue(100);
                newPlayer.dexterity.setValue(100);
                newPlayer.strength.setValue(100);
                newPlayer.intelligence.setValue(100);
                app.activateEasterEgg();
            }
            Application.player = newPlayer;

            app.goToTheCity();
        });

//      Load the first character
        setCharacter(classPresets[0]);

//      Add the gui elements to containers and base class
        HBox buttonsAndClassnameContainer = new HBox(prevClassButton, classNameLabel, nextClassButton);
        buttonsAndClassnameContainer.setPadding(new Insets(0,Config.windowWidth*0.21,0,Config.windowWidth*0.2));
        statsBox.getChildren().addAll(buttonsAndClassnameContainer);
        VBox nameContainer = new VBox(characterNameLabel, characterNameField);
        VBox container = new VBox(nameContainer, buttonsAndClassnameContainer, classImageView,statsBox, continueButton);

//        Apply sizes
        buttonsAndClassnameContainer.setMinHeight(navigationButtonHeight+10);
        classImageView.setFitWidth(classImageSize);
        classImageView.setFitHeight(classImageSize);

        characterNameField.setMaxWidth(prefWidth);
        classNameLabel.setPrefWidth(prefWidth - 2*arrowButtonSize);
        characterNameField.setFont(Font.font(Config.windowWidth*0.012));
//      Setup alignments
        container.setAlignment(Pos.CENTER);
        statsBox.setAlignment(Pos.CENTER);
        statsBox.setMaxWidth(prefWidth);
        buttonsAndClassnameContainer.setAlignment(Pos.CENTER);
        characterNameField.setAlignment(Pos.CENTER);
        classNameLabel.setAlignment(Pos.CENTER);

        classImageView.setTranslateY(-Config.windowHeight*0.01f);
        statsBox.setTranslateY(-Config.windowHeight*0.05f);
        nameContainer.setTranslateY(-Config.windowHeight*0.018f);
        buttonsAndClassnameContainer.setTranslateY(-Config.windowHeight*0.018);

        continueButton.setMinHeight(navigationButtonHeight + 10);
        nameContainer.setAlignment(Pos.CENTER);
        this.setAlignment(Pos.CENTER);
        container.setPadding(new Insets(Config.windowHeight*0.25, 0, 0, 0));

//        Setup font stylesheet
        this.getStylesheets().add(getClass().getResource("/stylesheets/main.css").toExternalForm());

        this.getChildren().add(container);
        setBack();
    }
    private void setCharacter(Player character){
        classImageView.setImage(character.idleImage);
        classNameLabel.setText(character.className);
        statsBox.getChildren().clear();
        statsBox.getChildren().add(character.getStatsView());
    }
    private void setBack(){
        BackgroundSize backgroundSize = new BackgroundSize(1920,
                1080,
                true,
                true,
                true,
                false);
        BackgroundImage image = new BackgroundImage(new Image("backgrounds/createCharacterViewBig.gif"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize);
        this.setBackground(new Background(image));
    }
}
