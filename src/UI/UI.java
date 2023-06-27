package UI;

import main.GamePanel;
import world.Frontier;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameOver = false;
    public String currentDialogue = "";
    public int commandNumber = 0;
    public boolean dialogOn = false;
    public boolean choiceCity = false;
    public boolean choiceQuest = false;
    public boolean choiceExchange = false;
    public static int dialogueCounter = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        if (gp.gameState == gp.startState){
            drawStartScreen();
        }
        else if (gp.gameState == gp.playerState){
            //
        }
        else if(gp.gameState == gp.shopkeeperState){
            drawDialogScreen();
        }
        else if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        else if(gp.gameState == gp.gameoverState){
            drawGameOverScreen();
        }
        else if(gp.gameState == gp.endState || gp.gameState == gp.abbandonState || gp.gameState == gp.questState){
            drawEndScreen();
        }
    }

    public void drawStartScreen(){
        g2.setColor(new Color(0,0,0));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,80F));
        String text = "Maxwell Journey";
        int xPosition = getXForCenteredText(text);
        int yPosition = gp.tileSizeScaled*3;

        g2.setColor(Color.gray);
        g2.drawString(text, xPosition+5, yPosition+5);

        g2.setColor(Color.WHITE);
        g2.drawString(text, xPosition, yPosition);

        xPosition = gp.screenWidth/2 - gp.tileSizeScaled*3/2;
        yPosition += gp.tileSizeScaled*2;
        g2.drawImage(gp.player.getDown1, xPosition, yPosition, gp.tileSizeScaled*3, gp.tileSizeScaled*3, null);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
        text = "Start Game";
        xPosition = getXForCenteredText(text);
        yPosition += gp.tileSizeScaled*6;
        g2.drawString(text, xPosition, yPosition);
        if (commandNumber == 0){
            g2.drawString(">", xPosition - gp.tileSizeScaled, yPosition);
        }

        text = "Exit Game";
        xPosition = getXForCenteredText(text);
        yPosition += gp.tileSizeScaled*2;
        g2.drawString(text, xPosition, yPosition);
        if (commandNumber == 1){
            g2.drawString(">", xPosition - gp.tileSizeScaled, yPosition);
        }
    }

    public void drawGameOverScreen(){
        g2.setColor(new Color(50,0,0));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,80F));
        String text = "Game Over";
        int xPosition = getXForCenteredText(text);
        int yPosition = gp.tileSizeScaled*3;

        g2.setColor(Color.gray);
        g2.drawString(text, xPosition+5, yPosition+5);

        g2.setColor(Color.WHITE);
        g2.drawString(text, xPosition, yPosition);

        xPosition = gp.screenWidth/2 - gp.tileSizeScaled*3/2;
        yPosition += gp.tileSizeScaled*2;
        g2.drawImage(gp.player.getDown1, xPosition, yPosition, gp.tileSizeScaled*3, gp.tileSizeScaled*3, null);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
        text = "Restart Game";
        xPosition = getXForCenteredText(text);
        yPosition += gp.tileSizeScaled*6;
        g2.drawString(text, xPosition, yPosition);
        if (commandNumber == 0){
            g2.drawString(">", xPosition - gp.tileSizeScaled, yPosition);
        }

        text = "Exit Game";
        xPosition = getXForCenteredText(text);
        yPosition += gp.tileSizeScaled*2;
        g2.drawString(text, xPosition, yPosition);
        if (commandNumber == 1){
            g2.drawString(">", xPosition - gp.tileSizeScaled, yPosition);
        }
    }

    public void drawEndScreen(){
        int xPosition = gp.tileSizeScaled * 2;
        int yPosition = gp.tileSizeScaled/2;
        int width = gp.screenWidth - (gp.tileSizeScaled * 4);
        int height = gp.tileSizeScaled*8;
        drawSubWindow(xPosition, yPosition, width, height);

        xPosition+= gp.tileSizeScaled;
        yPosition+= gp.tileSizeScaled;
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        xPosition+= gp.tileSizeScaled;
        yPosition+= gp.tileSizeScaled;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 25F));

        for (String line: currentDialogue.split("\n")) {
            g2.drawString(line, xPosition, yPosition);
            yPosition += g2.getFontMetrics().getHeight();
        }
    }

    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSED";
        int xPosition = getXForCenteredText(text);
        int yPosition = gp.screenHeight/2;

        g2.drawString(text, xPosition, yPosition);
    }

    public void drawDialogScreen(){
        int xPosition = gp.tileSizeScaled * 2;
        int yPosition = gp.tileSizeScaled/2;
        int width = gp.screenWidth - (gp.tileSizeScaled * 4);
        int height = gp.tileSizeScaled*9;
        drawSubWindow(xPosition, yPosition, width, height);

        xPosition+= gp.tileSizeScaled;
        yPosition+= gp.tileSizeScaled;
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g2.drawImage(image, xPosition, yPosition, gp.tileSizeScaled, gp.tileSizeScaled, null);

        xPosition+= gp.tileSizeScaled;
        yPosition+= gp.tileSizeScaled;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 25F));

        for (String line: currentDialogue.split("\n")) {
            g2.drawString(line, xPosition, yPosition);
            yPosition += g2.getFontMetrics().getHeight();
        }

        if (choiceCity){
            for (Frontier frontier : gp.cityManager.getCityCurrent().getFrontier()){
                yPosition += g2.getFontMetrics().getHeight();
                g2.drawString(frontier.getCityDestination().name, xPosition, yPosition);
                if (commandNumber == gp.cityManager.getCityCurrent().getFrontier().indexOf(frontier)){
                    g2.drawString(">", xPosition - gp.tileSizeScaled, yPosition);
                }
            }
        }
        if ((choiceQuest && dialogueCounter>1) || choiceExchange){
            yPosition += g2.getFontMetrics().getHeight();
            g2.drawString("Topo", xPosition, yPosition);
            if (commandNumber == 0){
                g2.drawString(">", xPosition - gp.tileSizeScaled, yPosition);
            }
            yPosition += g2.getFontMetrics().getHeight();
            g2.drawString("NAH!", xPosition, yPosition);
            if (commandNumber == 1){
                g2.drawString(">", xPosition - gp.tileSizeScaled, yPosition);
            }
        }
    }

    public void drawSubWindow(int xPosition, int yPosition, int width, int height){
        Color c = new Color(0,0,0,230);
        g2.setColor(c);
        g2.fillRoundRect(xPosition, yPosition, width, height, 20, 20);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(xPosition+5, yPosition+5, width-10, height-10, 25, 25);
    }

    public int getXForCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return (gp.screenWidth/2) - (length/2);
    }
}
