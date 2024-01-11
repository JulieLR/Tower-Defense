package config;

import java.util.ArrayList;

import java.util.Random;
import config.Tile.Type;
import gui.Game;
import model.Base;
import model.Coordinates;
import model.Direction;
import model.Enemies.Bat;
import model.Enemies.Enemy;
import model.Enemies.Knight;
import model.Enemies.Slime;
import model.Enemies.Tank;

public class EnemiesConfig {

    private Game game;
    private Base base;
    private MapConfig mapConfig;
    private ArrayList<Enemy> enemies= new ArrayList<>();

    private int nbEnemies;
    private int nbSpawned;
    private int nbEnemiesDead=0;
    private Coordinates start;
    private int mode;
    private boolean isMarathon;
    private float coeff=1;
    public EnemiesConfig(Game game,int n, int mode, MapConfig mapConfig){

        this.game=game;
        this.base = this.game.getBase();
        this.nbEnemies=n;
        this.nbSpawned=0;
        this.mode=mode;
        this.mapConfig=mapConfig;
        this.start = this.mapConfig.getStartCoor();

        startLevel(this.mode,1);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public int getNbEnemies() {
        return nbEnemies;
    }

    public int getNbSpawned() {
        return nbSpawned;
    }

    public int getNbEnemiesDead() {
        return nbEnemiesDead;
    }

    public void setNbEnemiesDead(int nbEnemiesDead) {
        this.nbEnemiesDead = nbEnemiesDead;
    }


    public void startLevel(int mode, int level){
        int nbEnemies=0;
        switch(mode){
            case 1 : 
                if(level==1){
                    setCoefficient(1);
                }
                else{
                    setCoefficient(1.1f);
                }
                nbEnemies=10;
                break;
            case 2 : 
                if(level==1){
                    setCoefficient(1.3f);
                }
                else{
                    setCoefficient(1.4f);
                }
                nbEnemies=20;
                break;
            case 3 : 
                if(level==1){
                    setCoefficient(2f);
                }
                else{
                    setCoefficient(2.1f);
                }
                nbEnemies=30;
                break;
            case 4 : nbEnemies=10;this.isMarathon=true;
        }
        addNewEnemies(nbEnemies);
        this.nbEnemies=nbEnemies;
    }

    public void setCoefficient(float coeff){
        this.coeff=coeff;
    }

    public void reinitialiseSpeed(){
        for(Enemy e : enemies){
            e.setSpeed(e.getInitialSpeed());
        }
    }


    public Direction startDirection(){
        if(this.game.getMapNumber()==2) {
            return Direction.SOUTH;
        }
        else{
            return Direction.NORTH;
        }
    }

    /*On change les valeurs de spawn et alive des ennemies par true */
    public void spawn() {
        if(nbSpawned<nbEnemies){
            enemies.get(nbSpawned).setDir(startDirection());
            enemies.get(nbSpawned).setSpawned(true);
            enemies.get(nbSpawned).setAlived(true);
            enemies.get(nbSpawned).setNumber(nbSpawned);
            changeStats(enemies.get(nbSpawned));
            nbSpawned++;
        }

    }

    public void changeStats(Enemy e){
        e.setPointDeVie((int)(e.getPointDeVie()*coeff));
        e.setSpeed(e.getSpeed()*coeff);
        e.setDegat((int)(e.getDegat()*coeff));
    }

    public void addNewEnemies(int number){
        Random r = new Random();
        for(int i=0;i<number;i++){
            int x = r.nextInt(number);
            int y = r.nextInt(2);
            if(x<number*0.3f){
                this.enemies.add(new Bat( start,this.game));
            }
            else if(x<number*0.6f){
                this.enemies.add(new Tank(start, this.game));
            }
            else if(x<number*0.9f){
                this.enemies.add(new Slime(start,this.game)); 
            }
            else{
                if(y==0){
                    this.enemies.add(new Bat(start,this.game)); 
                }
                else{
                    this.enemies.add(new Knight(1,start,this.game)); 
                }
            }
            nbEnemies++;
        }
    }

    /* Pour chaque enemies, si il est déjà spawn alors on update ses mouvements */
    public void update(){
        for( Enemy e : enemies){
            if(e.isSpawned() && e.isAlived()){
                updateMove(e);
            }
        }
        if(nbSpawned==nbEnemies && isMarathon){
            addNewEnemies(10);
            setCoefficient(coeff*1.1f);
        }
    }

    public Coordinates getNextCoor(Enemy e){
        if(e.getDir()== Direction.NORTH || e.getDir()== Direction.SOUTH){
            return new Coordinates((int) (e.getPos().getX()), (int) (e.getPos().getY() + getAddSpeed(e.getDir(),e)));
        }
        else{
            return new Coordinates((int) (e.getPos().getX() + getAddSpeed(e.getDir(),e)), (int) (e.getPos().getY()));
        }
    }

    //update la position de l'enemie
    /**
     * @param e
     */
    private void updateMove(Enemy e) {
        //On initialise deux
        Coordinates next = getNextCoor(e);

        /*Si la prochaine coordonée est un chemin alors on avance
         * Sinon on vérifie si le personnage est arrivé au chateau, sinon on change sa direction
        */
        if(getTileType((int)next.getX(),(int)next.getY())==Type.PATH|| getTileType((int)next.getX(),(int)next.getY())==Type.INTERSECTION3 || getTileType((int)next.getX(),(int)next.getY())==Type.INTERSECTION4){
            e.move(e.getDir());
        }
        else if(isAtEnd(next)){
                e.setAtEnd(true);
                if(getTile((int)e.getPos().getX(),(int) e.getPos().getY()-1).getType()== Type.CASTLE){
                    attaque(base, e); 
                }       
        }
        else{
            setNextDir(e);
        }
    }

    public boolean isAtEnd(Coordinates c) {
        return (getTileType((int)c.getX(), (int)c.getY())== Type.CASTLE);
    }

    public Tile getTile(int x, int y) {
        return this.game.getTile(x,y);
    }

    public boolean isPath(Coordinates c){
        return(getTileType((int)c.getX(),(int)c.getY())==Type.PATH || getTileType((int)c.getX(),(int)c.getY())==Type.CASTLE);
    }

    //Change la direction de l'enemie
    public void setNextDir(Enemy e) {
        Direction dir = e.getDir();
        Direction nextDir=e.getDir();

        int x = (int) e.getPos().getX();
        int y = (int) e.getPos().getY();
        
        //si la direction etait EAST ou SOUTH on centre le perso car il atteind jamais le centre dans ces cas
        setAtCenterTile(e,dir,x,y);

        //Si on allait verticalement alors la prochaine direction sera horizontale
        if(dir == Direction.NORTH || dir == Direction.SOUTH){
            int newX = (int) (e.getPos().getX() + getAddSpeed(Direction.EAST,e));
            if(isPath(new Coordinates(newX, (int)e.getPos().getY()))){
                nextDir=Direction.EAST;
            }
            else{
                nextDir=Direction.WEST;
            }
        }
        else{
            int newY = (int) (e.getPos().getY() + getAddSpeed(Direction.NORTH,e));
            if(isPath(new Coordinates((int)e.getPos().getX(),newY))){
                nextDir=Direction.NORTH;
            }
            else{
                nextDir=Direction.SOUTH;
            }
        }
        e.move(nextDir);
    }

    public void setAtCenterTile(Enemy e, Direction dir, int x, int y) {
        if(dir == Direction.EAST){
            x++;
        }
        else{
            if(dir == Direction.SOUTH){
                y++;
            }
        }
        e.setPos(x,y);
    }

    public Type getTileType(int x, int y) {
        return game.getTileType(x,y);
    }

    public float getAddSpeed(Direction dir, Enemy e){
        if(dir == Direction.NORTH|| dir ==Direction.WEST){
            return -e.getSpeed();
        }
        else{
            return e.getSpeed()+this.game.getTileSize();
        }
    }

    public void attaque(Base base, Enemy e){
        if(base.getPointDeVie()-e.getDegat()<0){
            base.setPointDeVie(0);
        }
        else{
            base.setPointDeVie(base.getPointDeVie()-e.getDegat());
        }
    }

    public void depasse () {
        boolean isChange= false;
        for (Enemy e0: this.enemies) {
            for (Enemy e1: this.enemies) {
                if (e0!=e1 && e0.isAlived() && e1.isAlived() && !e0.isAtEnd() && !e1.isAtEnd()) {
                    double precision = 0.00000000001f; 
                    if (Math.abs(e0.getPos().getX()-e1.getPos().getX())<precision && Math.abs(e0.getPos().getY()-e1.getPos().getY())<precision && !isChange) {
                        System.out.print(e0.getNumber()+ "\t"+ e1.getNumber()+ "\t");
                        int tmp= e0.getNumber();
                        e0.setNumber(e1.getNumber());
                        e1.setNumber(tmp);
                        isChange=true;
                    }
                }
            }
        }
        isChange=false;
    }

}

