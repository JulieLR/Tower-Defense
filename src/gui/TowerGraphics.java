package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import config.MapConfig;
import config.TowerConfig;
import model.Direction;
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

    // attaque dans la direction voulue en parametre
    private void attaqueDirection (Direction direction) {
         
    }

    // si la tour fait des degats magiques
    private boolean isPhysic (int ind) {
        return this.towerConfig.getTowers().get(ind).getType()==0;
    }

    // si la tour fait des degats physiques
    private boolean isMagic (int ind) {
        return this.towerConfig.getTowers().get(ind).getType()==1;
    }

    // la i-eme attaque (boule, ou fleche)
    private int iTower (int id) {
        switch (id%6) {
            case 0: return id;
            case 1: 
                break;
            case 2: 
                break;
            case 3: 
                break;
            case 4: 
                break;
            case 5:
                break;
        }

        return 0;
    }

    private void attaqueDraw (Graphics g, long time, Tower t) {
        float n= 200f;
        int t0= (int) (n/t.getVitesseAtk());
        int t1= (int) (n*2/t.getVitesseAtk());
        int t2= (int) (n*3/t.getVitesseAtk());

        /* if (time%t0<t1) {
            g.drawImage(this.towerAsset.get(t.getId()+6), (int)t.getPos().getX(), (int)t.getPos().getY(), this.game.getTileSize(),this.game.getTileSize(), null);
        } 
        else if (time%t0<t2) {
            g.drawImage(this.towerAsset.get(t.getId()+6*2), (int)t.getPos().getX(), (int)t.getPos().getY(), this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else {
            g.drawImage(this.towerAsset.get(t.getId()+6*3), (int)t.getPos().getX(), (int)t.getPos().getY(), this.game.getTileSize(),this.game.getTileSize(), null);
        } */


    }


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
        long time= System.currentTimeMillis();
        for (int i=0; i<this.towerConfig.getNbTower()&& i<6; i++) {
            g.drawImage(towerAsset.get(i+1), (int)this.towerConfig.getPosTower()[i].getX(), (int)this.towerConfig.getPosTower()[i].getY(), this.game.getTileSize(), this.game.getTileSize()*2,null);  
            System.out.println(this.tower.get(i+1).getId());
        }
        for (Tower t: tower) {
            attaqueDraw(g, time, t);
        }
    }

}
