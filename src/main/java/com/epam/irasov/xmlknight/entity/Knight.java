package com.epam.irasov.xmlknight.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "knight")
public class Knight extends BaseEntity {
    private String name;
    private List<Ammunition> ammunitionList;
    private int ammunitionPrice;

    public Knight() {
        super();
        ammunitionList = new ArrayList<Ammunition>();
    }

    public Knight(String nameKnight) {
        this();
        this.name = nameKnight;
    }

    public void setName(String knightName) {
        this.name = knightName;
    }

    public String getName() {
        return name;
    }

    @XmlElements({
            @XmlElement(name = "armor", type = Armor.class),
            @XmlElement(name = "helmet", type = Helmet.class),
            @XmlElement(name = "meleeWeapon", type = MeleeWeapon.class),
            @XmlElement(name = "rangedWeapon", type = RangedWeapon.class),
            @XmlElement(name = "shield", type = Shield.class)
    })
    @XmlElementWrapper
    public void setAmmunitionList(ArrayList<Ammunition> ammunitionList) {
        this.ammunitionList = ammunitionList;
        for (Ammunition ammunition : ammunitionList) {
            this.ammunitionPrice += ammunition.getPrice();
        }
    }

    public List<Ammunition> getAmmunitionList() {
        return ammunitionList;
    }

    public void addAmmunition(Ammunition ammunitionTo) {
        this.ammunitionList.add(ammunitionTo);
        this.ammunitionPrice += ammunitionTo.getPrice();
    }

    public int getAmmunitionPrice() {
        return this.ammunitionPrice;
    }

    @Override
    public String toString() {
        String str = " Name knight: " + name + "\n" + "Ammunition:\n";
        for (Ammunition element : ammunitionList) {
            str += element.toString() + "\n";
        }
        str += "Ammunition price: " + getAmmunitionPrice();
        return super.toString() + str;
    }
}
