package com.pjeskiem_i_mieczem;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Popup;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {
//    Setup the stage and the player class
    Stage stage;
    public static Player player;
    LeaderboardGui leaderboardGui = new LeaderboardGui(this);
//    Easteregg flag
    private boolean easteregg = false;
    @Override
    public void start(Stage stage)  {
//        Set the icon, launch the menu gui
        this.stage = stage;
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);
        WelcomeScreenGui welcomeScreenGui = new WelcomeScreenGui(this);

        Scene scene = new Scene(welcomeScreenGui, Config.windowWidth, Config.windowHeight);
        scene.getStylesheets().add("/stylesheets/welcomeScreen.css");
//        Block resizing of the window to avoid alignment problems
        this.stage.setResizable(false);
        this.stage.setTitle("Pjeskiem I Mieczem");
        this.stage.setScene(scene);
        this.stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

//    function that switches the application to the menu gui
    public void goToTheCity(){
        CityViewGui cityViewGui = new CityViewGui(this);
        Scene scene = new Scene(cityViewGui, Config.windowWidth, Config.windowHeight);
//      Activate the easteregg
        if (easteregg){
            Popup popup = new Popup();
            ImageView imageView  =new ImageView(new Image("/backgrounds/easteregg_with_background.gif"));
            imageView.setFitWidth(400);
            imageView.setFitHeight(135);
            popup.getContent().addAll(imageView);
            double screenWidth = Screen.getPrimary().getBounds().getWidth();
            double screenHeight = Screen.getPrimary().getBounds().getHeight();
            popup.setX(screenWidth/2 - popup.getWidth()/2);
            popup.setY(screenHeight - popup.getHeight());
            popup.show(this.stage);

        }
//        Set style
        scene.getStylesheets().add("/stylesheets/main.css");
        this.stage.setScene(scene);
    }

//       Function that sets up the fight and passes it to the fight gui and switches to it
    public void goToTheArena(){
        EnemyGenerator generator = new EnemyGenerator();
        Player enemy = generator.getEnemy();
        enemy.name = NameGenerator.generateName();
        FightGui fightGui = new FightGui(enemy);
        Fight fight = new Fight(this, fightGui, enemy);
        Thread fightThread = new Thread(fight);
        fightThread.start();
        Scene scene = new Scene(fightGui, Config.windowWidth, Config.windowHeight);
        scene.getStylesheets().add("/stylesheets/main.css");
        this.stage.setScene(scene);
    }

//    Function that returns the player to main menu
    public void goToTheStart(){
        WelcomeScreenGui welcomeScreenGui = new WelcomeScreenGui(this);
        Scene scene = new Scene(welcomeScreenGui, Config.windowWidth, Config.windowHeight);
        scene.getStylesheets().add("/stylesheets/main.css");
        this.stage.setScene(scene);
    }

//    Function that switches the view to the Training
    public void goToTheTraining(){
        TrainingGui trainingGui = new TrainingGui(this);
        Scene scene = new Scene(trainingGui, Config.windowWidth, Config.windowHeight);
        scene.getStylesheets().add("/stylesheets/trainingGui.css");
        this.stage.setScene(scene);
    }

//    Function that switches to the leaderboard view
    public void goToTheLeaderboard(){
        LeaderboardGui leaderboardGui = new LeaderboardGui(this);
        Scene scene = new Scene(leaderboardGui, Config.windowWidth, Config.windowHeight);
        scene.getStylesheets().add("/stylesheets/main.css");
        this.stage.setScene(scene);
    }

//    Function that switches to the create character gui
    public void goToCreateCharacterGui(){
        CreateCharacterGui createCharacterGui = new CreateCharacterGui(this);
        Scene scene = new Scene(createCharacterGui, Config.windowWidth, Config.windowHeight);
        scene.getStylesheets().add("/stylesheets/main.css");
        this.stage.setScene(scene);
    }

//    Function that shows the failure screen
    public void goToFailureGui(){
        FailureGui failureGui = new FailureGui(leaderboardGui, this);
        Scene scene = new Scene(failureGui, Config.windowWidth, Config.windowHeight);
        scene.getStylesheets().add("/stylesheets/main.css");
        this.stage.setScene(scene);
    }

//    Function that switches to the victory screen
    public void goToVictoryGui(){
        VictoryGui victoryGui = new VictoryGui(this);
        Scene scene = new Scene(victoryGui, Config.windowWidth, Config.windowHeight);
        scene.getStylesheets().add("/stylesheets/main.css");
        this.stage.setScene(scene);
    }
    public void activateEasterEgg(){
        this.easteregg = true;
    }


}