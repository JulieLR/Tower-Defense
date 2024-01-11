package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import config.EnemiesConfig;
import config.IconsConfig;
import config.TowerConfig;
import model.Base;
import model.Coordinates;
import model.Power;
import model.Tower;
import model.Enemies.Enemy;

public class NumberGraphics implements Graphic{
    private Game game;
    private BufferedImage numberImage, allSpriteImage;
    private ArrayList<BufferedImage> numberAsset = new ArrayList<>();
    private ArrayList<Icon> icons = new ArrayList<>();
    private Tower tower;

    public NumberGraphics (Game game, IconsConfig iconConfig) {
        this.game= game;
        this.numberImage= getImage("src/ressources/number/number.png");
        this.allSpriteImage= getImage("src/ressources/sprite.png");
        this.addAsset();
        this.tower=this.game.getTowerConfig().getTowers().get(0);
        this.icons= iconConfig.getIcons();
        //this.towerConfig=this.game.getTowerConfig();
    }

    @Override
    public void addAsset() {
        for (int ligne=0; ligne<10; ligne++) { 
            // asset des chiffres
            numberAsset.add(this.numberImage.getSubimage(ligne*9, 0, 9, 12));
        }
        // asset du coeur
        numberAsset.add(this.allSpriteImage.getSubimage(16*9, 16*7, 16, 16));
        // asset de l'argent
        numberAsset.add(this.allSpriteImage.getSubimage(16*9, 16*8, 16, 16));
    }

    @Override
    public void drawImages(Graphics g) {
        this.drawBaseLife(g);
        g.drawImage(this.numberAsset.get(10), 170, 10, 54, 54, null);
        this.drawMoney(g);
        g.drawImage(this.numberAsset.get(11), 170, 65, 54, 54, null);
        this.drawIconsPrice(g);

    }

    public void drawNumber(Graphics g, int n, Coordinates c, int taille, int ecart) {
         if (n==0) {
           g.drawImage(this.numberAsset.get(0), (int) c.getX(), (int) c.getY(), 9*taille, 12*taille, null);
        }
        else {
            int tmp= n;
            int j= 0; 
            int div=1000;
            while ((tmp/div)%10==0 && j<4) {
                j++;
                div/=10;

            }
            for (int i=0; i<4-j; i++) {
                int chiffre=tmp%10;
                g.drawImage(this.numberAsset.get(chiffre), (int) c.getX()-(i)*ecart, (int) c.getY(), 9*taille, 12*taille, null);
                tmp/=10;
            } 
        }
    }

    private void drawBaseLife(Graphics g) {
        drawNumber(g, this.game.getBase().getPointDeVie(), new Coordinates(130, 15), 4, 32);
    }

    private void drawMoney (Graphics g) {
        drawNumber(g, this.game.getBase().getArgent(), new Coordinates(130, 65), 4, 32);
    }

    private void drawTowerPrice (Graphics g) {
        for (int i=0; i<6; i++) {
            if (i==0 || i==3 || i==4) {
                drawNumber(g, this.tower.towerEnum(i).getPrice(), new Coordinates(70+160*i, 780), 3, 25);
                g.drawImage(this.numberAsset.get(11), 95+160*i, 785, 27, 27, null);
            }
            else {
                drawNumber(g, this.tower.towerEnum(i).getPrice(), new Coordinates(80+160*i, 780), 3, 25);
                g.drawImage(this.numberAsset.get(11), 105+160*i, 785, 27, 27, null);
            }
        }
    }

    public void drawIconsPrice(Graphics g){
        for(Icon icon : icons){
            //Towers
            if(icon.getPower()==null){
                drawNumber(g, Tower.getPriceByNumber(icon.getTower()), new Coordinates((int)(icon.getZone().getX()+icon.getZone().getWidth()/2-icon.getZone().getWidth()/8-4), (int) (icon.getZone().getY()+icon.getZone().getHeight()+3)), 3, 25);
            }
            //Powers
            else{
                drawNumber(g, Power.prices(icon.getPower()), new Coordinates((int)(icon.getZone().getX()+icon.getZone().getWidth()/2-icon.getZone().getWidth()/8-4), (int) (icon.getZone().getY()+icon.getZone().getHeight()+3)), 3, 25);
            }
            g.drawImage(this.numberAsset.get(11), (int)(icon.getZone().getX()+icon.getZone().getWidth()-icon.getZone().getWidth()/4-8),(int) (icon.getZone().getY()+icon.getZone().getHeight()+7), 27, 27, null);
        }
    }

}
