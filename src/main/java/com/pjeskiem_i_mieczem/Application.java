package com.pjeskiem_i_mieczem;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    Stage stage;
    public static Player player;
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;

        WelcomeScreenGui welcomeScreenGui = new WelcomeScreenGui(this);
        CreateCharacterGui createCharacterGui = new CreateCharacterGui(this);
//        LeaderboardGui leaderboardGui = new LeaderboardGui(this);
//        TrainingGui trainingGui = new TrainingGui(4, this);
//        FailureGui failureGui = new FailureGui(leaderboardGui, this);
//        VictoryGui victoryGui = new VictoryGui(this);

        Scene scene = new Scene(welcomeScreenGui, Config.windowWidth, Config.windowHeight);
//        Scene scene = new Scene(trainingGui, Config.windowWidth, Config.windowHeight);
//        Scene scene = new Scene(createCharacterGui, Config.windowWidth, Config.windowHeight);
        //Scene scene = new Scene(failureGui, Config.windowWidth, Config.windowHeight);
//        Scene scene = new Scene(victoryGui);
        this.stage.setTitle("Pjeskiem I Mieczem");
        this.stage.setScene(scene);
        this.stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void goToTheCity(){
        CityViewGui cityViewGui = new CityViewGui(this);
        Scene scene = new Scene(cityViewGui, Config.windowWidth, Config.windowHeight);
        this.stage.setScene(scene);
    }


    public void goToTheArena(){
        Player hunterPreset = new Player("Łowca", 2, 2, 2, 100, 2, "dino.jpg");
        hunterPreset.setName("Dino");
        Player warriorPreset = new Player("Wojownik", 2, 2, 2, 2, 2, "zloty.jpg");
        warriorPreset.setName("Złoty");
        FightGui fightGui = new FightGui(hunterPreset, warriorPreset);
        Fight fight = new Fight(fightGui, hunterPreset, warriorPreset);
        Thread fightThread = new Thread(fight);
        fightThread.start();
        Scene scene = new Scene(fightGui, Config.windowWidth, Config.windowHeight);
        this.stage.setScene(scene);
    }

    public void goToTheStart(){
        WelcomeScreenGui welcomeScreenGui = new WelcomeScreenGui(this);
        Scene scene = new Scene(welcomeScreenGui, Config.windowWidth, Config.windowHeight);
        this.stage.setScene(scene);
    }

    public void goToTheTraining(){
        TrainingGui trainingGui = new TrainingGui(4, this);
        Scene scene = new Scene(trainingGui, Config.windowWidth, Config.windowHeight);
        this.stage.setScene(scene);
    }

    public void goToTheLeaderboard(){
        LeaderboardGui leaderboardGui = new LeaderboardGui(this);
        Scene scene = new Scene(leaderboardGui, Config.windowWidth, Config.windowHeight);
        this.stage.setScene(scene);
    }

    public void goToCreateCharacterGui(){
        CreateCharacterGui createCharacterGui = new CreateCharacterGui(this);
        Scene scene = new Scene(createCharacterGui, Config.windowWidth, Config.windowHeight);
        this.stage.setScene(scene);
    }



}