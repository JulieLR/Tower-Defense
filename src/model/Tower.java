package model;
import java.awt.image.BufferedImage;
import java.lang.Math.*;

public class Tower extends Entities {
    // Attribut
    private int pointDeVie;
    private int prix;
    
public Tower(int vie, int prix, Coordinates pos,int degat, int vitesseAtk, BufferedImage img){
    super(vitesseAtk, pos, degat, img);
    this.pointDeVie=vie;
    this.prix=prix;
}
    // Methodes

    // getter et setter
    public int getPointDeVie () {
        return this.pointDeVie;
    }
    public void setPointDeVie (int pointDeVie) {
        this.pointDeVie=pointDeVie;
    }

    public int getPrix () {
        return this.prix;
    }
    public void setPrix (int prix) {
        this.prix=prix;
    }

    // isDead
    public boolean isDead () {
        return this.pointDeVie<=0;
    }
    
    // attaque
    public void attaque (Entities p) {
        //p.pointDeVie=-super.getDegat();
    }

    // distance entre les deux personnes 
   /*  public double disBetween (Personnages p) {
        return Math.sqrt(
        Math.pow(p.position.x()-this.position.x(),2) +
        Math.pow(p.position.y()-this.position.y(),2));
    }*/


}
