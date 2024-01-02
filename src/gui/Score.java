package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import config.EnemiesConfig;
import model.Enemy;

public class Score implements Graphic{
    private Game game;
    private ArrayList<BufferedImage> numberAsset = new ArrayList<>();
    private ArrayList<Enemy> enemies= new ArrayList<> ();
    private int score=0;

    public Score (Game game) {
        this.game= game;
        this.addAsset();
        this.enemies=this.game.getEnemyConfig().getEnemies();
    }

    @Override
    public void addAsset() {
        for (int ligne=0; ligne<10; ligne++) {
            numberAsset.add(this.game.getNumberImage().getSubimage(ligne*9, 0, 9, 12));
        }
    }

    @Override
    public void drawImages(Graphics g) {
        int score_tmp=getScore();
        for (int i=4; i>=0; i--) {
            int chiffre=score_tmp%10;
            g.drawImage(this.numberAsset.get(chiffre), 20+i*40, 15, 45, 60, null);
            System.out.println(score_tmp);
            score_tmp/=10;
        } 
        

    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int n) {
        this.score=n;
    }

    public void addScore(int n) {
        this.score+=n;
    }
    
    public void updateScore () {
        for (Enemy e: enemies) {
            if (e.isSpawned()) {
                if (!e.isAlived()) {
                    this.addScore(13);
                    e.setSpawned(false);
                }
            }
        }
        
    }
}
