package config;

import gui.Game;
import gui.Icon;
import gui.IconsGraphics;
import model.Base;
import model.Coordinates;
import model.Tower;
import gui.TowerGraphics;

import java.util.ArrayList;

import config.Tile.Type;

import java.awt.image.BufferedImage;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class IconsConfig implements MouseListener, MouseMotionListener{
 
    private Game game;
    private MapConfig mapConfig;
    private TowerConfig towerConfig;
    private TowerGraphics towerGraphics;
    private IconsGraphics iconsGraphics;

    private ArrayList<BufferedImage> towerImages;
    private ArrayList<Icon> icons = new ArrayList<>();
    private Tower towerChosen;
    private boolean isClicked=false;
    private int iconNb;
    private Base base;

    public IconsConfig(Game game){

        this.game=game;
        this.mapConfig=this.game.getMapConfig();
        this.towerConfig=this.game.getTowerConfig();
        this.towerGraphics= this.game.getTowerGraphics();
        this.towerImages= towerGraphics.getTowerIcons();
        this.base = this.game.getBase();
        addIcons();
        this.game.addMouseListener(this);
        this.game.addMouseMotionListener(this);
    }

    
    public Tower getTowerChosen() {
        return towerChosen;
    }

    public void setTowerChosen(Tower towerChosen) {
        this.towerChosen = towerChosen;
    }

    public void setIconsGraphics(IconsGraphics iconsGraphics) {
        this.iconsGraphics = iconsGraphics;
    }

    public void addIcons(){
        for(int i=0; i<6;i++){
            icons.add(new Icon(this.towerImages.get(i),i));
        }
    }

    public ArrayList<Icon> getIcons() {
        return icons;
    }

    public void payCastle(int price){
        this.base.enleveArgent(price);
    }

    /* FAIRE QUE L ON NE PUISSE PAS POSER UNE TOUR SI UNE TOUR EST DE NIVEAU SUPERIEUR 
    A LA TOUR QUE L ON VEUT METTRE SAUF SI C UN AUTRE TYPE DE TOUR
     * PRIX
     */

    public void addTower(Tile tile){
        //Si la Tile sur laquelle on a cliqué est une tour
        if(tile.getType()==Type.TOWER){
            //System.out.println("BON TYPE ? "+(tile.getType()==Type.TOWER));
    
            ArrayList<Tower> newList = new ArrayList<>();
            for(Tower t:towerConfig.getMouseTowers()){
                if(t.getPos()==tile.getTileCoor()){
                    //System.out.println("tower removed");
                    //System.out.println("TPOS : "+t.getPos().getX()+","+t.getPos().getY()+"      TILE : "+tile.getTileCoor().getX()+","+tile.getTileCoor().getY());
                }
                else{
                    newList.add(t);
                    //System.out.println("tower added");

                }
            }
            towerConfig.setMouseTowers(newList);
            //System.out.println("mouseTowers changed");
            towerChosen.setPos(tile.getTileCoor());
            //System.out.println("CHOSENPOS : "+towerChosen.getPos().getX()+","+towerChosen.getPos().getY());
            ///actualise la zone d'attaque
            towerChosen.setAttackZone((int)towerChosen.getAttackZone().getWidth(),(int)towerChosen.getAttackZone().getHeight());
            towerConfig.getMouseTowers().add(towerChosen);
            this.base.enleveArgent(towerChosen.getPrice());
            System.out.println("APRES " +this.base.getArgent());
            //System.out.println("towerChosen added");
        }
    }

    private boolean isEnoughMoney(Icon icon){
        System.out.println(this.base.getArgent());
        return this.base.getArgent()>=towerConfig.getTowerPrice(icon.getTower());
        
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(isClicked){
            this.iconsGraphics.setActualBackground(this.iconsGraphics.getBackgroundIcons().get(0));
            System.out.println("ISCLICKED PART");
            this.isClicked=false;
            Coordinates tPos= new Coordinates((int)e.getPoint().getX(), (int)e.getPoint().getY());
            Tile tile =mapConfig.getTile(tPos);
            addTower(tile);
            this.iconsGraphics.setFollowing(false);
            //towerChosen.setPos(new Coordinates((int)e.getPoint().getX(), (int)e.getPoint().getY()));
            //this.towerGraphics.setTest(towerChosen);
        }
        else{
            for(Icon icon : this.icons){
                if(icon.getZone().contains(e.getPoint())){
                    this.iconsGraphics.setActualBackground(this.iconsGraphics.getBackgroundIcons().get(1));
                    System.out.println("IN ZONE CLICKED");
                    if(isEnoughMoney(icon)){
                        this.towerChosen=towerConfig.towerNum(icon.getTower());
                        this.iconsGraphics.setChosenIcon(icon);
                        this.iconNb=icon.getTower();
                        this.isClicked=true;
                        //image qui suit souris
                        System.out.println("AVANT "+this.base.getArgent());
                        this.iconsGraphics.setFollowing(true);
                    }
                    else{
                        System.out.println("NO MONEY");
                    }
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


    @Override
    public void mouseDragged(MouseEvent e) {
    }


    @Override
    public void mouseMoved(MouseEvent e) {
        if(this.iconsGraphics.isFollowing()){
            Coordinates tPos= new Coordinates((int)e.getPoint().getX(), (int)e.getPoint().getY());
            this.iconsGraphics.setDrawMouseIcons(iconNb, tPos);
        }
        else{
            this.iconsGraphics.setDrawMouseIcons(0,null);
        }
    }

}
