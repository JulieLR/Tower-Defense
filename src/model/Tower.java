package model;
import java.awt.image.BufferedImage;
import java.lang.Math.*;

public class Tower extends Entities {
    // Attribut
    //private int pointDeVie;
    private int prix;
    private int type; // 0: physique, 1: magique
    //private int id; // identifiant pour savoir quelle tour specifiquement
    public enum TowerColor {TOWER_BLUE, TOWER_ORANGE, TOWER_RED, TOWER_SMALL, TOWER_MEDIUM, TOWER_EXTRA};
    private TowerColor towerColor;
    
    public Tower(int prix, Coordinates pos,int degat,float vitesseAtk, int type, TowerColor towerColor){
    super(vitesseAtk, pos, degat);
    this.prix=prix;
    this.type=type;
    this.towerColor=towerColor;
    }

    // Methodes

    // getter et setter
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

    public TowerColor getTowerColor () {
        return this.towerColor;
    }
    public void setTowerColor (TowerColor towerColor) {
        this.towerColor=towerColor;
    }

    public TowerColor towerBlue () {
        return TowerColor.TOWER_BLUE;
    }
    public TowerColor towerOrange () {
        return TowerColor.TOWER_ORANGE;
    }
    public TowerColor towerRed () {
        return TowerColor.TOWER_RED;
    }
    public TowerColor towerSmall () {
        return TowerColor.TOWER_SMALL;
    }
    public TowerColor towerMedium () {
        return TowerColor.TOWER_MEDIUM;
    }
    public TowerColor towerExtra () {
        return TowerColor.TOWER_EXTRA;
    }

    public int idTower () {
        switch (this.getTowerColor()) {
            case TOWER_BLUE: return 0;
            case TOWER_ORANGE: return 1;
            case TOWER_RED: return 2;
            case TOWER_SMALL: return 3;
            case TOWER_MEDIUM: return 4;
            case TOWER_EXTRA: return 5;
        }
        return -1;
    } 

    public TowerColor ColorTower (int n){
        switch (n) {
            case 0: return TowerColor.TOWER_BLUE;
            case 1: return TowerColor.TOWER_ORANGE;
            case 2: return TowerColor.TOWER_RED;
            case 3: return TowerColor.TOWER_SMALL;
            case 4: return TowerColor.TOWER_MEDIUM;
            case 5: return TowerColor.TOWER_EXTRA;
        }
        return null;
    }

}
