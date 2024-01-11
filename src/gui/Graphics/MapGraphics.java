package gui.Graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import config.MapConfig;
import config.Tile;
import config.Tile.Type;
import gui.Game;

public class MapGraphics implements Graphic {
    
    private Game game;
    private MapConfig mapConfig;
    private Tile[][] map;
    private BufferedImage mapImage;
    private ArrayList<BufferedImage> tiles = new ArrayList<>();

    public MapGraphics(Game game, MapConfig mapConfig){

        this.game=game;
        this.mapConfig=mapConfig;
        this.mapImage = getImage("src/ressources/map/sprite.png");
        addAsset();
        this.map = mapConfig.getMap();
        
    }

    @Override
    public void addAsset() {
        for(int ligne=0;ligne<6;ligne++){
            for(int col=0;col<6;col++){
                if(ligne==5){
                    break;
                }
                if(ligne>3){
                    tiles.add(this.mapImage.getSubimage(col*this.game.getInitialTileSize(), ligne*this.game.getInitialTileSize(), this.game.getInitialTileSize(), this.game.getInitialTileSize()*2));
                }
                else{
                    tiles.add(this.mapImage.getSubimage(col*this.game.getInitialTileSize(), ligne*this.game.getInitialTileSize(), this.game.getInitialTileSize(), this.game.getInitialTileSize()));
                }
            }
        }
    }
    @Override
    public void drawImages(Graphics g) {
        for(int x=0;x<this.map.length;x++){
            for(int y=0;y<this.map[0].length-2;y++){
                /*si ce n'est pas un chateau alors on change la rotation de l'image avec la valeur du tile
                 * sinon la valeur sert à afficher la bonne image de chateau*/
                if(this.map[x][y].getType()!= Type.CASTLE){
                    g.drawImage(rotate(this.tiles.get(map[x][y].getImg()),90*map[x][y].getValue()),x*game.getTileSize(), y*game.getTileSize(), game.getTileSize(), game.getTileSize(), null);
                }
                else{
                    g.drawImage(this.tiles.get(17),x*game.getTileSize(), y*game.getTileSize(), game.getTileSize(), game.getTileSize(), null);
                    g.drawImage(this.tiles.get(map[x][y].getImg()),x*game.getTileSize(), y*game.getTileSize()-this.game.getTileSize(), game.getTileSize(), game.getTileSize()*2, null);
   
                }
                //Si c'est de l'herbe on rajoute de la décoration par dessus
                if(this.map[x][y].getType()== Type.GRASS ||this.map[x][y].getType()== Type.TOWER){
                    g.drawImage(this.tiles.get(map[x][y].getSndImg()), x*game.getTileSize(), y*game.getTileSize(), game.getTileSize(), game.getTileSize(), game);
                }
            }
        }
    }

    public void drawBottomBarAndScore(Graphics g){
        for(int x=0;x<this.map.length;x++){
            for(int y = this.map[0].length-3; y<this.map[0].length;y++){
                g.drawImage(rotate(this.tiles.get(map[x][y].getImg()),90*map[x][y].getValue()),x*game.getTileSize(), y*game.getTileSize(), game.getTileSize(), game.getTileSize(), null);
            }
        }
        for(int x=0;x<4;x++){
            for(int y =0; y<2;y++){
                g.drawImage(rotate(this.tiles.get(map[x][y].getImg()),90*map[x][y].getValue()),x*game.getTileSize(), y*game.getTileSize(), game.getTileSize(), game.getTileSize(), null);
            }
        }
    }

}
