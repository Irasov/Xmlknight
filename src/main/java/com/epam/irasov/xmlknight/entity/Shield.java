package com.epam.irasov.xmlknight.entity;

import java.util.UUID;

public class Shield extends Ammunition {
    private Material material;

    public static class Material {
        String name;

        public String getMaterial() {
            return this.name;
        }

        public void setMaterial(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return  name;
        }
    }

    public Shield() {

    }

    public Shield(Long id, UUID uuid, String name, Type type, int weight, int price, Material material) {
        super(id, uuid, name, type, weight, price);
        this.material = material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }

    @Override
    public String toString() {
        return super.toString() + " | material: " + getMaterial().toString();
    }
}
