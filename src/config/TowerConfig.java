package config;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import config.Tile.Type;
import gui.Game;
import model.Coordinates;
import model.Enemy;
import model.Entities;
import model.Tower;
import model.Tower.TowerColor;

public class TowerConfig implements Serializable{
    private BufferedImage towerImage;
    // public transient BufferedImage towerImageTransient; // transient pour igniorer le serializes
    // private static final int serialVersionUID= 1;
    private ArrayList<Tower> towers= new ArrayList<> ();
    private Coordinates pos;
    private Game game;
    
    public TowerConfig (Game game) /* throws IOException, ClassNotFoundException */ {
        this.game=game;
        //createTile();
        addTower(getPosTower());
        //loadTowerImage();
        System.out.println("ok");
    }

    private void createTile() {
        towers.add(new Tower(75, pos, 40, 1, 0));
        towers.add(new Tower(150, pos, 65, 3, 1));
        towers.add(new Tower(200, pos, 100, 5, 2));
        towers.add(new Tower(25, pos, 10, 2, 3));
        towers.add(new Tower(50, pos, 20, 5, 4));
        towers.add(new Tower(150, pos, 60, 15, 5));
    }

    public void addTower(Coordinates[] c){
        Random random= new Random();
        for(int i=0;i<c.length;i++){
            int r= random.nextInt(6);
            this.towers.add(new Tower(20, c[i], 5, 0.5f, r));
        }
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
    
    public ArrayList<Tower> getTowers () {
        return this.towers;
    }
    
    /* 
    // le type de la tuile aux coordonnées (x, y)
    private Type getTileType(int x, int y) {
        return game.getTileType(x,y);
    }

    // si la tuile aux coordonnees c correspond a une tour
    private boolean isTower(Coordinates c){
        return(getTileType((int)c.getX(),(int)c.getY())==Type.TOWER);
    }

    // si la tuile aux coordonnees c correspond a une route (pour les ennemis)
    private boolean isPath(Coordinates c){
        return(getTileType((int)c.getX(),(int)c.getY())==Type.PATH);
    }
    
    // si la tuile aux coordonnees c contient un ennemis (pas fini, et marche pas)
    private boolean isEnemy(Coordinates c){
        return(getTileType((int)c.getX(),(int)c.getY())==Type.TOWER);
    } */
 
    // attaque
    private void attaque (Enemy enemy, Tower tower) {
        enemy.setPointDeVie(enemy.getPointDeVie()-tower.getDegat());
        System.out.println("point de vie ennemi = "+enemy.getPointDeVie());
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

    // nombre d'enemis dans la zone rectangulaire
    private int numberEnemyInZone (int width, int height, int iTower) {
        int n=0;
        for (int ligne= (int) zoneAtk(width, height, iTower).getX(); ligne<zoneAtk(width, height, iTower).getWidth(); ligne++) {
            for (int col= (int) zoneAtk(width, height, iTower).getY(); col<zoneAtk(width, height, iTower).getWidth(); col++) {
            n++;
            }
        }
        return n;
    }

    // tableau des coordonnees des ennemis dans la zone de la tour voulu
    public Coordinates[] CoordinatesEnemyInZone (Enemy e, int width, int height, int iTower) {
        Coordinates[] coordinates= new Coordinates[numberEnemyInZone(width, height, iTower)];
        int n=0;
        for (int ligne= (int) zoneAtk(width, height, iTower).getX(); ligne<zoneAtk(width, height, iTower).getWidth(); ligne++) {
            for (int col= (int) zoneAtk(width, height, iTower).getY(); col<zoneAtk(width, height, iTower).getWidth(); col++) {
                coordinates[n]=new Coordinates(ligne, height);
                n++;
            }
        }
        return coordinates;
    }

    // inverse les positions de deux ennemis dans le tableau, si un ennemis depasse un autre
    public void enemyOvertake (Enemy e0, Enemy e1) {

    }
    
    // distance entre les deux personnes 
   /*  public double disBetween (Personnages p) {
        return Math.sqrt(
        Math.pow(p.position.x()-this.position.x(),2) +
        Math.pow(p.position.y()-this.position.y(),2));
    }*/

    
    
}
