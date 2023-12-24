package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse_Listener implements MouseListener, MouseMotionListener{

    // MouseListener: pour interactions avec les clics de souris
    @Override
    public void mouseClicked(MouseEvent e) { /*
        // clic gauche
        if (e.getButton()== MouseEvent.BUTTON1) {
            System.out.println("left button pressed");
        } 
        // scroller 
        else if (e.getButton()== MouseEvent.BUTTON2) {
            System.out.println("scroll button pressed");
        }
        // clic droit
        else if (e.getButton()== MouseEvent.BUTTON3) {
            System.out.println("right button pressed");
        } */
        /* 
        // coordonnees de quand on clique sur la souris (clic gauche)
        if (e.getButton()== MouseEvent.BUTTON1) {
            System.out.println("position: "+ e.getX()+ " "+ e.getY());
        } */
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        // peut faire 1 swithc selon "la fenêtre de jeu on est"
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    // MouseMotionListener: pour les intercations avec les mouvements de la souris
    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
    }
    
}
