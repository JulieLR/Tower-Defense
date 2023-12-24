package model;

import java.awt.Rectangle;

public class Enemy extends Entities{

    private int pointDeVie;
    private int speed;
    private int type;
    private Rectangle zone;

    public Enemy(int vie,int speed, Coordinates pos,int degat, int vitesseAtk){
        super(vitesseAtk, pos, degat);
        this.pointDeVie=vie;
        this.speed=speed;
        this.zone= new Rectangle( (int) super.getPos().getX(), (int) super.getPos().getY(),48,48);
    }

    public int getPointDeVie() {
        return pointDeVie;
    }

    public void setPointDeVie(int pointDeVie) {
        this.pointDeVie = pointDeVie;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Rectangle getZone() {
        return zone;
    }

    public void setZone(Rectangle zone) {
        this.zone = zone;
    }
}
