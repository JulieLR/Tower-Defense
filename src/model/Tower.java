package model;
import java.awt.image.BufferedImage;
import java.lang.Math.*;

public class Tower extends Entities {
    // Attribut
    private int prix;
    private int type;
    public enum TowerColor {TOWER_BLUE, TOWER_ORANGE, TOWER_RED, TOWER_SMALL, TOWER_MEDIUM, TOWER_EXTRA};
    private TowerColor towerColor;
    
    public Tower(int prix, Coordinates pos,int degat,float vitesseAtk, int n){
        super(vitesseAtk, pos, degat);
        this.prix=prix;
        this.towerColor=this.colorTower(n);
        this.towerType();
        
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
    public void towerType () {
        this.type=-1;
        if (0>=idColorTower() && idColorTower()>=2) {
            this.type=1;
        }
        else if (3>=idColorTower() && idColorTower()>=5) {
            this.type=0;
        }
    }
    // si la tour fait des degats magiques
    public boolean isMagic () {
        return this.getTowerColor()==TowerColor.TOWER_BLUE || this.getTowerColor()==TowerColor.TOWER_ORANGE || this.getTowerColor()==TowerColor.TOWER_RED;
    }

    // si la tour fait des degats physiques
    public boolean isPhysic () {
        return this.getTowerColor()==TowerColor.TOWER_SMALL || this.getTowerColor()==TowerColor.TOWER_MEDIUM || this.getTowerColor()==TowerColor.TOWER_EXTRA;
    }

    public TowerColor getTowerColor () {
        return this.towerColor;
    }
    public void setTowerColor (TowerColor towerColor) {
        this.towerColor=towerColor;
    }

    public TowerColor towerColorBlue () {
        return TowerColor.TOWER_BLUE;
    }
    public TowerColor towerColorOrange () {
        return TowerColor.TOWER_ORANGE;
    }
    public TowerColor towerColorRed () {
        return TowerColor.TOWER_RED;
    }
    public TowerColor towerColorSmall () {
        return TowerColor.TOWER_SMALL;
    }
    public TowerColor towerColorMedium () {
        return TowerColor.TOWER_MEDIUM;
    }
    public TowerColor towerColorExtra () {
        return TowerColor.TOWER_EXTRA;
    }

    public int idColorTower () {
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

    public TowerColor colorTower (int n){
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

    public Tower towerEnum (int n){
        switch (n) {
            case 0: return new Tower(75, this.getPos(), 40, 1, 0);
            case 1: return new Tower(150, this.getPos(), 65, 3, 1);
            case 2: return new Tower(200, this.getPos(), 100, 5, 2);
            case 3: return new Tower(25, this.getPos(), 10, 2, 3);
            case 4: return new Tower(50, this.getPos(), 20, 5, 4);
            case 5: return new Tower(150, this.getPos(), 60, 15, 5);
        }
        return null;
    }

}
