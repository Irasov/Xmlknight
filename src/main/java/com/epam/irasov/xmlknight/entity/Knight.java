package com.epam.irasov.xmlknight.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Knight extends BaseEntity {
    private String name;
    @XmlElement(name="ammunition")
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
