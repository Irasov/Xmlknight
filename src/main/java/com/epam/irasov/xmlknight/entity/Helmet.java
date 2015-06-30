package com.epam.irasov.xmlknight.entity;

public class Helmet extends Ammunition {
    private Balaclava balaclava = new Balaclava();

    protected class Balaclava {
        private String balaclava;

        public void setBalaclava(String balaclava) {
            this.balaclava = balaclava;
        }

        public String getBalaclava() {
            return this.balaclava;
        }
    }

    public Helmet() {

    }

    public Helmet(String name, String type, int weight, int price, String balaclava) {
        super(name, type, weight, price);
        this.balaclava.setBalaclava(balaclava);
    }

    public void setBalaclava(String balaclava) {
        this.balaclava.setBalaclava(balaclava);
    }

    public String getBalaclava() {
        return balaclava.getBalaclava();
    }

    @Override
    public String toString() {
        return super.toString() + " | balaclava: " + balaclava.getBalaclava();
    }
}
