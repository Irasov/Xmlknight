package com.epam.irasov.xmlknight.entity;

public class Helmet extends Ammunition {
    public enum  HelmetType {NORMAN,DORIE,DEAF};
    public enum ConstBalaclava {THERE_BALACLAVA,NO_BALACLAVA};

    private HelmetType type;
    private ConstBalaclava balaclava;

    public Helmet(){

    }

    public Helmet(ConstAmmunition name,HelmetType type,int weight,int price, ConstBalaclava balaclava){
        super(name,weight,price);
        this.balaclava=balaclava;
        this.type = type;
    }

    public void setType(HelmetType type){
        this.type = type;
    }

    public HelmetType getType(){
        return this.type;
    }

    public void setBalaclava(ConstBalaclava balaclava) {
        this.balaclava = balaclava;
    }

    public ConstBalaclava getBalaclava(){
        return balaclava;
    }

    @Override
    public String toString(){
        return super.toString()+" | type: "+type+" | balaclava: "+balaclava;
    }
}
