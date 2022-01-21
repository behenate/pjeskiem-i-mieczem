package com.pjeskiem_i_mieczem;

public class Warrior extends Player{
    public Warrior(){
        super("Wojownik",8,6,2,1,3,"wojownik.png");
        this.hp = new Statistic("Hp", this.endurance.getValue()*10);
        this.currentHp = new Statistic("Hp", this.endurance.getValue()*10);
    }

    @Override
    public double getDamage() {
        return this.strength.getValue() * 10 + luck.getValue();
    }

    @Override
    public void takeDamage(Player other) {
        double mage_reduction = (other instanceof Mage) ? intelligence.getValue() :0;
        double warrior_reduction = (other instanceof Warrior) ? strength.getValue() :0;
        double hunter_reduction = (other instanceof Hunter) ? dexterity.getValue() :0;
        double shield_protection = Math.min(50,strength.getValue());
        int dmg = (int)(other.getDamage() * (100-mage_reduction-warrior_reduction-hunter_reduction-shield_protection)/100);
        currentHp.setValue(currentHp.getValue() - dmg);
    }
}
