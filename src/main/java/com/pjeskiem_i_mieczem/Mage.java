package com.pjeskiem_i_mieczem;

public class Mage extends Player{

    // Mage which accepts stats in order: Strength, dexterity, luck, intelligence, endurance
    public Mage(int[] stats){
        super("Mag",stats[4],stats[0],stats[1],stats[3],stats[2],"mage.png");
        this.maxHp = new Statistic("Hp", this.endurance.getValue()*5);
        this.hp = new Statistic("Hp", this.endurance.getValue()*5);
        updateStatDescriptions();
    }


    public Mage(){
        super("Mag",6,1,1,8,6,"mage.png");
        this.maxHp = new Statistic("Hp", this.endurance.getValue()*5);
        this.hp = new Statistic("Hp", this.endurance.getValue()*5);
        updateStatDescriptions();
    }
    private void updateStatDescriptions(){
        this.strength.setDescription("Dodaje 1pkt odporności przeciwko wojownikowi");
        this.endurance.setDescription("Dodaje +5 punktów życia");
        this.dexterity.setDescription("Dodaje 1pkt odporności przeciwko łowcy");
        this.intelligence.setDescription("Dodaje 1pkt odporności przeciwko magowi, dodaje +20 punktów obrażeń");
        this.luck.setDescription("Zwiększa obrażenia o 1% (max 100%)");
    }
    @Override
    public double getDamage() {
        return 20*intelligence.getValue() + luck.getValue();
    }
    @Override
    public void takeDamage(Player other, float multiplier) {
        double mage_reduction = (other instanceof Mage) ? intelligence.getValue() :0;
        double warrior_reduction = (other instanceof Warrior) ? strength.getValue() :0;
        double hunter_reduction = (other instanceof Hunter) ? dexterity.getValue() :0;
        int dmg = (int)(other.getDamage() * (100-mage_reduction-warrior_reduction-hunter_reduction)/100);
        hp.setValue(hp.getValue() - dmg*multiplier);
    }
    public void recalculateHp() {
        super.recalculateHp(7);
    }

}
