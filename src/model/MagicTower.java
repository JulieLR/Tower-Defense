package model;

import gui.Game;

public class MagicTower extends Tower {
    
    public MagicTower(float attackSpeed, Coordinates position, int degat, int color, int price, int width, int height, Game game){
        super(attackSpeed, position, degat, color, price, width, height, game);
    }

    public void setStats(int n){
        switch(n){
            case 1: setWeak();
                break;
            case 2: setNormal();
                break;
            case 3: setStrong();
                break;
        }
    }

    public void setWeak(){

    }

    public void setNormal(){

    }

    public void setStrong(){

    }

}
