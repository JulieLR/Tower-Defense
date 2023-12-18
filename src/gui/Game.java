package gui;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Game extends JPanel {
    
    private Random random = new Random();
    private BufferedImage image;

    private ArrayList<BufferedImage> assets = new ArrayList<>();

    public Game(BufferedImage img){
        this.image = img;
        addAsset();

    }

    public void paintComponent(Graphics g)  {
        super.paintComponent(g);

        for(int x=0;x<20;x++){
            for(int y=0;y<21;y++){
                g.drawImage(assets.get(random.nextInt(5)),y*30, x*30, 64, 64, null);
            }
        }
    }

    public void addAsset(){
        for(int x=0;x<5;x++){
            assets.add(image.getSubimage(x*16, 0, 16, 16));
        }
    }

    public int getRandomNb(){
        return random.nextInt(3);
    }
}
