package com.pjeskiem_i_mieczem;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class Tools {
    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener((ov, oldValue, newValue) -> {
            if (tf.getText().length() > maxLength) {
                String s = tf.getText().substring(0, maxLength);
                tf.setText(s);
            }
        });
    }

//  setup background for every gui
    public static void setBack(Pane screen, String path){
        BackgroundSize backgroundSize = new BackgroundSize(1920,
                1080,
                true,
                true,
                true,
                false);
        BackgroundImage image = new BackgroundImage(new Image(path),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize);
        screen.setBackground(new Background(image));
    }
}
