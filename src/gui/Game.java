package gui;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;

public class Game extends JPanel implements Frames{
    
    private Random random = new Random();
    private BufferedImage image;

    private ArrayList<BufferedImage> assets = new ArrayList<>();

    public Game(BufferedImage img){
        this.image=img;
        addAsset();
        setVisible(true);

    }

    public void paintComponent(Graphics g)  {
        super.paintComponent(g);
        for(int x=0;x<21;x++){
            for(int y=0;y<20;y++){
                g.drawImage(assets.get(random.nextInt(5)),x*30, y*30, 64, 64, null);
                //g.setColor(getColor());
                //g.fillRect(x*30, y*30, 64, 64);
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

    public Color getColor(){
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return new Color(r,g,b);
    }

}
