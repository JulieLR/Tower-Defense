package config;

import java.util.ArrayList;

import config.Tile.Type;
import gui.Game;
import model.Coordinates;
import model.Direction;
import model.Enemy;

public class EnemiesConfig {

    private Game game;
    private ArrayList<Enemy> enemies = new ArrayList<>();

    private int nbEnemies;
    private Coordinates start;
    private Enemy e;

    public EnemiesConfig(Game game,int n){

        this.game=game;
        this.nbEnemies=n;
        this.start = this.game.getMapConfig().getStartCoor();
        this.e= new Enemy(200,start,20, 3f,1);
        //this.enemies = makeEnemies(nbEnemies);
        this.enemies.add(e);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    private ArrayList<Enemy> makeEnemies(int n) {
        ArrayList<Enemy> enemies = new ArrayList<>();
        int a = (int)(n*0.5f);
        int b = (int)(n*0.3f);
        int c = (int)(n*0.2f);

        for(int i=0;i<a;i++){
            enemies.add(new Enemy(200, start, 20, 0.5f, 0));
        }
        for(int i=0;i<b;i++){
            enemies.add(new Enemy(200, start, 20, 0.5f, 1));
        }
        for(int i=0;i<c;i++){
            enemies.add(new Enemy(200, start, 20, 0.5f, 2));
        }
        return enemies;
    }

    public Enemy getE() {
        return e;
    }

    public void update(){
        for( Enemy e : enemies){
            updateMove(e);
        }
    }

    private void updateMove(Enemy e) {
        int newX = (int) (e.getPos().getX() + getHorizontalSpeed(e.getDir(),e));
        int newY = (int) (e.getPos().getY() + getVerticalSpeed(e.getDir(),e));

        if(getTileType(newX, newY) == Type.PATH){
            e.move(e.getDir());
        }
        else if(atEnd(e)){
            System.out.print("END");
        }
        else{
            setNextDir(e);
        }
    }
    //a revoir
    private boolean atEnd(Enemy e) {
        return (getTile((int)e.getPos().getX(),(int)e.getPos().getY())== this.game.getMapConfig().getEnd());
    }

    private Tile getTile(int x, int y) {
        return this.game.getTile(x,y);
    }

    public boolean isPath(int x, int y){
        return (getTileType(x,y)== Type.PATH);
    }

    private void setNextDir(Enemy e) {
        Direction dir = e.getDir();

        int xCord = (int) e.getPos().getX()/this.game.getTileSize();
        int yCord = (int) e.getPos().getY()/this.game.getTileSize();

        atCenterTile(e,dir,xCord,yCord);

        //Si on allait de horizontalement alors le prochain c'est vertical
        if(dir == Direction.WEST || dir == Direction.EAST){
            int newY = (int) (e.getPos().getY() + getVerticalSpeed(Direction.NORTH,e));
            if(isPath((int)e.getPos().getX(),newY)){
                e.move(Direction.NORTH);
            }
            else{
                e.move(Direction.SOUTH);
            }
        }
        else{
            int newX = (int) (e.getPos().getX() + getHorizontalSpeed(Direction.EAST,e));
            if(isPath(newX, (int)e.getPos().getY())){
                e.move(Direction.EAST);
            }
            else{
                e.move(Direction.WEST);
            }
        }
    }

    public float getSpeed(Direction d, Direction cur){
        return 0;
    }

    private void atCenterTile(Enemy e, Direction dir, int x, int y) {
        if(dir == Direction.EAST && x<14){
            x++;
        }
        else{
            if(dir == Direction.SOUTH && y<14){
                y++;
            }
        }
        e.setPos(x*this.game.getTileSize(),y*this.game.getTileSize());
    }

    private Type getTileType(int x, int y) {
        return game.getTileType(x,y);
    }

    private float getVerticalSpeed(Direction dir,Enemy e) {
        if(dir == Direction.NORTH){
            return -e.getSpeed();
        }
        else if(dir== Direction.SOUTH){
            return e.getSpeed()+this.game.getTileSize();
        }
        return 0;
    }

    private float getHorizontalSpeed(Direction dir,Enemy e) {
        if(dir == Direction.WEST){
            return -e.getSpeed();
        }
        else if(dir == Direction.EAST){
            return e.getSpeed()+this.game.getTileSize();
        }
        return 0;
    }

}

