package gui;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import config.EnemiesConfig;
import config.MapConfig;
import config.Tile;
import config.TowerConfig;
import config.Tile.Type;
import model.Base;
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

    final int tileSize= initialTileSize*scale; //64x64

    //Barre pour les tour
    final int barLine = 2;

    //Ecran
    final int col = 15;
    final int ligne= 10+barLine;//10 jeu et 2 bar

    final int width= col*tileSize; //960
    final int height= ligne*tileSize; //640+128

    private BufferedImage mapImage;
    private MapConfig tiles;
    private MapGraphics mapGraphics;

    private BufferedImage enemyImage;
    private BufferedImage batImage;

    private EnemiesConfig enemiesConfig;
    private EnemiesGraphics enemies;

    private TowerConfig towerConfig;
    private TowerGraphics towerGraphics;

    private BufferedImage towerImage;
    private JPanel towerButton;

    private Base base;

    private Thread gameThread;
    private final double FPS_SET= 120.0;
    private final double UPS_SET= 60.0;
    
    
    // interaction clavier et souris (ici psk sinon ça compte aussi les coordonnées de la barre en haut avec le titre)
    private Mouse_Listener mouseListener; 
    private Keyboard_Listener keyboardlistener;

    public Game(){

        this.mapImage = getImage("src/ressources/map/sprite.png");
        this.enemyImage= getImage("src/ressources/enemies/enemiesSprite.png");
        this.batImage = getImage("src/ressources/enemies/batSprite.png");
        this.towerImage= getImage("src/ressources/towers/towerSprite.png");

        this.tiles= new MapConfig(this);
        this.mapGraphics= new MapGraphics(this, tiles);

        this.base = new Base(this,1000);

        this.enemiesConfig = new EnemiesConfig(this,10);
        this.enemies= new EnemiesGraphics(this,this.enemiesConfig);

        this.towerConfig= new TowerConfig(this);
        this.towerGraphics= new TowerGraphics(this, towerConfig);

        //this.towerButton= new TowerBottomBar(this);
        //add(towerButton);

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

    public int getScale(){
        return this.scale;
    }

    public BufferedImage getEnemyImage() {
        return enemyImage;
    }

    public BufferedImage getTowerImage () {
        return this.towerImage;
    }

    public MapConfig getMapConfig() {
        return tiles;
    }

    public Base getBase() {
        return base;
    }

    public BufferedImage getBatImage() {
        return batImage;
    }
    public EnemiesConfig getEnemyConfig () {
        return this.enemiesConfig;
    }

    public void paintComponent(Graphics g)  {
        super.paintComponent(g);

        mapGraphics.drawImages(g); //draw map à mettre avant le draw des characters
        enemies.drawImages(g);
        mapGraphics.drawBottomBar(g);


        g.dispose(); //
    }

    public void enemiesUpdate(Graphics g){
        enemies.drawImages(g);
    }

    private void start() {
        gameThread= new Thread(this){};
        gameThread.start();
    }
    private void updateGame () {
        //System.out.println("Game Updated");
        this.enemies.update();
        if(this.base.isDestroyed()){
            System.exit(0);
            System.out.println("DESTROYED");
        }
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

        double spawnInterval = 3000000000d;//3s
        double delta =0;
        long currentTime;
        long lastTime = System.nanoTime();
        
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
                System.out.println("FPS: "+ frames+ " | UPS: "+ updates);
                frames= 0;
                updates= 0;
                lastTimeCheck= System.currentTimeMillis();

            }
            currentTime = System.nanoTime();
            delta+= (currentTime-lastTime)/spawnInterval;
            lastTime=currentTime;
            if(delta >=1){
                enemiesConfig.spawn();
                delta--;
            }
        }

    } 

    public Type getTileType(int x, int y) {
        return this.tiles.getMap()[x/this.tileSize][y/tileSize].getType();
    }
    public Tile getTile(int x, int y){
        return this.tiles.getMap()[x/this.tileSize][y/tileSize];
    }

}
