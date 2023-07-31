package entity;

import main.GamePanel;
import main.KeyHandeler;
import main.UtilityTool;
import object.OBJ_Door_Open;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Player extends Entity {
	//Attributes
	GamePanel gp;
	KeyHandeler keyH;
	public final int screenX;
	public final int screenY;
	public int hasKey = 0;
	
	//Constructor
	public Player(GamePanel gp, KeyHandeler keyH) {
		 this.gp = gp;
		 this.keyH = keyH;
		 
		 screenX = gp.screenWidth/2 - gp.tileSize/2;
		 screenY = gp.screenHeight/2 - gp.tileSize/2;
		 
		 solidArea = new Rectangle(16,41,29,20);
		 solidAreaDefaultX = solidArea.x;
		 solidAreaDefaultY = solidArea.y;

		 setDefaultValues();
		 getPlayerImage();
	}
	
	//methods
	public void setDefaultValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 4;
		direction = "down";
	}
	
	public void getPlayerImage() {
		
		up0 = setup("FH_Up");
		up1 = setup("FH_Up_1I");
		up2 = setup("FH_Up_1K");
		up3 = setup("FH_Up_1I");
		up4 = setup("FH_Up");
		up5 = setup("FH_Up_2I");
		up6 = setup("FH_Up_2K");
		up7 = setup("FH_Up_2I");
		
		down0 = setup("FH_Down");
		down1 = setup("FH_Down_1I");
		down2 = setup("FH_Down_1K");
		down3 = setup("FH_Down_1I");
		down4 = setup("FH_Down");
		down5 = setup("FH_Down_2I");
		down6 = setup("FH_Down_2K");
		down7 = setup("FH_Down_2I");
		
		left0 = setup("FH_Left_0");
		left1 = setup("FH_Left_1");
		left2 = setup("FH_Left_2");
		left3 = setup("FH_Left_3");
		left4 = setup("FH_Left_4");
		left5 = setup("FH_Left_5");
		left6 = setup("FH_Left_6");
		left7 = setup("FH_Left_7");
		
		right0 = setup("FH_Right_0");
		right1 = setup("FH_Right_1");
		right2 = setup("FH_Right_2");
		right3 = setup("FH_Right_3");
		right4 = setup("FH_Right_4");
		right5 = setup("FH_Right_5");
		right6 = setup("FH_Right_6");
		right7 = setup("FH_Right_7");
		
	}
	
	public BufferedImage setup(String imageName) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/fringehead/" + imageName + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}
	
	public void update() {
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
			if (keyH.upPressed == true) {
				direction = "up";
			} else if (keyH.downPressed == true) {
				direction = "down";
			}
			if (keyH.leftPressed == true) {
				direction = "left";
			} else if (keyH.rightPressed == true) {
				direction = "right";
			}
			
			//check tile collision
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			//check object collision
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			//can move if no collision
			if(collisionOn == false) {
				if(direction == "up") {
					worldY -= speed;
				} else if (direction == "down") {
					worldY += speed;
				}
				if(direction == "left") {
					worldX -= speed;
				} else if (direction == "right") {
					worldX += speed;
				}
			}
			
			spriteCounter++;
			if(spriteCounter > 5) {
				if (spriteNum >= 7) {
					spriteNum = 0;
				} else {
					spriteNum++;
				}
				spriteCounter = 0;
			}	
		} else {
			spriteNum = 0;
			spriteCounter = 0;
		}
	}
	
	public void pickUpObject(int i) {
		if(i != -1) {
			String objectName = gp.obj[i].name;
			
			switch(objectName) {
			case "key":
				gp.playSE(1);
				hasKey++;
				gp.obj[i] = null;
				gp.ui.showMessage("You got a key!");
				break;
			case "door":
				if(hasKey > 0) {
					gp.playSE(3);
					int x = gp.obj[i].worldX;
					int y = gp.obj[i].worldY;
					gp.obj[i] = new OBJ_Door_Open(gp);
					gp.obj[i].worldX = x;
					gp.obj[i].worldY = y;
					hasKey--;
					gp.ui.showMessage("You opened the door!");
				} else {
					gp.ui.showMessage("You need a key!");
				}
				break;
			case "spear":
				gp.playSE(2);
				speed += 2;
				gp.obj[i] = null;
				gp.ui.showMessage("Speed up!");
				break;
			case "chest":
				gp.ui.gameFinished = true;
				gp.stopMusic();
				gp.playSE(4);
				break;
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 0) {
				image = up0;				
			}else if (spriteNum == 1) {
				image = up1;
			}else if (spriteNum == 2) {
				image = up2;
			}else if (spriteNum == 3) {
				image = up3;
			}else if (spriteNum == 4) {
				image = up4;
			}else if (spriteNum == 4) {
				image = up4;
			}else if (spriteNum == 5) {
				image = up5;
			}else if (spriteNum == 6) {
				image = up6;
			}else {
				image = up7;
			}
			break;
		case "down":
			if(spriteNum == 0) {
				image = down0;				
			}else if (spriteNum == 1) {
				image = down1;
			}else if (spriteNum == 2) {
				image = down2;
			}else if (spriteNum == 3) {
				image = down3;
			}else if (spriteNum == 4) {
				image = down4;
			}else if (spriteNum == 4) {
				image = down4;
			}else if (spriteNum == 5) {
				image = down5;
			}else if (spriteNum == 6) {
				image = down6;
			}else {
				image = down7;
			}
			break;
		case "left":
			if(spriteNum == 0) {
				image = left0;				
			}else if (spriteNum == 1) {
				image = left1;
			}else if (spriteNum == 2) {
				image = left2;
			}else if (spriteNum == 3) {
				image = left3;
			}else if (spriteNum == 4) {
				image = left4;
			}else if (spriteNum == 4) {
				image = left4;
			}else if (spriteNum == 5) {
				image = left5;
			}else if (spriteNum == 6) {
				image = left6;
			}else {
				image = left7;
			}
			break;
		case "right":
			if(spriteNum == 0) {
				image = right0;				
			}else if (spriteNum == 1) {
				image = right1;
			}else if (spriteNum == 2) {
				image = right2;
			}else if (spriteNum == 3) {
				image = right3;
			}else if (spriteNum == 4) {
				image = right4;
			}else if (spriteNum == 4) {
				image = right4;
			}else if (spriteNum == 5) {
				image = right5;
			}else if (spriteNum == 6) {
				image = right6;
			}else {
				image = right7;
			}
			break;
		}
		
		g2.drawImage(image, screenX, screenY, null);
		
	}
}
