package model;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.lang.Math.*;

public class Tower extends Entities {
    // Attribut
    public enum TowerColor {TOWER_BLUE, TOWER_ORANGE, TOWER_RED, TOWER_SMALL, TOWER_MEDIUM, TOWER_EXTRA};
    private TowerColor towerColor;
    private int type;
    private int price;
    private Rectangle attackZone;
    
    public Tower(float attackSpeed, Coordinates pos, int degat, int color, int price, int width, int height){
        super(attackSpeed, pos, degat);
        this.towerColor=this.colorTower(color);
        this.getType();
        this.price=price;
        this.attackZone= new Rectangle((int) (pos.getX()-width/2), (int) (pos.getX()-height/2), width, height);
    }


    // Methodes

    // getter et setter
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
            case 0: return new Tower(1, this.getPos(), 40, n, 75, 30, 30);
            case 1: return new Tower(3, this.getPos(), 65, n, 150, 40, 40);
            case 2: return new Tower(5, this.getPos(), 100, n, 200, 45, 45);
            case 3: return new Tower(2, this.getPos(), 10, n, 25, 5, 5);
            case 4: return new Tower(5, this.getPos(), 20, n, 50, 10, 10);
            case 5: return new Tower(15, this.getPos(), 60, n, 150, 20, 20);
        }
        return null;
    }

    public int getType () {
        return this.type;
    }
    public void setType () {
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

    public int getPrice () {
        return this.price;
    }
    public void setPrix (int price) {
        this.price=price;
    }

    public Rectangle getAttackZone () {
        return this.attackZone;
    }
    public void setAttackZone (int width, int height) {
        this.attackZone= new Rectangle((int) (getPos().getX()-width/2), (int) (getPos().getY()-height/2), width, height);
    }
    

}
