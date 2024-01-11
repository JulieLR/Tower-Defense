package gui;

import config.IconsConfig;
import config.PowersConfig;
import model.Power;
import model.Tower;
import model.Enemies.Bat;
import model.Enemies.Enemy;
import model.Power.Element;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PowersGraphics implements Graphic{
    
    private Game game;
    private PowersConfig powersConfig;
    private ArrayList<Enemy> enemies;
    private ArrayList<Tower> towers;

    private BufferedImage iceAndFireImg;
    private BufferedImage thunderImg;
    private BufferedImage upImg;

    private ArrayList<BufferedImage> powersAsset= new ArrayList<>();


    private Power actualPower;
    private boolean animationDone=false;


    public PowersGraphics(PowersConfig powersConfig, Game game){

        this.iceAndFireImg = getImage("src/ressources/Powers/powersSprite.png");
        this.thunderImg= getImage("src/ressources/Powers/thunderSprite.png");
        this.upImg= getImage("src/ressources/Powers/upSprite.png");

        this.game=game;
        this.powersConfig=powersConfig;

        addAsset();
    }

    public Power getActualPower() {
        return actualPower;
    }

    public void setActualPower(Power actualPower) {
        this.actualPower = actualPower;
    }

    public void setAnimationDone(boolean animationDone) {
        this.animationDone = animationDone;
    }

    public ArrayList<BufferedImage> getPowersAsset() {
        return powersAsset;
    }

    @Override
    public void addAsset() {
        for(int ligne=0;ligne<2;ligne++){
            for(int col=0;col<10;col++){
                powersAsset.add(this.iceAndFireImg.getSubimage(col*32, ligne*32, 32, 32));
            }
        }
        for(int col=0;col<8;col++){
            powersAsset.add(this.thunderImg.getSubimage(col*64, 0, 64, 64));
        }
        for(int col=0;col<4;col++){
            powersAsset.add(this.upImg.getSubimage(col*25, 0, 25, 25));
        }
    }

    @Override
    public void drawImages(Graphics g) {
        long time = System.currentTimeMillis();
        this.enemies=this.game.getEnemyConfig().getEnemies();
        this.towers=this.game.getTowerConfig().getMouseTowers();

        if(actualPower!=null ) {
        if(!actualPower.isAnimationDone()){
        if(this.actualPower.getType()== Element.FIRE){
            for(Enemy e :enemies){
                if(e.isAlived()){
                    int hauteur=0;
                    if(e instanceof Bat){
                        hauteur=this.game.getTileSize();
                    }  
                    drawIceOrFire(g,e,time,false,hauteur);
                }
            }
        }
        else if(this.actualPower.getType()==Element.ICE){
            for(Enemy e :enemies){
                if(e.isAlived() && !(e instanceof Bat)){
                    drawIce(g,e,time);
                }
            }
        }
        else if(this.actualPower.getType()==Element.THUNDER){
            for(Enemy e :enemies){
                if(e.isAlived()){
                    int hauteur=0;
                    if(e instanceof Bat){
                        hauteur=this.game.getTileSize();
                    }           
                    drawThunder(g,e,time,hauteur);
                }
            }
        }
        else if(this.actualPower.getType()==Element.HEAL){
            for(Tower t :towers){
                drawUp(g,t,time);
            }
        }
    }
    }
    }

    private void drawThunder(Graphics g, Enemy e, long times,int hauteur) { 

        long time = times - actualPower.getClickedTime();

        int r = (int)(1600f/0.5f);
        int s = (int)(1400f/0.5f);
        int q = (int)(1200f/0.5f);
        int n = (int)(1000f/0.5f);
        int h = (int)(800f/0.5f);
        int m = (int)(600f/0.5f);
        int k = (int)(400f/0.5f);
        int l = (int)(200f/0.5f);
        int o = (int)(100f/0.5f);


        if(time%r<o){
            g.drawImage(this.powersAsset.get(20),(int) e.getPos().getX()-this.game.getInitialTileSize(),(int)e.getPos().getY()-this.game.getInitialTileSize()*3-hauteur,this.game.getInitialTileSize()*6,this.game.getInitialTileSize()*6, null);
        }
        else if(time%r<l){
            g.drawImage(this.powersAsset.get(21),(int) e.getPos().getX()-this.game.getInitialTileSize(),(int)e.getPos().getY()-this.game.getInitialTileSize()*3-hauteur,this.game.getInitialTileSize()*6,this.game.getInitialTileSize()*6, null);
        }
        else if(time%r<k){
            g.drawImage(this.powersAsset.get(22),(int) e.getPos().getX()-this.game.getInitialTileSize(),(int)e.getPos().getY()-this.game.getInitialTileSize()*3-hauteur,this.game.getInitialTileSize()*6,this.game.getInitialTileSize()*6, null);
        }
        else if(time%r<m){
            g.drawImage(this.powersAsset.get(23),(int) e.getPos().getX()-this.game.getInitialTileSize(),(int)e.getPos().getY()-this.game.getInitialTileSize()*3-hauteur,this.game.getInitialTileSize()*6,this.game.getInitialTileSize()*6, null);
        }
        else if(time%r<h){
            g.drawImage(this.powersAsset.get(24),(int) e.getPos().getX()-this.game.getInitialTileSize(),(int)e.getPos().getY()-this.game.getInitialTileSize()*3-hauteur,this.game.getInitialTileSize()*6,this.game.getInitialTileSize()*6, null);
        }
        else if(time%r<n){
            g.drawImage(this.powersAsset.get(25),(int) e.getPos().getX()-this.game.getInitialTileSize(),(int)e.getPos().getY()-this.game.getInitialTileSize()*3-hauteur,this.game.getInitialTileSize()*6,this.game.getInitialTileSize()*6, null);
        }
        else if(time%r<q){
            g.drawImage(this.powersAsset.get(26),(int) e.getPos().getX()-this.game.getInitialTileSize(),(int)e.getPos().getY()-this.game.getInitialTileSize()*3-hauteur,this.game.getInitialTileSize()*6,this.game.getInitialTileSize()*6, null);
        }
        else if(time%r<s){
            g.drawImage(this.powersAsset.get(27),(int) e.getPos().getX()-this.game.getInitialTileSize(),(int)e.getPos().getY()-this.game.getInitialTileSize()*3-hauteur,this.game.getInitialTileSize()*6,this.game.getInitialTileSize()*6, null);
        }
        else if(time%r>=s){
            actualPower.setAnimationDone(true);
        }
    }

    private void drawIceOrFire(Graphics g, Enemy e, long times, boolean Ice, int hauteur) {

        long time = times - actualPower.getClickedTime();

        int ecart=0;
        if(!Ice){
            ecart = 10;
        }

        int r = (int)(2200f/0.5f);
        int s = (int)(2000f/0.5f);
        int q = (int)(1800f/0.5f);
        int n = (int)(1200f/0.5f);
        int h = (int)(1000f/0.5f);
        int m = (int)(800f/0.5f);
        int k = (int)(600f/0.5f);
        int l = (int)(400f/0.5f);
        int o = (int)(200f/0.5f);

        if(time%r<o){
            g.drawImage(this.powersAsset.get(ecart),(int) e.getPos().getX()-this.game.getInitialTileSize(),(int)e.getPos().getY()-this.game.getInitialTileSize()*3-hauteur,this.game.getInitialTileSize()*6,this.game.getInitialTileSize()*6, null);
        }
        else if(time%r<l){
            g.drawImage(this.powersAsset.get(ecart+1),(int) e.getPos().getX()-this.game.getInitialTileSize(),(int)e.getPos().getY()-this.game.getInitialTileSize()*3-hauteur,this.game.getInitialTileSize()*6,this.game.getInitialTileSize()*6, null);
        }
        else if(time%r<k){
            g.drawImage(this.powersAsset.get(ecart+2),(int) e.getPos().getX()-this.game.getInitialTileSize(),(int)e.getPos().getY()-this.game.getInitialTileSize()*3-hauteur,this.game.getInitialTileSize()*6,this.game.getInitialTileSize()*6, null);
        }
        else  if(time%r<m){
            g.drawImage(this.powersAsset.get(ecart+3),(int) e.getPos().getX()-this.game.getInitialTileSize(),(int)e.getPos().getY()-this.game.getInitialTileSize()*3-hauteur,this.game.getInitialTileSize()*6,this.game.getInitialTileSize()*6, null);
        }
        else if(time%r<h){
            g.drawImage(this.powersAsset.get(ecart+4),(int) e.getPos().getX()-this.game.getInitialTileSize(),(int)e.getPos().getY()-this.game.getInitialTileSize()*3-hauteur,this.game.getInitialTileSize()*6,this.game.getInitialTileSize()*6, null);
        }
        else if(time%r<n){
            g.drawImage(this.powersAsset.get(ecart+5),(int) e.getPos().getX()-this.game.getInitialTileSize(),(int)e.getPos().getY()-this.game.getInitialTileSize()*3-hauteur,this.game.getInitialTileSize()*6,this.game.getInitialTileSize()*6, null);
        }
        else if(time%r<q){
            g.drawImage(this.powersAsset.get(ecart+6),(int) e.getPos().getX()-this.game.getInitialTileSize(),(int)e.getPos().getY()-this.game.getInitialTileSize()*3-hauteur,this.game.getInitialTileSize()*6,this.game.getInitialTileSize()*6, null);
        }
         else if(time%r<s){
            g.drawImage(this.powersAsset.get(ecart+7),(int) e.getPos().getX()-this.game.getInitialTileSize(),(int)e.getPos().getY()-this.game.getInitialTileSize()*3-hauteur,this.game.getInitialTileSize()*6,this.game.getInitialTileSize()*6, null);
        } 
        else{
            g.drawImage(this.powersAsset.get(ecart+8),(int) e.getPos().getX()-this.game.getInitialTileSize(),(int)e.getPos().getY()-this.game.getInitialTileSize()*3-hauteur,this.game.getInitialTileSize()*6,this.game.getInitialTileSize()*6, null);
            actualPower.setAnimationDone(true);
        }

    }

    public void drawIce(Graphics g, Enemy e, long times){

        long time = times - actualPower.getClickedTime();

        int r = (int)(2400f/0.5f);
        int s = (int)(2000f/0.5f);
        int k = (int)(400f/0.5f);
        int l = (int)(200f/0.5f);
        int o = (int)(100f/0.5f);

        if(time%r<o){
            g.drawImage(this.powersAsset.get(3),(int) e.getPos().getX()-this.game.getInitialTileSize(),(int)e.getPos().getY()-this.game.getInitialTileSize()*3,this.game.getInitialTileSize()*6,this.game.getInitialTileSize()*6, null);
        }
        else if(time%r<l){
            g.drawImage(this.powersAsset.get(4),(int) e.getPos().getX()-this.game.getInitialTileSize(),(int)e.getPos().getY()-this.game.getInitialTileSize()*3,this.game.getInitialTileSize()*6,this.game.getInitialTileSize()*6, null);
        }
        else if(time%r<k){
            g.drawImage(this.powersAsset.get(5),(int) e.getPos().getX()-this.game.getInitialTileSize(),(int)e.getPos().getY()-this.game.getInitialTileSize()*3,this.game.getInitialTileSize()*6,this.game.getInitialTileSize()*6, null);
        }
         else if(time%r<s){
            g.drawImage(this.powersAsset.get(6),(int) e.getPos().getX()-this.game.getInitialTileSize(),(int)e.getPos().getY()-this.game.getInitialTileSize()*3,this.game.getInitialTileSize()*6,this.game.getInitialTileSize()*6, null);
        } 
        else{
            g.drawImage(this.powersAsset.get(7),(int) e.getPos().getX()-this.game.getInitialTileSize(),(int)e.getPos().getY()-this.game.getInitialTileSize()*3,this.game.getInitialTileSize()*6,this.game.getInitialTileSize()*6, null);
            actualPower.setAnimationDone(true);
            this.powersConfig.setIceDone(true);
        }
    }

    public void drawUp(Graphics g, Tower t, long times){

        long time = times - actualPower.getClickedTime();

        int n = (int)(400f/0.5f);
        int m = (int)(300f/0.5f);
        int k = (int)(200f/0.5f);
        int l = (int)(100f/0.5f);
        
        if(time%n<l){
            g.drawImage(this.powersAsset.get(28),(int) t.getPos().getX(),IconsConfig.getYAlignment(t),this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else if(time%n<k){
            g.drawImage(this.powersAsset.get(29),(int) t.getPos().getX(),IconsConfig.getYAlignment(t),this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else if(time%n<m){
            g.drawImage(this.powersAsset.get(30),(int) t.getPos().getX(),IconsConfig.getYAlignment(t),this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else{
            g.drawImage(this.powersAsset.get(31),(int) t.getPos().getX(),IconsConfig.getYAlignment(t),this.game.getTileSize(),this.game.getTileSize(), null);
            actualPower.setAnimationDone(true);
        } 

    }
    

     
}
