package com.epam.irasov.xmlknight.entity;

import java.util.Comparator;

public abstract class Ammunition {
    public static final Comparator<Ammunition> WEIGHT_ORDER = new WeightComparator();
    public static final Comparator<Ammunition> NAME_ORDER = new NameComparator();
    public static final Comparator<Ammunition> PRICE_ORDER = new PriceComparator();
    private NameAmmunition name = new NameAmmunition();
    private AmmunitionType type = new AmmunitionType();
    private int weight;
    private int price;


    protected class AmmunitionType {
        private String type;

        public void setType(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }
    }

    protected class NameAmmunition {
        private String name;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    public Ammunition() {

    }

    public Ammunition(String name, String type, int weight, int price) {
        this.name.setName(name);
        this.type.setType(type);
        this.weight = weight;
        this.price = price;
    }

    public void setName(String name) {
        this.name.setName(name);
    }

    public String getName() {
        return this.name.getName();
    }

    public void setType(String type) {
        this.type.setType(type);
    }

    public String getType() {
        return this.type.getType();
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return this.price;
    }

    public static class WeightComparator implements Comparator<Ammunition> {
        public int compare(Ammunition o1, Ammunition o2) {
            return o1.getWeight() - o2.getWeight();
        }
    }

    public static class PriceComparator implements Comparator<Ammunition> {
        public int compare(Ammunition o1, Ammunition o2) {
            return o1.getPrice() - o2.getPrice();
        }
    }

    public static class NameComparator implements Comparator<Ammunition> {
        public int compare(Ammunition o1, Ammunition o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ammunition)) return false;
        Ammunition that = (Ammunition) o;
        if (weight != that.weight) return false;
        if (price != that.price) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return !(type != null ? !type.equals(that.type) : that.type != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + weight;
        result = 31 * result + price;
        return result;
    }

    @Override
    public String toString() {
        return "name: " + getName() + " |ammunition type: " + getType() + " | weight(kg): " + getWeight() + " | price(gold): " + getPrice();
    }
}
