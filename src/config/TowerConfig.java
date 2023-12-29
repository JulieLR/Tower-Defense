package config;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import config.Tile.Type;
import gui.Game;
import model.Coordinates;
import model.Enemy;
import model.Entities;
import model.Tower;

public class TowerConfig implements Serializable{
    private Tower TOWER_BLUE, TOWER_ORANGE, TOWER_RED, TOWER_SMALL, TOWER_MEDIUM, TOWER_EXTRA;
    private BufferedImage towerImage;
    // public transient BufferedImage towerImageTransient; // transient pour igniorer le serializes
    // private static final int serialVersionUID= 1;
    private ArrayList<Tower> towers= new ArrayList<> ();
    private Coordinates pos;
    private Game game;
    // private Tower t;
    
    public TowerConfig (Game game) /* throws IOException, ClassNotFoundException */ {
        this.game=game;
        //this.pos=this.game.getMapConfig().getPosTower()[0];
        //this.pos=new Coordinates(3, 3);
        //this.t= new Tower(75, pos, 40, 1, 1, 0);
        createTile();
        System.out.println(getNbTower());

        //towerSerialize(towerImage, "TowerManager.ser");
        //towerDeserialize("TowerManager.ser");
        //loadTowerImage();
    }

    private void createTile() {
        towers.add(TOWER_BLUE= new Tower(75, pos, 40, 1, 1, 0));
        towers.add(TOWER_ORANGE= new Tower(150, pos, 65, 3, 1, 1));
        towers.add(TOWER_RED= new Tower(200, pos, 100, 5, 1, 2));
        towers.add(TOWER_SMALL= new Tower(25, pos, 10, 2, 0, 3));
        towers.add(TOWER_MEDIUM= new Tower(50, pos, 20, 5, 0, 4));
        towers.add(TOWER_EXTRA= new Tower(150, pos, 60, 15, 0, 5));
    }

    private void loadTowerImage () {
        towerSerialize(towerImage, "TowerManager.ser");
        towerImage= towerDeserialize("TowerManager.ser");
    }

    // conversion des donnees en fichier binaire
    /* private void towerSerialize (BufferedImage img, String fichier) throws IOException {
        /* this.towerImage= getSprite(serialVersionUID);
        FileOutputStream fileout= new FileOutputStream("TowerManager.ser");
        ObjectOutputStream out= new ObjectOutputStream(fileout);
        out.writeObject(towerImage);
        out.close();
        fileout.close();
        
        System.out.println("object info serealised");

        long serialVersionUID= ObjectStreamClass.lookup(towerImage.getClass()).getSerialVersionUID();
        System.out.println(serialVersionUID); 
    }*/
    private void towerSerialize (BufferedImage img, String fichier) {
        try (ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream(fichier))) {
            out.writeObject(img);
            System.out.println("object info serealised");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // consersion des donnees du fichier binaire en donnees que reconnait java
    /* private void towerDeserialize () throws IOException, ClassNotFoundException {
        this.towerImage= null;
        FileInputStream fileIn= new FileInputStream("src/gui/TowerManager.ser");
        ObjectInputStream in= new ObjectInputStream(fileIn);
        this.towerImage= (BufferedImage) in.readObject();
        in.close();
        fileIn.close();

        System.out.println("object info serealised");

        long serialVersionUID= ObjectStreamClass.lookup(towerImage.getClass()).getSerialVersionUID();
        System.out.println(serialVersionUID);
        
    } */
    private BufferedImage towerDeserialize (String fichier) {
        try (ObjectInputStream in= new ObjectInputStream(new FileInputStream(fichier))) {
            System.out.println("object info serealised");
            return (BufferedImage) in.readObject();
        } catch (IOException| ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private Type getTileType(int x, int y) {
        return game.getTileType(x,y);
    }

    private boolean isTower(Coordinates c){
        return(getTileType((int)c.getX(),(int)c.getY())==Type.TOWER);
    }

    private boolean isPath(Coordinates c){
        return(getTileType((int)c.getX(),(int)c.getY())==Type.PATH);
    }
    /* private boolean isEnemy(Coordinates c){
        return(getTileType((int)c.getX(),(int)c.getY())==Type.TOWER);
    } */
 
    // attaque
    private void attaque (Enemy enemy, Tower tower) {
        enemy.setPointDeVie(enemy.getPointDeVie()-tower.getDegat());
        System.out.println("point de vie ennemi = "+enemy.getPointDeVie());
    }

    public ArrayList<Tower> getTowers () {
        return this.towers;
    }

    // le nombre de tours qu'il y a sur la map
    public int getNbTower () {
        int n=0;
        for (int ligne=0; ligne<this.game.getMapConfig().getMap().length; ligne++) {
            for (int col=0; col<this.game.getMapConfig().getMap()[1].length; col++) {
                if (this.game.getMapConfig().getMap()[ligne][col].getType()==Type.TOWER) {
                    n++;
                }
            }
        }
        return n;
    }

    // les coordonnees des tours sur la map
    public Coordinates[] getPosTower () {
        Coordinates[] posTower= new Coordinates[getNbTower()];
        int n=0;
        for (int ligne=0; ligne<this.game.getMapConfig().getMap().length; ligne++) {
            for (int col=0; col<this.game.getMapConfig().getMap()[0].length; col++) {
                if (this.game.getMapConfig().getMap()[ligne][col].getType()==Type.TOWER) {
                    posTower[n]= new Coordinates(ligne*this.game.getTileSize() , col*this.game.getTileSize()- this.game.getTileSize());
                    n++;
                }
            }
        }
        return posTower;

    }

    // zone d'attaque rectangulaire de la i-ème tour
    private Rectangle zoneAtk (int width, int height, int iTower) {
        Rectangle zone= new Rectangle((int)this.getPosTower()[iTower].getX()-width/2, (int)this.getPosTower()[iTower].getY()-height/2, width, height);
        return zone;  
    }

    // si aux coordonnees (x, y) il y a un ennemi
    private boolean isEnemy (int x, int y) {
        if (this.game.getEnemyConfig().getE().getPos().getX()==x && this.game.getEnemyConfig().getE().getPos().getY()==y) {
            return true;
        }
        return false;
    }

    // si dans la zone rectangulaire il y a un ennemi
    private boolean isEnemyInZone (int width, int height, int iTower) {
        for (int ligne= 0; ligne<zoneAtk(width, height, iTower).getWidth(); ligne++) {
            for (int col= 0; col<zoneAtk(width, height, iTower).getWidth(); col++) {
                return isEnemy(ligne, col);
            }
        }
        return true;
    }

    public int idTowerBlue () {
        return this.TOWER_BLUE.getId();
    }
    
    // distance entre les deux personnes 
   /*  public double disBetween (Personnages p) {
        return Math.sqrt(
        Math.pow(p.position.x()-this.position.x(),2) +
        Math.pow(p.position.y()-this.position.y(),2));
    }*/
    
}
