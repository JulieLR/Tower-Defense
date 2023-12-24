package model;

public class Ennemies extends Entities{
    private int pointDeVie;
    
public Ennemies(int vie, Coordinates pos,int degat, int vitesseAtk){
    super(vitesseAtk, pos, degat);
    this.pointDeVie=vie;
}
}
