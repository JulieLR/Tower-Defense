package model;

import gui.Game;
import java.util.Random;

public class Slime extends Enemy {

    private int Color;

    public Slime(Coordinates pos, Game game) {
        super(450, pos, 3, 0.6f, 0, 15, game);
        Random r = new Random();
        this.Color = r.nextInt(7);
    }
    
    public int getColor() {
        return Color;
    }

}
