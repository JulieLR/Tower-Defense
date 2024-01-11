package model;

public enum Direction {
    NORTH,SOUTH,EAST,WEST;

    public Direction getOppositeDir(){
        switch (this) {
            case NORTH : return Direction.SOUTH;
            case SOUTH : return Direction.NORTH;
            case EAST : return Direction.WEST;
            case WEST : return Direction.EAST;
        };
        return null;
    }

    public String DirToString(){
        switch(this){
            case EAST : return"EAST";
            case NORTH : return"NORTH";
            case SOUTH : return"SOUTH" ;
            case WEST : return"WEST";

        };
        return null;
    }
}
