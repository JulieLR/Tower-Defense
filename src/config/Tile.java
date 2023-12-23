package config;

import java.awt.image.BufferedImage;

public class Tile {

    public enum Type{PATH,GRASS,WATER};
    private BufferedImage image; // image du tile
    private boolean isPath=false;
    private Type type;

    public Tile(BufferedImage img, Type type){
        this.image=img;
        this.type=type;
    }

    public Type getType() {
        return type;
    }

    public BufferedImage getImage() {
        return image;
    }
}
