package com.pjeskiem_i_mieczem;
import java.util.concurrent.ThreadLocalRandom;

public class EnemyGenerator {
    int class_num;
    private final Player enemy;

    public EnemyGenerator(){
//        Get random class number for enemy
        class_num = ThreadLocalRandom.current().nextInt(0, 2 + 1);
//        How many points can enemy spend, enemy points distributions
        int pointsToSpend = 20 + (Application.player.level-1)*4;
        float[] pointsProportionsWarrior = {0.3f, 0.1f, 0.15f, 0.05f, 0.4f};
        float[] pointsProportionsHunter = {0.05f, 0.35f, 0.2f,0.1f, 0.3f};
        float[] pointsProportionsMage = {0.05f, 0.05f, 0.3f, 0.4f, 0.2f};
        float[][] pointsProportions = {pointsProportionsWarrior, pointsProportionsHunter, pointsProportionsMage};
        int[] enemyStats = new int[5];
//        Generate enemy stats
        for (int i = 0; i < 5; i++) {
            enemyStats[i] = (int)(pointsProportions[class_num][i] * pointsToSpend *
                    (Config.enemyLowPointsModifier + Math.random() * (Config.enemyHighPointsModifier-Config.enemyLowPointsModifier)));
        }
//        Create the enemy
        switch (class_num){
            case 0 ->
                this.enemy = new Warrior(enemyStats);
            case 1 ->
                this.enemy = new Hunter(enemyStats);
            default ->
                this.enemy = new Mage(enemyStats);
        }
    }

    public Player getEnemy(){
        return enemy;
    }
}
