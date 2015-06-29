package com.epam.irasov.xmlknight.entity;

public class RangedWeapon extends Weapon {
    public enum RangedWeaponType {BOW,CROSS_BOW,JAVELIN};
    private int numberOfShells;
    private RangedWeaponType type;

    public RangedWeapon(){

    }

    public RangedWeapon(ConstAmmunition name,RangedWeaponType type,int weight,int price,Captured captured,int numberOfShells){
        super(name,weight,price,captured);
        this.numberOfShells=numberOfShells;
        this.type = type;
    }

    public void setType(RangedWeaponType type){
        this.type = type;
    }

    public RangedWeaponType getType(){
        return this.type;
    }

    public void setNumberOfShells(int numberOfShells) {
        this.numberOfShells = numberOfShells;
    }

    public int getNumberOfShells() {
        return numberOfShells;
    }

    @Override
    public String toString(){
        return super.toString()+" | type: "+type+" | number of shells: "+numberOfShells;
    }
}
