package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame(); // Instantiate JFrame object to create the game window.
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set how to close the window, in this case: on close.
        window.setResizable(false); // The window cannot be resized.
        window.setTitle("Maxwell Journey"); // Title of the window, TODO: Move the window string title to a constant and then call it here.

        GamePanel gamePanel = new GamePanel(); // Instantiate the game panel object.
        window.add(gamePanel); // Add the game panel as component of the game window.
        window.pack(); // Cause the game window to be sized to the preferred size and it's subcomponents.

        window.setLocationRelativeTo(null); // Will display the window at the center of the screen.
        window.setVisible(true); // Will make the window visible.

        gamePanel.startGameThread(); // Call the start of the game thread process;
    }
}