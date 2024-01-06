package model;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.lang.Math.*;
import java.util.ArrayList;

import config.EnemiesConfig;
import gui.Game;

public class Tower extends Entities {
    // Attribut
    public enum TowerColor {TOWER_BLUE, TOWER_ORANGE, TOWER_RED, TOWER_SMALL, TOWER_MEDIUM, TOWER_EXTRA};
    private TowerColor towerColor;
    private int type;
    private int price;
    private Rectangle attackZone;
    private Game game;
    private Enemy target;
    private ArrayList<Enemy> enemyArray= new ArrayList<>();
    
    public Tower(float attackSpeed, Coordinates position, int degat, int color, int price, int width, int height, Game game){
        super(attackSpeed, position, degat);
        this.towerColor=this.colorTower(color);
        this.price=price;
        this.game=game;
        Coordinates c = pos(position,width,height,game.getTileSize());
        this.attackZone= new Rectangle((int) c.getX(), (int) c.getY(), width, height);
    }

    public Coordinates pos(Coordinates cor, int width, int height, int size){
        float x = cor.getX()-(width-size)/2;
        float y = cor.getY()-(height-size)/2;

        return new Coordinates((int)x, (int)y);
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
            case 0: return new Tower(0.5f, this.getPos(), 2, n, 75, this.game.getTileSize()*3, this.game.getTileSize()*3, this.game);
            case 1: return new Tower(0.5f, this.getPos(), 2, n, 150, this.game.getTileSize()*5, this.game.getTileSize()*5, this.game);
            case 2: return new Tower(0.5f, this.getPos(), 2, n, 200, this.game.getTileSize()*7, this.game.getTileSize()*7, this.game);
            case 3: return new Tower(0.5f, this.getPos(), 2, n, 25, this.game.getTileSize()*3, this.game.getTileSize()*3, this.game);
            case 4: return new Tower(0.5f, this.getPos(), 2, n, 50, this.game.getTileSize()*5, this.game.getTileSize()*3, this.game);
            case 5: return new Tower(0.5f, this.getPos(), 2, n, 150, this.game.getTileSize()*7, this.game.getTileSize()*5, this.game);
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

    public ArrayList<Enemy> getEnemyArray() {
        return this.enemyArray;
    }

    public Rectangle getAttackZone () {
        return this.attackZone;
    }

    public Enemy getTarget() {
        return target;
    }

     public void setTarget(Enemy e){
        this.target=e;
    }

    public void setAttackZone (int width, int height) {
        this.attackZone= new Rectangle((int) (getPos().getX()-(width-this.game.getTileSize())/2), (int) (getPos().getY()-(height-this.game.getTileSize())/2), width, height);
    }

    public boolean isInZone(Enemy e){
        if(this.getAttackZone().contains(e.getHitBox())){
            return true; 
        }
        return false;
    }

    public int getLevel(){
        switch (this.idColorTower()) {
            case 0: return 1;
            case 1: return 2;
            case 2: return 3;
            case 3: return 1;
            case 4: return 2;
            case 5: return 3;
        }
        return 0;
    }

    public int getNextPrice(int id){
        switch (this.getTowerColor()) {
            case TOWER_BLUE: return 150;
            case TOWER_ORANGE: return 200;
            case TOWER_SMALL: return 50;
            case TOWER_MEDIUM: return 150;
        }
        return -1;
    }

}
