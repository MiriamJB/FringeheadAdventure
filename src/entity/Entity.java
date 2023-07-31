package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Entity {
	GamePanel gp;
	
	public BufferedImage up0, up1, up2, up3, up4, up5, up6, up7, 
		down0, down1, down2, down3, down4, down5, down6, down7, 
		left0, left1, left2, left3, left4, left5, left6, left7,
		right0, right1, right2, right3, right4, right5, right6, right7;
	public BufferedImage attackUp0, attackUp1, attackUp2, attackUp3, attackUp4, attackUp5, attackUp6, attackUp7,
		attackDown0, attackDown1, attackDown2, attackDown3, attackDown4, attackDown5, attackDown6, attackDown7,
		attackLeft0, attackLeft1, attackLeft2, attackLeft3, attackLeft4, attackLeft5, attackLeft6, attackLeft7,
		attackRight0, attackRight1, attackRight2, attackRight3, attackRight4, attackRight5, attackRight6, attackRight7;
	public BufferedImage throwUp0, throwUp1, throwUp2, throwUp3, throwUp4, throwUp5, throwUp6, throwUp7,
		throwDown0, throwDown1, throwDown2, throwDown3, throwDown4, throwDown5, throwDown6, throwDown7,
		throwLeft0, throwLeft1, throwLeft2, throwLeft3, throwLeft4, throwLeft5, throwLeft6, throwLeft7,
		throwRight0, throwRight1, throwRight2, throwRight3, throwRight4, throwRight5, throwRight6, throwRight7;
	public BufferedImage image, image2, image3;
	public Rectangle solidArea = new Rectangle(0,0,60,60);
	public Rectangle attackArea = new Rectangle();
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collision = false;
	String dialogues[] = new String[20];
	
	//state
	public int worldX, worldY;
	public String direction = "down";
	public int spriteNum = 0;
	public int attackSpriteNum = 0;
	int dialogueIndex = 0;
	public boolean collisionOn = false;
	public boolean invincible = false;
	boolean attacking = false;
	boolean throwing = false;
	public boolean alive = true;
	public boolean dying = false;
	boolean hpBarOn = false;

	//counters
	public int spriteCounter = 0;
	public int attackSpriteCounter = 0;
	public int actionLockCounter = 0;
	public int invincibleCounter = 0;
	public int shotAvaliableCounter = 0;
	int dyingCounter = 0;
	int dyingLockCounter = 0;
	float alpha = 0;
	int hpBarCounter = 0;
	
	//character attributes
	public type type;
	public String name;
	public int speed;
	public int maxLife;
	public int life;
	public int maxMana;
	public int mana;
	public int level;
	public int strength;
	public int dexterity;
	public int attack;
	public int defense;
	public int exp;
	public int nextLevelExp;
	public int coin;
	public Entity currentWeapon;
	public Entity currentShield;
	public Projectile projectile;
	
	//item attributes
	public int value;
	public int attackValue;
	public int defenseValue;
	public String description = "";
	public int useCost;
	
	//particle
	public Color particleColor;
	public int particleSize;
	public int particleSpeed;
	public int particleMaxLife;
	
	public enum type {
		PLAYER, NPC, MONSTER, WEAPON, SHIELD, CONSUMABLE, PICKUP, LARGE_TILE;
	}

	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setAction() {}
	
	public void damageReaction() {}
	
	public void speak() {
		if(dialogues[dialogueIndex] == null) {
			dialogueIndex = 0;
		}
		gp.ui.currentDialogue = dialogues[dialogueIndex];
		dialogueIndex++;
		
		switch(gp.player.direction) {
		case "up": direction = "down"; break;
		case "down": direction = "up"; break;
		case "left": direction = "right"; break;
		case "right": direction = "left"; break;
		}
	}
	
	public void use(Entity entity) {}
	
	public void checkDrop() {}
	
	public void dropItem(Entity droppedItem) {
		for(int i = 0; i < gp.obj.length; i++) {
			if(gp.obj[i] == null) {
				gp.obj[i] = droppedItem;
				gp.obj[i].worldX = worldX;
				gp.obj[i].worldY = worldY;
				break;
			}
		}
	}
	
	//fix this
	public void generateParticle(Entity generator, Entity target) {
		Color color = generator.particleColor;
		int size = generator.particleSize;
		int speed = generator.particleSpeed;
		int maxLife = generator.particleMaxLife;
		
		Particle p1 = new Particle(gp, target, color, size, speed, maxLife, -2, -1);
		gp.particleList.add(p1);
		Particle p2 = new Particle(gp, target, color, size, speed, maxLife, 2, -1);
		gp.particleList.add(p2);
		Particle p3 = new Particle(gp, target, color, size, speed, maxLife, -2, 1);
		gp.particleList.add(p3);
		Particle p4 = new Particle(gp, target, color, size, speed, maxLife, 2, 1);
		gp.particleList.add(p4);
	}
	
	public void update() {
		setAction();
		
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);
		gp.cChecker.checkEntity(this, gp.iTile);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		
		if(this.type == type.MONSTER && contactPlayer) {
			damagePlayer(attack);
		}
		
		
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
		
		if(invincible) {
			invincibleCounter++;
			if(invincibleCounter > gp.FPS/2) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
		
		if(shotAvaliableCounter < 30) {
			shotAvaliableCounter++;
		}
	}
	
	public void damagePlayer(int attack) {
		if(!gp.player.invincible) {
			int damage = attack - gp.player.defense;
			if (damage < 1) {
				damage = 1;
			}
			gp.player.life -= damage;
			gp.player.invincible = true;
			gp.playSE(6);
		}
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
				worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
				worldY + gp.tileSize*2 > gp.player.worldY - gp.player.screenY && 
				worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {			
			switch(direction) {
			case "up":
				if(spriteNum == 0) {image = up0;}
				else if (spriteNum == 1) {image = up1;}
				else if (spriteNum == 2) {image = up2;}
				else if (spriteNum == 3) {image = up3;}
				else if (spriteNum == 4) {image = up4;}
				else if (spriteNum == 4) {image = up4;}
				else if (spriteNum == 5) {image = up5;}
				else if (spriteNum == 6) {image = up6;}
				else {image = up7;}
				break;
			case "down":
				if(spriteNum == 0) {image = down0;}
				else if (spriteNum == 1) {image = down1;}
				else if (spriteNum == 2) {image = down2;}
				else if (spriteNum == 3) {image = down3;}
				else if (spriteNum == 4) {image = down4;}
				else if (spriteNum == 4) {image = down4;}
				else if (spriteNum == 5) {image = down5;}
				else if (spriteNum == 6) {image = down6;}
				else {image = down7;}
				break;
			case "left":
				if(spriteNum == 0) {image = left0;}
				else if (spriteNum == 1) {image = left1;}
				else if (spriteNum == 2) {image = left2;}
				else if (spriteNum == 3) {image = left3;}
				else if (spriteNum == 4) {image = left4;}
				else if (spriteNum == 4) {image = left4;}
				else if (spriteNum == 5) {image = left5;}
				else if (spriteNum == 6) {image = left6;}
				else {image = left7;}
				break;
			case "right":
				if(spriteNum == 0) {image = right0;}
				else if (spriteNum == 1) {image = right1;}
				else if (spriteNum == 2) {image = right2;}
				else if (spriteNum == 3) {image = right3;}
				else if (spriteNum == 4) {image = right4;}
				else if (spriteNum == 4) {image = right4;}
				else if (spriteNum == 5) {image = right5;}
				else if (spriteNum == 6) {image = right6;}
				else {image = right7;}
				break;
			}
			
			if (type == type.MONSTER && hpBarOn) {
				double hpBarValue = (double) gp.tileSize/2 * life/maxLife;
				
				g2.setColor(new Color(35, 35, 35, 210));
				g2.fillRoundRect(screenX+gp.tileSize/4, screenY, gp.tileSize/2, 5, 1, 1);
				
				g2.setColor(new Color(255, 80, 0));
				g2.fillRoundRect(screenX+gp.tileSize/4, screenY, (int)hpBarValue, 5, 1, 1);
				
				g2.setColor(new Color(35, 35, 35));
				g2.drawRoundRect(screenX+gp.tileSize/4, screenY, gp.tileSize/2, 5, 1, 1);
				
				hpBarCounter++;
				if(hpBarCounter > gp.FPS*10) {
					hpBarCounter = 0;
					hpBarOn = false;
				}
			}
			
			if (invincible && type == type.MONSTER) {
				hpBarOn = true;
				hpBarCounter = 0;
				changeAlpha(g2, 0.6F);
			}
			
			if (dying) {
				dyingAnimation(g2);
			}
			
			g2.drawImage(image, screenX, screenY, null);
			
			changeAlpha(g2, 1);

		}
	}
	
	public void dyingAnimation(Graphics2D g2) {
		dyingLockCounter++;
		
		if(dyingLockCounter >= 10) {
			if (dyingCounter >= 3) {
				dyingCounter = 0;
				alive = false;
			} else {
				dyingCounter++;
				if(alpha == 0) {alpha = 0.7F;} 
				else {alpha = 0;}
			}
			dyingLockCounter = 0;
		}
		
		changeAlpha(g2, alpha);
	}
	
	public void changeAlpha(Graphics2D g2, float alphaValue) {
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
	}
	
	public BufferedImage setup(String imagePath) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}
	
	public BufferedImage setup(String imagePath, int width, int height) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, width, height);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}
}
