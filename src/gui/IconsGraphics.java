package gui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

import config.IconsConfig;
import config.TowerConfig;
import model.Coordinates;
import model.Tower;
import model.Power.Element;

import java.util.ArrayList;
import java.awt.image.BufferedImage;

public class IconsGraphics implements Graphic{
    
    private Game game;
    private IconsConfig iconsConfig;
    private ArrayList<Icon> icons;
    private BufferedImage iconsBackground;
    private BufferedImage iconsUpgrade;
    private BufferedImage powersIcons;
    private ArrayList<BufferedImage> backgroundIcons= new ArrayList<>();
    private ArrayList<BufferedImage> upgradeAsset= new ArrayList<>();
    private ArrayList<BufferedImage> powersIconAsset= new ArrayList<>();

    private TowerConfig towerConfig;

    private BufferedImage actualBackground;
    private Icon chosenIcon;

    private boolean isFollowing=false;


    private int followingIconNumber;
    private Coordinates mouse;

    public IconsGraphics(Game game, IconsConfig iconsConfig){

        this.game = game;
        this.iconsConfig=iconsConfig;
        this.iconsConfig.setIconsGraphics(this);
        this.icons = iconsConfig.getIcons();
        this.iconsBackground = getImage("src/ressources/towers/iconBackground.png");
        this.iconsUpgrade = getImage("src/ressources/towers/icons.png");
        this.powersIcons = getImage("src/ressources/Powers/powerIconSprite.png");
        addAsset();
        //this.towerConfig= this
    }

    public ArrayList<BufferedImage> getBackgroundIcons() {
        return backgroundIcons;
    }

    public ArrayList<BufferedImage> getUpgradeAsset() {
        return upgradeAsset;
    }

    public Icon getChosenIcon() {
        return chosenIcon;
    }

    public void setChosenIcon(Icon chosenIcon) {
        this.chosenIcon = chosenIcon;
    }

    public void setActualBackground(BufferedImage img){
        this.actualBackground=img;
    }

    public boolean isFollowing() {
        return isFollowing;
    }

    public void setFollowing(boolean isFollowing) {
        this.isFollowing = isFollowing;
    }

    public void setDrawMouseIcons(int number, Coordinates mouse){
        this.followingIconNumber=number;
        this.mouse=mouse;
    }

    public ArrayList<BufferedImage> getPowersIconAsset() {
        return powersIconAsset;
    }

    public void addAsset(){
        for(int i=0; i<2;i++){
            backgroundIcons.add(iconsBackground.getSubimage(i*32, 0, 32, 32));
        }
        for(int i=0;i<4;i++){
            upgradeAsset.add(iconsUpgrade.getSubimage(16*i, 0, 16, 16));
            System.out.println(upgradeAsset.size());
        }
        for(int i=0; i<3;i++){
            powersIconAsset.add(powersIcons.getSubimage(i*32, 0, 32, 32));
        }
        powersIconAsset.add(powersIcons.getSubimage(0, 32, 16, 16));
        this.actualBackground= backgroundIcons.get(0);
    }

    public void drawUpgrade(Graphics g,Icon icon, long time){
        int n = (int)(400f);
        int m = (int)(100f);

        if(time%n<m){
            g.drawImage(upgradeAsset.get(icon.getLevel()-1), (int)icon.getImgCoordinates().getX(),(int)icon.getImgCoordinates().getY(),64,64, null);        
        }
        else{
            g.drawImage(upgradeAsset.get(icon.getLevel()-1), (int)icon.getImgCoordinates().getX(),(int)icon.getImgCoordinates().getY()-2,64,64, null);
        }
        //g.drawRect((int) icon.getZone().getX(), (int)icon.getZone().getY(), (int)icon.getZone().getWidth(),(int) icon.getZone().getHeight());
    }

    public void drawImages(Graphics g){
        long time = System.currentTimeMillis();
        int ecart = this.game.getWidth()/6;
        int hauteur = this.game.getInitialTileSize();

        ArrayList<Icon> upgradeIcon = this.iconsConfig.getUpgradeIcon();

        for(Icon icon : upgradeIcon){
            drawUpgrade(g, icon,time);
        }
        
        for(int i=0; i<icons.size();i++){
            icons.get(i).setZone(new Rectangle(this.game.getTileSize()+ecart*i-10-(this.game.getTileSize()+this.game.getTileSize()/2)/4, 655+hauteur, this.game.getTileSize()+this.game.getTileSize()/2, this.game.getTileSize()+this.game.getTileSize()/2));
            
            if(icons.get(i)==chosenIcon){
                g.drawImage(actualBackground, (int)icons.get(i).getZone().getX(), (int)icons.get(i).getZone().getY(), (int)icons.get(i).getZone().getWidth(), (int)icons.get(i).getZone().getHeight(), null);
            }
            else{
                g.drawImage(backgroundIcons.get(0), (int)icons.get(i).getZone().getX(), (int)icons.get(i).getZone().getY(), (int)icons.get(i).getZone().getWidth(), (int)icons.get(i).getZone().getHeight(), null);
            }
            //Powers icons
            if(i>1){
                g.drawImage(icons.get(i).getImage(), this.game.getTileSize()+ecart*i-this.game.getTileSize()/4, 655+this.game.getTileSize()/2, this.game.getTileSize(), this.game.getTileSize() ,null);
                if(!this.iconsConfig.isUnlocked() && !(icons.get(i).getPower()==Element.HEAL)){
                    g.drawImage(powersIconAsset.get(3), this.game.getTileSize()+ecart*i-this.game.getTileSize()/4, 655+this.game.getTileSize()/2, this.game.getTileSize(), this.game.getTileSize() ,null);
                }
                if(icons.get(i).getPower()==Element.HEAL && !this.iconsConfig.isTowerUpgradeUnlocked()){
                    g.drawImage(powersIconAsset.get(3), this.game.getTileSize()+ecart*i-this.game.getTileSize()/4, 655+this.game.getTileSize()/2, this.game.getTileSize(), this.game.getTileSize() ,null);
                }
            }
            else{
                g.drawImage(icons.get(i).getImage(), this.game.getTileSize()+ecart*i-10, 655+hauteur/5, this.game.getTileSize()-this.game.getTileSize()/4, this.game.getTileSize()+this.game.getTileSize()/2 ,null);
            }
            //g.setColor(Color.BLACK);
            //g.drawString("PRICE", (int)icons.get(i).getZone().getX(), (int)icons.get(i).getZone().getY()+(int)icons.get(i).getZone().getHeight()+32);
        }
        if(this.mouse!=null){
            g.drawImage(icons.get(getIndex(followingIconNumber)).getImage(), (int)mouse.getX()-this.game.getInitialTileSize()*3/2, (int)mouse.getY()-this.game.getInitialTileSize()*6, this.game.getTileSize(), this.game.getTileSize()*2 ,null);
            g.setColor(new Color(200, 200, 200, 100));
            //this.iconsConfig.getTowerChosen().getAttackZone().getWidth()
            g.fillRect((int) (mouse.getX()-this.iconsConfig.getTowerChosen().getAttackZone().getWidth()/2), (int) (mouse.getY()-this.iconsConfig.getTowerChosen().getAttackZone().getHeight()/2), (int) this.iconsConfig.getTowerChosen().getAttackZone().getWidth() , (int) this.iconsConfig.getTowerChosen().getAttackZone().getHeight());
        }
    }

    public int getIndex(int n){
        if(n ==3){
            return 1;
        }else{
            return n;
        }
    }
}
