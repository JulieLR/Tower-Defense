package config;

import gui.Game;
import gui.Icon;
import model.Coordinates;
import model.Tower;
import gui.TowerGraphics;

import java.util.ArrayList;

import config.Tile.Type;

import java.awt.image.BufferedImage;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class IconsConfig implements MouseListener{
 
    private Game game;
    private MapConfig mapConfig;
    private TowerConfig towerConfig;
    private TowerGraphics towerGraphics;
    private ArrayList<BufferedImage> towerImages;
    private ArrayList<Icon> icons = new ArrayList<>();
    private Tower towerChosen;
    private boolean isClicked=false;

    public IconsConfig(Game game){

        this.game=game;
        this.mapConfig=this.game.getMapConfig();
        this.towerConfig=this.game.getTowerConfig();
        this.towerGraphics= this.game.getTowerGraphics();
        this.towerImages= this.game.getTowerGraphics().getTowerIcons();
        addIcons();
        this.game.addMouseListener(this);
    }

    
    public Tower getTowerChosen() {
        return towerChosen;
    }

    public void setTowerChosen(Tower towerChosen) {
        this.towerChosen = towerChosen;
    }

    public void addIcons(){
        for(int i=0; i<6;i++){
            icons.add(new Icon(this.towerImages.get(i),towerConfig.towerNum(i)));
        }
    }

    public ArrayList<Icon> getIcons() {
        return icons;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(isClicked){
            System.out.println("ISCLICKED PART");
            this.isClicked=false;
            Coordinates tPos= new Coordinates((int)e.getPoint().getX(), (int)e.getPoint().getY());
            Tile tile =mapConfig.getTile(tPos);
            if(tile.getType()==Type.TOWER){
                System.out.println("BON TYPE ?"+(tile.getType()==Type.TOWER));
                towerChosen.setPos(tile.getTileCoor());
                towerChosen.setAttackZone((int)towerChosen.getAttackZone().getWidth(),(int)towerChosen.getAttackZone().getHeight());
                for(Tower t:towerConfig.getMouseTowers()){
                    if(t.getPos()==tile.getTileCoor()){
                        towerConfig.getMouseTowers().remove(t);
                        System.out.println("tower removed");
                    }
                }
                towerConfig.getMouseTowers().add(towerChosen);
            }
            //towerChosen.setPos(new Coordinates((int)e.getPoint().getX(), (int)e.getPoint().getY()));
            //this.towerGraphics.setTest(towerChosen);
        }
        else{
            for(Icon icon : this.icons){
                if(icon.getZone().contains(e.getPoint())){
                    System.out.println("IN ZONE CLICKED");
                    this.towerChosen=icon.getTower();
                    this.isClicked=true;
                }
            }
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
    }


    @Override
    public void mouseReleased(MouseEvent e) {
    }


    @Override
    public void mouseEntered(MouseEvent e) {
    }


    @Override
    public void mouseExited(MouseEvent e) {
    }

}
