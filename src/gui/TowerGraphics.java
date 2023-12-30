package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import config.MapConfig;
import config.TowerConfig;
import config.EnemiesConfig;
import model.Coordinates;
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

        this.addAsset();
        //loadTowerImages();
    }

    /* private void loadTowerImages () {
        //towerImage= new BufferedImage[6];
        //addAsset();
        
    } */

    // attaque dans la direction voulue en parametre
    private float[] attaqueDirection (Direction direction, Tower t) {
        float[] tab= new float[2];
        switch (direction) {
            case WEST: tab[0]=-t.getVitesseAtk(); tab[1]=0; break;
            case EAST: tab[0]=t.getVitesseAtk(); tab[1]=0; break;
            case NORTH: tab[0]=0; tab[1]=-t.getVitesseAtk(); break;
            case SOUTH:tab[0]=0; tab[1]=t.getVitesseAtk(); break;
        }
        return tab;
         
    }

    private void attackTower (Graphics g, long time, Tower t) {
        
        if(t.getTarget()!=null){

        float n= 100f;
        int t0= (int) (n/t.getVitesseAtk());
        int t1= (int) (n*2.0f/t.getVitesseAtk());
        int t2= (int) (n*3.0f/t.getVitesseAtk());
        int t3= (int) (n*4.0f/t.getVitesseAtk());

        Coordinates e = this.game.getEnemyConfig().getNextCoor((t.getTarget()));
        Coordinates target = t.getTarget().getPos();

        int nb=6;
        // System.out.println(t.isMagic());
        if (time%t3<t0) {
            g.drawImage(
                this.towerAsset.get(t.idColorTower()+nb), 
                (int)target.getX(), 
                (int)target.getY(), 
                this.game.getTileSize(),
                this.game.getTileSize(), 
                null);
        } else if (time%t3<t1) {
            g.drawImage(
                this.towerAsset.get(t.idColorTower()+nb*2), 
                (int)target.getX(), 
                (int)target.getY(), 
                this.game.getTileSize(),
                this.game.getTileSize(), 
                null);
        } else if (time%t3<t2) {
            if (t.isMagic()) {
                g.drawImage(
                    this.towerAsset.get(t.idColorTower()+nb*3), 
                    (int)(target.getX()), 
                    (int)(target.getY()), 
                    this.game.getTileSize(),
                    this.game.getTileSize(), 
                    null);
            } else if (t.isPhysic()) {
                g.drawImage(
                    this.towerAsset.get(t.idColorTower()+nb*2), 
                    (int)(target.getX()), 
                    (int)(target.getY()), 
                    this.game.getTileSize(),
                    this.game.getTileSize(), 
                    null);
                //g.rotate(Math.toRadians(45), (int)t.getPos().getX()+this.game.getTileSize()*3*attaqueDirection(d)[0]+(this.game.getTileSize()/2), (int)t.getPos().getY()+this.game.getTileSize()*3*attaqueDirection(d)[1]+(this.game.getTileSize()/2));
            }

        } }
    }

    private void attackTowerDraw (Graphics g, long time, Tower t, Direction d) {
        float n= 150f;
        int t0= (int) (n/t.getVitesseAtk());
        int t1= (int) (n*2.0f/t.getVitesseAtk());
        int t2= (int) (n*3.0f/t.getVitesseAtk());
        int t3= (int) (n*4.0f/t.getVitesseAtk());

        int nb=6;
        // System.out.println(t.isMagic());
        if (time%t3<t0) {
            g.drawImage(
                this.towerAsset.get(t.idColorTower()+nb), 
                (int)(t.getPos().getX()+this.game.getTileSize()*attaqueDirection(d,t)[0]), 
                (int)(t.getPos().getY()+this.game.getTileSize()*attaqueDirection(d,t)[1]), 
                this.game.getTileSize(),
                this.game.getTileSize(), 
                null);
        } else if (time%t3<t1) {
            g.drawImage(
                this.towerAsset.get(t.idColorTower()+nb*2), 
                (int)(t.getPos().getX()+this.game.getTileSize()*2*attaqueDirection(d,t)[0]), 
                (int)(t.getPos().getY()+this.game.getTileSize()*2*attaqueDirection(d,t)[1]), 
                this.game.getTileSize(),
                this.game.getTileSize(), 
                null);
        } else if (time%t3<t2) {
            if (t.isMagic()) {
                g.drawImage(
                    this.towerAsset.get(t.idColorTower()+nb*3), 
                    (int)(t.getPos().getX()+this.game.getTileSize()*3*attaqueDirection(d,t)[0]), 
                    (int)(t.getPos().getY()+this.game.getTileSize()*3*attaqueDirection(d,t)[1]), 
                    this.game.getTileSize(),
                    this.game.getTileSize(), 
                    null);
            } else if (t.isPhysic()) {
                g.drawImage(
                    this.towerAsset.get(t.idColorTower()+nb*2), 
                    (int)(t.getPos().getX()+this.game.getTileSize()*3*attaqueDirection(d,t)[0]), 
                    (int)(t.getPos().getY()+this.game.getTileSize()*3*attaqueDirection(d,t)[1]), 
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
                (int)this.towerConfig.getPosTower()[i].getY()- this.game.getTileSize(), 
                this.game.getTileSize(), 
                this.game.getTileSize()*2,
                null);  
            // System.out.println(this.towerConfig.getTowers().get(i).idColorTower());
        }
        for (Tower t: tower) {
            //attackTowerDraw(g, time, t, Direction.SOUTH);
            attackTower(g, time, t);
            drawZone(g,t);
        }
        
        // attaqueDraw(g, time, this.towerConfig.getTowers().get(1));
    }

    public void drawZone(Graphics g, Tower t){
        g.drawRect((int)t.getAttackZone().getX(), (int)t.getAttackZone().getY(), (int)t.getAttackZone().getWidth(), (int)t.getAttackZone().getHeight());
    }

}
