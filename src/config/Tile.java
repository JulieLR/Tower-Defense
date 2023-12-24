package config;

import java.awt.image.BufferedImage;

public class Tile {

    public enum Type{PATH,GRASS,WATER,CASTLE,TOWER,BAR};
    private BufferedImage image; // image du tile
    private BufferedImage secondImg;

    private boolean isPath=false;
    private int value;

    private Type type;

    public Tile(BufferedImage img, Type type, int v){
        this.image=img;
        this.type=type;
        this.value=v;
    }

    public Tile(BufferedImage img,BufferedImage sImg, Type type, int v){
        this.image=img;
        this.secondImg=sImg;
        this.type=type;
        this.value=v;
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
}
