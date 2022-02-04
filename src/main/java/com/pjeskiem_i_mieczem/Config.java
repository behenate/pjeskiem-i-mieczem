package com.pjeskiem_i_mieczem;

public class Config {
    public static int windowWidth = 1280;
    public static int windowHeight = 720;

//    Balance modifiers
    public static final double expModifier = 100;
    public static final double damageModifier = 0.3;
    public static final double goldModifier = 1;
//    What is the lowes amount of skill points enemy generator has to spend in reference to player level
    public static final double enemyLowPointsModifier = 0.5;
//    What is the highest amount of skill point enemy generator has to spend in reference to player level
    public static final double enemyHighPointsModifier = 1;
//  How much the exp required to next level multiplier increases each level eq. for default value 0.1
//    On level 1 you need 1.0*levelForLevelOne experience
//    On level 2 you need 1.1*levelForLevelTwo experience etc.
    public static final double expModifierOffset = 0.1;
//  How much of the required exp you actually need to have in order to level up
    public static final double expCurveModifier = 1;
}

