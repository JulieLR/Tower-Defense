package config;


import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
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
        mapFromFile("src/config/mapT.txt");


    }
    
    public void mapFromFile(String path){
        try{
            int ligne = 0;
            int col = 0;
            BufferedReader reader_2 = new BufferedReader(new FileReader(path));
            String readline;
            while ((readline = reader_2.readLine()) != null) {
                System.out.println(ligne + " " + readline);
                for(int i =0; i< readline.length();i++){
                    if(readline.charAt(i) == 'G'){
                        this.map[ligne][col] = new Tile(tiles.get(0), Type.GRASS);
                        ligne++;
                    }
                    else if(readline.charAt(i) == 'D'){
                        this.map[ligne][col] = new Tile(tiles.get(1), Type.PATH);
                        ligne++;
                    }
                    else if(readline.charAt(i) == 'W'){
                        this.map[ligne][col] = new Tile(tiles.get(2), Type.WATER);
                        ligne++;
                    }
                }
                col++;
                ligne=0;
            }
            reader_2.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addAsset() {
        //0: herbe, 1: terre, 2:eau
        tiles.add(this.game.getImage().getSubimage(1*this.game.getInitialTileSize(), 1*this.game.getInitialTileSize(), this.game.getInitialTileSize(), this.game.getInitialTileSize()));
        tiles.add(this.game.getImage().getSubimage(1*this.game.getInitialTileSize(), 6*this.game.getInitialTileSize(), this.game.getInitialTileSize(), this.game.getInitialTileSize()));
        tiles.add(this.game.getImage().getSubimage(5*this.game.getInitialTileSize(), 1*this.game.getInitialTileSize(), this.game.getInitialTileSize(), this.game.getInitialTileSize()));
    }

    public void drawBackground(Graphics g) {
        for(int x=0;x<this.map.length;x++){
            for(int y=0;y<this.map[0].length;y++){
                g.drawImage(map[x][y].getImage(),x*game.getTileSize(), y*game.getTileSize(), game.getTileSize(), game.getTileSize(), null);
                //mettre de la déco sur l'herbe
                /* if(this.map[x][y].getType()== Type.GRASS){
                    int r = random.nextInt(12);
                    if(r!=12){
                        g.drawImage(tilesDeco.get(r), x*game.getTileSize(), y*game.getTileSize(), game.getTileSize(), game.getTileSize(), game);
                    }    
                } */
            }
        }
    }

    public int getRandomNb(){
        return random.nextInt(3);
    }

}
