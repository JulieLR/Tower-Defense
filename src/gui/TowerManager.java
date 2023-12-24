package gui;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gui.TowerTile;

public class TowerManager {
    public TowerTile TOWER_BLUE, TOWER_ORANGE, TOWER_RED, TOWER_SMALL, TOWER_MEDIUM, TOWER_EXTRA;
    public BufferedImage towerImage;
    public ArrayList<TowerTile> tile= new ArrayList<> ();

    public TowerManager () {
        createTile();
        loadTowerImage();
    }

    private void createTile() {
        tile.add(TOWER_BLUE= new TowerTile(getSprite(0, 0)));
        tile.add(TOWER_ORANGE= new TowerTile(getSprite(1, 0)));
        tile.add(TOWER_RED= new TowerTile(getSprite(2, 0)));
        tile.add(TOWER_SMALL= new TowerTile(getSprite(3, 0)));
        tile.add(TOWER_MEDIUM= new TowerTile(getSprite(4, 0)));
        tile.add(TOWER_EXTRA= new TowerTile(getSprite(5, 0)));
    }

    private void loadTowerImage() {

    }

    public BufferedImage getSprite (int id) {
        return tile.get(id).getTower();
    }

    private BufferedImage getSprite (int cordX, int cordY) {
		return towerImage.getSubimage(cordX* 16, cordY* 29, 16, 29);
		} 

    
}
