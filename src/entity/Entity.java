package entity;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Entity {
	public int worldX, worldY;
	public int speed;
	
	public BufferedImage up0, up1, up2, up3, up4, up5, up6, up7, 
	down0, down1, down2, down3, down4, down5, down6, down7, 
	left0, left1, left2, left3, left4, left5, left6, left7,
	right0, right1, right2, right3, right4, right5, right6, right7;
	public String direction;
	public int spriteCounter = 0;
	public int spriteNum = 0;
	public Rectangle solidArea;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
}
