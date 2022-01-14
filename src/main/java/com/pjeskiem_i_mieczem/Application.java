package com.pjeskiem_i_mieczem;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        ImageButton testButton = new ImageButton("Testowy teścik", 300, 100, "buttons/button_0.png");
        WelcomeScreenGui welcomeScreenGui = new WelcomeScreenGui();
        Scene scene = new Scene(welcomeScreenGui, Config.windowWidth, Config.windowHeight);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}