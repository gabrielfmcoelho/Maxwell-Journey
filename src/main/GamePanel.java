package main;

import entity.Player;
import world.CityManager;
import world.Tile;
import world.TileManager;

import javax.swing.JPanel;

import java.awt.*;

import java.lang.Thread;

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
    CityManager cityManager = new CityManager(this);
    KeyHandler keyH = new KeyHandler(); // Instantiate the key handler to listen and deal with pressed keys.
    Thread gameThread; // Define a thread to be used the control the flow of the game.
    Player player = new Player(this, keyH, cityManager.getCityCurrent());

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
        player.update();
    }

    public void paintComponent(Graphics g){ // PaintComponent is a standard method, already existent in java do draw (or "paint") in the JPanel, and it receives Graphics class (that have many features to draw objects on screens).
        super.paintComponent(g); // Access the parent JPanel through super. and it's method paintComponent.
        Graphics2D g2 = (Graphics2D)g; // Convert the graphics object to graphics2D, which is a class that extends graphics and have more/better features to handle 2D (geometry, coordinates...).

        tileManager.draw(g2);
        cityManager.draw(g2);
        player.draw(g2);

        g2.dispose(); // Good practice to save memory, dispose of this graphic in context and release any system resources that is using it.
    }


}
