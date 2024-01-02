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

    private Tower test;

    public void setTest(Tower test) {
        this.test = test;
    }

    public TowerGraphics (Game game, TowerConfig towerConfig) {
        this.game= game;
        this.towerConfig= towerConfig;
        this.tower= towerConfig.getMouseTowers();
        this.addAsset();
    }

    // attaque selon l'angle en radian voulu en paramètre
    private double[] attackCorner (double corner) {
        double[] tab= new double[2]; 
        tab[0]= Math.cos(-corner); // sens horaire, donc on doit mettre le signe - avant la valeur de l'angle
        tab[1]= Math.sin(-corner); 
        return tab;
         
    }

    public Direction getVerticalPos(Coordinates t, Coordinates e){
        if(e.getY()<t.getY()){
            return Direction.NORTH;
        }else{
            return Direction.SOUTH;
        }
    }

    public Direction getHorizontalePos(Coordinates t, Coordinates e){
        if(e.getX()<t.getX()){
            return Direction.WEST;
        }else{
            return Direction.EAST;
        }
    }

    public float getVerticalNombre(Coordinates t, Coordinates e){
        Direction h= getHorizontalePos(t, e);
        if(h==Direction.NORTH){
            return -((t.getY()-e.getY())/3);
        }
        else{
            return (e.getY()-t.getY())/3;
        }
    }

    public float getHorizontalNombre(Coordinates t, Coordinates e){
        Direction h= getHorizontalePos(t, e);
        if(h==Direction.EAST){
            return (e.getX()-t.getX())/3;
        }
        else{
            return -(t.getX()-e.getX())/3;
        }
    }

    private void attackTower (Graphics g, long time, Tower t) {
        
        if(t.getTarget()!=null){

        float n= 100f;
        int t0= (int) (n/t.getVitesseAtk());
        int t1= (int) (n*2.0f/t.getVitesseAtk());
        int t2= (int) (n*3.0f/t.getVitesseAtk());
        int t3= (int) (n*4.0f/t.getVitesseAtk());

        Coordinates e = this.game.getEnemyConfig().getNextCoor((t.getTarget()));
        float y = getVerticalNombre(t.getPos(), t.getTarget().getPos());
        float x = getHorizontalNombre(t.getPos(), t.getTarget().getPos());

        int nb=6;
        if (time%t3<t0) {
            g.drawImage(
                this.towerAsset.get(t.idColorTower()+nb), 
                (int)(t.getPos().getX()+x), 
                (int)(t.getPos().getY()+y), 
                this.game.getTileSize(),
                this.game.getTileSize(), 
                null);
        } else if (time%t3<t1) {
            g.drawImage(
                this.towerAsset.get(t.idColorTower()+nb*2), 
                (int)(t.getPos().getX()+x*2), 
                (int)(t.getPos().getY()+y*2), 
                this.game.getTileSize(),
                this.game.getTileSize(), 
                null);
        } else if (time%t3<t2) {
            if (t.isMagic()) {
                g.drawImage(
                    this.towerAsset.get(t.idColorTower()+nb*3), 
                    (int)(t.getPos().getX()+x*3), 
                    (int)(t.getPos().getY()+y*3), 
                    this.game.getTileSize(),
                    this.game.getTileSize(), 
                    null);
            } else if (t.isPhysic()) {
                g.drawImage(
                    this.towerAsset.get(t.idColorTower()+nb*2), 
                    (int)(t.getPos().getX()+x*3), 
                    (int)(t.getPos().getY()+y*3),  
                    this.game.getTileSize(),
                    this.game.getTileSize(), 
                    null);
                //g.rotate(Math.toRadians(45), (int)t.getPos().getX()+this.game.getTileSize()*3*attaqueDirection(d)[0]+(this.game.getTileSize()/2), (int)t.getPos().getY()+this.game.getTileSize()*3*attaqueDirection(d)[1]+(this.game.getTileSize()/2));
            }

        } }
    }

    private void attackTowerDraw (Graphics g, long time, Tower t, double corner) {
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
                (int) (t.getPos().getX()+this.game.getTileSize()*attackCorner(corner)[0]), 
                (int) (t.getPos().getY()+this.game.getTileSize()*attackCorner(corner)[1]- this.game.getTileSize()), 
                this.game.getTileSize(),
                this.game.getTileSize(), 
                null);
        } else if (time%t3<t1) {
            g.drawImage(
                this.towerAsset.get(t.idColorTower()+nb*2), 
                (int) (t.getPos().getX()+this.game.getTileSize()*2*attackCorner(corner)[0]), 
                (int) (t.getPos().getY()+this.game.getTileSize()*2*attackCorner(corner)[1]- this.game.getTileSize()), 
                this.game.getTileSize(),
                this.game.getTileSize(), 
                null);
        } else if (time%t3<t2) {
            if (t.isMagic()) {
                g.drawImage(
                    this.towerAsset.get(t.idColorTower()+nb*3), 
                    (int) (t.getPos().getX()+this.game.getTileSize()*3*attackCorner(corner)[0]), 
                    (int) (t.getPos().getY()+this.game.getTileSize()*3*attackCorner(corner)[1]- this.game.getTileSize()), 
                    this.game.getTileSize(),
                    this.game.getTileSize(), 
                    null);
            } else if (t.isPhysic()) {
                g.drawImage(
                    this.towerAsset.get(t.idColorTower()+nb*2), 
                    (int) (t.getPos().getX()+this.game.getTileSize()*3*attackCorner(corner)[0]), 
                    (int) (t.getPos().getY()+this.game.getTileSize()*3*attackCorner(corner)[1]- this.game.getTileSize()), 
                    this.game.getTileSize(),
                    this.game.getTileSize(), 
                    null);
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
        this.tower= towerConfig.getMouseTowers();
        for (Tower t: tower) {
            g.drawImage(
                towerAsset.get(t.idColorTower()), 
                (int)t.getPos().getX(), 
                (int)t.getPos().getY()- this.game.getTileSize(), 
                this.game.getTileSize(), 
                this.game.getTileSize()*2,
                null); 
            ////attackTowerDirectionDraw(g, time, t, Direction.SOUTH);
            attackTower(g, time, t);
            //attackTowerDraw(g,time,t,Direction.EAST);
            //attackTowerCornerDraw(g, time, t, Math.PI/4);
            //attackTowerCornerDraw(g, time, t, Math.PI);
            drawZone(g,t);
        }
        if(test!=null){
            drawOneTower(test, g);
        }
        // attaqueDraw(g, time, this.towerConfig.getTowers().get(1));
    }
    public void drawZone(Graphics g, Tower t){
        g.drawRect((int)t.getAttackZone().getX(), (int)t.getAttackZone().getY(), (int)t.getAttackZone().getWidth(), (int)t.getAttackZone().getHeight());
    }

    public ArrayList<BufferedImage> getTowerIcons(){
        ArrayList<BufferedImage> icons = new ArrayList<>();
        for(int i=0; i<6;i++){
            icons.add(this.towerAsset.get(i));
        }
        return icons;
    }

    public void drawOneTower(Tower t, Graphics g){
        g.drawImage(this.towerAsset.get(t.idColorTower()), (int)t.getPos().getX(), (int)t.getPos().getY(),this.game.getTileSize(), this.game.getTileSize()*2, null);
    }
}
