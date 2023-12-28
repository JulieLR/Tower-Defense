package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import config.EnemiesConfig;
import config.Tile;
import config.Tile.Type;
import model.Enemy;
import model.Coordinates;
import model.Direction;

public class EnemiesGraphics implements Graphic{
    
    private Game game;
    private EnemiesConfig enemiesConfig;
    private ArrayList<BufferedImage> enemiesAsset = new ArrayList<>();
    private ArrayList<Enemy> enemies;

    public EnemiesGraphics(Game game,EnemiesConfig e){

        this.game = game;
        this.enemiesConfig= e;
        this.enemies=e.getEnemies();
        addAsset();

    }

    @Override
    public void drawImages(Graphics g){
        long time = System.currentTimeMillis();
        for(Enemy e: enemies){
            if(e.isSpawned()&& e.isAlived()){
                if(e.isAtEnd()){
                    drawAttack(g, time,e);
                    //drawStun(g, time,e);
                }
                else{
                    drawWalking(g, time,e);
                    //drawDead(g, time, e);
                }
            }
        }
    }
    
    public void drawWalking(Graphics g, long time,Enemy e){
        //vitesse d'animation selon la vitesse du personnage
        int n = (int)(400f/e.getSpeed());
        int m = (int)(200f/e.getSpeed());

        int p = getEcart(e.getType());
        //time%250<100 et +-1, % tjr ratio 2:1 et plus % haut plus lent
        if(time%n<m){
            g.drawImage(this.enemiesAsset.get(2+p),(int) e.getPos().getX(),(int)e.getPos().getY()-(this.game.getTileSize()/this.game.getScale()),this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else{
            g.drawImage(this.enemiesAsset.get(3+p),(int) e.getPos().getX(),(int)e.getPos().getY()-(this.game.getTileSize()/this.game.getScale()),this.game.getTileSize(),this.game.getTileSize(), null);
        }   
        //g.drawImage(this.enemiesAsset.get(8),128,128,this.game.getTileSize(),this.game.getTileSize(), null);
 
    }

    //Animation d'attaque
    public void drawAttack(Graphics g,long time,Enemy e){

        int n = (int)(450f/e.getSpeed());
        int m = (int)(300f/e.getSpeed());
        int k = (int)(150f/e.getSpeed());
        int p = getEcart(e.getType());
        
        if(time%n<k){
            g.drawImage(this.enemiesAsset.get(4+p),(int) e.getPos().getX(),(int)e.getPos().getY()-32,this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else if(time%n<m){
            g.drawImage(this.enemiesAsset.get(5+p),(int) e.getPos().getX(),(int)e.getPos().getY()-32,this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else{
            g.drawImage(this.enemiesAsset.get(6+p),(int) e.getPos().getX(),(int)e.getPos().getY()-32,this.game.getTileSize(),this.game.getTileSize(), null);
        } 
    }

    //Animation de l'enemie lorsqu'il ne bouge pas
    public void drawStun(Graphics g, long time, Enemy e){
        int n = (int)(400f/e.getSpeed());
        int m = (int)(200f/e.getSpeed());

        int p = getEcart(e.getType());
        if(time%n<m){
            g.drawImage(this.enemiesAsset.get(0+p),(int) e.getPos().getX(),(int)e.getPos().getY()-(this.game.getTileSize()/this.game.getScale()),this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else{
            g.drawImage(this.enemiesAsset.get(1+p),(int) e.getPos().getX(),(int)e.getPos().getY()-(this.game.getTileSize()/this.game.getScale()),this.game.getTileSize(),this.game.getTileSize(), null);
        } 
    }

    public void drawDead(Graphics g, long time, Enemy e){
        int n = (int)(500f/0.5f);
        int m = (int)(400f/0.5f);
        int k = (int)(300f/0.5f);
        int l = (int)(200f/0.5f);
        int o = (int)(100f/0.5f);
        int p = 23;

        if(time%n<o){
            g.drawImage(this.enemiesAsset.get(p),(int) e.getPos().getX(),(int)e.getPos().getY()-32,this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else if(time%n<l){
            g.drawImage(this.enemiesAsset.get(p+1),(int) e.getPos().getX(),(int)e.getPos().getY()-32,this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else if(time%n<k){
            g.drawImage(this.enemiesAsset.get(p+2),(int) e.getPos().getX(),(int)e.getPos().getY()-32,this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else if(time%n<m){
            g.drawImage(this.enemiesAsset.get(p+3),(int) e.getPos().getX(),(int)e.getPos().getY()-32,this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else{
            g.drawImage(this.enemiesAsset.get(p+4),(int) e.getPos().getX(),(int)e.getPos().getY()-32,this.game.getTileSize(),this.game.getTileSize(), null);
        } 
    }

    //Ecart dans le sprite des enemies pour les chevaliers
    public int getEcart(int n){
        int p = 0;
        switch(n){
            case 1: p=1*8;
                break;
            case 2: p=2*8;
                break;
        }
        return p;
    }

    @Override
    public void addAsset(){
        for(int ligne=0;ligne<11;ligne++){
            for(int col=0;col<4;col++){
                enemiesAsset.add(this.game.getEnemyImage().getSubimage(col*this.game.getInitialTileSize(), ligne*this.game.getInitialTileSize(), this.game.getInitialTileSize(), this.game.getInitialTileSize()));
            }
        }
    }

    public void update(){
        this.enemiesConfig.update();
    }
}
