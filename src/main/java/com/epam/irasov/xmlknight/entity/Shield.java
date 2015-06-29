package com.epam.irasov.xmlknight.entity;

public class Shield extends Ammunition {
    public enum ShieldType {CROWS,NORMAN,BUCKLER,TORCH,PAVESE};
    public enum ShieldMaterial {METAL,TREE};
    private ShieldMaterial material;
    private ShieldType type;


    public Shield(){

    }

    public Shield(ConstAmmunition name,ShieldType type,int weight,int price,ShieldMaterial material){
        super(name,weight,price);
        this.material=material;
        this.type = type;
    }

    public void setType(ShieldType type){
        this.type = type;
    }

    public ShieldType getType(){
        return this.type;
    }

    public void setMaterial(ShieldMaterial material) {
        this.material = material;
    }

    public ShieldMaterial getMaterial() {
        return material;
    }

    @Override
    public String toString(){
        return super.toString()+" | type: "+type+" | material: "+material;
    }
}
