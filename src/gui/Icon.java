package gui;

import model.Tower;
import model.Coordinates;
import java.awt.image.BufferedImage;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Rectangle;

public class Icon{
    
    private BufferedImage image;
    private int towernb;
    private Rectangle zone;
    private int level;
    private Coordinates Coordinates;
    private Tower actualTower;


    public Icon(BufferedImage img, int tower){
        this.image= img;
        this.towernb=tower;
    }

    public Icon(int level){
        this.level =level;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getTower() {
        return towernb;
    }

    public void setTower(int tower) {
        this.towernb = tower;
    }

    public Rectangle getZone() {
        return zone;
    }

    public void setZone(Rectangle zone) {
        this.zone = zone;
    }

    public int getLevel() {
        return level;
    }

    public Coordinates getImgCoordinates() {
        return Coordinates;
    }

    public void setImgCoordinates(Coordinates coordinates) {
        Coordinates = coordinates;
    }

    public void setActualTower(Tower actualTower) {
        this.actualTower = actualTower;
    }

    public Tower getActualTower() {
        return actualTower;
    }
}
