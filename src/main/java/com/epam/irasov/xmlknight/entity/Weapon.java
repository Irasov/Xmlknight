package com.epam.irasov.xmlknight.entity;

import java.util.UUID;

public abstract class Weapon extends Ammunition {
    private boolean captured;

    public Weapon() {

    }

    public Weapon(Long id, UUID uuid, String name, Type type, int weight, int price, boolean captured) {
        super(id, uuid, name, type, weight, price);
        this.captured = captured;
    }

    public void setCaptured(boolean captured) {
        this.captured = captured;
    }

    public boolean getCaptured() {
        return captured;
    }

    @Override
    public String toString() {
        return super.toString() + " | captured: " + getCaptured();
    }
}
