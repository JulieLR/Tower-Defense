package gui;

import java.io.*;

public class TowerDeserialize {
    // private TowerManager towerManager;
    
    public TowerDeserialize (TowerManager towerManager) throws IOException, ClassNotFoundException {
        towerManager= null;
        FileInputStream fileIn= new FileInputStream("src/gui/TowerManager.ser");
        ObjectInputStream in= new ObjectInputStream(fileIn);
        towerManager= (TowerManager) in.readObject();
        in.close();
        fileIn.close();

        //System.out.println("");

        long serialVersionUID= ObjectStreamClass.lookup(towerManager.getClass()).getSerialVersionUID();
        System.out.println(serialVersionUID);
    }

    
}