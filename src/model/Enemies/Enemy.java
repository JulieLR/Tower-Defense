package model.Enemies;

import java.awt.Rectangle;

import gui.Game;
import model.Coordinates;
import model.Direction;
import model.Entities;

public class Enemy extends Entities{

    private Game game;

    private int pointDeVie;
    private int type;
    private int prime;
    private int number; 

    private Rectangle zone;
    private Rectangle hitBox;
    private boolean atEnd;
    private boolean isSpawned;
    private boolean isAlived;
    private boolean deadAnimation;
    private final float initialSpeed;

    private Direction dir = Direction.SOUTH;

    private long timeDead;

    public Enemy(int vie, Coordinates pos,int degat, float vitesseAtk,int type, int prime, Game game){
        super(vitesseAtk, pos, degat);
        this.pointDeVie=vie;
        this.type=type;
        this.prime=prime;
        this.game=game;
        this.deadAnimation=false;
        this.initialSpeed=vitesseAtk;
        this.zone= new Rectangle( (int) this.getPos().getX(), (int) this.getPos().getY()-this.game.getTileSize()/this.game.getScale(),this.game.getTileSize(),this.game.getTileSize());
        this.hitBox= new Rectangle( (int) this.getPos().getX()+this.game.getTileSize()/3, (int) this.getPos().getY()-(this.game.getTileSize()/this.game.getScale())+this.game.getTileSize()/3,this.game.getTileSize()/3,this.game.getTileSize()/3);
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

    public void setSpeed(float speed) {
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

    public float getInitialSpeed(){
        return this.initialSpeed;
    }

    public void setZone() {
        this.zone = new Rectangle( (int) this.getPos().getX()+this.game.getTileSize()/2, (int) this.getPos().getY()-(this.game.getTileSize()/this.game.getScale())+this.game.getTileSize()/2,this.game.getTileSize(),this.game.getTileSize());
    }

    public Rectangle getHitBox() {
        return this.hitBox;
    }
    public void setHitBox() {
        this.hitBox= new Rectangle( (int) this.getPos().getX()+this.game.getTileSize()/3, (int) this.getPos().getY()-(this.game.getTileSize()/this.game.getScale())+this.game.getTileSize()/3,this.game.getTileSize()/3,this.game.getTileSize()/3);
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

    public boolean getDeadAnimation() {
        return deadAnimation;
    }

    public void setDeadAnimation(boolean deadAnimation) {
        this.deadAnimation = deadAnimation;
    }

    public long getTimeDead() {
        return timeDead;
    }

    public void setTimeDead(long timeDead) {
        this.timeDead = timeDead;
    }

    public void move(float x, float y){
        this.setPos(new Coordinates(this.getPos().getX()+x, this.getPos().getY()+y));
    }

    public void move(Direction dir){
        this.dir=dir;
        switch(dir){
            case EAST:this.setPos(new Coordinates(this.getPos().getX()+this.getVitesseAtk(), this.getPos().getY()));
                break;
            case NORTH: this.setPos(new Coordinates(this.getPos().getX(), this.getPos().getY()-this.getVitesseAtk()));
                break;
            case SOUTH: this.setPos(new Coordinates(this.getPos().getX(), this.getPos().getY()+this.getVitesseAtk()));
                break;
            case WEST: this.setPos(new Coordinates(this.getPos().getX()-this.getVitesseAtk(), this.getPos().getY()));
                break;
        }
        this.setZone(); 
        this.setHitBox();
    }

    public void setPos(int x, int y){
        super.setPos(new Coordinates(x,y));
    }

    public int getNumber() {
        return this.number;
    }
    
    public void setNumber(int n) {
        this.number=n;
    }
}
