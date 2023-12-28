package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse_Listener implements MouseListener, MouseMotionListener{

    // MouseListener: pour interactions avec les clics de souris
    @Override
    public void mouseClicked(MouseEvent e) { /*
        // click gauche
        if (e.getButton()== MouseEvent.BUTTON1) {
            System.out.println("left button pressed");
        } 
        // scroller 
        else if (e.getButton()== MouseEvent.BUTTON2) {
            System.out.println("scroll button pressed");
        }
        // click droit
        else if (e.getButton()== MouseEvent.BUTTON3) {
            System.out.println("right button pressed");
        } */
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
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
