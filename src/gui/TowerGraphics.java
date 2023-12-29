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
    private int[] attaqueDirection (Direction direction) {
        int[] tab= new int[2];
        switch (direction) {
            case WEST: tab[0]=-1; tab[1]=0; break;
            case EAST: tab[0]=1; tab[1]=0; break;
            case NORTH: tab[0]=0; tab[1]=-1; break;
            case SOUTH:tab[0]=0; tab[1]=1; break;
        }
        return tab;
         
    }

    // la i-eme attaque (boule, ou fleche)
    private int idAttaqueTower (int id) {
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

    private void attaqueDraw/*Magic*/ (Graphics g, long time, Tower t, Direction d) {
        float n= 800f;
        int t0= (int) (n*3/t.getVitesseAtk());
        int t1= (int) (n*2/t.getVitesseAtk());
        int t2= (int) (n/t.getVitesseAtk());

        int nb=6;
        System.out.println(this.towerConfig.getTower().idTower());
        //if (isMagic(t.getId())) {
        if (time%t0<t1) {
            g.drawImage(this.towerAsset.get(this.towerConfig.getTower().idTower()+nb), (int)t.getPos().getX()+this.game.getTileSize()*attaqueDirection(d)[0], (int)t.getPos().getY()+this.game.getTileSize()*attaqueDirection(d)[1], this.game.getTileSize(),this.game.getTileSize(), null);
        } else if (time%t0<t2) {
            g.drawImage(this.towerAsset.get(this.towerConfig.getTower().idTower()+nb*2), (int)t.getPos().getX()+this.game.getTileSize()*2*attaqueDirection(d)[0], (int)t.getPos().getY()+this.game.getTileSize()*2*attaqueDirection(d)[1], this.game.getTileSize(),this.game.getTileSize(), null);
        } else {
            g.drawImage(this.towerAsset.get(this.towerConfig.getTower().idTower()+nb*3), (int)t.getPos().getX()+this.game.getTileSize()*3*attaqueDirection(d)[0], (int)t.getPos().getY()+this.game.getTileSize()*3*attaqueDirection(d)[1], this.game.getTileSize(),this.game.getTileSize(), null);
        } 
        //}
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
            //System.out.println(this.tower.get(i+1).getId());
        }
        for (Tower t: tower) {
            attaqueDraw/*Magic*/(g, time, t, Direction.EAST);
        }
        // attaqueDraw(g, time, this.towerConfig.getTowers().get(1));
    }

}
