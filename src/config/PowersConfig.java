package config;

import java.util.ArrayList;

import gui.Game;
import gui.Graphics.PowersGraphics;
import model.Power;
import model.Tower;
import model.Enemies.*;
import model.Power.Element;

public class PowersConfig {
    

    private Game game;
    private ArrayList<Enemy> enemies;
    private ArrayList<Tower> towers;
    private PowersGraphics powersGraphics;
    private EnemiesConfig enemiesConfig;
    private TowerConfig towersConfig;

    private Power power;
    private Power lastPower;

    private boolean iceDone=false;


    public PowersConfig(Game game){
        this.game=game;
        powersGraphics = new PowersGraphics(this, game);
        this.enemiesConfig=this.game.getEnemyConfig();
        this.towersConfig=this.game.getTowerConfig();
    }

    public void setPower(Power p){
        this.power=p;
    }

    public Power getPower() {
        return power;
    }

    public PowersGraphics getPowersGraphics() {
        return powersGraphics;
    }

    public void setIceDone(boolean iceDone) {
        this.iceDone = iceDone;
    }

    public void update(){
        this.enemies=this.enemiesConfig.getEnemies();
        this.towers=this.towersConfig.getMouseTowers();
        if(power!=null){
            switch(power.getType()){
                case FIRE: fireOrThunderAction(enemies, false);
                    break;
                case ICE: IceAction(enemies);
                    break;
                case THUNDER: fireOrThunderAction(enemies, true);
                    break;
                case HEAL: healAction(towers);
                    break;
                default:
                    break;
            }
            this.lastPower=power;
            power=null;
        }
        if(iceDone){
            for(Enemy e : enemies){
                e.setSpeed(e.getInitialSpeed());
            }
            this.iceDone=false;
        }
    }

    private void healAction(ArrayList<Tower> towers) {
        for(Tower t : towers){
            t.setDegat(t.getDegat()+20);
        }

    }

    public void fireOrThunderAction(ArrayList<Enemy> enemies, boolean thunder){
        int degat =50;
        if(thunder){
            degat=100;
        }
        for(Enemy e : enemies){
            if(e.isSpawned() && e.isAlived()){
                if(e.getPointDeVie()-degat<=0){
                    e.setPointDeVie(0);
                    e.setAlived(false);
            }
                else{
                    e.setPointDeVie(e.getPointDeVie()-degat);
                }
                if(thunder){
                    if(e.getSpeed()>=0.3f){
                        e.setSpeed(e.getSpeed()-0.1f);
                    }
                }
            }
        }
    }

    //stop enemies pdt 2s puis reinitialise speed

    public void IceAction(ArrayList<Enemy> enemies){
        for(Enemy e: enemies){
            if(!(e instanceof Bat)){
                 e.setSpeed(0);
            }
        }
    }

    public int getNbUnlock(int mode){
        switch(mode){
            case 1: return 10;
            case 2: return 10;
            case 3: return 15;
            case 4: return 20;
        }
        return 0;
    }
}
