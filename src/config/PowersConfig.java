package config;

import java.util.ArrayList;

import gui.Game;
import gui.PowersGraphics;
import model.Enemy;
import model.Power;

public class PowersConfig {
    
    private boolean isFireClicked=false;
    private boolean isIceClicked=false;
    private boolean isThunderClicked=false;

    private Game game;
    private ArrayList<Enemy> enemies;
    private PowersGraphics powersGraphics;

    public PowersConfig(Game game){
        this.game=game;
        powersGraphics = new PowersGraphics(this, game);
    }

    public void setPower(Power p){
        this.powersGraphics.setActualPower(p);
    }
}
