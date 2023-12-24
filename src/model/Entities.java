package model;

public class Entities {

    private int vitesseAtk;
    private Coordinates pos;
    private int degat;

    public Entities(int v, Coordinates c, int degat){
        this.vitesseAtk=v;
        this.pos=c;
        this.degat=degat;
    }

    public int getVitesseAtk() {
        return vitesseAtk;
    }

    public void setVitesseAtk(int vitesseAtk) {
        this.vitesseAtk = vitesseAtk;
    }

    public int getDegat() {
        return degat;
    }

    public void setDegat(int degat) {
        this.degat = degat;
    }

    public Coordinates getPos() {
        return pos;
    }

    public void setPos(Coordinates pos) {
        this.pos = pos;
    }
}
