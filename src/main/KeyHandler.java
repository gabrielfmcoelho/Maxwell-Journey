package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    int code;
    public boolean isPressedUp, isPressedDown, isPressedEnter;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) { // Standard method that deals with the event of a key being pressed.
        code = e.getKeyCode(); // Receives the code associated with the key pressed.

        switch (code){
            case KeyEvent.VK_UP -> isPressedUp = true;
            case KeyEvent.VK_DOWN -> isPressedDown = true;
            case KeyEvent.VK_ENTER -> isPressedEnter = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        code = e.getKeyCode(); // Receives the code associated with the key pressed.

        switch (code){
            case KeyEvent.VK_UP -> isPressedUp = false;
            case KeyEvent.VK_DOWN -> isPressedDown = false;
            case KeyEvent.VK_ENTER -> isPressedEnter = false;
        }
    }
}
