package gui;

import model.Tower;
import java.awt.image.BufferedImage;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Rectangle;

public class Icon{
    
    private BufferedImage image;
    private int tower;
    private Rectangle zone;

    public Icon(BufferedImage img, int tower){
        this.image= img;
        this.tower=tower;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getTower() {
        return tower;
    }

    public void setTower(int tower) {
        this.tower = tower;
    }

    public Rectangle getZone() {
        return zone;
    }

    public void setZone(Rectangle zone) {
        this.zone = zone;
    }
}
