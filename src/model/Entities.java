package model;

import java.awt.image.BufferedImage;

public class Entities {

    private int vitesseAtk;
    private Coordinates pos;
    private int degat;
    private BufferedImage img;

    public Entities(int v, Coordinates c, int degat, BufferedImage img){
        this.vitesseAtk=v;
        this.pos=c;
        this.degat=degat;
        this.img=img;
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

     public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }
}
