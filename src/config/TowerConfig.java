package config;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import gui.Game;
import model.Coordinates;
import model.Tower;

public class TowerConfig implements Serializable{
    private Tower TOWER_BLUE, TOWER_ORANGE, TOWER_RED, TOWER_SMALL, TOWER_MEDIUM, TOWER_EXTRA;
    private BufferedImage towerImage;
    // public transient BufferedImage towerImageTransient; // transient pour igniorer le serializes
    // private static final int serialVersionUID= 1;
    private ArrayList<Tower> towers= new ArrayList<> ();
    private Coordinates pos;
    private Game game;
    
    public TowerConfig (Game game) throws IOException, ClassNotFoundException {
        this.game=game;
        createTile();
        //towerSerialize(towerImage, "TowerManager.ser");
        //towerDeserialize("TowerManager.ser");
        loadTowerImage();
    }

    private void createTile() {
        towers.add(TOWER_BLUE= new Tower(75, pos, 40, 1, 1, 0, getSprite(0, 0)));
        towers.add(TOWER_ORANGE= new Tower(150, pos, 65, 3, 1, 0, getSprite(1, 0)));
        towers.add(TOWER_RED= new Tower(200, pos, 100, 5, 1, 0, getSprite(2, 0)));
        towers.add(TOWER_SMALL= new Tower(25, pos, 10, 2, 0, 0, getSprite(3, 0)));
        towers.add(TOWER_MEDIUM= new Tower(50, pos, 20, 5, 0, 0, getSprite(4, 0)));
        towers.add(TOWER_EXTRA= new Tower(150, pos, 60, 15, 0, 0, getSprite(5, 0)));
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

    public BufferedImage getSprite (int id) {
        return towers.get(id).getTowerImage();
    }

    public BufferedImage getSprite (int cordX, int cordY) {
		return towerImage.getSubimage(cordX* 16, cordY* 29, 16, 29);
		} 

    
}
