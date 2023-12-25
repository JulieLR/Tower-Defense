package gui;

import java.io.*;

public class TowerSerialize implements Serializable {
    //private /* transient */ TowerManager towerManager; // transient pour igniorer le serializes

    public TowerSerialize (TowerManager towerManager) throws IOException {
        towerManager= new TowerManager();
        FileOutputStream fileout= new FileOutputStream("TowerManager.ser");
        ObjectOutputStream out= new ObjectOutputStream(fileout);
        out.writeObject(towerManager);
        out.close();
        fileout.close();
        
        System.out.println("object info saved");

        long serialVersionUID= ObjectStreamClass.lookup(towerManager.getClass()).getSerialVersionUID();
        System.out.println(serialVersionUID);
    }

    
}