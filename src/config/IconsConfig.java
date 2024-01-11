package config;

import gui.Game;
import gui.Icon;
import model.Base;
import model.Coordinates;
import model.Power;
import model.Tower;
import model.Power.Element;
import gui.Graphics.IconsGraphics;
import gui.Graphics.TowerGraphics;

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
    private PowersConfig powersConfig;
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

    private boolean unlocked=false;
    private boolean towerUpgradeUnlocked=false;


    private ArrayList<Tower> towers;

    public IconsConfig(Game game){

        this.game=game;
        this.mapConfig=this.game.getMapConfig();
        this.towerConfig=this.game.getTowerConfig();
        this.powersConfig=this.game.getPowersConfig();
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

    public boolean isUnlocked() {
        return unlocked;
    }


    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }

    public boolean isTowerUpgradeUnlocked() {
        return towerUpgradeUnlocked;
    }


    public void setTowerUpgradeUnlocked(boolean towerUpgradeUnlocked) {
        this.towerUpgradeUnlocked = towerUpgradeUnlocked;
    }

    public void addIcons(){
        icons.add(new Icon(this.towerImages.get(0),0));
        icons.add(new Icon(this.towerImages.get(3),3));
        icons.add(new Icon(this.iconsGraphics.getPowersIconAsset().get(0),Element.FIRE));
        icons.add(new Icon(this.iconsGraphics.getPowersIconAsset().get(1),Element.ICE));
        icons.add(new Icon(this.iconsGraphics.getPowersIconAsset().get(2),Element.THUNDER));
        icons.add(new Icon(this.powersConfig.getPowersGraphics().getPowersAsset().get(29),Element.HEAL));
        
    }

    public void update(){
        addUpgradeIcons();
        if(this.game.getEnemyConfig().getNbEnemiesDead()>=this.powersConfig.getNbUnlock(this.game.getLevel())){
            this.unlocked=true;
        }
        if(this.game.getTowerConfig().isAllMaxLevel()){
            this.towerUpgradeUnlocked=true;
        }
    }

    public static int getYAlignment(Tower t){
        int y =(int) t.getPos().getY();
        if(t.getLevel()==1){
            y = (int) t.getPos().getY()-20;
        }
        else if(t.getLevel()==2){
            y = (int) t.getPos().getY()-40;
        }
        else if(t.getLevel()==3){
            y = (int) t.getPos().getY()-60;
        }
        return y;
    }

    public void addUpgradeIcons(){
        this.towers=this.towerConfig.getMouseTowers();
        ArrayList<Icon> upgradeIcons = new ArrayList<>();
        for(Tower t : towers){
            if(t.getLevel()!=3 && t.getNextPrice(t.idColorTower()+1)<= this.base.getArgent()){

                Icon i = new Icon(t.getLevel()+1);

                int y =getYAlignment(t);

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
    
            ArrayList<Tower> newList = new ArrayList<>(towerConfig.getMouseTowers());
            for(Tower t:towerConfig.getMouseTowers()){
                if(t.getPos()==tile.getTileCoor()){
                    newList.remove(t);
                }
            }
            towerConfig.setMouseTowers(newList);
            towerChosen.setPos(tile.getTileCoor());
            ///actualise la zone d'attaque
            towerChosen.setAttackZone((int)towerChosen.getAttackZone().getWidth(),(int)towerChosen.getAttackZone().getHeight());
            towerConfig.getMouseTowers().add(towerChosen);
            this.base.enleveArgent(towerChosen.getPrice());
        }
    }

    private boolean isEnoughMoneyTower(Icon icon){
        return this.base.getArgent()>=towerConfig.getTowerPrice(icon.getTower());
        
    }

    private boolean isEnoughMoneyPower(Icon icon){
        return this.base.getArgent()>=Power.prices(icon.getPower());
        
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(isClicked){
            this.iconsGraphics.setActualBackground(this.iconsGraphics.getBackgroundIcons().get(0));
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
                    if(icon.getPower()==null){
                        this.iconsGraphics.setActualBackground(this.iconsGraphics.getBackgroundIcons().get(1));
                        this.iconsGraphics.setChosenIcon(icon);
                        if(isEnoughMoneyTower(icon)){
                            this.towerChosen=towerConfig.towerNum(icon.getTower());
                            this.iconNb=icon.getTower();
                            this.isClicked=true;
                            //image qui suit souris
                            this.iconsGraphics.setFollowing(true);
                        }
                        else{
                        }
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

    public void pressed(Icon icon){
        if(isEnoughMoneyPower(icon)){
            this.iconsGraphics.setActualBackground(this.iconsGraphics.getBackgroundIcons().get(1));
            this.iconsGraphics.setChosenIcon(icon);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(unlocked){
            for(int i=2;i<icons.size()-1;i++){
                if(icons.get(i).getZone().contains(e.getPoint())){
                    pressed(icons.get(i));
                }
            }
        }
        if(towerUpgradeUnlocked){
            if(icons.get(5).getZone().contains(e.getPoint())){
                pressed(icons.get(5));
            }
        }
    }

    public void released(Icon icon){
        this.iconsGraphics.setActualBackground(this.iconsGraphics.getBackgroundIcons().get(0));

        if(isEnoughMoneyPower(icon)){
            Power p = new Power(icon.getPower());
            p.setClickedTime(System.currentTimeMillis());
            this.powersConfig.getPowersGraphics().setActualPower(p);
            this.powersConfig.setPower(p);
            this.powersConfig.getPowersGraphics().setAnimationDone(false);
            this.base.enleveArgent(Power.prices(p.getType()));
        }
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        if(unlocked){
            for(int i=2;i<icons.size()-1;i++){
                if(icons.get(i).getZone().contains(e.getPoint())){
                    released(icons.get(i));
                }
            }
        }
        if(towerUpgradeUnlocked){
            if(icons.get(5).getZone().contains(e.getPoint())){
                released(icons.get(5));
            }
        }
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
