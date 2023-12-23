package gui;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import config.MapConfig;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import java.awt.Dimension;

public class Game extends JPanel{
    
     //Tiles
    final int scale =4;
    final int initialTileSize = 16;

    final int tileSize= initialTileSize*scale; //32x32

    //Ecran
    final int col = 15;
    final int ligne= 10;

    final int width= col*tileSize; //960
    final int height= ligne*tileSize; //640

    private BufferedImage image;
    private MapConfig tiles;

    public Game(BufferedImage img){
        this.image=img;
        this.tiles= new MapConfig(this);

        setPreferredSize(new Dimension(width, height));
        setVisible(true);

    }

    public BufferedImage getImage() {
        return image;
    }

    public int getInitialTileSize() {
        return initialTileSize;
    }

    public int getCol() {
        return col;
    }

    public int getLigne() {
        return ligne;
    }

    public int getTileSize() {
        return tileSize;
    }

    public void paintComponent(Graphics g)  {
        super.paintComponent(g);

        tiles.drawBackground(g); //draw map à mettre avant le draw des characters

        g.dispose(); //
    }

}
