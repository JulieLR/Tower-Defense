package gui;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;

public class TowerManager implements Serializable{
    public TowerTile TOWER_BLUE, TOWER_ORANGE, TOWER_RED, TOWER_SMALL, TOWER_MEDIUM, TOWER_EXTRA;
    public BufferedImage towerImage;
    // public transient BufferedImage towerImageTransient; // transient pour igniorer le serializes
    private static final int serialVersionUID= 1;
    public ArrayList<TowerTile> tile= new ArrayList<> ();
    
    public TowerManager () throws IOException, ClassNotFoundException {
        createTile();
        towerSerialize(towerImage, "TowerManager.ser");
        towerDeserialize("TowerManager.ser");
        loadTowerImage();
    }

    private void createTile() {
        tile.add(TOWER_BLUE= new TowerTile(getSprite(0, 0)));
        tile.add(TOWER_ORANGE= new TowerTile(getSprite(1, 0)));
        tile.add(TOWER_RED= new TowerTile(getSprite(2, 0)));
        tile.add(TOWER_SMALL= new TowerTile(getSprite(3, 0)));
        tile.add(TOWER_MEDIUM= new TowerTile(getSprite(4, 0)));
        tile.add(TOWER_EXTRA= new TowerTile(getSprite(5, 0)));
    }

    private void loadTowerImage () {
        // towerImage= 
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
            return (BufferedImage) in.readObject();
        } catch (IOException| ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BufferedImage getSprite (int id) {
        return tile.get(id).getTower();
    }

    private BufferedImage getSprite (int cordX, int cordY) {
		return towerImage.getSubimage(cordX* 16, cordY* 29, 16, 29);
		} 

    
}
