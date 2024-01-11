package model;

import gui.Game;

public class Tank extends Enemy{

    public Tank(Coordinates pos, Game game) {
        super(1000, pos, 1, 0.4f, 1, 15,game);
    }

}
