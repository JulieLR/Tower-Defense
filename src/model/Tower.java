package model;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.lang.Math.*;

import gui.Game;

public class Tower extends Entities {
    // Attribut
    public enum TowerColor {TOWER_BLUE, TOWER_ORANGE, TOWER_RED, TOWER_SMALL, TOWER_MEDIUM, TOWER_EXTRA};
    private TowerColor towerColor;
    private int type;
    private int prix;
    private Rectangle attackZone;
    private Enemy target;
    private Game game;
    
    public Tower(float vitesseAtk, Coordinates pos, int degat, int color, int prix, int width, int height, Game game){
        super(vitesseAtk, pos, degat);
        this.towerColor=this.colorTower(color);
        this.getType();
        this.prix=prix;
        this.game=game;
        Coordinates c = pos(pos,width,height,game.getTileSize());
        this.attackZone= new Rectangle((int) c.getX(), (int) c.getY(), width, height);
    }

    public Coordinates pos(Coordinates cor, int width, int height, int size){
        float x = cor.getX()-(width-size)/2;
        float y = cor.getY()-(height-size)/2;

        return new Coordinates((int)x, (int)y);
    }

    // Methodes

    // getter et setter
    public Enemy getTarget() {
        return target;
    }

    public void setTarget(Enemy target) {
        this.target = target;
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
            case 0: return new Tower(1, this.getPos(), 40, n, 75, 30, 30,this.game);
            case 1: return new Tower(3, this.getPos(), 65, n, 150, 40, 40,this.game);
            case 2: return new Tower(5, this.getPos(), 100, n, 200, 45, 45,this.game);
            case 3: return new Tower(2, this.getPos(), 10, n, 25, 5, 5,this.game);
            case 4: return new Tower(5, this.getPos(), 20, n, 50, 10, 10,this.game);
            case 5: return new Tower(15, this.getPos(), 60, n, 150, 20, 20,this.game);
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

    public int getPrix () {
        return this.prix;
    }
    public void setPrix (int prix) {
        this.prix=prix;
    }

    public Rectangle getAttackZone () {
        return this.attackZone;
    }
    public void setAttackZone (int width, int height) {
        this.attackZone= new Rectangle((int) (getPos().getX()-width/2), (int) (getPos().getY()-height/2), width, height);
    }
    
    public boolean isInZone(Enemy e){
        if(this.getAttackZone().contains(e.getZone())){
            return true;
        }
        return false;
    }

}
