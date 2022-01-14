package com.pjeskiem_i_mieczem;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

// Class that extends the regular JavaFX button but uses custom graphics as background
public class ImageButton extends Button {
    private Button button;

    public ImageButton(String text, int width, int height, String image) {
//        Set text on the base class
        super(text);
//        Create and load background
        BackgroundImage myBI= new BackgroundImage(new Image(image,width,height,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(myBI));
        this.setPrefWidth(width);
        this.setPrefHeight(height);

//        Setup mouse events for responsiveness
        this.addEventFilter(MouseEvent.MOUSE_ENTERED, (event)->{
            this.setScaleX(1.03);
            this.setScaleY(1.03);
        });
        this.addEventFilter(MouseEvent.MOUSE_EXITED, (event)->{
            this.setScaleX(1);
            this.setScaleY(1);
        });
        this.addEventFilter(MouseEvent.MOUSE_PRESSED, (event)->{
            this.setScaleX(1.05);
            this.setScaleY(1.05);
        });
        this.addEventFilter(MouseEvent.MOUSE_RELEASED, (event)->{
            this.setScaleX(1);
            this.setScaleY(1);
        });
    }
}

