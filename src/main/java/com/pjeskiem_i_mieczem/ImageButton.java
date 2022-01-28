package com.pjeskiem_i_mieczem;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.Date;

import static java.awt.Color.white;


// Class that extends the regular JavaFX button but uses custom graphics as background
public class ImageButton extends Button {
    private Button button;
    private Thread hoverThread;
    public ImageButton(String text, int width, int height, String image){
        this(text,width,height,image,null, null);
    }
    public ImageButton(String text, int width, int height, String image, Runnable onHover, Runnable onStopHover) {
//        Set text on the base class
        super(text);
//        Create and load background
        BackgroundImage myBI= new BackgroundImage(new Image(image,width,height,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(myBI));
        //this.setStyle("-fx-background-color: rgba(196,38,128,0.5) ");
        this.setPrefWidth(width);
        this.setPrefHeight(height);

        // Setup the hoverThread
        setupThread(onHover);
//        Setup mouse events for responsiveness
        this.addEventFilter(MouseEvent.MOUSE_ENTERED, (event)->{
            if (onHover != null){
                this.hoverThread.start();
            }
            this.setScaleX(1.03);
            this.setScaleY(1.03);
        });
        this.addEventFilter(MouseEvent.MOUSE_EXITED, (event)->{
            if (onHover != null){
                this.hoverThread.interrupt();
                setupThread(onHover);
                onStopHover.run();
            }
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
    private void setupThread(Runnable onHover){
        this.hoverThread = new Thread(()->{
//            Interrupting this thread is expected
            try {
                Thread.sleep(500);
                Platform.runLater(()->{
                    onHover.run();
                });

            } catch (InterruptedException ignored) {}
        });
    }

}

