package com.epam.irasov.xmlknight.entity;

import java.util.ArrayList;
import java.util.List;

public class Knight extends BaseEntity{
    private String knightName;
    private List<Ammunition> ammunitionList;
    private int calculatePrice;

    public Knight(){
        ammunitionList = new ArrayList<Ammunition>();
    }

    public Knight(String nameKnight){
        this();
        this.knightName =nameKnight;
    }

    public void setKnightName(String knightName){
        this.knightName = knightName;
    }

    public String getKnightName(){
        return knightName;
    }

    public void setAmmunitionList(ArrayList<Ammunition> ammunitionList) {
        this.ammunitionList = ammunitionList;
    }

    public List<Ammunition> getAmmunitionList(){
        return ammunitionList;
    }

    public void addAmmunition(Ammunition ammunitionTo){
        this.ammunitionList.add(ammunitionTo);
        this.calculatePrice += ammunitionTo.getPrice();
    }

    public int getCalculatePrice(){
        return this.calculatePrice;
    }

    @Override
    public String toString(){
        String str=" Name knight: "+ knightName +"\n"+"Ammunition:\n";
        for(Ammunition element:ammunitionList){
            str+=element.toString()+"\n";
        }
        str+="Sum gold: "+getCalculatePrice();
        return str;
    }
}
