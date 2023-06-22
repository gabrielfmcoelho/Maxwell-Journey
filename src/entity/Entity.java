package entity;

import java.awt.image.BufferedImage;

public class Entity {
    public int positionX, positionY;
    public int movementSpeed;

    public BufferedImage getUp1, getUp2, getDown1, getDown2, getLeft1, getLeft2, getRight1, getRight2;
    public int spriteCounter = 0;
    public boolean isSpriteOne = true;
    public String direction;
}
