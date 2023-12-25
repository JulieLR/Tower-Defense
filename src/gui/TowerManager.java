package gui;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class TowerManager implements Serializable{
    // transient pour igniorer le serializes
    private static final int serialVersionUID= 1;
    public TowerTile TOWER_BLUE, TOWER_ORANGE, TOWER_RED, TOWER_SMALL, TOWER_MEDIUM, TOWER_EXTRA;
    public BufferedImage towerImage;
    // public transient BufferedImage towerImageTransient; // transient pour igniorer le serializes
    public ArrayList<TowerTile> tile= new ArrayList<> ();
    
    public TowerManager () throws IOException, ClassNotFoundException {
        createTile();
        towerSerialize();
        towerDeserialize();
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

    private void towerSerialize () throws IOException {
        this.towerImage= getSprite(serialVersionUID);
        FileOutputStream fileout= new FileOutputStream("TowerManager.ser");
        ObjectOutputStream out= new ObjectOutputStream(fileout);
        out.writeObject(towerImage);
        out.close();
        fileout.close();
        
        System.out.println("object info saved");

        long serialVersionUID= ObjectStreamClass.lookup(towerImage.getClass()).getSerialVersionUID();
        System.out.println(serialVersionUID);
    }

    private void towerDeserialize () throws IOException, ClassNotFoundException {
        this.towerImage= null;
        FileInputStream fileIn= new FileInputStream("src/gui/TowerManager.ser");
        ObjectInputStream in= new ObjectInputStream(fileIn);
        this.towerImage= (BufferedImage) in.readObject();
        in.close();
        fileIn.close();

        //System.out.println("");

        long serialVersionUID= ObjectStreamClass.lookup(towerImage.getClass()).getSerialVersionUID();
        System.out.println(serialVersionUID);
    }

    public BufferedImage getSprite (int id) {
        return tile.get(id).getTower();
    }

    private BufferedImage getSprite (int cordX, int cordY) {
		return towerImage.getSubimage(cordX* 16, cordY* 29, 16, 29);
		} 

    
}
