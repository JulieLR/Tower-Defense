package model;

public enum Direction {
    NORTH,SOUTH,EAST,WEST;

    public Direction getOppositeDir(){
        return switch (this) {
            case NORTH -> Direction.SOUTH;
            case SOUTH -> Direction.NORTH;
            case EAST -> Direction.WEST;
            case WEST -> Direction.EAST;
        };
    }

    public String DirToString(){
        return switch(this){
            case EAST -> "EAST";
            case NORTH -> "NORTH";
            case SOUTH -> "SOUTH" ;
            case WEST -> "WEST";

        };
    }
}
