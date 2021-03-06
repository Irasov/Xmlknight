package com.epam.irasov.xmlknight.entity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;
@XmlRootElement(name="shield")
public class Shield extends Ammunition {
    private Material material;

    public static class Material extends NamedEntity{
        public Material()  {
        }

        public Material(Long id, UUID uuid, String name) {
            super(id, uuid, name);
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
        return super.toString() + " |material: " + getMaterial().toString();
    }
}
