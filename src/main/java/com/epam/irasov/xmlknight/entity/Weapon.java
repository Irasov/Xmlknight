package com.epam.irasov.xmlknight.entity;

public abstract class Weapon extends Ammunition {
    private Captured captured = new Captured();

    protected class Captured{
        String captured;

        public String getCaptured() {
            return this.captured;
        }

        public void setCaptured(String captured) {
            this.captured = captured;
        }
    }

    public Weapon(){

    }

    public Weapon(String name,String type, int weight, int price, Captured captured){
        super(name,type,weight,price);
        this.captured=captured;
    }

    public void setCaptured(String captured) {
        this.captured.setCaptured(captured);
    }

    public String getCaptured(){
        return captured.getCaptured();
    }

    @Override
    public String toString(){
        return super.toString()+" | captured: "+captured.getCaptured();
    }
}
