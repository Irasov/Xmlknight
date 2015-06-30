package com.epam.irasov.xmlknight.entity;

public class RangedWeapon extends Weapon {
    private int numberOfShells;

    public RangedWeapon() {

    }

    public RangedWeapon(String name, String type, int weight, int price, Captured captured, int numberOfShells) {
        super(name, type, weight, price, captured);
        this.numberOfShells = numberOfShells;
    }

    public void setNumberOfShells(int numberOfShells) {
        this.numberOfShells = numberOfShells;
    }

    public int getNumberOfShells() {
        return numberOfShells;
    }

    @Override
    public String toString() {
        return super.toString() + " | number of shells: " + numberOfShells;
    }
}
