package com.epam.irasov.xmlknight.entity;

import javax.xml.bind.annotation.*;
import java.util.UUID;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "armor", propOrder = {
        "protection"
})
public class Armor extends Ammunition {
    private Protection protection;

    @XmlRootElement
    public static class Protection extends NamedEntity {
        public Protection() {
        }

        public Protection(Long id, UUID uuid, String name) {
            super(id, uuid, name);
        }
    }

    public Armor() {

    }

    public Armor(Long id, UUID uuid, String name, Type type, int weight, int price, Protection protection) {
        super(id, uuid, name, type, weight, price);
        this.protection = protection;
    }

    public void setProtection(Protection protection) {
        this.protection = protection;
    }

    public Protection getProtection() {
        return protection;
    }

    @Override
    public String toString() {
        return super.toString() + " |protection: " + getProtection().toString();
    }
}
