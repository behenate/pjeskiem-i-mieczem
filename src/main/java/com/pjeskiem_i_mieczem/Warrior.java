package com.pjeskiem_i_mieczem;

public class Warrior extends Player{
    public Warrior(){
        super("Wojownik",8,6,2,1,3,"wojownik.png");
        this.hp = new Statistic("Hp", this.endurance.getValue()*10);
    }
}
