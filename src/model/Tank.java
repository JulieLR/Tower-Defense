package model;

import gui.Game;

public class Tank extends Enemy{

    public Tank(Coordinates pos, Game game) {
        super(500, pos, 1, 0.7f, 15, 5,game);
    }
    
}
