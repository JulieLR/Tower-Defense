package model;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends Entities{

    private int pointDeVie;
    private int type;
    private int prime;

    private Rectangle zone;
    private boolean atEnd;
    private boolean isSpawned;
    private boolean isAlived;

    private Direction dir = Direction.NORTH;
    private Random r = new Random();


    public Enemy(int vie, Coordinates pos,int degat, float vitesseAtk,int type, int prime){
        super(vitesseAtk, pos, degat);
        this.pointDeVie=vie;
        this.type=type;
        this.prime=prime;
        this.zone= new Rectangle( (int) super.getPos().getX(), (int) super.getPos().getY(),48,48);
    }

    public int getPointDeVie() {
        return pointDeVie;
    }

    public void setPointDeVie(int pointDeVie) {
        this.pointDeVie = pointDeVie;
    }

    public float getSpeed() {
        return super.getVitesseAtk();
    }

    public void setSpeed(int speed) {
        super.setVitesseAtk(speed);
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

    public Coordinates getPos(){
        return super.getPos();
    }
    
    public void setPos(Coordinates pos){
        super.setPos(pos);
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }
    public boolean isAtEnd() {
        return atEnd;
    }

    public void setAtEnd(boolean atEnd) {
        this.atEnd = atEnd;
    }

    public boolean isSpawned() {
        return isSpawned;
    }

    public void setSpawned(boolean isSpawned) {
        this.isSpawned = isSpawned;
    }

    public boolean isAlived() {
        return isAlived;
    }

    public void setAlived(boolean isAlived) {
        this.isAlived = isAlived;
    }

    public int getPrime() {
        return prime;
    }

    public void setPrime(int prime) {
        this.prime = prime;
    }

    public void move(float x, float y){
        this.setPos(new Coordinates(this.getPos().getX()+x, this.getPos().getY()+y));
    }

    public void move(Direction dir){
        this.dir=dir;
        switch(dir){
            case EAST:this.setPos(new Coordinates(this.getPos().getX()+super.getVitesseAtk(), this.getPos().getY()));
                break;
            case NORTH: this.setPos(new Coordinates(this.getPos().getX(), this.getPos().getY()-super.getVitesseAtk()));
                break;
            case SOUTH: this.setPos(new Coordinates(this.getPos().getX(), this.getPos().getY()+super.getVitesseAtk()));
                break;
            case WEST: this.setPos(new Coordinates(this.getPos().getX()-super.getVitesseAtk(), this.getPos().getY()));
                break;
        } 
    }

    public void setPos(int x, int y){
        super.setPos(new Coordinates(x,y));
    }
}
