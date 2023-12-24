package model;
import java.awt.image.BufferedImage;
import java.lang.Math.*;

public class Tower extends Entities {
    // Attribut
    //private int pointDeVie;
    private int prix;
    private String type; // type de tour
    private int id; // identifiant pour savoir quelle tour specifiquement
    private int cd; // cool down
    
public Tower(/* int vie, */ int prix, Coordinates pos,int degat, int vitesseAtk, String type, int id, int cd){
    super(vitesseAtk, pos, degat);
    //this.pointDeVie=vie;
    this.prix=prix;
    this.type=type;
    this.id= id;
    this.cd=cd;
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

    public String getType () {
        return this.type;
    }
    public void setType (String nom) {
        this.type= nom;
    }

    public int getId () {
        return this.id;
    }
    public void setid(int id) {
        this.id= id;
    }
    
    public int getCoolDown () {
        return this.cd;
    }
    public void setCoolDown (int cd) {
        this.cd=cd;
    }

    // isDead
    /* public boolean isDead () {
        return this.pointDeVie<=0;
    } */
    
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
