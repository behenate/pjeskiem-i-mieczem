package com.pjeskiem_i_mieczem;

public class Hunter extends Player{

    public Hunter(){
        super("Hunter",6,1,7,2,4,"łowca.png");
        this.hp = new Statistic("Hp", this.endurance.getValue()*7 );
        this.currentHp = new Statistic("Hp", this.endurance.getValue()*7);
    }

    public double getDamage(){
        return dexterity.getValue() * 12 + luck.getValue();
    }
    @Override
    public void takeDamage(Player other) {
        double mage_reduction = (other instanceof Mage) ? intelligence.getValue() :0;
        double warrior_reduction = (other instanceof Warrior) ? strength.getValue() :0;
        double hunter_reduction = (other instanceof Hunter) ? dexterity.getValue() :0;
        double dodge_chance = Math.min(0.5f, dexterity.getValue()/100f);
        int dmg = (int)(other.getDamage() * (100-mage_reduction-warrior_reduction-hunter_reduction)/100);
        if (Math.random() > dodge_chance){
            currentHp.setValue(currentHp.getValue() - dmg);
        }else {
            System.out.println("Dodge!");
        }
    }
}
