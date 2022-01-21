package com.pjeskiem_i_mieczem;

public class Warrior extends Player{
    public Warrior(){
        super("Wojownik",8,6,2,1,3,"wojownik.png");
        this.hp = new Statistic("Hp", this.endurance.getValue()*10);
    }

    @Override
    public int getDamage() {
        return this.strength.getValue() * 10 + luck.getValue();
    }

    @Override
    public void takeDamage(Player other) {
        float mage_reduction = (other instanceof Mage) ? intelligence.getValue() :0;
        float warrior_reduction = (other instanceof Warrior) ? strength.getValue() :0;
        float hunter_reduction = (other instanceof Hunter) ? dexterity.getValue() :0;
        float shield_protection = strength.getValue();

    }
}
