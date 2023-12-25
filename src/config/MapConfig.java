package config;


import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import config.Tile.Type;
import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.Color;

import gui.Frames;
import gui.Game;
import model.Coordinates;

public class MapConfig implements Frames {
    
    private Game game;
    private Tile[][] map;
    private Tile end;
    private Coordinates start;
    private ArrayList<BufferedImage> tiles = new ArrayList<>();
    Random random = new Random();

    public MapConfig(Game game) {

        this.game=game;
        this.map= new Tile[this.game.getCol()][this.game.getLigne()];
        addAsset();
        mapFromFile("src/config/mapT.txt","src/config/map.txt");
        //System.out.print(this.getTile(new Coordinates(130, 130)).typeToString());
    } 

    public Tile getTile(Coordinates c){
        return this.map[((int)c.getX()/this.game.getTileSize())][(int)c.getY()/this.game.getTileSize()];    
    }

    public Tile[][] getMap(){
        return this.map;
    }

    public Tile getEnd(){
        return this.end;
    }

    public Coordinates getStartCoor(){
        return this.start;
    }

    
    public void mapFromFile(String path, String path2){
        try{
            int ligne = 0;
            int col = 0;
            BufferedReader reader_2 = new BufferedReader(new FileReader(path2));
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String readline;
            String readline2;
            while ((readline = reader.readLine()) != null && (readline2 = reader_2.readLine()) != null){
                System.out.println(ligne + " " + readline);
                for(int i =0; i< readline.length();i++){
                    int r = random.nextInt(10);
                    if(readline.charAt(i) == 'G'){//Grass
                        this.map[ligne][col] = new Tile(tiles.get(17),tiles.get(6+r), Type.GRASS, Character.getNumericValue(readline2.charAt(i)));
                        ligne++;
                    }
                    else if(readline.charAt(i) == 'D'){//Path
                        this.map[ligne][col] = new Tile(tiles.get(1), Type.PATH, Character.getNumericValue(readline2.charAt(i)),new Coordinates(ligne, col));
                        ligne++;
                    }
                    else if(readline.charAt(i) == 'W'){//water
                        this.map[ligne][col] = new Tile(tiles.get(3), Type.WATER,Character.getNumericValue(readline2.charAt(i)),new Coordinates(ligne, col));
                        ligne++;
                    }
                    else if(readline.charAt(i) == 'w'){//water corner
                        this.map[ligne][col] = new Tile(tiles.get(2), Type.WATER,Character.getNumericValue(readline2.charAt(i)),new Coordinates(ligne, col));
                        ligne++;
                    }
                    else if(readline.charAt(i) == 'd'){//path corner
                        this.map[ligne][col] = new Tile(tiles.get(0), Type.PATH,Character.getNumericValue(readline2.charAt(i)),new Coordinates(ligne, col));
                        ligne++;
                    }
                    else if(readline.charAt(i) == 'C'){//castle
                        int x =Character.getNumericValue(readline2.charAt(i));//type of castle
                        this.map[ligne][col] = new Tile(tiles.get(21+x), Type.CASTLE,0,new Coordinates(ligne, col));
                        ligne++;
                        this.end=this.map[ligne-1][col];
                    }
                    else if(readline.charAt(i) == 'B'){//Bar border
                        this.map[ligne][col] = new Tile(tiles.get(28), Type.BAR,Character.getNumericValue(readline2.charAt(i)),new Coordinates(ligne, col));
                        ligne++;
                    }
                    else if(readline.charAt(i) == 'b'){//Bar corner
                        this.map[ligne][col] = new Tile(tiles.get(27), Type.BAR,Character.getNumericValue(readline2.charAt(i)),new Coordinates(ligne, col));
                        ligne++;
                    }
                    else if(readline.charAt(i) == 'X'){//Bar inside
                        this.map[ligne][col] = new Tile(tiles.get(29), Type.BAR,Character.getNumericValue(readline2.charAt(i)),new Coordinates(ligne, col));
                        ligne++;
                    }
                    else if(readline.charAt(i) == 'T'){//Tower place
                        this.map[ligne][col] = new Tile(tiles.get(17),tiles.get(18), Type.TOWER,Character.getNumericValue(readline2.charAt(i)));
                        ligne++;
                    }
                    else if(readline.charAt(i) == 'S'){//ennemies start
                        this.map[ligne][col] = new Tile(tiles.get(28), Type.PATH,Character.getNumericValue(readline2.charAt(i)),new Coordinates(ligne, col));
                        this.start= new Coordinates(ligne*this.game.getTileSize(), col*this.game.getTileSize());
                        ligne++;
                    }
                }
                col++;
                ligne=0;
            }
            reader.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void addAsset() {
        //0: herbe, 1: terre, 2:eau
        for(int ligne=0;ligne<5;ligne++){
            for(int col=0;col<6;col++){
                tiles.add(this.game.getMapImage().getSubimage(col*this.game.getInitialTileSize(), ligne*this.game.getInitialTileSize(), this.game.getInitialTileSize(), this.game.getInitialTileSize()));
            }
        }
    }
    @Override
    public void drawImages(Graphics g) {
        for(int x=0;x<this.map.length;x++){
            for(int y=0;y<this.map[0].length;y++){
                /*si ce n'est pas un chateau alors on change la rotation de l'image avec la valeur du tile
                 * sinon la valeur sert à afficher la bonne image de chateau*/
                if(this.map[x][y].getType()!= Type.CASTLE){
                    g.drawImage(rotate(map[x][y].getImage(),90*map[x][y].getValue()),x*game.getTileSize(), y*game.getTileSize(), game.getTileSize(), game.getTileSize(), null);
                }
                else{
                    g.drawImage(map[x][y].getImage(),x*game.getTileSize(), y*game.getTileSize(), game.getTileSize(), game.getTileSize(), null);
   
                }
                //Si c'est de l'herbe on rajoute de la décoration par dessus
                if(this.map[x][y].getType()== Type.GRASS ||this.map[x][y].getType()== Type.TOWER){
                    g.drawImage(map[x][y].getSecondImg(), x*game.getTileSize(), y*game.getTileSize(), game.getTileSize(), game.getTileSize(), game);
                }
            }
        }
    }

    public void drawStartTile(Graphics g){
        g.drawImage(tiles.get(28), (int)start.getX(),(int) start.getY(), game.getTileSize(), game.getTileSize(), game);

    }

    public static BufferedImage rotate(BufferedImage img, double angle) {

        int w = img.getWidth();    
        int h = img.getHeight();
    
        BufferedImage rotated = new BufferedImage(w, h, img.getType());  
        Graphics2D graphic = rotated.createGraphics();
        graphic.rotate(Math.toRadians(angle), w/2, h/2);
        graphic.drawImage(img, null, 0, 0);
        graphic.dispose();
        return rotated;
    }

    public int getRandomNb(){
        return random.nextInt(3);
    }

}
