package com.pjeskiem_i_mieczem;

public class Hunter extends Player{

    public Hunter(){
        super("Hunter",6,1,7,2,4,"łowca.png");
        this.hp = new Statistic("Hp", this.endurance.getValue()*7 );
    }

    public int getDamage(){
        return dexterity.getValue() * 12 + luck.getValue();
    }
    @Override
    public void takeDamage(Player other) {
        float mage_reduction = (other instanceof Mage) ? intelligence.getValue() :0;
        float warrior_reduction = (other instanceof Warrior) ? strength.getValue() :0;
        float hunter_reduction = (other instanceof Hunter) ? dexterity.getValue() :0;
        float dodge_chance = dexterity.getValue()/100f;
        int dmg = (int)(other.getDamage() * (1-mage_reduction-warrior_reduction-hunter_reduction));
        if (Math.random() < dodge_chance){
            currentHp.setValue(currentHp.getValue() - dmg);
        }else {
            System.out.println("Dodge!");
        }
    }
}
