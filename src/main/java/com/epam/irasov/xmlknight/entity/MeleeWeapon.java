package com.epam.irasov.xmlknight.entity;

public class MeleeWeapon extends Weapon {
    private int lengthWeapon;

    public MeleeWeapon() {

    }

    public MeleeWeapon(String name, String type, int weight, int price, Captured captured, int lengthWeapon) {
        super(name, type, weight, price, captured);
        this.lengthWeapon = lengthWeapon;
    }

    public void setLength_weapon(int length_weapon) {
        this.lengthWeapon = length_weapon;
    }

    public int getLength_weapon() {
        return lengthWeapon;
    }

    @Override
    public String toString() {
        return super.toString() + " | length weapon(cm): " + lengthWeapon;
    }
}