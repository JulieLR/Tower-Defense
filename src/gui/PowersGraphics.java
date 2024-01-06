package gui;

import config.PowersConfig;
import model.Enemy;
import model.Power;
import model.Power.Element;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PowersGraphics implements Graphic{
    
    private Game game;
    private PowersConfig powersConfig;
    private ArrayList<Enemy> enemies;

    private BufferedImage iceAndFireImg;
    private BufferedImage thunderImg;

    private ArrayList<BufferedImage> powersAsset;

    private Power actualPower;

    public PowersGraphics(PowersConfig powersConfig, Game game){

        this.iceAndFireImg = getImage("src/ressources/Powers/powersSprite.png");
        this.thunderImg= getImage("src/ressources/Powers/thunderSprite.png");

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
    }

    @Override
    public void drawImages(Graphics g) {
        long time = System.currentTimeMillis();
        if(actualPower!=null) {
        if(this.actualPower.getType()== Element.FIRE){
            for(Enemy e :enemies){
                drawIceOrFire(g,e,time,false);
            }
        }
        else if(this.actualPower.getType()==Element.ICE){
            for(Enemy e :enemies){
                drawIceOrFire(g,e,time,false);
            }
        }
        else if(this.actualPower.getType()==Element.THUNDER){
            for(Enemy e :enemies){
                drawThunder(g,e,time);
            }
        }
        this.actualPower=null;
    }
    }

    private void drawThunder(Graphics g, Enemy e, long time) {
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
            g.drawImage(this.powersAsset.get(0),(int) e.getPos().getX(),(int)e.getPos().getY()-this.game.getInitialTileSize(),this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else if(time%r<l){
            g.drawImage(this.powersAsset.get(1),(int) e.getPos().getX(),(int)e.getPos().getY()-this.game.getInitialTileSize(),this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else if(time%r<k){
            g.drawImage(this.powersAsset.get(2),(int) e.getPos().getX(),(int)e.getPos().getY()-this.game.getInitialTileSize(),this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else if(time%r<m){
            g.drawImage(this.powersAsset.get(3),(int) e.getPos().getX(),(int)e.getPos().getY()-this.game.getInitialTileSize(),this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else if(time%r<h){
            g.drawImage(this.powersAsset.get(4),(int) e.getPos().getX(),(int)e.getPos().getY()-this.game.getInitialTileSize(),this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else if(time%r<n){
            g.drawImage(this.powersAsset.get(5),(int) e.getPos().getX(),(int)e.getPos().getY()-this.game.getInitialTileSize(),this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else if(time%r<q){
            g.drawImage(this.powersAsset.get(6),(int) e.getPos().getX(),(int)e.getPos().getY()-this.game.getInitialTileSize(),this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else if(time%r<s){
            g.drawImage(this.powersAsset.get(7),(int) e.getPos().getX(),(int)e.getPos().getY()-this.game.getInitialTileSize(),this.game.getTileSize(),this.game.getTileSize(), null);
        }
    }

    private void drawIceOrFire(Graphics g, Enemy e, long time, boolean Ice) {
        int ecart=0;
        if(!Ice){
            ecart = 10;
        }

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
            g.drawImage(this.powersAsset.get(ecart),(int) e.getPos().getX(),(int)e.getPos().getY()-this.game.getInitialTileSize(),this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else if(time%r<l){
            g.drawImage(this.powersAsset.get(ecart+1),(int) e.getPos().getX(),(int)e.getPos().getY()-this.game.getInitialTileSize(),this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else if(time%r<k){
            g.drawImage(this.powersAsset.get(ecart+2),(int) e.getPos().getX(),(int)e.getPos().getY()-this.game.getInitialTileSize(),this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else if(time%r<m){
            g.drawImage(this.powersAsset.get(ecart+3),(int) e.getPos().getX(),(int)e.getPos().getY()-this.game.getInitialTileSize(),this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else if(time%r<h){
            g.drawImage(this.powersAsset.get(ecart+4),(int) e.getPos().getX(),(int)e.getPos().getY()-this.game.getInitialTileSize(),this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else if(time%r<n){
            g.drawImage(this.powersAsset.get(ecart+5),(int) e.getPos().getX(),(int)e.getPos().getY()-this.game.getInitialTileSize(),this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else if(time%r<q){
            g.drawImage(this.powersAsset.get(ecart+6),(int) e.getPos().getX(),(int)e.getPos().getY()-this.game.getInitialTileSize(),this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else if(time%r<s){
            g.drawImage(this.powersAsset.get(ecart+7),(int) e.getPos().getX(),(int)e.getPos().getY()-this.game.getInitialTileSize(),this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else{
            g.drawImage(this.powersAsset.get(ecart+8),(int) e.getPos().getX(),(int)e.getPos().getY()-this.game.getInitialTileSize(),this.game.getTileSize(),this.game.getTileSize(), null);
        }

    }

     
}
