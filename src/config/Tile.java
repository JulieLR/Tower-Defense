package config;

import java.awt.image.BufferedImage;

import model.Coordinates;

public class Tile {

    public enum Type{PATH,GRASS,WATER,CASTLE,TOWER,BAR,END};
    private BufferedImage image; // image du tile
    private BufferedImage secondImg;
    private int img, sndImg;

    private int value;
    private Coordinates tileCoor;

    public Coordinates getTileCoor() {
        return tileCoor;
    }

    private Type type;

    /* public Tile(BufferedImage img, Type type, int v,Coordinates coor){
        this.image=img;
        this.type=type;
        this.value=v;
        this.tileCoor=coor;
    }

    public Tile(BufferedImage img,BufferedImage sImg, Type type, int v){
        this.image=img;
        this.secondImg=sImg;
        this.type=type;
        this.value=v; 
    }*/

    public Tile(int img, Type type, int v,Coordinates coor){
        this.img=img;
        this.type=type;
        this.value=v;
        this.tileCoor=coor;
    }

    public Tile(int img, int sndImg, Type type, int v,Coordinates coor){
        this(img,type,v,coor);
        this.sndImg=sndImg;
    }

    public Type getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public BufferedImage getImage() {
        return image;
    }
    
    public BufferedImage getSecondImg() {
        return secondImg;
    }

    public int getImg() {
        return img;
    }

    public int getSndImg() {
        return sndImg;
    }

    public String typeToString(){
        String s="";
        switch(this.type){
            case BAR: s="BAR";
                break;
            case CASTLE: s="CASTLE";
                break;
            case GRASS: s="GRASS";
                break;
            case PATH: s="PATH";
                break;
            case TOWER: s="TOWER";
                break;
            case WATER: s="WATER";
                break;
            default:
                break;
        }
    return s;
    }
}
