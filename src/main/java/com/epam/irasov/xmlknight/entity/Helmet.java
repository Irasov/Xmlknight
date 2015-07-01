package com.epam.irasov.xmlknight.entity;

import java.util.UUID;

public class Helmet extends Ammunition {
    private boolean balaclava;

    public Helmet() {

    }

    public Helmet(Long id, UUID uuid, String name, Type type, int weight, int price, boolean balaclava) {
        super(id, uuid, name, type, weight, price);
        this.balaclava = balaclava;
    }

    public void setBalaclava(boolean balaclava) {
        this.balaclava = balaclava;
    }

    public boolean getBalaclava() {
        return balaclava;
    }

    @Override
    public String toString() {
        return super.toString() + " | balaclava: " + getBalaclava();
    }
}
