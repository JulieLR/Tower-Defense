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
        this.setBounds(0, 10*this.game.getTileSize(), this.game.getWidth(), this.game.getHeight());
        this.tower= towerConfig.getTowers();
        this.addAsset();
    }

    public void addAsset() {
        for (int ligne=0; ligne<1; ligne++) {
            for (int col= 0; col<6; col++) {
                if(ligne==0){
                    JButton button = new JButton("TEST");
                    button.setBounds(100, 500+this.game.getTileSize()*col, this.game.getTileSize(), this.game.getTileSize()*2);
                    towerButtonAsset.add(button);
                }
            }
            if(ligne==0){
                ligne++;
            }
        }
    }

    public void addButton() {
        for (int i=0; i<towerButtonAsset.size(); i++) {
            this.add(this.towerButtonAsset.get(i));
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
