package com.epam.irasov.xmlknight.entity;

public class Armor extends Ammunition {
    public enum ArmorType {ARMOR, CHAIN_ARMOR};
    public enum ConstProtection {FULLY,BREAST,CHEST_AND_BACK,CHEST_AND_ARMS,CHEST_AND_LEGS};
    private ArmorType type;
    private ConstProtection protection;
    public Armor(){

    }
    public Armor(ConstAmmunition name,ArmorType type,int weight,int price,ConstProtection protection){
        super(name,weight,price);
        this.protection=protection;
        this.type = type;
    }

    public void setType(ArmorType type){
        this.type = type;
    }

    public ArmorType getType(){
        return this.type;
    }

    public void setProtection(ConstProtection protection){
        this.protection = protection;
    }

    public ConstProtection getProtection() {
        return protection;
    }

    @Override
    public String toString(){
        return super.toString()+" | type: "+type+" | protection: "+protection;
    }
}
