package model;

public class Knight extends Enemy {

    private int rang;

    public Knight(int n, Coordinates pos) {
        super(200, pos, 1, 0.5f, 0,1);
        this.rang=n;
        if(rang==1){
            super.setPointDeVie(400);
            super.setDegat(5);
            super.setVitesseAtk(0.7f);
            super.setPrime(5);
        }
    }
    
}
