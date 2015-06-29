package com.epam.irasov.xmlknight.entity;

public abstract class Weapon extends Ammunition {
    public enum Captured {CAPTURED,NOT_CAPTURED};
    private Captured captured;

    public Weapon(){

    }

    public Weapon(ConstAmmunition name, int weight, int price, Captured captured){
        super(name,weight,price);
        this.captured=captured;
    }

    public void setCaptured(Captured captured) {
        this.captured = captured;
    }

    public Captured getCaptured(){
        return captured;
    }

    @Override
    public String toString(){
        return super.toString()+" | captured: "+captured;
    }
}
