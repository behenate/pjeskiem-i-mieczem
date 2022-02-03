package com.pjeskiem_i_mieczem;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    Stage stage;
    public static Player player;
    LeaderboardGui leaderboardGui = new LeaderboardGui(this);

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);
        WelcomeScreenGui welcomeScreenGui = new WelcomeScreenGui(this);

//        TrainingGui trainingGui = new TrainingGui(4, this);
//        FailureGui failureGui = new FailureGui(leaderboardGui, this);
//        VictoryGui victoryGui = new VictoryGui(this);

        Scene scene = new Scene(welcomeScreenGui, Config.windowWidth, Config.windowHeight);
        scene.getStylesheets().add("/stylesheets/welcomeScreen.css");

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
        scene.getStylesheets().add("/stylesheets/main.css");
        this.stage.setScene(scene);
    }


    public void goToTheArena(){
        EnemyGenerator generator = new EnemyGenerator();
        Player enemy = generator.getEnemy();
        enemy.name = "Złoty :<";
        FightGui fightGui = new FightGui(enemy);
        Fight fight = new Fight(this, fightGui, enemy);
        Thread fightThread = new Thread(fight);
        fightThread.start();
        Scene scene = new Scene(fightGui, Config.windowWidth, Config.windowHeight);
        scene.getStylesheets().add("/stylesheets/main.css");
        this.stage.setScene(scene);
    }

    public void goToTheStart(){
        WelcomeScreenGui welcomeScreenGui = new WelcomeScreenGui(this);
        Scene scene = new Scene(welcomeScreenGui, Config.windowWidth, Config.windowHeight);
        scene.getStylesheets().add("/stylesheets/main.css");
        this.stage.setScene(scene);
    }

    public void goToTheTraining(){
        TrainingGui trainingGui = new TrainingGui(this);
        Scene scene = new Scene(trainingGui, Config.windowWidth, Config.windowHeight);
        scene.getStylesheets().add("/stylesheets/trainingGui.css");
        this.stage.setScene(scene);
    }

    public void goToTheLeaderboard(){
        LeaderboardGui leaderboardGui = new LeaderboardGui(this);
        Scene scene = new Scene(leaderboardGui, Config.windowWidth, Config.windowHeight);
        scene.getStylesheets().add("/stylesheets/main.css");
        this.stage.setScene(scene);
    }

    public void goToCreateCharacterGui(){
        CreateCharacterGui createCharacterGui = new CreateCharacterGui(this);
        Scene scene = new Scene(createCharacterGui, Config.windowWidth, Config.windowHeight);
        scene.getStylesheets().add("/stylesheets/main.css");
        this.stage.setScene(scene);
    }

    public void goToFailureGui(){
        FailureGui failureGui = new FailureGui(leaderboardGui, this);
        Scene scene = new Scene(failureGui, Config.windowWidth, Config.windowHeight);
        scene.getStylesheets().add("/stylesheets/main.css");
        this.stage.setScene(scene);
    }

    public void goToVictoryGui(){
        VictoryGui victoryGui = new VictoryGui(this);
        Scene scene = new Scene(victoryGui, Config.windowWidth, Config.windowHeight);
        scene.getStylesheets().add("/stylesheets/main.css");
        this.stage.setScene(scene);
    }



}