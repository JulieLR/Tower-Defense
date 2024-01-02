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
        for (int ligne=0; ligne<10; ligne++) {
            numberAsset.add(this.game.getNumberImage().getSubimage(ligne*9, 0, 9, 12));
        }
        numberAsset.add(this.game.getAllSpriteImage().getSubimage(16*9, 16*7, 16, 16));
    }

    @Override
    public void drawImages(Graphics g) {
        int life_tmp=this.base.getPointDeVie();
        for (int i=3; i>=0; i--) {
            int chiffre=life_tmp%10;
            g.drawImage(this.numberAsset.get(chiffre), 20+i*40, 15, 45, 60, null);
            life_tmp/=10;
        } 
        g.drawImage(this.numberAsset.get(10), 190, 20, 45, 45, null);

    }

}
