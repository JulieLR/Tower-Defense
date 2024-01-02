package model;

import gui.Game;

public class Base {
    
    private Game game;
    private int pointDeVie, argent;

    public Base(Game game,int p){
        this.game=game;
        this.pointDeVie=p;
        this.argent=200;
    }

    public void setPointDeVie(int pointDeVie) {
        this.pointDeVie = pointDeVie;
    }

    public int getPointDeVie() {
        return pointDeVie;
    }

    public int getArgent() {
        return argent;
    }

    public void setArgent(int argent) {
        this.argent = argent;
    }

    public void enleveArgent(int somme){
        if(this.argent-somme<0){
            this.argent=0;
        }
        else{
            this.argent-=somme;
        }
    }

    public boolean isDestroyed(){
        if(pointDeVie<=0){
            return true;
        }
        return false;
    }
    
}
