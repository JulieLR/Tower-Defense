package model;

import gui.Game;

public class Knight extends Enemy {

    private int rang;

    public Knight(int n, Coordinates pos,Game game) {
        super(500, pos, 1, 0.5f, 0,10,game);
        this.rang=n;
        if(rang==1){
            super.setPointDeVie(800);
            super.setDegat(5);
            super.setVitesseAtk(0.7f);
            super.setPrime(5);
        }
    }
    
}
