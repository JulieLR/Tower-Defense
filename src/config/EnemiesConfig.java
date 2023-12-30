package config;

import java.util.ArrayList;

import config.Tile.Type;
import gui.Game;
import model.Base;
import model.Bat;
import model.Coordinates;
import model.Direction;
import model.Enemy;
import model.Knight;
import model.Slime;

public class EnemiesConfig {

    private Game game;
    private Base base;
    private ArrayList<Enemy> enemies= new ArrayList<>();

    private int nbEnemies;
    private int nbSpawned;
    private Coordinates start;
    private Coordinates end;
    private Enemy e;

    public EnemiesConfig(Game game,int n){

        this.game=game;
        this.base = this.game.getBase();
        this.nbEnemies=n;
        this.nbSpawned=1;
        this.start = this.game.getMapConfig().getStartCoor();
        this.end = this.game.getMapConfig().getEndCoor();
        this.e= new Enemy(200,start,20, 4f,1,1,this.game);

        makeEnemies(nbEnemies);
        //enemies.get(0).setSpawned(true);
        //this.enemies.add(e);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    /*On change les valeurs de spawn et alive des ennemies par true */
    public void spawn() {
        if(nbSpawned<nbEnemies){
            enemies.get(nbSpawned).setSpawned(true);
            enemies.get(nbSpawned).setAlived(true);
            nbSpawned++;
        }

    }
    //Création d'enemies(ajout dans l'arraylist)
    private void makeEnemies(int n) {
        int a = (int)(n*0.5f);//50% d'ennemies faible
        int b = (int)(n*0.3f);//30% d'ennemies moyen
        int c = (int)(n*0.2f);//20% d'ennemies fort

        //ajout enemies faible
        for(int i=0;i<a;i++){
            this.enemies.add(new Knight(0, start,this.game));
        }
        //ajout enemies moyen
        for(int j=0;j<b;j++){
            this.enemies.add(new Slime(start,this.game));        }
        //ajout enemies fort
        for(int k=0;k<c;k++){
            this.enemies.add(new Bat(start,this.game));        }
    }

    public Enemy getE() {
        return e;
    }

    /* Pour chaque enemies, si il est déjà spawn alors on update ses mouvements */
    public void update(){
        for( Enemy e : enemies){
            if(e.isSpawned() && e.isAlived()){
                updateMove(e);
            }
        }
    }

    public Coordinates getNextCoor(Enemy e){
        return new Coordinates((int) (e.getPos().getX() + getHorizontalSpeed(e.getDir(),e)), (int) (e.getPos().getY() + getVerticalSpeed(e.getDir(),e)));
    }

    //update la position de l'enemie
    private void updateMove(Enemy e) {
        //On initialise deux
        Coordinates next = getNextCoor(e);

        /*Si la prochaine coordonée est un chemin alors on avance
         * Sinon on vérifie si le personnage est arrivé au chateau, sinon on change sa direction
        */
        if(isPath(next)){
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
        return(getTileType((int)c.getX(),(int)c.getY())==Type.PATH);
    }

    //Change la direction de l'enemie
    public void setNextDir(Enemy e) {
        Direction dir = e.getDir();
        Direction nextDir=e.getDir();

        int x = (int) e.getPos().getX()/this.game.getTileSize();
        int y = (int) e.getPos().getY()/this.game.getTileSize();
        
        //si la direction etait EAST ou SOUTH on centre le perso car il atteind jamais le centre dans ces cas
        setAtCenterTile(e,dir,x,y);

        //Si on allait verticalement alors la prochaine direction sera horizontale
        if(dir == Direction.NORTH || dir == Direction.SOUTH){
            int newX = (int) (e.getPos().getX() + getHorizontalSpeed(Direction.EAST,e));
            if(isPath(new Coordinates(newX, (int)e.getPos().getY()))){
                nextDir=Direction.EAST;
            }
            else{
                nextDir=Direction.WEST;
            }
        }
        else{
            int newY = (int) (e.getPos().getY() + getVerticalSpeed(Direction.NORTH,e));
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
        e.setPos(x*this.game.getTileSize(),y*this.game.getTileSize());
    }

    public Type getTileType(int x, int y) {
        return game.getTileType(x,y);
    }

    public float getVerticalSpeed(Direction dir,Enemy e) {
        if(dir == Direction.NORTH){
            return -e.getSpeed();
        }
        else if(dir== Direction.SOUTH){
            return e.getSpeed()+this.game.getTileSize();
        }
        return 0;
    }

    public float getHorizontalSpeed(Direction dir,Enemy e) {
        if(dir == Direction.WEST){
            return -e.getSpeed();
        }
        else if(dir == Direction.EAST){
            return e.getSpeed()+this.game.getTileSize();
        }
        return 0;
    }

    public void attaque(Base base, Enemy e){
        if(base.getPointDeVie()-e.getDegat()<0){
            base.setPointDeVie(0);
        }
        else{
            base.setPointDeVie(base.getPointDeVie()-e.getDegat());
        }
        System.out.println(base.getPointDeVie());
    }

}

