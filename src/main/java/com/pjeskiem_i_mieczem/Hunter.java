package com.pjeskiem_i_mieczem;

public class Hunter extends Player{

    public Hunter(){
        super("Hunter",6,1,7,2,4,"łowca.png");
        this.hp = new Statistic("Hp", this.endurance.getValue()*7 );
    }

    public int getDamage(){
        return (int)( dexterity.getValue() * 12 + luck.getValue());
    }
}
