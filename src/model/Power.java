package model;

public class Power {
    
    public enum Element{THUNDER,FIRE,ICE,HEAL};

    private Element type;
    private long clickedTime;
    private boolean animationDone=false;
    private int price;

    public Power(Element type){
        this.type=type;
        this.price=prices(type);
    }

    public Element getType() {
        return type;
    }

    public void setType(Element type) {
        this.type = type;
    }

    public long getClickedTime() {
        return clickedTime;
    }

    public void setClickedTime(long clickedTime) {
        this.clickedTime = clickedTime;
    }

    public boolean isAnimationDone() {
        return animationDone;
    }

    public void setAnimationDone(boolean animationDone) {
        this.animationDone = animationDone;
    }

    public int getPrice() {
        return price;
    }

    public static int prices(Element e){
        switch(e){
            case FIRE: return 100;
            case HEAL: return 200; 
            case ICE: return 100;
            case THUNDER: return 250;
            default:
                break;
        }
        return 0;
    }
    
}
