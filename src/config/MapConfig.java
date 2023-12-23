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
import gui.Game;

public class MapConfig {
    
    private Game game;
    private Tile[][] map;
    private ArrayList<BufferedImage> tiles = new ArrayList<>();
    private ArrayList<BufferedImage> tilesDeco = new ArrayList<>();
    Random random = new Random();

    public MapConfig(Game game) {
        this.game=game;
        this.map= new Tile[this.game.getCol()][this.game.getLigne()];
        addAsset();
        mapFromFile("src/config/mapT.txt","src/config/map.txt");


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
                    if(readline.charAt(i) == 'G'){
                        this.map[ligne][col] = new Tile(tiles.get(17),tiles.get(6+r), Type.GRASS, Integer.valueOf(readline2.charAt(i)));
                        ligne++;
                    }
                    else if(readline.charAt(i) == 'D'){
                        this.map[ligne][col] = new Tile(tiles.get(1), Type.PATH, Integer.valueOf(readline2.charAt(i)));
                        ligne++;
                    }
                    else if(readline.charAt(i) == 'W'){
                        this.map[ligne][col] = new Tile(tiles.get(3), Type.WATER,Integer.valueOf(readline2.charAt(i)));
                        ligne++;
                    }
                    else if(readline.charAt(i) == 'w'){
                        this.map[ligne][col] = new Tile(tiles.get(2), Type.WATER,Integer.valueOf(readline2.charAt(i)));
                        ligne++;
                    }
                    else if(readline.charAt(i) == 'd'){
                        this.map[ligne][col] = new Tile(tiles.get(0), Type.WATER,Integer.valueOf(readline2.charAt(i)));
                        ligne++;
                    }
                    else if(readline.charAt(i) == 'T'){
                        this.map[ligne][col] = new Tile(tiles.get(4), Type.WATER,Integer.valueOf(readline2.charAt(i)));
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

    public void addAsset() {
        //0: herbe, 1: terre, 2:eau
        for(int ligne=0;ligne<3;ligne++){
            for(int col=0;col<6;col++){
                tiles.add(this.game.getImage().getSubimage(col*this.game.getInitialTileSize(), ligne*this.game.getInitialTileSize(), this.game.getInitialTileSize(), this.game.getInitialTileSize()));
            }
        }
    }

    public void drawBackground(Graphics g) {
        for(int x=0;x<this.map.length;x++){
            for(int y=0;y<this.map[0].length;y++){
                g.drawImage(rotate(map[x][y].getImage(),90*map[x][y].getValue()),x*game.getTileSize(), y*game.getTileSize(), game.getTileSize(), game.getTileSize(), null);
                if(this.map[x][y].getType()== Type.GRASS){
                    g.drawImage(map[x][y].getSecondImg(), x*game.getTileSize(), y*game.getTileSize(), game.getTileSize(), game.getTileSize(), game);
                }
            }
        }
    }
    public static BufferedImage rotate(BufferedImage bimg, double angle) {

        int w = bimg.getWidth();    
        int h = bimg.getHeight();
    
        BufferedImage rotated = new BufferedImage(w, h, bimg.getType());  
        Graphics2D graphic = rotated.createGraphics();
        graphic.rotate(Math.toRadians(angle), w/2, h/2);
        graphic.drawImage(bimg, null, 0, 0);
        graphic.dispose();
        return rotated;
    }

    public int getRandomNb(){
        return random.nextInt(3);
    }

}
