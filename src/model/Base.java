package model;

import gui.Game;

public class Base {
    
    private Game game;
    private int pointDeVie;

    public Base(Game game,int p){
        this.game=game;
        this.pointDeVie=p;
    }

    public void setPointDeVie(int pointDeVie) {
        this.pointDeVie = pointDeVie;
    }

    public int getPointDeVie() {
        return pointDeVie;
    }

    public boolean isDestroyed(){
        if(pointDeVie<=0){
            return true;
        }
        return false;
    }
    
}
