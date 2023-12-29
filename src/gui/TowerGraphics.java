package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import config.MapConfig;
import config.TowerConfig;
import model.Direction;
import model.Enemy;
import model.Tower;
import model.Tower.TowerColor;

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

    private void attackTowerDraw (Graphics g, long time, Tower t, Direction d) {
        float n= 150f;
        int t0= (int) (n/t.getVitesseAtk());
        int t1= (int) (n*2/t.getVitesseAtk());
        int t2= (int) (n*3/t.getVitesseAtk());
        int t3= (int) (n*4/t.getVitesseAtk());

        int nb=6;
        // System.out.println(t.isMagic());
        if (time%t3<t0) {
            g.drawImage(
                this.towerAsset.get(t.idColorTower()+nb), 
                (int)t.getPos().getX()+this.game.getTileSize()*attaqueDirection(d)[0], 
                (int)t.getPos().getY()+this.game.getTileSize()*attaqueDirection(d)[1], 
                this.game.getTileSize(),
                this.game.getTileSize(), 
                null);
        } else if (time%t3<t1) {
            g.drawImage(
                this.towerAsset.get(t.idColorTower()+nb*2), 
                (int)t.getPos().getX()+this.game.getTileSize()*2*attaqueDirection(d)[0], 
                (int)t.getPos().getY()+this.game.getTileSize()*2*attaqueDirection(d)[1], 
                this.game.getTileSize(),
                this.game.getTileSize(), 
                null);
        } else if (time%t3<t2) {
            if (t.isMagic()) {
                g.drawImage(
                    this.towerAsset.get(t.idColorTower()+nb*3), 
                    (int)t.getPos().getX()+this.game.getTileSize()*3*attaqueDirection(d)[0], 
                    (int)t.getPos().getY()+this.game.getTileSize()*3*attaqueDirection(d)[1], 
                    this.game.getTileSize(),
                    this.game.getTileSize(), 
                    null);
            } else if (t.isPhysic()) {
                g.drawImage(
                    this.towerAsset.get(t.idColorTower()+nb), 
                    (int)t.getPos().getX()+this.game.getTileSize()*3*attaqueDirection(d)[0], 
                    (int)t.getPos().getY()+this.game.getTileSize()*3*attaqueDirection(d)[1], 
                    this.game.getTileSize(),
                    this.game.getTileSize(), 
                    null);
                //g.rotate(Math.toRadians(45), (int)t.getPos().getX()+this.game.getTileSize()*3*attaqueDirection(d)[0]+(this.game.getTileSize()/2), (int)t.getPos().getY()+this.game.getTileSize()*3*attaqueDirection(d)[1]+(this.game.getTileSize()/2));
            }

        } 
    }

    @Override
    public void addAsset(){
        for (int ligne=0; ligne<6; ligne++) {
            for (int col= 0; col<6; col++) {
                if(ligne==0){
                    towerAsset.add(this.game.getTowerImage().getSubimage(
                        col*this.game.getInitialTileSize(), 
                        ligne*this.game.getInitialTileSize(), 
                        this.game.getInitialTileSize(), 
                        this.game.getInitialTileSize()*2));
                }else{
                    towerAsset.add(this.game.getTowerImage().getSubimage(
                        col*this.game.getInitialTileSize(), 
                        ligne*this.game.getInitialTileSize(), 
                        this.game.getInitialTileSize(), 
                        this.game.getInitialTileSize()));

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
        for (int i=0; i<this.towerConfig.getNbTower(); i++) {
            g.drawImage(
                towerAsset.get(this.towerConfig.getTowers().get(i).idColorTower()), 
                (int)this.towerConfig.getPosTower()[i].getX(), 
                (int)this.towerConfig.getPosTower()[i].getY(), 
                this.game.getTileSize(), 
                this.game.getTileSize()*2,
                null);  
            // System.out.println(this.towerConfig.getTowers().get(i).idColorTower());
        }
        for (Tower t: tower) {
            attackTowerDraw(g, time, t, Direction.EAST);
        }
        
        // attaqueDraw(g, time, this.towerConfig.getTowers().get(1));
    }

}
