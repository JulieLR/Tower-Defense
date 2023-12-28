package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import config.MapConfig;
import config.TowerConfig;
import model.Enemy;
import model.Tower;

public class TowerGraphics implements Graphic {
    private Game game;
    private TowerConfig towerConfig;
    private ArrayList<BufferedImage> towerAsset = new ArrayList<>();
    private ArrayList<Tower> tower;

    public TowerGraphics (Game game, TowerConfig towerConfig) {
        this.game= game;
        this.towerConfig= towerConfig;
        this.tower= towerConfig.getTowers();

        addAsset();
        //loadTowerImages();
    }

    /* private void loadTowerImages () {
        //towerImage= new BufferedImage[6];
        //addAsset();
        
    } */

    @Override
    public void addAsset(){
        for (int ligne=0; ligne<6; ligne++) {
            for (int col= 0; col<6; col++) {
                towerAsset.add(this.game.getTowerImage().getSubimage(col*this.game.getInitialTileSize(), ligne*this.game.getInitialTileSize(), this.game.getInitialTileSize(), this.game.getInitialTileSize()));
            }
        }
    }

    public void drawTower(Graphics g, Tower tow) {
        g.drawImage(this.towerAsset.get(6), (int)tow.getPos().getX(), (int)tow.getPos().getY()-32, this.game.getTileSize(),this.game.getTileSize(), null);
    }

    @Override
    public void drawImages(Graphics g) {
        for (Tower tow: tower)
        drawTower(g, tow);
    }

}
