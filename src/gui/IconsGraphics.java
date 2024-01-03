package gui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

import config.IconsConfig;
import config.TowerConfig;
import model.Coordinates;
import model.Tower;

import java.util.ArrayList;
import java.awt.image.BufferedImage;

public class IconsGraphics implements Graphic{
    
    private Game game;
    private IconsConfig iconsConfig;
    private ArrayList<Icon> icons;
    private BufferedImage iconsBackground;
    private ArrayList<BufferedImage> backgroundIcons= new ArrayList<>();
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
        this.iconsBackground= this.game.getIconBackground();
        this.towerConfig=this.game.getTowerConfig();
        addAsset();
        //this.towerConfig= this
    }

    public ArrayList<BufferedImage> getBackgroundIcons() {
        return backgroundIcons;
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

    public void addAsset(){
        for(int i=0; i<2;i++){
            backgroundIcons.add(iconsBackground.getSubimage(i*32, 0, 32, 32));
        }
        this.actualBackground= backgroundIcons.get(0);
    }

    public void drawImages(Graphics g){
        int ecart = this.game.getWidth()/6;
        int hauteur = this.game.getInitialTileSize();
        
        for(int i=0; i<icons.size();i++){
            icons.get(i).setZone(new Rectangle(this.game.getTileSize()+ecart*i-(this.game.getTileSize()+this.game.getTileSize()/2)/4, 655+hauteur, this.game.getTileSize()+this.game.getTileSize()/2, this.game.getTileSize()+this.game.getTileSize()/2));
            if(icons.get(i)==chosenIcon){
                g.drawImage(actualBackground, (int)icons.get(i).getZone().getX(), (int)icons.get(i).getZone().getY(), (int)icons.get(i).getZone().getWidth(), (int)icons.get(i).getZone().getHeight(), null);
            }
            else{
                g.drawImage(backgroundIcons.get(0), (int)icons.get(i).getZone().getX(), (int)icons.get(i).getZone().getY(), (int)icons.get(i).getZone().getWidth(), (int)icons.get(i).getZone().getHeight(), null);
            }
            g.drawImage(icons.get(i).getImage(), this.game.getTileSize()+ecart*i, 655+hauteur, this.game.getTileSize()-this.game.getTileSize()/4, this.game.getTileSize()+this.game.getTileSize()/2 ,null);
            //g.setColor(Color.BLACK);
            //g.drawString("PRICE", (int)icons.get(i).getZone().getX(), (int)icons.get(i).getZone().getY()+(int)icons.get(i).getZone().getHeight()+32);
        }
        if(this.mouse!=null){
            g.drawImage(icons.get(followingIconNumber).getImage(), (int)mouse.getX()-this.game.getInitialTileSize()*3/2, (int)mouse.getY()-this.game.getInitialTileSize()*6, this.game.getTileSize(), this.game.getTileSize()*2 ,null);
            g.setColor(new Color(200, 200, 200, 100));
            //this.iconsConfig.getTowerChosen().getAttackZone().getWidth()
            g.fillRect((int) (mouse.getX()-this.iconsConfig.getTowerChosen().getAttackZone().getWidth()/2), (int) (mouse.getY()-this.iconsConfig.getTowerChosen().getAttackZone().getHeight()/2), (int) this.iconsConfig.getTowerChosen().getAttackZone().getWidth() , (int) this.iconsConfig.getTowerChosen().getAttackZone().getHeight());
        }
    }
}
