package gui;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import config.MapConfig;
import inputs.Keyboard_Listener;
import inputs.Mouse_Listener;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Random;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;


public class Game extends JPanel implements Runnable {
    
     //Tiles
    final int scale =4;
    final int initialTileSize = 16;

    final int tileSize= initialTileSize*scale; //48x48

    //Barre pour les tour
    final int barLine = 2;

    //Ecran
    final int col = 15;
    final int ligne= 10+barLine;//10 jeu et 2 bar

    final int width= col*tileSize; //960
    final int height= ligne*tileSize; //640+128

    private BufferedImage mapImage;
    private MapConfig tiles;

    private BufferedImage enemyImage;

    private BufferedImage towerImage;
    private JPanel towerButton;

    private Thread gameThread;
    private final double FPS_SET= 120.0;
    private final double UPS_SET= 60.0;
    
    // interaction clavier et souris (ici psk sinon ça compte aussi les coordonnées de la barre en haut avec le titre)
    private Mouse_Listener mouseListener; 
    private Keyboard_Listener keyboardlistener;

    public Game(){
        this.mapImage = getImage("src/ressources/map/sprite1.png");
        this.enemyImage= getImage("src/ressources/enemies/enemiesSprite.png");
        this.towerImage= getImage("src/ressources/towers/towerSprite.png");

        this.tiles= new MapConfig(this);
        this.towerButton= new TowerBottomBar(this);
        add(towerButton);

        setPreferredSize(new Dimension(width, height));
        setVisible(true);

        start();
    }

    public void initInput() {
        mouseListener= new Mouse_Listener();
        keyboardlistener= new Keyboard_Listener();

        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
        addKeyListener(keyboardlistener);

        requestFocus();
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

    public BufferedImage getMapImage() {
        return mapImage;
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

    private void start() {
        gameThread= new Thread(this){};
        gameThread.start();
    }
    private void updateGame () {

        //System.out.println("Game Updated");
    }

    @Override
    public void run() {
        double timePerFrame= 1000000000.0/FPS_SET;
        double timePerUpdate= 1000000000.0/UPS_SET;

        long lastFrame= System.nanoTime();
        long lastUpdate= System.nanoTime();
        long lastTimeCheck= System.currentTimeMillis(); 

        int frames=0;
        int updates= 0;
        
        while (true) {
            if (System.nanoTime()- lastFrame>= timePerFrame) {
                repaint();
                lastFrame= System.nanoTime();
                frames++;

            } 

            if (System.nanoTime()- lastUpdate>= timePerUpdate) {
                updateGame();
                lastUpdate= System.nanoTime();
                updates++;

            }

            if (System.currentTimeMillis()- lastTimeCheck>= 1000) {
                // System.out.println("FPS: "+ frames+ " | UPS: "+ updates);
                frames= 0;
                updates= 0;
                lastTimeCheck= System.currentTimeMillis();

            }
        }

    } 

}
