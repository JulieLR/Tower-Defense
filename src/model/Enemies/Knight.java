package model.Enemies;

import gui.Game;
import model.Coordinates;

public class Knight extends Enemy {

    private int rang;


    public Knight(int n, Coordinates pos,Game game) {
        super(300, pos, 2, 0.5f, 0,15,game);
        this.rang=n;
        if(rang==1){
            super.setPointDeVie(800);
            super.setDegat(5);
            super.setVitesseAtk(0.7f);
            super.setType(2);
            super.setPrime(35);
        }
    }

    
}
