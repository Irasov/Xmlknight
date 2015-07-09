package com.epam.irasov.xmlknight.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;
@XmlRootElement
public class RangedWeapon extends Weapon {
    @XmlElement
    private int numberOfShells;

    public RangedWeapon() {

    }

    public RangedWeapon(Long id, UUID uuid, String name, Type type, int weight, int price, boolean captured, int numberOfShells) {
        super(id, uuid, name, type, weight, price, captured);
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
        return super.toString() + " |number of shells: " + numberOfShells;
    }
}
