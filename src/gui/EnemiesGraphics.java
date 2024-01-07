package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import config.EnemiesConfig;
import config.Tile;
import config.Tile.Type;
import model.Enemy;
import model.Knight;
import model.Slime;
import model.Tank;
import model.Bat;
import model.Coordinates;
import model.Direction;

public class EnemiesGraphics implements Graphic{
    
    private Game game;
    private EnemiesConfig enemiesConfig;
    private BufferedImage enemiesSprite, batSprite, slimeSprite;
    private ArrayList<BufferedImage> enemiesAsset = new ArrayList<>();
    private ArrayList<BufferedImage> batAsset = new ArrayList<>();
    private ArrayList<BufferedImage> slimeAsset = new ArrayList<>();
    private ArrayList<Enemy> enemies;
    private int count=0;

    public EnemiesGraphics(Game game,EnemiesConfig e){

        this.enemiesSprite= getImage("src/ressources/enemies/enemiesSprite.png");
        this.batSprite= getImage("src/ressources/enemies/batSprite.png");
        this.slimeSprite= getImage("src/ressources/enemies/slimesSprite.png");

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
                g.drawString(Integer.toString(e.getNumber()), (int) e.getPos().getX(), (int) e.getPos().getY());
                if(e.isAtEnd()){
                    if(e instanceof Knight){
                        drawKnightAttack(g, time,e);
                    }else if(e instanceof Bat){
                        drawBatAttack(g, time, e);
                    }
                    else if(e instanceof Slime){
                            drawSlimeAttack(g, time, (Slime)e);
                        }
                    //drawKnightStun(g, time,e);
                }
                else{
                    if(e instanceof Knight || e instanceof Tank){
                        drawKnightWalking(g, time,e);
                    }
                    else{
                        if(e instanceof Slime){
                            drawSlimeWalk(g, time,(Slime) e);
                        }
                        else{
                            if(e instanceof Bat){
                                drawBatFlying(g, time, e);
                            }
                        }
                    }
                }
                g.drawRect((int)e.getHitBox().getX(), (int)e.getHitBox().getY(), (int)e.getHitBox().getWidth(), (int)e.getHitBox().getHeight());
                
            }
            else if(e.isSpawned()){
                if(!e.getDeadAnimation()){
                    drawDead(g, time, e);
                    /* count++;
                    if(count>=30){
                        e.setDeadAnimation(true);
                        count=0;
                    } */
                }
            }
        }
    }

    public int getEcartDirection(Direction dir, boolean slime){
        int d=0;
        switch (dir){
            case NORTH : d= slime? 6: 4;
                break;
            case SOUTH : d= slime? 4: 0;
                break;
            case WEST: d= slime? 2:8;
                break;
            case EAST: d= slime? 0:12;
                break;
        }
        return d;
    }


    public void drawRainbowSlimeWaalk(Graphics g, long time, Slime e){
        int p = getEcartDirection(e.getDir(), true);

        int q = (int)(1200f/0.5f);
        int n = (int)(300f/0.5f);
        int h = (int)(800f/0.5f);
        int m = (int)(600f/0.5f);
        int k = (int)(400f/0.5f);
        int l = (int)(200f/0.5f);
        int o = (int)(100f/0.5f);

        if(time%n<o){
            g.drawImage(this.slimeAsset.get(e.getColor()+p),(int) e.getPos().getX(),(int)e.getPos().getY()-this.game.getInitialTileSize(),this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else{
            g.drawImage(this.slimeAsset.get(e.getColor()+p+1),(int) e.getPos().getX(),(int)e.getPos().getY()-this.game.getInitialTileSize(),this.game.getTileSize(),this.game.getTileSize(), null);
        }
        /* else if(time%n<k){
            g.drawImage(this.slimeAsset.get(p+2),(int) e.getPos().getX(),(int)e.getPos().getY()-this.game.getInitialTileSize(),this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else if(time%n<m){
            g.drawImage(this.slimeAsset.get(p+3),(int) e.getPos().getX(),(int)e.getPos().getY()-this.game.getInitialTileSize(),this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else if(time%n<h){
            g.drawImage(this.slimeAsset.get(p+4),(int) e.getPos().getX(),(int)e.getPos().getY()-this.game.getInitialTileSize(),this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else if(time%n<q){
            g.drawImage(this.slimeAsset.get(p+5),(int) e.getPos().getX(),(int)e.getPos().getY()-this.game.getInitialTileSize(),this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else{
            g.drawImage(this.slimeAsset.get(p+6),(int) e.getPos().getX(),(int)e.getPos().getY()-this.game.getInitialTileSize(),this.game.getTileSize(),this.game.getTileSize(), null);
        }  */
    }

    public void drawSlimeAttack(Graphics g, long time, Slime e){
        int n = (int)(200f/e.getSpeed());
        int m = (int)(100f/e.getSpeed());

        if(time%n<m){
            g.drawImage(this.slimeAsset.get(e.getColor()*9+0),(int)( e.getPos().getX()-this.game.getInitialTileSize()*2),(int)(e.getPos().getY()-this.game.getInitialTileSize()*3),this.game.getTileSize()*2,this.game.getTileSize()*2, null);
        }
        else{
            g.drawImage(this.slimeAsset.get(e.getColor()*9+8),(int)( e.getPos().getX()-this.game.getInitialTileSize()*2),(int)(e.getPos().getY()-this.game.getInitialTileSize()*3),this.game.getTileSize()*2,this.game.getTileSize()*2, null);
        }
    }

    public void drawSlimeWalk(Graphics g, long time, Slime e){

        int n = (int)(200f/e.getSpeed());
        int m = (int)(100f/e.getSpeed());

        int p = getEcartDirection(e.getDir(), true);
        if(time%n<m){
            g.drawImage(this.slimeAsset.get(e.getColor()*9+p),(int)( e.getPos().getX()-this.game.getInitialTileSize()*2),(int)(e.getPos().getY()-this.game.getInitialTileSize()*3),this.game.getTileSize()*2,this.game.getTileSize()*2, null);
        }
        else{
            g.drawImage(this.slimeAsset.get(e.getColor()*9+p+1),(int) (e.getPos().getX()-this.game.getInitialTileSize()*2),(int)(e.getPos().getY()-this.game.getInitialTileSize()*3),this.game.getTileSize()*2,this.game.getTileSize()*2, null);
        }


    }

    public void drawBatFlying(Graphics g, long time, Enemy e){

        int d=getEcartDirection(e.getDir(),false);

        int n = (int)(400f/e.getSpeed());
        int m = (int)(300f/e.getSpeed());
        int k = (int)(200f/e.getSpeed());
        int l = (int)(100f/e.getSpeed());
        
        if(time%n<l){
            g.drawImage(this.batAsset.get(0+d),(int) e.getPos().getX()-this.game.getTileSize()/2,(int)e.getPos().getY()-this.game.getTileSize(),this.game.getTileSize()*2,this.game.getTileSize(), null);
        }
        else if(time%n<k){
            g.drawImage(this.batAsset.get(1+d),(int) e.getPos().getX()-this.game.getTileSize()/2,(int)e.getPos().getY()-this.game.getTileSize(),this.game.getTileSize()*2,this.game.getTileSize(), null);
        }
        else if(time%n<m){
            g.drawImage(this.batAsset.get(2+d),(int) e.getPos().getX()-this.game.getTileSize()/2,(int)e.getPos().getY()-this.game.getTileSize(),this.game.getTileSize()*2,this.game.getTileSize(), null);
        }
        else{
            g.drawImage(this.batAsset.get(3+d),(int) e.getPos().getX()-this.game.getTileSize()/2,(int)e.getPos().getY()-this.game.getTileSize(),this.game.getTileSize()*2,this.game.getTileSize(), null);
        } 
    }

    public void drawBatAttack(Graphics g, long time, Enemy e){
        int x = 16;

        int a = (int)(600f/e.getSpeed());
        int b = (int)(500f/e.getSpeed());
        int c= (int)(400f/e.getSpeed());
        int d = (int)(300f/e.getSpeed());
        int f = (int)(200f/e.getSpeed());
        int h = (int)(100f/e.getSpeed());
        
        
        if(time%a<h){
            g.drawImage(this.batAsset.get(0+x),(int) e.getPos().getX()-this.game.getTileSize()/2,(int)e.getPos().getY()-this.game.getTileSize(),this.game.getTileSize()*2,this.game.getTileSize(), null);
        }
        else if(time%a<f){
            g.drawImage(this.batAsset.get(1+x),(int) e.getPos().getX()-this.game.getTileSize()/2,(int)e.getPos().getY()-this.game.getTileSize(),this.game.getTileSize()*2,this.game.getTileSize(), null);
        }
        else if(time%a<d){
            g.drawImage(this.batAsset.get(2+x),(int) e.getPos().getX()-this.game.getTileSize()/2,(int)e.getPos().getY()-this.game.getTileSize(),this.game.getTileSize()*2,this.game.getTileSize(), null);
        }
        else if(time%a<c){
            g.drawImage(this.batAsset.get(3+x),(int) e.getPos().getX()-this.game.getTileSize()/2,(int)e.getPos().getY()-this.game.getTileSize(),this.game.getTileSize()*2,this.game.getTileSize(), null);
        }
        else if(time%a<b){
            g.drawImage(this.batAsset.get(4+x),(int) e.getPos().getX()-this.game.getTileSize()/2,(int)e.getPos().getY()-this.game.getTileSize(),this.game.getTileSize()*2,this.game.getTileSize(), null);
        }
        else{
            g.drawImage(this.batAsset.get(5+x),(int) e.getPos().getX()-this.game.getTileSize()/2,(int)e.getPos().getY()-this.game.getTileSize(),this.game.getTileSize()*2,this.game.getTileSize(), null);
        } 
    }
    
    public void drawKnightWalking(Graphics g, long time,Enemy e){
        //vitesse d'animation selon la vitesse du personnage
        int n = (int)(200f/e.getSpeed());
        int m = (int)(100f/e.getSpeed());

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
    public void drawKnightAttack(Graphics g,long time,Enemy e){

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
    public void drawKnightStun(Graphics g, long time, Enemy e){
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
        e.setTimeDead(System.currentTimeMillis());

        int n = (int)(600f/0.5f);
        int h = (int)(500f/0.5f);
        int m = (int)(400f/0.5f);
        int k = (int)(300f/0.5f);
        int l = (int)(200f/0.5f);
        int o = (int)(100f/0.5f);
        int p = 23;

        if(e.getTimeDead()%n<o){
            g.drawImage(this.enemiesAsset.get(p),(int) e.getPos().getX(),(int)e.getPos().getY()-32,this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else if(e.getTimeDead()%n<l){
            g.drawImage(this.enemiesAsset.get(p+1),(int) e.getPos().getX(),(int)e.getPos().getY()-32,this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else if(e.getTimeDead()%n<k){
            g.drawImage(this.enemiesAsset.get(p+2),(int) e.getPos().getX(),(int)e.getPos().getY()-32,this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else if(e.getTimeDead()%n<m){
            g.drawImage(this.enemiesAsset.get(p+3),(int) e.getPos().getX(),(int)e.getPos().getY()-32,this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else if(e.getTimeDead()%n<h){
            g.drawImage(this.enemiesAsset.get(p+4),(int) e.getPos().getX(),(int)e.getPos().getY()-32,this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else if(e.getTimeDead()%n>h){
            e.setDeadAnimation(true);
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
                enemiesAsset.add(this.enemiesSprite.getSubimage(col*this.game.getInitialTileSize(), ligne*this.game.getInitialTileSize(), this.game.getInitialTileSize(), this.game.getInitialTileSize()));
            }
        }
        for(int ligne=0;ligne<6;ligne++){
            for(int col=0;col<8;col++){
                batAsset.add(this.batSprite.getSubimage(col*this.game.getInitialTileSize(), ligne*this.game.getInitialTileSize(), this.game.getInitialTileSize()*2, this.game.getInitialTileSize()));
                col++;
            }
        }

        for(int ligne=0;ligne<7;ligne++){
            for(int col=0;col<9;col++){
                slimeAsset.add(this.slimeSprite.getSubimage(col*72, ligne*72, 72, 72));
            }
        }

    }

    public void update(){
        this.enemiesConfig.update();
    }
}
