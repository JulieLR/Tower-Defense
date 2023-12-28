package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import config.MapConfig;
import config.Tile;
import config.Tile.Type;

public class MapGraphics implements Graphic {
    
    private Game game;
    private MapConfig mapConfig;
    private Tile[][] map;
    private ArrayList<BufferedImage> tiles = new ArrayList<>();

    public MapGraphics(Game game, MapConfig mapConfig){

        this.game=game;
        this.mapConfig=mapConfig;
        addAsset();
        this.map = mapConfig.getMap();
        
    }

    @Override
    public void addAsset() {
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
                    g.drawImage(rotate(this.tiles.get(map[x][y].getImg()),90*map[x][y].getValue()),x*game.getTileSize(), y*game.getTileSize(), game.getTileSize(), game.getTileSize(), null);
                }
                else{
                    g.drawImage(this.tiles.get(map[x][y].getImg()),x*game.getTileSize(), y*game.getTileSize(), game.getTileSize(), game.getTileSize(), null);
   
                }
                //Si c'est de l'herbe on rajoute de la décoration par dessus
                if(this.map[x][y].getType()== Type.GRASS ||this.map[x][y].getType()== Type.TOWER){
                    g.drawImage(this.tiles.get(map[x][y].getSndImg()), x*game.getTileSize(), y*game.getTileSize(), game.getTileSize(), game.getTileSize(), game);
                }
            }
        }
    }

    public void drawStartTile(Graphics g){
        g.drawImage(tiles.get(28), (int)this.mapConfig.getStart().getX(),(int) this.mapConfig.getStart().getY(), game.getTileSize(), game.getTileSize(), game);

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
}
