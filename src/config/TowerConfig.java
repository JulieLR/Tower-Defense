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
    private Game game;
    private ArrayList<Tower> towers= new ArrayList<> ();
    private ArrayList<Enemy> enemies= new ArrayList<> ();
    private BufferedImage towerImage;
    // public transient BufferedImage towerImageTransient; // transient pour igniorer le serializes
    // private static final int serialVersionUID= 1;
    
    public TowerConfig (Game game) /* throws IOException, ClassNotFoundException */ {
        this.game=game;
        this.enemies=this.game.getEnemyConfig().getEnemies();
        addTower(getPosTower());
        //loadTowerImage();
    }

    public void update(){
        for(Tower t : towers){
            getTarget(t);
        }
    }

    public ArrayList<Tower> getTowers () {
        return this.towers;
    }

    public void addTower(Coordinates[] c){
        Random random= new Random();
        for(int i=0;i<c.length;i++){
            int r= random.nextInt(6);
            Tower t= new Tower(0.1f, c[i], 10, r, 20, 300, 300, this.game);
            //t=t.towerEnum(r);
            //this.towers.add(t);

        }
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

    private void loadTowerImage () {
        towerSerialize(towerImage, "TowerManager.ser");
        towerImage= towerDeserialize("TowerManager.ser");
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
                    posTower[n]= new Coordinates(ligne*this.game.getTileSize() , col*this.game.getTileSize());
                    n++;
                }
            }
        }
        return posTower;
    }
 
    // attaque
    private void attaque (Enemy enemy, Tower tower) {
        enemy.setPointDeVie(enemy.getPointDeVie()-tower.getDegat());
        System.out.println("point de vie ennemi = "+enemy.getPointDeVie());
    }

    // si aux coordonnees (x, y) il y a un ennemi
    private boolean isEnemy (int x, int y) {
        if (this.game.getEnemyConfig().getE().getPos().getX()==x && this.game.getEnemyConfig().getE().getPos().getY()==y) {
            return true;
        }
        return false;
    }

    // si dans la zone rectangulaire il y a un ennemi
    private boolean isEnemyInZone (Tower tower) {
        for (int ligne= 0; ligne<tower.getAttackZone().getWidth(); ligne++) {
            for (int col= 0; col<tower.getAttackZone().getHeight(); col++) {
                if (isEnemy(ligne, col)) {
                    return true;
                }
            }
        }
        return false;
    }

    // nombre d'enemis dans la zone rectangulaire
    private int numberEnemyInZone (Tower tower) {
        int n=0;
        for (int ligne= (int) tower.getAttackZone().getX(); ligne<tower.getAttackZone().getWidth(); ligne++) {
            for (int col= (int) tower.getAttackZone().getY(); col<tower.getAttackZone().getHeight(); col++) {
                if (isEnemy(ligne, col)) {
                    n++;
                }
            }
        }
        return n;
    }

    // tableau des coordonnees des ennemis dans la zone de la tour voulu
    public Coordinates[] CoordinatesEnemyInZone (Tower tower) {
        Coordinates[] coordinates= new Coordinates[numberEnemyInZone(tower)];
        int n=0;
        for (int ligne= (int) tower.getAttackZone().getX(); ligne<tower.getAttackZone().getWidth(); ligne++) {
            for (int col= (int) tower.getAttackZone().getY(); col<tower.getAttackZone().getHeight(); col++) {
                coordinates[n]=new Coordinates(ligne, col);
                n++;
            }
        }
        return coordinates;
    }

    // tableau des coordonnees des ennemis dans la zone de la tour voulu
    public Coordinates[] nextCoordinatesEnemyInZone (Enemy e, Tower tower) {
        Coordinates[] nextCoordinates= new Coordinates[numberEnemyInZone(tower)];
        int n=0;
        for (int ligne= (int) tower.getAttackZone().getX(); ligne<tower.getAttackZone().getWidth(); ligne++) {
            for (int col= (int) tower.getAttackZone().getY(); col<tower.getAttackZone().getHeight(); col++) {
                nextCoordinates[n]=new Coordinates(ligne+this.game.getTileSize()*e.getSpeed(), col+this.game.getTileSize()*e.getSpeed());
                n++;
            }
        }
        return nextCoordinates;
    }



    // inverse les positions de deux ennemis dans le tableau, si un ennemis depasse un autre
    public void enemyOvertake (Enemy e0, Enemy e1) {

    }

    
    // distance entre une tour et un ennemi
    public double distanceTowerEnemy (Tower t, Enemy e) {
        return Math.sqrt(
        Math.pow(e.getPos().getX()-t.getPos().getX(),2) +
        Math.pow(e.getPos().getY()-t.getPos().getY(),2));
    }
    
    // le type de la tuile aux coordonnées (x, y)
    private Type getTileType(int x, int y) {
        return game.getTileType(x,y);
    }

    // si la tuile aux coordonnees c correspond a une route (pour les ennemis)
    private boolean isPath(Coordinates c){
        return(getTileType((int)c.getX(),(int)c.getY())==Type.PATH);
    }
    
    /* // tableau de tableau de boolean pour savoir les tiles qui sont des routes (donc ou il peut y avoir un ennemi)
    private boolean[][] tabPath (Tower tower) {
        boolean[][] tab= new boolean[(int)tower.getAttackZone().getWidth()][(int)tower.getAttackZone().getHeight()];
        for (int ligne= (int) tower.getAttackZone().getX(); ligne<tower.getAttackZone().getWidth(); ligne++) {
            for (int col= (int) tower.getAttackZone().getY(); col<tower.getAttackZone().getHeight(); col++) {
                if (isPath(new Coordinates(ligne, col))) {
                    tab[ligne][col]= true;
                }
            }
        }
        return tab;
    } */

    private int nbPath (Tower tower) {
        int n=0;
        for (int ligne= (int) tower.getAttackZone().getX(); ligne<tower.getAttackZone().getWidth(); ligne++) {
            for (int col= (int) tower.getAttackZone().getY(); col<tower.getAttackZone().getHeight(); col++) {
                if (isPath(new Coordinates(ligne, col))) {
                    n++;
               }
            }
        } 
        return n;
    }
    

    // distance entre une tour et une tile route 
    private double[] distanceTowerPath (Tower t, Tile path) {
        double[] distance= new double[nbPath(t)];

        return distance;
    }

    // le temps que mets un projectile pour arriver à un path
    private long timeProjectileToPath () {
        return 0;
    }

    private void getTarget(Tower t){
            for(Enemy e : enemies){
                if(t.getTarget()==null){
                    if(t.isInZone(e)){
                        t.setTarget(e);
                    }
                }
            }
    }

}
