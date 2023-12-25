package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import config.Tile;
import config.Tile.Type;
import model.Enemy;
import model.Coordinates;
import model.Direction;

public class EnemiesGraphics implements Frames{
    
    private Game game;
    private ArrayList<BufferedImage> enemiesAsset = new ArrayList<>();
    private Enemy enemy;

    public EnemiesGraphics(Game game){
        this.game = game;
        addAsset();
        this.enemy= new Enemy(200,2f,enemiesAsset.get(1), new Coordinates(2*this.game.getTileSize(),9*this.game.getTileSize()),20, 20,0);

    }

    @Override
    public void drawImages(Graphics g){
        long time = System.currentTimeMillis();
        //time%250<100 et +-1, % pour la vitesse et < l'intervalle de changement d'image
        if(time%400<200){
            g.drawImage(this.enemiesAsset.get(2),(int) this.enemy.getPos().getX(),(int)this.enemy.getPos().getY()-(this.game.getTileSize()/this.game.getScale()),this.game.getTileSize(),this.game.getTileSize(), null);
        }
        else{
            g.drawImage(this.enemiesAsset.get(3),(int) this.enemy.getPos().getX(),(int)this.enemy.getPos().getY()-(this.game.getTileSize()/this.game.getScale()),this.game.getTileSize(),this.game.getTileSize(), null);
        }   
        //g.drawImage(this.enemiesAsset.get(8),128,128,this.game.getTileSize(),this.game.getTileSize(), null);
 
    }  

    @Override
    public void addAsset(){
        for(int ligne=0;ligne<6;ligne++){
            for(int col=0;col<4;col++){
                enemiesAsset.add(this.game.getEnemyImage().getSubimage(col*this.game.getInitialTileSize(), ligne*this.game.getInitialTileSize(), this.game.getInitialTileSize(), this.game.getInitialTileSize()));
            }
        }
    }

    public void update(){
        updateMove(this.enemy);
    }

    private void updateMove(Enemy e) {
        int newX = (int) (e.getPos().getX() + getSpeedAndWidth(e.getDir()));
        int newY = (int) (e.getPos().getY() + getSpeedAndHeight(e.getDir()));

        if(getTileType(newX, newY) == Type.PATH){
            e.move(e.getDir());
        }
        else if(atEnd(e)){
            System.out.print("END");
        }
        else{
            setNewDirectionAndMove(e);
        }
    }

    private boolean atEnd(Enemy e) {
        return (getTile((int)e.getPos().getX(),(int)e.getPos().getY())== this.game.getMapConfig().getEnd());
    }

    private Tile getTile(int x, int y) {
        return this.game.getTile(x,y);
    }

    public boolean isPath(int x, int y){
        return (getTileType(x,y)== Type.PATH);
    }

    private void setNewDirectionAndMove(Enemy e) {
        Direction dir = e.getDir();

        int xCord = (int) e.getPos().getX()/64;
        int yCord = (int) e.getPos().getY()/64;

        fixEnemyOffsetTile(e,dir,xCord,yCord);

        if(dir == Direction.WEST || dir == Direction.EAST){
            int newY = (int) (e.getPos().getY() + getSpeedAndHeight(Direction.NORTH));
            if(isPath((int)e.getPos().getX(),newY)){
                e.move(Direction.NORTH);
            }
            else{
                e.move(Direction.SOUTH);
            }
        }
        else{
            int newX = (int) (e.getPos().getX() + getSpeedAndWidth(Direction.EAST));
            if(isPath(newX, (int)e.getPos().getY())){
                e.move(Direction.EAST);
            }
            else{
                e.move(Direction.WEST);
            }
        }
    }

    private void fixEnemyOffsetTile(Enemy e, Direction dir, int xCord, int yCord) {

        switch(dir){
            case EAST: 
                if(xCord <14)
                    xCord++;
                break;
            case SOUTH:
                if(yCord <14)
                    yCord++;
                break;

        }
        e.setPos(xCord*64,yCord*64);
    }

    private Type getTileType(int x, int y) {
        return game.getTileType(x,y);
    }

    private float getSpeed(Direction dir) {
        float speed = enemy.getSpeed();
        if (dir == Direction.NORTH) {
            return -speed;
        } else if (dir == Direction.SOUTH) {
            return speed;
        } else if (dir == Direction.WEST) {
            return -speed;
        } else if (dir == Direction.EAST) {
            return speed;
        }
        return 0;
    }
    

    private float getSpeedAndHeight(Direction dir) {
        if(dir == Direction.NORTH){
            return -enemy.getSpeed();
        }
        else if(dir== Direction.SOUTH){
            return enemy.getSpeed()+64;
        }
        return 0;
    }

    private float getSpeedAndWidth(Direction dir) {
        if(dir == Direction.WEST){
            return -enemy.getSpeed();
        }
        else if(dir == Direction.EAST){
            return enemy.getSpeed()+64;
        }
        return 0;
    }

}
