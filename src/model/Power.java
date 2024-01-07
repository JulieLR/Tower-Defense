package model;

public class Power {
    
    public enum Element{THUNDER,FIRE,ICE};

    private Element type;
    private long clickedTime;
    private boolean animationDone=false;

    public Power(Element type){
        this.type=type;
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
    
}
