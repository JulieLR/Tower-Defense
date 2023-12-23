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
import java.awt.Dimension;

public class Game extends JPanel implements Frames{
    
     //Tiles
    final int scale =2;
    final int initialTileSize = 16;
    final int tileSize= initialTileSize*scale; //32x32
    
    //Ecran
    final int col = 30;
    final int ligne= 20;
    final int width= col*tileSize; //960
    final int height= ligne*tileSize; //640

    private Random random = new Random();
    private BufferedImage image;

    private ArrayList<BufferedImage> assets = new ArrayList<>();

    public Game(BufferedImage img){
        this.image=img;
        addAsset();
        setPreferredSize(new Dimension(width, height));
        setVisible(true);

    }

    public void paintComponent(Graphics g)  {
        super.paintComponent(g);
        for(int x=0;x<col;x++){
            for(int y=0;y<ligne;y++){
                g.drawImage(assets.get(random.nextInt(5)),x*tileSize, y*tileSize, tileSize, tileSize, null);
            }
        }
    }

    public void addAsset(){
        for(int x=0;x<5;x++){
            assets.add(image.getSubimage(x*initialTileSize, 0, initialTileSize, initialTileSize));
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
