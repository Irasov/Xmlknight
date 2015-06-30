package com.epam.irasov.xmlknight.entity;

public class Shield extends Ammunition {
    private ShieldMaterial material;

    protected class ShieldMaterial {
        String material;

        public String getMaterial() {
            return this.material;
        }

        public void setMaterial(String material) {
            this.material = material;
        }
    }

    public Shield() {

    }

    public Shield(String name, String type, int weight, int price, String material) {
        super(name, type, weight, price);
        this.material.setMaterial(material);
    }

    public void setMaterial(String material) {
        this.material.setMaterial(material);
    }

    public String getMaterial() {
        return material.getMaterial();
    }

    @Override
    public String toString() {
        return super.toString() + " | material: " + material.getMaterial();
    }
}
