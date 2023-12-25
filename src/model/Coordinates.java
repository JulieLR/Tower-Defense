package model;
public class Coordinates {

    public static final Coordinates ZERO = new Coordinates(0, 0);
    public static final Coordinates NORTH_UNIT = new Coordinates(0, -1);
    public static final Coordinates EAST_UNIT = new Coordinates(1, 0);
    public static final Coordinates SOUTH_UNIT = new Coordinates(0, 1);
    public static final Coordinates WEST_UNIT = new Coordinates(-1, 0);
    private float x,y;

    public Coordinates(float x, float y){
        this.x=x;
        this.y=y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Coordinates plus(Coordinates other) {
        return new Coordinates(x + other.x, y + other.y);
    }

    public static Coordinates nextCoordinates(Coordinates pos, Direction d,float i){
        Coordinates newC= pos;
        switch(d){
            case NORTH : newC =new Coordinates(pos.getX(), pos.getY()-i);
                break;
            case SOUTH : newC =new Coordinates(pos.getX(), pos.getY()+i);
                break;
            case EAST : newC =new Coordinates(pos.getX()+i, pos.getY());
                break;
            case WEST : newC =new Coordinates(pos.getX()-i, pos.getY());
                break;
        }
        return newC;
    }

    public Coordinates neighbour(Direction d){
        return switch (d) {
            case NORTH -> new Coordinates((int) Math.round(x), (int) Math.ceil(y)-1); // Cellule en haut
            case WEST -> new Coordinates((int) Math.ceil(x)-1, (int) Math.round(y)); // Cellule à gauche
            case EAST -> new Coordinates((int) Math.floor(x)+1, (int) Math.round(y)); // Cellule à droite
            case SOUTH -> new Coordinates((int) Math.round(x), (int) Math.floor(y)+1); // Cellule en bas
            default -> new Coordinates((int) Math.round(x), (int) Math.round(y)); // Cas jamais atteint
        };
    }

    public Coordinates floorX() {
        return new Coordinates((int) Math.floor(x), y);
    }

    public Coordinates floorY() {
        return new Coordinates(x, (int) Math.floor(y));
    }

    public Coordinates ceilX() {
        return new Coordinates((int) Math.ceil(x), y);
    }

    public Coordinates ceilY() {
        return new Coordinates(x, (int) Math.ceil(y));
    }

    public Coordinates roundRC() {
        return new Coordinates( Math.round(x),  Math.round(y));
    }

    public Coordinates warp(int width, int height) {
        var rx = x;
        var ry = y;
        while (Math.round(rx) < 0)
            rx += width;
        while (Math.round(ry) < 0)
            ry += height;
        while (Math.round(rx) >= width)
            rx -= width;
        while (Math.round(rx) >= height)
            ry -= height;
        return new Coordinates(rx, ry);
    }
}
