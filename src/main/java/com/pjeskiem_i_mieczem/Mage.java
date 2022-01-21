package com.pjeskiem_i_mieczem;

public class Mage extends Player{
    public Mage(){
        super("Mag",6,1,1,8,6,"mage.png");
        this.hp = new Statistic("Hp", this.endurance.getValue()*5);
    }

    @Override
    public int getDamage() {
        return 20*intelligence.getValue() + luck.getValue();
    }
    @Override
    public void takeDamage(Player other) {
        float mage_reduction = (other instanceof Mage) ? intelligence.getValue() :0;
        float warrior_reduction = (other instanceof Warrior) ? strength.getValue() :0;
        float hunter_reduction = (other instanceof Hunter) ? dexterity.getValue() :0;
        int dmg = (int)(other.getDamage() * (1-mage_reduction-warrior_reduction-hunter_reduction));
        currentHp.setValue(currentHp.getValue() - dmg);
    }

}
