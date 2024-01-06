package model;

public class Power {
    
    public enum Element{THUNDER,FIRE,ICE};

    private Element type;

    public Power(Element type){
        this.type=type;
    }

    public Element getType() {
        return type;
    }

    public void setType(Element type) {
        this.type = type;
    }
    
}
