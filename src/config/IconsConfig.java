package config;

import gui.Game;
import gui.Icon;
import gui.IconsGraphics;
import model.Base;
import model.Coordinates;
import model.Tower;
import model.Power.Element;
import gui.TowerGraphics;

import java.util.ArrayList;

import config.Tile.Type;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;
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
    private ArrayList<Icon> upgradeIcon = new ArrayList<>();

    private Tower towerChosen;
    private boolean isClicked=false;
    private int iconNb;
    private Base base;

    private ArrayList<Tower> towers;

    public IconsConfig(Game game){

        this.game=game;
        this.mapConfig=this.game.getMapConfig();
        this.towerConfig=this.game.getTowerConfig();
        this.towerGraphics= this.game.getTowerGraphics();
        this.towerImages= towerGraphics.getTowerIcons();
        this.iconsGraphics=new IconsGraphics(game, this);
        this.base = this.game.getBase();
        addIcons();
        addUpgradeIcons();
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
        icons.add(new Icon(this.towerImages.get(0),0));
        icons.add(new Icon(this.towerImages.get(3),3));
        icons.add(new Icon(this.iconsGraphics.getPowersIconAsset().get(0),Element.FIRE));
        icons.add(new Icon(this.iconsGraphics.getPowersIconAsset().get(1),Element.ICE));
        icons.add(new Icon(this.iconsGraphics.getPowersIconAsset().get(2),Element.THUNDER));

        
    }

    public void update(){
        addUpgradeIcons();
    }

    public void addUpgradeIcons(){
        this.towers=this.towerConfig.getMouseTowers();
        ArrayList<Icon> upgradeIcons = new ArrayList<>();
        for(Tower t : towers){
            if(t.getLevel()!=3 && t.getNextPrice(t.idColorTower()+1)<= this.base.getArgent()){

                Icon i = new Icon(t.getLevel()+1);

                int y =(int) t.getPos().getY();
                if(t.getLevel()==1){
                    y = (int) t.getPos().getY()-20;
                }
                else if(t.getLevel()==2){
                    y = (int) t.getPos().getY()-40;
                }

                i.setZone(new Rectangle((int)(t.getPos().getX()+8+28),y, 9*4, 9*4));
                i.setImgCoordinates(new Coordinates((int)t.getPos().getX()+8, y));
                i.setActualTower((t));
                upgradeIcons.add(i);
            }
        }
        this.upgradeIcon= upgradeIcons;
    }

    public ArrayList<Icon> getIcons() {
        return icons;
    }

    public ArrayList<Icon> getUpgradeIcon() {
        return upgradeIcon;
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
    
            ArrayList<Tower> newList = new ArrayList<>(towerConfig.getMouseTowers());
            for(Tower t:towerConfig.getMouseTowers()){
                if(t.getPos()==tile.getTileCoor()){
                    newList.remove(t);
                    //System.out.println("tower removed");
                    //System.out.println("TPOS : "+t.getPos().getX()+","+t.getPos().getY()+"      TILE : "+tile.getTileCoor().getX()+","+tile.getTileCoor().getY());
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
        for(Icon icon : upgradeIcon ){
            if(icon.getZone().contains(e.getPoint())){
                this.towerChosen=towerConfig.towerNum(icon.getActualTower().idColorTower()+1);
                addTower(mapConfig.getTile(icon.getActualTower().getPos()));
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
