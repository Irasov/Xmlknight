package com.epam.irasov.xmlknight.entity;

import java.util.Comparator;
import java.util.UUID;

public abstract class Ammunition extends NamedEntity {
    public static final Comparator<Ammunition> WEIGHT_ORDER = new WeightComparator();
    public static final Comparator<Ammunition> PRICE_ORDER = new PriceComparator();
    private Type type;
    private int weight;
    private int price;

    public static class Type extends NamedEntity {
        public Type() {
        }

        public Type(Long id, UUID uuid, String name) {
            super(id, uuid, name);
        }
    }

    public Ammunition() {
        super();
    }

    public Ammunition(Long id, UUID uuid, String name, Type type, int weight, int price) {
        super(id, uuid, name);
        this.type = type;
        this.weight = weight;
        this.price = price;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return this.type;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ammunition)) return false;
        if (!super.equals(o)) return false;
        Ammunition that = (Ammunition) o;
        if (weight != that.weight) return false;
        if (price != that.price) return false;
        return !(type != null ? !type.equals(that.type) : that.type != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + weight;
        result = 31 * result + price;
        return result;
    }

    @Override
    public String toString() {
        return super.toString()  + " |ammunition type: " + getType().toString() + " | weight(kg): " + getWeight() + " | price(gold): " + getPrice();
    }
}
