package config;

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
    private Tower t;
    
    public TowerConfig (Game game) /* throws IOException, ClassNotFoundException */ {
        this.game=game;
        //this.pos=this.game.getMapConfig().getPosTower()[0];
        this.pos=new Coordinates(0, 0);
        this.t= new Tower(75, pos, 40, 1, 1, 0);
        createTile();

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

    /* private BufferedImage getSprite (int id) {
        return towers.get(id).getTowerImage();
    } */

    public BufferedImage getSprite (int cordX, int cordY) {
        if (cordY<2) {
		    return towerImage.getSubimage(cordX* 16, cordY* 29, 16, 29);
        }
        return towerImage.getSubimage(cordX* 16, cordY* 16, 16, 16);
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

    // distance entre les deux personnes 
   /*  public double disBetween (Personnages p) {
        return Math.sqrt(
        Math.pow(p.position.x()-this.position.x(),2) +
        Math.pow(p.position.y()-this.position.y(),2));
    }*/

    public ArrayList<Tower> getTowers () {
        return this.towers;
    }
    
}
