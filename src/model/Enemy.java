package model;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends Entities{

    private int pointDeVie;
    private float speed;
    private int type;
    private Rectangle zone;
    private Direction dir = Direction.NORTH;
    private Random r = new Random();


    public Enemy(int vie,float speed,BufferedImage img, Coordinates pos,int degat, int vitesseAtk,int type){
        super(vitesseAtk, pos, degat, img);
        this.pointDeVie=vie;
        this.speed=speed;
        this.type=type;
        this.zone= new Rectangle( (int) super.getPos().getX(), (int) super.getPos().getY(),48,48);
    }

    public int getPointDeVie() {
        return pointDeVie;
    }

    public void setPointDeVie(int pointDeVie) {
        this.pointDeVie = pointDeVie;
    }

    public float getSpeed() {
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
    public BufferedImage getImage() {
        return super.getImg();
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

    public void move(float x, float y){
        this.setPos(new Coordinates(this.getPos().getX()+x, this.getPos().getY()+y));
    }

    public void move(Direction dir){
        this.dir=dir;
        switch(dir){
            case EAST:this.setPos(new Coordinates(this.getPos().getX()+speed, this.getPos().getY()));
                break;
            case NORTH: this.setPos(new Coordinates(this.getPos().getX(), this.getPos().getY()-speed));
                break;
            case SOUTH: this.setPos(new Coordinates(this.getPos().getX(), this.getPos().getY()+speed));
                break;
            case WEST: this.setPos(new Coordinates(this.getPos().getX()-speed, this.getPos().getY()));
                break;
        } 
    }

    public void setPos(int x, int y){
        super.setPos(new Coordinates(x,y));
    }

    public Direction getNextMove(ArrayList<Direction> possible){
        int nb = Math.abs(r.nextInt(possible.size()));
        System.out.println(possible.size());
        System.out.println(nb);
        System.out.println(possible.size());
        System.out.println(possible.get(nb).DirToString());

        return possible.get(nb);
    }/* 

    public void  adjustPos(int x, int y, int k){
        switch(dir){
            case EAST:
                 x++;
                break;
            case NORTH:
                 y--;
                break;
            case SOUTH:
                 y++;
                break;
            case WEST:
                x--;
                break;

        }
        this.setPos(new Coordinates((int)x*k,(int)y*k));
        System.out.println(this.getPos().getX()+" : "+ this.getPos().getY());
    }

    public float getSpeedAndHeight(Direction dir) {
		if (dir == Direction.NORTH)
			return -speed;
		else if (dir == Direction.SOUTH)
			return speed+64;

		return 0;
	}

	public float getSpeedAndWidth(Direction dir) {
		if (dir == Direction.WEST)
			return -speed;
		else if (dir == Direction.EAST)
			return speed +64;

		return 0;
	} */
}
