package config;

import java.util.ArrayList;

import gui.Game;
import gui.PowersGraphics;
import model.Enemy;
import model.Power;
import model.Power.Element;

public class PowersConfig {
    

    private Game game;
    private ArrayList<Enemy> enemies;
    private PowersGraphics powersGraphics;
    private EnemiesConfig enemiesConfig;

    private Power power;
    private Power lastPower;

    private int count=0;

    public PowersConfig(Game game){
        this.game=game;
        powersGraphics = new PowersGraphics(this, game);
        this.enemiesConfig=this.game.getEnemyConfig();
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

    public void update(){
        this.enemies=this.enemiesConfig.getEnemies();
        if(power!=null){
            switch(power.getType()){
                case FIRE: fireOrThunderAction(enemies, false);
                    break;
                case ICE: IceAction(enemies);
                    break;
                case THUNDER: fireOrThunderAction(enemies, true);
                    break;
                default:
                    break;
            }
            this.lastPower=power;
            power=null;
        }
        if(lastPower!=null && lastPower.getType()==Element.ICE){
            if(powersGraphics.getCount()>=180){
                for(Enemy e : enemies){
                    e.setSpeed(e.getInitialSpeed());
                }
                System.out.println("REINITIALISE");
            }
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
            e.setSpeed(0);
        }
    }
}
