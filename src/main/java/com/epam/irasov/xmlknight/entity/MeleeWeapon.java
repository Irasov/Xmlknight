package com.epam.irasov.xmlknight.entity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;
@XmlRootElement(name="meleeWeapon")
public class MeleeWeapon extends Weapon {
    private int lengthWeapon;

    public MeleeWeapon() {

    }

    public MeleeWeapon(Long id, UUID uuid, String name, Type type, int weight, int price, boolean captured, int lengthWeapon) {
        super(id, uuid, name, type, weight, price, captured);
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
        return super.toString() + " |length weapon(cm): " + lengthWeapon;
    }
}