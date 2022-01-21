package com.pjeskiem_i_mieczem;

public class Warrior extends Player{
    public Warrior(){
        super("Wojownik",8,6,2,1,3,"wojownik.png");
        this.hp = new Statistic("Hp", this.endurance.getValue()*10);
    }

    @Override
    public int getDamage() {
        return (int) (this.strength.getValue() * 10 + luck.getValue());
    }

    @Override
    public void takeDamage(Player other) {
        float mage_reduction = (other instanceof Mage) ? (float) intelligence.getValue() :0;
        float warrior_reduction = (other instanceof Warrior) ? (float) strength.getValue() :0;
        float hunter_reduction = (other instanceof Hunter) ? (float) dexterity.getValue() :0;
        float shield_protection = (float) strength.getValue();

    }
}
