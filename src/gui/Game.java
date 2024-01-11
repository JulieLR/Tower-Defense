package gui;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import config.EnemiesConfig;
import config.IconsConfig;
import config.MapConfig;
import config.PowersConfig;
import config.Tile;
import config.TowerConfig;
import config.Tile.Type;
import model.Base;
import model.Coordinates;
import inputs.Keyboard_Listener;
import inputs.Mouse_Listener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
    final int barLine = 3;

    //Ecran
    final int col = 15;
    final int ligne= 10+barLine;//10 jeu et 2 bar

    final int width= col*tileSize; //960
    final int height= ligne*tileSize; //640+128

    //Le numero de la map choisi par le joueur par défaut on l'initialise à 1
    private int mapNumber=1;

    private MapConfig tiles;
    private MapGraphics mapGraphics;

    private EnemiesConfig enemiesConfig;
    private EnemiesGraphics enemies;

    private TowerConfig towerConfig;
    private TowerGraphics towerGraphics;

    private IconsConfig iconsConfig;
    private IconsGraphics iconsGraphics;

    private PowersConfig powersConfig;
    private PowersGraphics powersGraphics;

    private App app;

    private Base base;

    private Thread gameThread;
    private final double FPS= 120.0;
    private final double UPS= 60.0;
    
    private NumberGraphics numberGraphics;

    private int level;
    private int mode;

    private boolean isEnd=false;
    private boolean isEndFrameOn=false;

    // interaction clavier et souris (ici psk sinon ça compte aussi les coordonnées de la barre en haut avec le titre)
    private Mouse_Listener mouseListener; 
    private Keyboard_Listener keyboardlistener;

    public Game(int mode,int mapNumber, App app){

        this.app=app;
        this.mapNumber=mapNumber;

        this.tiles= new MapConfig(this,mode);
        this.mapGraphics= new MapGraphics(this, tiles);

        this.base = new Base(this,1000);
        this.mode=mode;
        this.level=1;

        this.enemiesConfig = new EnemiesConfig(this,mode,level,this.tiles);
        this.enemies= new EnemiesGraphics(this,this.enemiesConfig);

        this.towerConfig= new TowerConfig(this);
        this.towerGraphics= new TowerGraphics(this, towerConfig);

        this.powersConfig=new PowersConfig(this);
        this.powersGraphics= this.powersConfig.getPowersGraphics();

        this.iconsConfig= new IconsConfig(this);
        this.iconsGraphics = new IconsGraphics(this,this.iconsConfig);

        this.numberGraphics= new NumberGraphics(this,this.iconsConfig);

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

    public int getMapNumber() {
        return mapNumber;
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
    public NumberGraphics getNumberGraphics () {
        return this.numberGraphics;
    }

    public MapConfig getMapConfig() {
        return tiles;
    }

    public Base getBase() {
        return base;
    }
    public EnemiesConfig getEnemyConfig () {
        return this.enemiesConfig;
    }

    public App getApp() {
        return app;
    }

    public TowerConfig getTowerConfig() {
        return this.towerConfig;
    }

    public TowerGraphics getTowerGraphics() {
        return towerGraphics;
    }

    public IconsGraphics getIconsGraphics() {
        return iconsGraphics;
    }

    public PowersConfig getPowersConfig() {
        return powersConfig;
    }

    public int getLevel() {
        return level;
    }

    public void setEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    public void setEndFrameOn(boolean isEndFrameOn) {
        this.isEndFrameOn = isEndFrameOn;
    }


    public void paintComponent(Graphics g)  {
        super.paintComponent(g);

        mapGraphics.drawImages(g); //draw map à mettre avant le draw des characters
        if(!isEnd){
            towerGraphics.drawImages(g);
            enemies.drawImages(g);
            powersGraphics.drawImages(g);
            mapGraphics.drawBottomBarAndScore(g);
            iconsGraphics.drawImages(g);
            numberGraphics.drawImages(g);
        }else{
            mapGraphics.drawBottomBarAndScore(g);
        }

        g.dispose(); //
    }

    private void start() {
        gameThread= new Thread(this){};
        gameThread.start();
    }
    private void updateGame () {
        if(!isEnd){
            this.towerConfig.update();
            this.enemies.update();
            this.enemiesConfig.depasse();
            this.iconsConfig.update();
            powersConfig.update();
        }
        if(this.base.isDestroyed()){
            isEnd=true;
            if(!isEndFrameOn){
                new EndFrame(this, level, mode,false);
                isEndFrameOn=true;
            }
        }
        else if(this.enemiesConfig.isAllDead()){
            isEnd=true;
            if(!isEndFrameOn){
                new EndFrame(this, level, mode,true);
                isEndFrameOn=true;
            }
        }
    }

    public void resetBase(){
        this.base=new Base(this,1000);
    }

    public void resetEnemies(int mode, int level){
        this.enemiesConfig= new EnemiesConfig(this, mode,level,tiles);
        this.enemies= new EnemiesGraphics(this, enemiesConfig);
    }

    public void resetTowers(){
        this.towerConfig= new TowerConfig(this);
        this.towerGraphics= new TowerGraphics(this, towerConfig);
    }

    public void resetIcons(){
        this.iconsConfig=new IconsConfig(this);
        this.iconsGraphics=new IconsGraphics(this, iconsConfig);
    }

    public void resetAll(int mode, int level){
        resetBase();
        resetEnemies(mode, level);
        resetTowers();
        resetIcons();
        this.level=level;
    }

    @Override
    public void run() {
        double timeFrame= 1000000000.0/FPS;
        double timeUpdate= 1000000000.0/UPS;

        long lastFrame= System.nanoTime();
        long lastUpdate= System.nanoTime();
        long lastTimeCheck= System.currentTimeMillis(); 


        double spawnInterval = 5000000000d;//5s
        double delta =0;
        long currentTime;
        long lastTime = System.nanoTime();
        
        while (true) {
            if (System.nanoTime()- lastFrame>= timeFrame) {
                repaint();
                lastFrame= System.nanoTime();

            } 

            if (System.nanoTime()- lastUpdate>= timeUpdate) {
                updateGame();
                lastUpdate= System.nanoTime();

            }

            if (System.currentTimeMillis()- lastTimeCheck>= 1000) {
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
