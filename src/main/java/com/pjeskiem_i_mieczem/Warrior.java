package com.pjeskiem_i_mieczem;

public class Warrior extends Player{
    // Warrior which accepts stats in order: Strength, dexterity, luck, intelligence, endurance
    public Warrior(int[] stats){
        super("Wojownik",stats[4],stats[0],stats[1],stats[3],stats[2], "characters/warrior.gif");
        this.maxHp = new Statistic("Hp", this.endurance.getValue()*10);
        this.hp = new Statistic("Hp", this.endurance.getValue()*10);
        updateStatDescriptions();
    }
    public Warrior(){
        super("Wojownik",8,6,2,1,3, "characters/warrior.gif");
        this.maxHp = new Statistic("Hp", this.endurance.getValue()*10);
        this.hp = new Statistic("Hp", this.endurance.getValue()*10);
//        this.currentHp = new Statistic("Hp", 30);
        updateStatDescriptions();
    }
    private void updateStatDescriptions(){
        this.strength.setDescription("Dodaje 1pkt odporności przeciwko wojownikowi, dodaje +10 punktów obrażeń");
        this.endurance.setDescription("Dodaje +10 punktów życia");
        this.dexterity.setDescription("Dodaje 1pkt odporności przeciwko łowcy");
        this.intelligence.setDescription("Dodaje 1pkt odporności przeciwko magowi");
        this.luck.setDescription("Zwiększa obrażenia o 1% (max 100%)");
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
        hp.setValue(hp.getValue() - dmg*Config.damageModifier);
    }

    public void recalculateHp() {
        super.recalculateHp(10);
    }
}
