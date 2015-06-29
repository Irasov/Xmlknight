package com.epam.irasov.xmlknight.entity;

import java.util.ArrayList;
import java.util.List;

public class Knight {
    private String nameKnight;
    private List<Ammunition> ammunitionList;
    private int calculatePrice;

    public Knight(String nameKnight){
        this.nameKnight=nameKnight;
        ammunitionList = new ArrayList<Ammunition>();
        this.calculatePrice=0;
    }

    public void setNameKnight(String nameKnight){
        this.nameKnight=nameKnight;
    }

    public String getNameKnight(){
        return nameKnight;
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
        String str=" Name knight: "+nameKnight+"\n"+"Ammunition:\n";
        for(Ammunition element:ammunitionList){
            str+=element.toString()+"\n";
        }
        str+="Sum gold: "+getCalculatePrice();
        return str;
    }
}
