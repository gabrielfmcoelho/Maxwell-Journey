package main;

import UI.UI;
import entity.Player;
import shopkeeper.Shopkeeper;
import world.CityManager;
import world.TileManager;


import javax.swing.JPanel;

import java.awt.*;

import java.lang.Thread;
import java.util.HashMap;

public class GamePanel extends JPanel implements Runnable{
    final int tileSize = 16; // Set every tile base unit as 16x16 pixels.
    final int scale = 3; // Scale of the final tile as the base unit will be too small to modern screens. TODO: How to define the scale according to the screen of the user?
    public final int tileSizeScaled = tileSize * scale; // Final base tile as 16x3 = 48x48pixels.

    public final int screenNumColumns = 18; // Number of final base tile columns that the screen comport.
    public final int screenNumRows = 16; // Number of final base tile rows that the screen comport.
    public final int screenWidth = tileSizeScaled * screenNumColumns; // Screen width 48x16 = 768 pixels.
    public final int screenHeight = tileSizeScaled * screenNumRows; // Screen height 48x12 = 576 pixels.
    // Therefore the screen presents 4/3

    TileManager tileManager = new TileManager(this);
    public CityManager cityManager = new CityManager(this);
    KeyHandler keyH = new KeyHandler(this); // Instantiate the key handler to listen and deal with pressed keys.
    Thread gameThread; // Define a thread to be used the control the flow of the game.
    public Player player = new Player(this, cityManager.getCityCurrent());
    UI ui = new UI(this);
    public Shopkeeper shopkeeper = new Shopkeeper(ui, cityManager, player, this);


    public int gameState; // 0 = Start, 1 = Game, 2 = Pause, 3 = Shop, 4 = End Game, 5 = Game Over.
    public final int startState = 0;
    public final int playerState = 1;
    public final int pauseState = 2;
    public final int shopkeeperState = 3;
    public final int endState = 4;
    public final int gameoverState = 5;
    public final int abbandonState = 6;
    public final int questState = 7;
    public boolean flag = true;

    public int endgameStatus = 2;
    public HashMap<Integer, String> endgameStatusMap = new HashMap<Integer, String>(){{
        put(0, "You have arrived at your destination\n wealthy and became the new king.");
        put(1, "You have arrived at your destination\n and became a lord of the crown.");
        put(2, "You have arrived at your destination\n poor and became someone's servant.");
    }};
    public void checkEndgameStatus(){
        if (player.getCoins() > 10){
            endgameStatus = 0;
        }
        else if (player.getCoins() <= 10 && player.getCoins() > 4){
            endgameStatus = 1;
        }
        else {
            endgameStatus = 2;
        }
        ui.currentDialogue = endgameStatusMap.get(endgameStatus);
    }

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Set size of the window screen.
        this.setBackground(Color.black); // Set the temporary background color of the window.
        this.setDoubleBuffered(true); // A programming technique that uses two buffers to speed up a computer that can overlap I/O with processing. Data in one buffer are being processed while the next set of data is read into the other one.
        this.addKeyListener(keyH); // Setting the object that will handle the key listening.
        this.setFocusable(true); // Will focus on receiving one key (I GUESS).
    }

    public void startGameThread() {
        gameThread = new Thread(this); // Instantiate the game thread that receives this class.
        gameThread.start(); // Start running the gameThread;
    }

    // This is a standard method that is mandatory upon the implementation of Runnable interface, that is automatically called on the start.
    @Override
    public void run() {
        int fps = 60;
        double drawInterval = 1000000000.0/fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        gameState = 0;

        // Base game loop
        while(gameThread != null){ // While the gameThread is running the game will loop these flow of commands.
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1) {
                // TODO: UPDATE information such as character position.
                update();
                // TODO: DRAW the screen with updated information.
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        if (gameState == endState){
            checkEndgameStatus();
        }
        else if (player.isAlive() && player.hasCoins()){
            if (gameState == playerState){
                player.update();
            }
            else if (gameState == shopkeeperState){
                //
            }
        }
        else {
            gameState = gameoverState;
        }
    }

    public void paintComponent(Graphics g){ // PaintComponent is a standard method, already existent in java do draw (or "paint") in the JPanel, and it receives Graphics class (that have many features to draw objects on screens).
        super.paintComponent(g); // Access the parent JPanel through super. and it's method paintComponent.
        Graphics2D g2 = (Graphics2D)g; // Convert the graphics object to graphics2D, which is a class that extends graphics and have more/better features to handle 2D (geometry, coordinates...).

        if (gameState == startState || gameState == gameoverState){
            ui.draw(g2);
        }

        if (gameState == playerState || gameState == shopkeeperState || gameState == pauseState){
            tileManager.draw(g2);
            cityManager.draw(g2);
            player.draw(g2);
            ui.draw(g2);
        }

        if (gameState == endState || gameState == abbandonState){
            tileManager.draw(g2);
            ui.draw(g2);
        }

        if (gameState == questState){
                if (flag) {
                    String path = cityManager.pathListToString(cityManager.navigateShortestPath());
                    ui.currentDialogue = "Você está na cidade " + cityManager.getCityCurrent().name + ".\n" +
                            "Você deve ir para a cidade " + player.activeQuest.destination.name + ".\n" +
                            "O caminho mais curto é: " + path + ".";
                    flag = false;
                }
            ui.draw(g2);
        }

        g2.dispose(); // Good practice to save memory, dispose of this graphic in context and release any system resources that is using it.
    }


}
