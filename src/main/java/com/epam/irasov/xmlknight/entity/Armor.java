package com.epam.irasov.xmlknight.entity;

public class Armor extends Ammunition {
    private Protection protection;

    protected class Protection {
        private String protection;

        public void setProtection(String protection) {
            this.protection = protection;
        }

        public String getProtection() {
            return this.protection;
        }
    }

    public Armor() {

    }

    public Armor(String name, String type, int weight, int price, String protection) {
        super(name, type, weight, price);
        this.protection.setProtection(protection);
    }

    public void setProtection(String protection) {
        this.protection.setProtection(protection);
    }

    public String getProtection() {
        return protection.getProtection();
    }

    @Override
    public String toString() {
        return super.toString() + " | protection: " + protection.getProtection();
    }
}
