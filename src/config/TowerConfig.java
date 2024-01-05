package config;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import config.Tile.Type;
import gui.Game;
import model.Base;
import model.Coordinates;
import model.Enemy;
import model.Entities;
import model.Tower;
import model.Tower.TowerColor;

public class TowerConfig implements Serializable{
    private Game game;
    private ArrayList<Tower> towers= new ArrayList<> ();
    private ArrayList<Tower> mouseTowers = new ArrayList<>();

    private ArrayList<Enemy> enemies= new ArrayList<> ();
    private BufferedImage towerImage;
    private Base base;
    // public transient BufferedImage towerImageTransient; // transient pour igniorer le serializes
    // private static final int serialVersionUID= 1;
    
    public TowerConfig (Game game) /* throws IOException, ClassNotFoundException */ {
        this.game=game;
        this.enemies=this.game.getEnemyConfig().getEnemies();
        addTower(getPosTower());
        this.base= this.game.getBase();
        //loadTowerImage();
    }

    public void update(){
        /* for(Tower t : towers){
            getTarget(t);
            if(t.getTarget()!=null){
                attaque(t.getTarget(),t);
            }
        } */

        for(Tower t : mouseTowers){
            getTarget(t);
            if(t.getTarget()!=null){
                atk(t.getTarget(),t);
            }
        }
    }

    public ArrayList<Tower> getTowers () {
        return this.towers;
    }

    public ArrayList<Tower> getMouseTowers() {
        return mouseTowers;
    }

    public void setMouseTowers(ArrayList<Tower> mouseTowers) {
        this.mouseTowers = mouseTowers;
    }

    public Tower towerNum (int n){
        switch (n) {
            case 0: return new Tower(0.5f, new Coordinates(0, 0), 2, n, 75, this.game.getTileSize()*3, this.game.getTileSize()*3, this.game);
            case 1: return new Tower(0.5f, new Coordinates(0, 0), 2, n, 150, this.game.getTileSize()*5, this.game.getTileSize()*5, this.game);
            case 2: return new Tower(0.5f, new Coordinates(0, 0), 2, n, 200, this.game.getTileSize()*7, this.game.getTileSize()*7, this.game);
            case 3: return new Tower(0.5f, new Coordinates(0, 0), 2, n, 25, this.game.getTileSize()*3, this.game.getTileSize()*3, this.game);
            case 4: return new Tower(0.5f, new Coordinates(0, 0), 2, n, 50, this.game.getTileSize()*5, this.game.getTileSize()*3, this.game);
            case 5: return new Tower(0.5f, new Coordinates(0, 0), 2, n, 150, this.game.getTileSize()*7, this.game.getTileSize()*5, this.game);
        }
        return null;
    }

    public int getTowerPrice (int n){
        switch (n) {
            case 0: return 75;
            case 1: return 150;
            case 2: return 200;
            case 3: return 25;
            case 4: return 50;
            case 5: return 150;
        }
        return 0;
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
        //tower.enemiesTab();
        tower.enemiesArray();
        //System.out.println(tower.nbEnemiesInZone());
        if(enemy.getPointDeVie()-tower.getDegat()<0){
            enemy.setPointDeVie(0);
            enemy.setAlived(false);
            //tower.deleteEnemyTab(enemy);
            tower.deleteEnemyArray(enemy);
            tower.setTarget();
            base.setArgent(base.getArgent()+enemy.getPrime());
        }
        else{
            enemy.setPointDeVie(enemy.getPointDeVie()-tower.getDegat());
            if (!tower.isInZone(enemy)) {
                //tower.deleteEnemyTab(enemy);
                tower.deleteEnemyArray(enemy);
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

    private void removeFromArray(Tower t, Enemy e){
        t.getEnemyArray().remove(t.getTarget());
        if(t.getEnemyArray().size()!=0){
            t.setTarget(this.min(t));
            System.out.println(t.getTarget().getNumber());
        }
        else{
            t.setTarget(null);
        }
    }

    public void atk(Enemy enemy,Tower tower){
        if(enemy.getPointDeVie()-tower.getDegat()<0){
            enemy.setPointDeVie(0);
            enemy.setAlived(false);
            base.setArgent(base.getArgent()+enemy.getPrime());
        }
        else{
            enemy.setPointDeVie(enemy.getPointDeVie()-tower.getDegat());
        }
         
    }

    private void getTarget(Tower t){
        if(t.getTarget()!=null){
            if(t.getTarget().isAlived()){
                if(!t.isInZone(t.getTarget())){
                    removeFromArray(t, t.getTarget());
                    System.out.println("TARGET NOT IN ZONE ANYMORE");
                }
            }
            else{
                removeFromArray(t, t.getTarget());
                System.out.println("TARGET DEAD");
            }
        }
        for(Enemy e : enemies){
            if(t.getTarget()==null){
                if(t.isInZone(e)&& e.isAlived()){
                    t.getEnemyArray().add(e);
                    t.setTarget(this.min(t));
                    System.out.println("NEW TARGET");
                    System.out.println(t.getTarget().getNumber());
                }
            }
            else{
                if(t.isInZone(e)&& e.isAlived() && !t.getEnemyArray().contains(e)){
                    t.getEnemyArray().add(e);
                    System.out.println("ENEMY ADDED");
                    System.out.println(t.getTarget().getNumber());
                }
            }
        }
    }

    private Enemy min (Tower t) {
        Enemy tmp= t.getEnemyArray().get(0);
        for (Enemy e: t.getEnemyArray()) {
            if (e.getNumber()<tmp.getNumber()) {
                tmp=e;
            }
        }
        return tmp;
    }

}
