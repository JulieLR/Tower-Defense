package gui;

import java.awt.image.BufferedImage;

public class TowerTile {
    private BufferedImage tower;

    public TowerTile(BufferedImage tower) {
        this.tower=tower;
    }

    public BufferedImage getTower () {
        return this.tower;
    }
    
}
