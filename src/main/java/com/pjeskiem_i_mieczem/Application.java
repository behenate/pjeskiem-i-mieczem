package com.pjeskiem_i_mieczem;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        Player hunterPreset = new Player("Łowca", 2, 2, 2, 100, 2, "dino.jpg");
        hunterPreset.setName("Dino");
        Player warriorPreset = new Player("Wojownik", 2, 2, 2, 2, 2, "zloty.jpg");
        warriorPreset.setName("Złoty");
        ImageButton testButton = new ImageButton("Testowy teścik", 300, 100, "buttons/button_0.png");
        WelcomeScreenGui welcomeScreenGui = new WelcomeScreenGui();
        CreateCharacterGui createCharacterGui = new CreateCharacterGui();
        LeaderboardGui leaderboardGui = new LeaderboardGui();
<<<<<<< HEAD
        CityViewGui cityViewGui = new CityViewGui();
        //Scene scene = new Scene(welcomeScreenGui, Config.windowWidth, Config.windowHeight);
        //Scene scene = new Scene(leaderboardGui, Config.windowWidth, Config.windowHeight);
        //Scene scene = new Scene(cityViewGui, Config.windowWidth, Config.windowHeight);
        Scene scene = new Scene(createCharacterGui, Config.windowWidth, Config.windowHeight);
        stage.setTitle("Hello!");
=======
        FightGui fightGui = new FightGui(hunterPreset, warriorPreset);
        //Scene scene = new Scene(welcomeScreenGui, Config.windowWidth, Config.windowHeight);
        Scene scene = new Scene(fightGui, Config.windowWidth, Config.windowHeight);
        //Scene scene = new Scene(createCharacterGui, Config.windowWidth, Config.windowHeight);
        stage.setTitle("Pjeskiem I Mieczem");
>>>>>>> 8ea06017c9c4a06aa059a7e6d0c95e5d927d9053
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}