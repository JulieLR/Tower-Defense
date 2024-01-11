package gui.Graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import config.TowerConfig;
import gui.Game;
import gui.Frames.Frame;
import model.Coordinates;
import model.Direction;
import model.Tower;

public class TowerGraphics implements Graphic,Frame {
    private Game game;
    private TowerConfig towerConfig;
    private BufferedImage towerImage;
    private ArrayList<BufferedImage> towerAsset = new ArrayList<>();
    private ArrayList<Coordinates> towersEmpty= new ArrayList<>();
    private ArrayList<Tower> tower;

    private Tower test;

    public void setTest(Tower test) {
        this.test = test;
    }

    public TowerGraphics (Game game, TowerConfig towerConfig) {
        this.towerImage= getImage("src/ressources/towers/towerSprite.png");
        this.game= game;
        this.towerConfig= towerConfig;
        this.tower= towerConfig.getMouseTowers();
        this.addAsset();
    }

    public Direction getVerticalPos(Tower t){
        if(t.getTarget().getPos().getY()<t.getPos().getY()){
            return Direction.NORTH;
        }else{
            return Direction.SOUTH;
        }
    }

    public Direction getHorizontalePos(Tower t){
        if(t.getTarget().getPos().getX()<t.getPos().getX()){
            return Direction.WEST;
        }else{
            return Direction.EAST;
        }
    }

    public float getVerticalNombre(Tower t){
        Direction v= getVerticalPos(t);
        if(v==Direction.NORTH){
            return -((t.getPos().getY()-t.getTarget().getPos().getY())/3);
        }
        else{
            return (t.getTarget().getPos().getY()-t.getPos().getY())/3;
        }
    }

    public float getHorizontalNombre(Tower t){
        Direction h= getHorizontalePos(t);
        if(h==Direction.EAST){
            return (t.getTarget().getPos().getX()-t.getPos().getX())/3;
        }
        else{
            return -(t.getPos().getX()-t.getTarget().getPos().getX())/3;
        }
    }

    public int getRotateCoef(Tower t){
        Direction h = getHorizontalePos(t);
        Direction v = getVerticalPos(t);
        if(v==Direction.NORTH){
            if(h==Direction.EAST){
                return 3;
            }
            else{
                return 2;
            }
        }
        else{
            if(h==Direction.EAST){
                return 0;
            }
            else{
                return 1;
            }
        }
    }

    public double angle(Coordinates t, Coordinates e){
        double angle = Math.abs(Math.abs(t.getX())-Math.abs(e.getX())/Math.abs(t.getY())-Math.abs(e.getY()));
        return angle;
    }

    private void attackTower (Graphics g, long time, Tower t) {
        if(t.getTarget()!=null){
            float n= 100f;
            int t0= (int) (n/t.getVitesseAtk());
            int t1= (int) (n*2.0f/t.getVitesseAtk());
            int t2= (int) (n*3.0f/t.getVitesseAtk());
            int t3= (int) (n*4.0f/t.getVitesseAtk());

            float y = getVerticalNombre(t);
            float x = getHorizontalNombre(t);
            double angle =getRotateCoef(t)*90+Math.toDegrees(Math.atan(this.angle(t.getPos(), t.getTarget().getPos())));

            int nb=6;
            if (time%t3<t0) {
                g.drawImage( rotate(this.towerAsset.get(t.idColorTower()+nb), angle),
                    (int)(t.getPos().getX()+x), (int)(t.getPos().getY()+y), 
                    this.game.getTileSize(), this.game.getTileSize(), 
                    null);
            } else if (time%t3<t1) {
                if (t.isMagic()) {
                    g.drawImage( rotate(this.towerAsset.get(t.idColorTower()+nb*2), angle),
                        (int)(t.getPos().getX()+x*2), (int)(t.getPos().getY()+y*2), 
                        this.game.getTileSize(), this.game.getTileSize(), 
                        null);
                } else if (t.isPhysic()) {
                    g.drawImage( rotate(this.towerAsset.get(t.idColorTower()+nb),angle),
                        (int)(t.getPos().getX()+x*2), (int)(t.getPos().getY()+y*2),  
                        this.game.getTileSize(), this.game.getTileSize(), 
                        null);
            } else if (time%t3<t2) {
                if (t.isMagic()) {
                    g.drawImage( rotate(this.towerAsset.get(t.idColorTower()+nb*3), angle),
                        (int)(t.getPos().getX()+x*3), (int)(t.getPos().getY()+y*3), 
                        this.game.getTileSize(), this.game.getTileSize(), 
                        null);
                } else if (t.isPhysic()) {
                    g.drawImage( rotate (
                        this.towerAsset.get(t.idColorTower()+nb),angle),
                        (int)(t.getPos().getX()+x*3), (int)(t.getPos().getY()+y*3),  
                        this.game.getTileSize(), this.game.getTileSize(), 
                        null);
                    //g.rotate(Math.toRadians(45), (int)t.getPos().getX()+this.game.getTileSize()*3*attaqueDirection(d)[0]+(this.game.getTileSize()/2), (int)t.getPos().getY()+this.game.getTileSize()*3*attaqueDirection(d)[1]+(this.game.getTileSize()/2));
                    }
                }
            }
        }
    }


    @Override
    public void addAsset(){
        for (int ligne=0; ligne<6; ligne++) {
            for (int col= 0; col<6; col++) {
                if(ligne==0){
                    towerAsset.add(this.towerImage.getSubimage(
                        col*this.game.getInitialTileSize(), 
                        ligne*this.game.getInitialTileSize(), 
                        this.game.getInitialTileSize(), 
                        this.game.getInitialTileSize()*2));
                }else{
                    towerAsset.add(this.towerImage.getSubimage(
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

    public void drawPanneau(Coordinates c, long time, Graphics g){
        int n = (int)(400f);
        int m = (int)(100f);

        if(time%n<m){
            g.drawImage(this.towerAsset.get(26),(int)( c.getX()),(int)(c.getY()-this.game.getTileSize()/2-3),this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else{
            g.drawImage(this.towerAsset.get(26),(int) (c.getX()),(int)(c.getY()-this.game.getTileSize()/2),this.game.getTileSize(),this.game.getTileSize(), null);
        }
    }

    @Override
    public void drawImages(Graphics g) {
        long time= System.currentTimeMillis();
        this.tower= towerConfig.getMouseTowers();
        this.towersEmpty= towerConfig.getTowersEmpty();
        if(towersEmpty.size()!=0){
            for(Coordinates c : towersEmpty){
                drawPanneau(c, time, g);
            }
        }

        for (Tower t: tower) {
            g.drawImage(
                towerAsset.get(t.idColorTower()), 
                (int)t.getPos().getX(), 
                (int)t.getPos().getY()- this.game.getTileSize(), 
                this.game.getTileSize(), 
                this.game.getTileSize()*2,
                null);  
        }
        for (Tower t: tower) {
            attackTower(g, time, t);
        }
        if(test!=null){
            drawOneTower(test, g);
        }
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
