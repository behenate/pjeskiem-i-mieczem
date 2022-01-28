package com.pjeskiem_i_mieczem;
import java.util.concurrent.ThreadLocalRandom;

public class EnemyGenerator {
    int class_num = 0;
    private Player enemy;

    public EnemyGenerator(){
//        Get player
        Player player = Application.player;
//        Get random class number for enemy
        class_num = ThreadLocalRandom.current().nextInt(0, 2 + 1);
//        How many points can enemy spend
        int pointsToSpend = 20 + (Application.player.level-1)*4;
        float[] pointsProportionsWarrior = {0.3f, 0.1f, 0.15f, 0.05f, 0.4f};
        float[] pointsProportionsHunter = {0.05f, 0.35f, 0.2f,0.1f, 0.3f};
        float[] pointsProportionsMage = {0.05f, 0.05f, 0.3f, 0.4f, 0.2f};
        float[][] pointsProportions = {pointsProportionsWarrior, pointsProportionsHunter, pointsProportionsMage};
        double[] playerStats = {player.strength.getValue(), player.dexterity.getValue(), player.luck.getValue(), player.intelligence.getValue(), player.endurance.getValue()};
        switch (class_num){
            case 0 ->{
                enemy = new Warrior()
            }
        }
    }
}
