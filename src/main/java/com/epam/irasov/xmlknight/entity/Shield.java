package com.epam.irasov.xmlknight.entity;

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
    }

    public Shield() {

    }

    public Shield(String name, Type type, int weight, int price, Material material) {
        super(name, type, weight, price);
        this.material = material;
    }

    public void setMaterial(Material material) {
        this.material=material;
    }

    public Material getMaterial() {
        return material;
    }

    @Override
    public String toString() {
        return super.toString() + " | material: " +getMaterial();
    }
}
