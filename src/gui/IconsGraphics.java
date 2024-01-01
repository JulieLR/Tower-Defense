package gui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

import config.IconsConfig;
import java.util.ArrayList;
import java.awt.image.BufferedImage;

public class IconsGraphics {
    
    private Game game;
    private IconsConfig iconsConfig;
    private ArrayList<Icon> icons;

    public IconsGraphics(Game game, IconsConfig iconsConfig){

        this.game = game;
        this.iconsConfig=iconsConfig;
        this.icons = iconsConfig.getIcons();

    }

    public void drawIcons(Graphics g){
        int ecart = this.game.getWidth()/6;
        int hauteur = this.game.getInitialTileSize();
        for(int i=0; i<icons.size();i++){
            icons.get(i).setZone(new Rectangle(this.game.getTileSize()+ecart*i-(this.game.getTileSize()+this.game.getTileSize()/2)/4, 640+hauteur, this.game.getTileSize()+this.game.getTileSize()/2, this.game.getTileSize()+this.game.getTileSize()/2));
            g.fillRect((int)icons.get(i).getZone().getX(), (int)icons.get(i).getZone().getY(), (int)icons.get(i).getZone().getWidth(), (int)icons.get(i).getZone().getHeight());
            g.drawRect((int)icons.get(i).getZone().getX(), (int)icons.get(i).getZone().getY(), (int)icons.get(i).getZone().getWidth(), (int)icons.get(i).getZone().getHeight());
            g.drawImage(icons.get(i).getImage(), this.game.getTileSize()+ecart*i, 640+hauteur, this.game.getTileSize()-this.game.getTileSize()/4, this.game.getTileSize()+this.game.getTileSize()/2 ,null);
            g.setColor(Color.BLACK);
            g.drawString("PRICE", (int)icons.get(i).getZone().getX(), (int)icons.get(i).getZone().getY()+(int)icons.get(i).getZone().getHeight()+32);
        }
    }
}
