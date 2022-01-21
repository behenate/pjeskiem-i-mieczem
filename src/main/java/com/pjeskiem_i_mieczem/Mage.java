package com.pjeskiem_i_mieczem;

public class Mage extends Player{
    public Mage(){
        super("Mag",6,1,1,8,6,"mage.png");
        this.hp = new Statistic("Hp", this.endurance.getValue()*5);
    }

    @Override
    public int getDamage() {
        return (int)(20*intelligence.getValue() + luck.getValue());
    }

}
