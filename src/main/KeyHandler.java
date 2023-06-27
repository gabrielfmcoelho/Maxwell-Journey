package main;

import world.Frontier;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    int code;
    GamePanel gp;
    public boolean isPressedUp, isPressedDown, isPressedEnter;
    Frontier destination = null;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) { // Standard method that deals with the event of a key being pressed.
        code = e.getKeyCode(); // Receives the code associated with the key pressed.

        if (gp.gameState == gp.startState || gp.gameState == gp.gameoverState) {
            switch (code){
                case KeyEvent.VK_UP -> gp.ui.commandNumber--;
                case KeyEvent.VK_DOWN -> gp.ui.commandNumber++;
                case KeyEvent.VK_ENTER -> {
                    if (gp.ui.commandNumber == 0){
                        gp.player.setDefaultValues();
                        gp.cityManager.setDefaultValues();
                        gp.player.updatePosition(gp.cityManager.getCityCurrent());
                        gp.ui.dialogueCounter = 0;
                        gp.gameState = gp.playerState;
                    }
                    if (gp.ui.commandNumber == 1){
                        System.exit(0);
                    }
                }
            }
            if (gp.ui.commandNumber < 0){
                gp.ui.commandNumber = 1;
            }
            if (gp.ui.commandNumber > 1){
                gp.ui.commandNumber = 0;
            }
        }
        else if (gp.gameState == gp.playerState){
            switch (code){
                case KeyEvent.VK_UP -> gp.gameState = gp.playerState;
                case KeyEvent.VK_ENTER -> {
                    gp.shopkeeper.speak();
                    gp.ui.dialogOn = true;
                    gp.gameState = gp.shopkeeperState;
                }
                case KeyEvent.VK_DOWN -> gp.gameState = gp.pauseState;
                case KeyEvent.VK_LEFT -> {
                    if (gp.player.activeQuest != null){
                        gp.ui.currentDialogue = "Você abandonou a missão: \n" + gp.player.activeQuest.name + ".";
                        gp.player.abandonQuest();
                        gp.gameState = gp.abbandonState;
                    }
                }
                case KeyEvent.VK_RIGHT -> {
                    if (gp.player.activeQuest != null){
                        gp.gameState = gp.questState;
                    }
                }
            }
        }
        else if (gp.gameState == gp.shopkeeperState){
            switch (code){
                case KeyEvent.VK_UP -> gp.ui.commandNumber--;
                case KeyEvent.VK_DOWN -> gp.ui.commandNumber++;
                case KeyEvent.VK_ENTER -> {
                    if (gp.ui.dialogOn){
                        gp.shopkeeper.updateDialogue();
                        gp.shopkeeper.speak();
                    }
                    else if (gp.ui.choiceCity){
                        destination = gp.cityManager.getCityCurrent().getFrontier().get(gp.ui.commandNumber);
                        gp.ui.choiceCity = false;
                        gp.shopkeeper.updateDialogue();
                        gp.shopkeeper.speak();
                    }
                    else if (gp.ui.choiceQuest && gp.ui.dialogueCounter > 1){
                        if (gp.ui.commandNumber == 0){
                            gp.player.acceptQuest(gp.cityManager.getCityCurrent().getQuest().acceptQuest());
                        }
                        gp.ui.choiceQuest = false;
                        gp.shopkeeper.updateDialogue();
                        gp.shopkeeper.speak();
                    }
                    else if(gp.ui.choiceQuest){
                        gp.shopkeeper.updateDialogue();
                        gp.shopkeeper.speak();
                    }
                    else if (gp.ui.choiceExchange){
                        if (gp.ui.commandNumber == 0){
                            gp.shopkeeper.exchange();
                        }
                        gp.ui.dialogOn = true;
                        gp.ui.choiceExchange = false;
                        gp.gameState = gp.playerState;
                        gp.shopkeeper.endConversation(destination);
                    }
                }
            }
            if (gp.ui.choiceCity){
                if (gp.ui.commandNumber >= gp.cityManager.getCityCurrent().getFrontier().size()){
                    gp.ui.commandNumber = 0;
                }
                if (gp.ui.commandNumber < 0){
                    gp.ui.commandNumber = gp.cityManager.getCityCurrent().getFrontier().size() - 1;
                }
            }
            else if (gp.ui.choiceQuest || gp.ui.choiceExchange){
                if (gp.ui.commandNumber < 0){
                    gp.ui.commandNumber = 1;
                }
                if (gp.ui.commandNumber > 1){
                    gp.ui.commandNumber = 0;
                }
            }
        }
        else if (gp.gameState == gp.endState){
            switch (code){
                case KeyEvent.VK_ENTER -> gp.gameState = gp.startState;
            }
        }
        else if (gp.gameState == gp.questState){
            switch (code){
                case KeyEvent.VK_ENTER -> {
                    gp.flag = true;
                    gp.gameState = gp.playerState;
                }
            }
        }
        else if (gp.gameState == gp.abbandonState){
            switch (code){
                case KeyEvent.VK_ENTER -> gp.gameState = gp.playerState;
            }
        }
        else if (gp.gameState == gp.pauseState){
            switch (code){
                case KeyEvent.VK_DOWN -> gp.gameState = gp.playerState;
            }
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
