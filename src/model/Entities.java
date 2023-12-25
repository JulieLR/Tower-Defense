package model;

import java.awt.image.BufferedImage;

public class Entities {

    private float vitesseAtk;
    private Coordinates pos;
    private int degat;

    public Entities(float v, Coordinates c, int degat){
        this.vitesseAtk=v;
        this.pos=c;
        this.degat=degat;
    }

    public float getVitesseAtk() {
        return vitesseAtk;
    }

    public void setVitesseAtk(float vitesseAtk) {
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
