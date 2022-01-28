package com.pjeskiem_i_mieczem;

public class Hunter extends Player{
    // Mage which accepts stats in order: Strength, dexterity, luck, intelligence, endurance
    public Hunter(int[] stats){
        super("Łowca",stats[4],stats[0],stats[1],stats[3],stats[2],"łowca.png");
        this.maxHp = new Statistic("Hp", this.endurance.getValue()*7 );
        this.hp = new Statistic("Hp", this.endurance.getValue()*7);
        updateStatDescriptions();
    }
    public Hunter(){
        super("Łowca",6,1,7,2,4,"łowca.png");
        this.maxHp = new Statistic("Hp", this.endurance.getValue()*7 );
        this.hp = new Statistic("Hp", this.endurance.getValue()*7);
        updateStatDescriptions();
    }

    private void updateStatDescriptions(){
        this.strength.setDescription("Dodaje 1pkt odporności przeciwko wojownikowi");
        this.endurance.setDescription("Dodaje +7 punktów życia");
        this.dexterity.setDescription("Dodaje 1pkt odporności przeciwko łowcy, dodaje +12 punktów obrażeń");
        this.intelligence.setDescription("Dodaje 1pkt odporności przeciwko magowi");
        this.luck.setDescription("Zwiększa obrażenia o 1% (max 100%)");
    }

    public double getDamage(){
        return dexterity.getValue() * 12 + luck.getValue();
    }
    @Override
    public void takeDamage(Player other, float multiplier) {
        double mage_reduction = (other instanceof Mage) ? intelligence.getValue() :0;
        double warrior_reduction = (other instanceof Warrior) ? strength.getValue() :0;
        double hunter_reduction = (other instanceof Hunter) ? dexterity.getValue() :0;
        double dodge_chance = Math.min(0.5f, dexterity.getValue()/100f);
        int dmg = (int)(other.getDamage() * (100-mage_reduction-warrior_reduction-hunter_reduction)/100);
        if (Math.random() > dodge_chance){
            hp.setValue(hp.getValue() - dmg*multiplier);
        }else {
            System.out.println("Dodge!");
        }
    }
    public void recalculateHp() {
        super.recalculateHp(10);
    }
}
