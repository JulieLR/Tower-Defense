package model;
import java.awt.image.BufferedImage;
import java.lang.Math.*;

public class Tower extends Entities {
    // Attribut
    //private int pointDeVie;
    private int prix;
    private int type; // 0: physique, 1: magique
    private int id; // identifiant pour savoir quelle tour specifiquement
    
public Tower(/* int vie, */ int prix, Coordinates pos,int degat, int vitesseAtk, int type, int id){
    super(vitesseAtk, pos, degat);
    //this.pointDeVie=vie;
    this.prix=prix;
    this.type=type;
    this.id= id;
    }

    // Methodes

    // getter et setter
    /* public int getPointDeVie () {
        return this.pointDeVie;
    } 
    public void setPointDeVie (int pointDeVie) {
        this.pointDeVie=pointDeVie;
    } */

    public int getPrix () {
        return this.prix;
    }
    public void setPrix (int prix) {
        this.prix=prix;
    }

    public int getType () {
        return this.type;
    }
    public void setType (int type) {
        this.type= type;
    }

    public int getId () {
        return this.id;
    }
    public void setid(int id) {
        this.id= id;
    }

    // isDead
    /* public boolean isDead () {
        return this.pointDeVie<=0;
    } */


}
