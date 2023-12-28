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
                if(ligne==0){
                    towerAsset.add(this.game.getTowerImage().getSubimage(col*this.game.getInitialTileSize(), ligne*this.game.getInitialTileSize(), this.game.getInitialTileSize(), this.game.getInitialTileSize()*2));
                }else{
                    towerAsset.add(this.game.getTowerImage().getSubimage(col*this.game.getInitialTileSize(), ligne*this.game.getInitialTileSize(), this.game.getInitialTileSize(), this.game.getInitialTileSize()));

                }
            }
            if(ligne==0){
                ligne++;
            }
        }
    }

    @Override
    public void drawImages(Graphics g) {
        //g.drawImage(towerAsset.get(1), 100, 200, this.game.getTileSize(), this.game.getTileSize()*2,null);
        for (int i=0; i<this.towerConfig.getNbTower()&& i<6; i++) {
            g.drawImage(towerAsset.get(i+1), (int)this.towerConfig.getPosTower()[i].getX(), (int)this.towerConfig.getPosTower()[i].getY(), this.game.getTileSize(), this.game.getTileSize()*2,null);
        }
    }

}
