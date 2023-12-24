package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard_Listener implements KeyListener{

    // KeyListener: pour les interactions avec les lettres du clavier
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) { /* 
        // appuie sur A
        if (e.getKeyCode()==KeyEvent.VK_A) {
            System.out.println("A pressed");
        } 
        // appuie sur 0 (marche pas avec le numpad, fonctionne avec ou sans maj)
        else if (e.getKeyCode()==KeyEvent.VK_0) {
            System.out.println("0 pressed");
        } */
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
