package config;


import java.util.Random;
import config.Tile.Type;
import java.io.BufferedReader;
import java.io.FileReader;

import gui.Game;
import model.Coordinates;
import java.util.ArrayList;


public class MapConfig {
    
    private Game game;
    private Tile[][] map;
    private Tile end;
    private Coordinates endCoor;
    private Coordinates start;
    private ArrayList<Coordinates> towersEmpty=new ArrayList<>();

    private String path1="src/config/map1T.txt";
    private String path1C="src/config/map1C.txt";
    private String path2="src/config/map2T.txt";
    private String path2C="src/config/map2C.txt";
    private String path3="src/config/map3T.txt";
    private String path3C="src/config/map3C.txt";
    private String path4="src/config/map4T.txt";
    private String path4C="src/config/map4C.txt";


    Random random = new Random();

    public MapConfig(Game game) {

        this.game=game;
        this.map= new Tile[this.game.getCol()][this.game.getLigne()];
        //addAsset();
        chosenMap();
        //System.out.print(this.getTile(new Coordinates(130, 130)).typeToString());
    } 

    public Tile getTile(Coordinates c){
        return this.map[((int)c.getX()/this.game.getTileSize())][(int)c.getY()/this.game.getTileSize()];    
    }

    public Tile[][] getMap(){
        return this.map;
    }

    public Tile getEnd(){
        return end;
    }

    public Coordinates getEndCoor() {
        return endCoor;
    }


    public Coordinates getStartCoor(){
        return this.start;
    }

    public Coordinates getStart() {
        return start;
    }

    public ArrayList<Coordinates> getTowersEmpty() {
        return towersEmpty;
    }

    public void chosenMap(){
        switch(this.game.getMapNumber()){
            case 1:mapFromFile(path1,path1C);
            break;
            case 2:mapFromFile(path2,path2C);
            break;
            case 3:mapFromFile(path3,path3C);
            break;
            case 4: mapFromFile(path4, path4C);
            break;
        }
    }
    
    public void mapFromFile(String path, String path2){
        try{
            int ligne = 0;
            int col = 0;int nbTower=0;
            BufferedReader reader_2 = new BufferedReader(new FileReader(path2));
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String readline;
            String readline2;
            while ((readline = reader.readLine()) != null && (readline2 = reader_2.readLine()) != null){
                System.out.println(ligne + " " + readline);
                for(int i =0; i< readline.length();i++){
                    int r = random.nextInt(10);
                    if(readline.charAt(i) == 'G'){//Grass
                        this.map[ligne][col] = new Tile(17,6+r, Type.GRASS, Character.getNumericValue(readline2.charAt(i)),new Coordinates(ligne, col));
                        ligne++;
                    }
                    else if(readline.charAt(i) == 'D'){//Path
                        this.map[ligne][col] = new Tile(1, Type.PATH, Character.getNumericValue(readline2.charAt(i)),new Coordinates(ligne, col));
                        ligne++;
                    }
                    else if(readline.charAt(i) == 'I'){//Intersection 4
                        this.map[ligne][col] = new Tile(5, Type.INTERSECTION4, Character.getNumericValue(readline2.charAt(i)),new Coordinates(ligne, col));
                        ligne++;
                    }
                    else if(readline.charAt(i) == 'i'){//Interssection 3
                        this.map[ligne][col] = new Tile(4, Type.INTERSECTION3, Character.getNumericValue(readline2.charAt(i)),new Coordinates(ligne, col));
                        ligne++;
                    }
                    else if(readline.charAt(i) == 'W'){//water
                        this.map[ligne][col] = new Tile(3, Type.WATER,Character.getNumericValue(readline2.charAt(i)),new Coordinates(ligne, col));
                        ligne++;
                    }
                    else if(readline.charAt(i) == 'w'){//water corner
                        this.map[ligne][col] = new Tile(2, Type.WATER,Character.getNumericValue(readline2.charAt(i)),new Coordinates(ligne, col));
                        ligne++;
                    }
                    else if(readline.charAt(i) == 'd'){//path corner
                        this.map[ligne][col] = new Tile(0, Type.PATH,Character.getNumericValue(readline2.charAt(i)),new Coordinates(ligne, col));
                        ligne++;
                    }
                    else if(readline.charAt(i) == 'C'){//castle
                        int x =Character.getNumericValue(readline2.charAt(i));//type of castle
                        this.map[ligne][col] = new Tile(24+x, Type.CASTLE,0,new Coordinates(ligne, col));
                        this.end=this.map[ligne][col];
                        this.endCoor = new Coordinates((ligne*this.game.getTileSize()), col*this.game.getTileSize());
                        ligne++;
                    }
                    else if(readline.charAt(i) == 'B'){//Bar border
                        this.map[ligne][col] = new Tile(22, Type.BAR,Character.getNumericValue(readline2.charAt(i)),new Coordinates(ligne, col));
                        ligne++;
                    }
                    else if(readline.charAt(i) == 'b'){//Bar corner
                        this.map[ligne][col] = new Tile(21, Type.BAR,Character.getNumericValue(readline2.charAt(i)),new Coordinates(ligne, col));
                        ligne++;
                    }
                    else if(readline.charAt(i) == 'X'){//Bar inside
                        this.map[ligne][col] = new Tile(23, Type.BAR,Character.getNumericValue(readline2.charAt(i)),new Coordinates(ligne, col));
                        ligne++;
                    }
                    else if(readline.charAt(i) == 'T'){//Tower place
                        this.map[ligne][col] = new Tile(17,18, Type.TOWER,Character.getNumericValue(readline2.charAt(i)),new Coordinates(ligne*this.game.getTileSize(), col*this.game.getTileSize()));
                        this.towersEmpty.add(new Coordinates(ligne*this.game.getTileSize(), col*this.game.getTileSize()));
                        ligne++;
                    }
                    else if(readline.charAt(i) == 'S'){//ennemies start
                        this.map[ligne][col] = new Tile(22, Type.PATH,Character.getNumericValue(readline2.charAt(i)),new Coordinates(ligne, col));
                        this.start= new Coordinates(ligne*this.game.getTileSize(), col*this.game.getTileSize());
                        ligne++;
                    }
                }
                col++;
                ligne=0;
            }
            reader.close();
            reader_2.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
