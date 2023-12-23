package perso;
import java.lang.Math.*;

public class Personnages {
    // Attribut
    private int pointDeVie;
    private int vitesseAtk;
    private RealCoordinates position;
    private int prix;
    private int degat;
    

    // Methodes

    // getter et setter
    public int getPointDeVie () {
        return this.pointDeVie;
    }
    public void setPointDeVie (int pointDeVie) {
        this.pointDeVie=pointDeVie;
    }

    public int getVitesseAtk () {
        return this.vitesseAtk;
    }
    public void setVitesseAtk (int vitesseAtk) {
        this.vitesseAtk=vitesseAtk;
    }

    public RealCoordinates getPosition () {
        return this.position;
    }
    public void setPosition (RealCoordinates position) {
        this.position=position;
    }

    public int getPrix () {
        return this.prix;
    }
    public void setPrix (int prix) {
        this.prix=prix;
    }

    public int getDegat () {
        return this.degat;
    }
    public void setDegat (int degat) {
        this.degat=degat;
    }

    // isDead
    public boolean isDead () {
        return this.pointDeVie<=0;
    }
    
    // attaque
    public void attaque (Personnages p) {
        p.pointDeVie=-this.degat;
    }

    // distance entre les deux personnes 
    public double disBetween (Personnages p) {
        return Math.sqrt(
        Math.pow(p.position.x()-this.position.x(),2) +
        Math.pow(p.position.y()-this.position.y(),2));
    }


}
