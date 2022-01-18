package com.pjeskiem_i_mieczem;

import javafx.scene.Scene;
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
        CityViewGui cityViewGui = new CityViewGui(new Player("Adam", 100));
        FightGui fightGui = new FightGui(hunterPreset, warriorPreset);
        TrainingGui trainingGui = new TrainingGui(new Player("Goodboi", 100), 4);
        FailureGui failureGui = new FailureGui(new Player("Adam", 1000), leaderboardGui);
        VictoryGui victoryGui = new VictoryGui(new Player("Adam", 1000));
        //Scene scene = new Scene(welcomeScreenGui, Config.windowWidth, Config.windowHeight);
        //Scene scene = new Scene(failureGui, Config.windowWidth, Config.windowHeight);
        //Scene scene = new Scene(leaderboardGui, Config.windowWidth, Config.windowHeight);
        //Scene scene = new Scene(cityViewGui, Config.windowWidth, Config.windowHeight);
        //Scene scene = new Scene(createCharacterGui, Config.windowWidth, Config.windowHeight);
        //Scene scene = new Scene(trainingGui, Config.windowWidth, Config.windowHeight);
        //Scene scene = new Scene(welcomeScreenGui, Config.windowWidth, Config.windowHeight);
        //Scene scene = new Scene(fightGui, Config.windowWidth, Config.windowHeight);
        Scene scene = new Scene(victoryGui);
        stage.setTitle("Hello!");
        stage.setTitle("Pjeskiem I Mieczem");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}