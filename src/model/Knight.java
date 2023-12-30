package model;

import gui.Game;

public class Knight extends Enemy {

    private int rang;

    public Knight(int n, Coordinates pos,Game game) {
        super(200, pos, 1, 1f, 0,1,game);
        this.rang=n;
        if(rang==1){
            super.setPointDeVie(400);
            super.setDegat(5);
            super.setVitesseAtk(0.7f);
            super.setPrime(5);
        }
    }
    
}
