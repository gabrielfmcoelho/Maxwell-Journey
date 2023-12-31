package entity;

import main.GamePanel;
import main.KeyHandler;
import quest.Quest;
import item.Item;
import quest.Reward;
import world.City;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class Player extends Entity {
    public int powerThreshold;
    public int powerCurrent;
    public static Quest activeQuest;
    public int coins;
    List<Item> itens;

    GamePanel gp;
    City city;

    public Player(GamePanel gp, City city) {
        this.gp = gp;
        this.city = city;

        setDefaultValues();
        setDefaultPosition();
        getPlayerImage();
    }

    public void setDefaultValues() {
        movementSpeed = 1;
        direction = "down";
        this.powerThreshold = 7;
        this.powerCurrent = 0;
        this.activeQuest = null;
        this.coins = 3;
    }

    public void setDefaultPosition(){
        positionX = city.getxPosition() * gp.tileSizeScaled;
        positionY = city.getyPosition() * gp.tileSizeScaled;
    }

    public void updatePosition(City city){
        this.city = city;
        positionX = city.getxPosition() * gp.tileSizeScaled;
        positionY = city.getyPosition() * gp.tileSizeScaled;
    }

    public void getPlayerImage() {
        try{
            getUp1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            getUp2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            getDown1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            getDown2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            getLeft1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            getLeft2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            getRight1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            getRight2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void update() {
//        if (keyH.isPressedDown || keyH.isPressedUp) {
//            if(keyH.isPressedUp){
//                direction = "up";
//                positionY -= movementSpeed;
//            } else if(keyH.isPressedDown){
//                direction = "down";
//                positionY += movementSpeed;
//            }
//
//            spriteCounter++;
//            if(spriteCounter > 20){
//                isSpriteOne = !isSpriteOne;
//                spriteCounter = 0;
//            }
//        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if(isSpriteOne) {
                    image = getUp1;
                } else {
                    image = getUp2;
                }
                break;
            case "down":
                if(isSpriteOne){
                    image = getDown1;
                } else {
                    image = getDown2;
                }
                break;
        }
        g2.drawImage(image, positionX, positionY, gp.tileSizeScaled, gp.tileSizeScaled, null);
    }

    public boolean isAlive(){
        return this.powerCurrent <= this.powerThreshold;
    }

    public boolean hasCoins(){
        return this.coins > -1;
    }

    public void updateRewards(int coinReward, int powerThresholdReward){
        coins += coinReward;
        powerThreshold += powerThresholdReward;

    }
    public int getCoins(){
        return coins;
    }
    public int getPowerThreshold(){
        return powerThreshold;
    }

    public void acceptQuest(Quest quest){
        this.coins = this.coins + quest.coinsAid;
        this.activeQuest = quest;
    }

    public void completeQuest(){
        if (this.activeQuest != null && this.activeQuest.destination == this.city){
            Reward reward = this.activeQuest.completeQuest();
            this.coins = this.coins + reward.getCoinsReward();
            this.powerThreshold = this.powerThreshold + reward.getItemReward().getPowerInfluence();
            this.activeQuest = null;
        }
    }

    public void abandonQuest(){
        this.activeQuest.abandonQuest();
        this.activeQuest = null;
    }

    public void updateStats(int updatePower, int updateCoins) {
        powerCurrent += updatePower;
        if (powerCurrent < 0) {
            powerCurrent = 0;
        }
        coins -= updateCoins;
    }

    public void exchangeCoins(int coins, int powerThreshold) {
        this.coins -= coins;
        this.powerThreshold += powerThreshold;
    }
}
