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
            if(t.getTarget()!=null){
                attaque(t.getTarget(),t);
            }
        }
    }

    public ArrayList<Tower> getTowers () {
        return this.towers;
    }

    public void addTower(Coordinates[] c){
        Random random= new Random();
        for(int i=0;i<c.length;i++){
            int r= random.nextInt(6);
            Tower t= new Tower(0.1f, c[i], 10, r, 20, 64*5, 64*5, this.game);
            t=t.towerEnum(r);
            this.towers.add(t);

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
        if(enemy.getPointDeVie()-tower.getDegat()<0){
            enemy.setPointDeVie(0);
            enemy.setAlived(false);
            tower.deleteEnemyTab(enemy);
            tower.setTarget();
        }
        else{
            enemy.setPointDeVie(enemy.getPointDeVie()-tower.getDegat());
            if (!tower.isInZone(enemy)) {
                tower.deleteEnemyTab(enemy);
                tower.setTarget();
            }
        }
    }
    
    // distance entre une tour et un ennemi
    public double distanceTowerEnemy (Tower t, Enemy e) {
        return Math.sqrt(
        Math.pow(e.getPos().getX()-t.getPos().getX(),2) +
        Math.pow(e.getPos().getY()-t.getPos().getY(),2));
    }

    private void getTarget(Tower t){
        for(Enemy e : enemies){
            if(t.getTarget()==null){
                if(t.isInZone(e)){
                    if(e.isAlived()){
                        t.setTarget();
                    }
                    /* if (t.isNextposInZone(e)) {
                        t.deleteEnemyTab(e);
                    } */
                
                }   
            }
        }
    }

}
