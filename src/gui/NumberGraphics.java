package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import config.EnemiesConfig;
import model.Base;
import model.Enemy;

public class NumberGraphics implements Graphic{
    private Game game;
    private ArrayList<BufferedImage> numberAsset = new ArrayList<>();
    private Base base;

    public NumberGraphics (Game game) {
        this.game= game;
        this.addAsset();
        this.base=this.game.getBase();
    }

    @Override
    public void addAsset() {
        for (int ligne=0; ligne<11; ligne++) { // image transparente
            numberAsset.add(this.game.getNumberImage().getSubimage(ligne*9, 0, 9, 12));
        }
        numberAsset.add(this.game.getAllSpriteImage().getSubimage(16*9, 16*7, 16, 16));
    }

    @Override
    public void drawImages(Graphics g) {
        if (this.game.getBase().getPointDeVie()==0) {
           g.drawImage(this.numberAsset.get(0), 80*32, 15, 36, 48, null);
        }
        else {
            int life_tmp= this.base.getPointDeVie();
            int j= 3; 
            int div=1000;
            while ((life_tmp/div)%10==0 && j>0) {
                j--;
                div/=10;

            }
            for (int i=j; i>=0; i--) {
                int chiffre=life_tmp%10;
                g.drawImage(this.numberAsset.get(chiffre), 80+i*32, 15, 36, 48, null);
                life_tmp/=10;
            } 
        }
        g.drawImage(this.numberAsset.get(11), 20, 10, 54, 54, null);

    }

}
