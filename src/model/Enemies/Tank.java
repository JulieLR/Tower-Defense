package model.Enemies;

import gui.Game;
import model.Coordinates;

public class Tank extends Enemy{

    public Tank(Coordinates pos, Game game) {
        super(1000, pos, 1, 0.4f, 1, 25,game);
    }

}
