package com.epam.irasov.xmlknight.entity;

public class MelleWeapon extends Weapon {

    public enum MelleWeaponType {SWORD,DAGER,MACE,SPEAR};

    private int lengthWeapon;
    private MelleWeaponType type;

    public MelleWeapon(){

    }

    public MelleWeapon(ConstAmmunition name,MelleWeaponType type,int weight,int price,Captured captured, int lengthWeapon){
        super(name,weight,price,captured);
        this.lengthWeapon = lengthWeapon;
        this.type = type;
    }

    public void setType(MelleWeaponType type){
        this.type = type;
    }

    public MelleWeaponType getType(){
        return this.type;
    }

    public void setLength_weapon(int length_weapon) {
        this.lengthWeapon = length_weapon;
    }

    public int getLength_weapon() {
        return lengthWeapon;
    }

    @Override
    public String toString(){
        return super.toString()+" | type: "+type+" | length weapon(cm): "+lengthWeapon;
    }
}