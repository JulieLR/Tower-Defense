package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import config.TowerConfig;
import model.Tower;

public class BottomBar extends JPanel{
    private Game game; 
    private TowerConfig towerConfig;
    private ArrayList<JButton> towerButtonAsset = new ArrayList<>();
    private ArrayList<Tower> tower;

    public BottomBar (Game game, TowerConfig towerConfig) {
        this.game=game;
        this.towerConfig=towerConfig;
        this.tower= towerConfig.getTowers();
        this.addAsset();
        this.addButton();
    }

    public void addAsset() {
        for (int ligne=0; ligne<1; ligne++) {
            for (int col= 0; col<6; col++) {
                if(ligne==0){
                    JButton button = new JButton();
                }
            }
            if(ligne==0){
                ligne++;
            }
        }
    }

    public void addButton() {
        for (int i=0; i<6; i++) {
            JButton button= this.towerButtonAsset.get(i);
            button.setBounds(400, 200, this.game.getTileSize(), this.game.getTileSize()*2);
            this.game.getApp().add(button);
        }
        
    }

    public BufferedImage getImage(String chemin){
        try {
            BufferedImage image = ImageIO.read(new File(chemin));
            return image;
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
    
}
