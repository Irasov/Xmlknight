package com.epam.irasov.xmlknight.entity;

public class MeleeWeapon extends Weapon {
    private int lengthWeapon;

    public MeleeWeapon() {

    }

    public MeleeWeapon(String name, Type type, int weight, int price, boolean captured, int lengthWeapon) {
        super(name, type, weight, price, captured);
        this.lengthWeapon = lengthWeapon;
    }

    public void setLengthWeapon(int length_weapon) {
        this.lengthWeapon = length_weapon;
    }

    public int getLengthWeapon() {
        return lengthWeapon;
    }

    @Override
    public String toString() {
        return super.toString() + " | length weapon(cm): " + lengthWeapon;
    }
}