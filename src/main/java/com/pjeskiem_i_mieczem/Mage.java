package com.pjeskiem_i_mieczem;

public class Mage extends Player{
    public Mage(){
        super("Mag",6,1,1,8,6,"mage.png");
        this.hp = new Statistic("Hp", this.endurance.getValue()*5);
        this.currentHp = new Statistic("Hp", this.endurance.getValue()*5);
    }

    @Override
    public double getDamage() {
        return 20*intelligence.getValue() + luck.getValue();
    }
    @Override
    public void takeDamage(Player other) {
        double mage_reduction = (other instanceof Mage) ? intelligence.getValue() :0;
        double warrior_reduction = (other instanceof Warrior) ? strength.getValue() :0;
        double hunter_reduction = (other instanceof Hunter) ? dexterity.getValue() :0;
        int dmg = (int)(other.getDamage() * (100-mage_reduction-warrior_reduction-hunter_reduction)/100);
        currentHp.setValue(currentHp.getValue() - dmg);
    }

}
