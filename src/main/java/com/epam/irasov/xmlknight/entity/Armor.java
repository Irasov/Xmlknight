package com.epam.irasov.xmlknight.entity;

public class Armor extends Ammunition {
    private Protection protection;

    public static class Protection {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public Armor() {

    }

    public Armor(String name, Type type, int weight, int price, Protection protection) {
        super(name, type, weight, price);
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
        return super.toString() + " | protection: " + getProtection();
    }
}
