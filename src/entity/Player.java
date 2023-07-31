package entity;

import main.GamePanel;
import main.GamePanel.gs;
import main.KeyHandeler;
import object.OBJ_Key;
import object.OBJ_Shield_Wood;
import object.OBJ_Spear;
import object.OBJ_Starfish;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Entity {
	//Attributes
	KeyHandeler keyH;
	public final int screenX;
	public final int screenY;
	public boolean attackCanceled = false;
	public ArrayList<Entity> inventory = new ArrayList<>();
	public final int maxInventorySize = 20;
	
	//Constructor
	public Player(GamePanel gp, KeyHandeler keyH) {
		 super(gp);
		 this.keyH = keyH;
		 
		 screenX = gp.screenWidth/2 - gp.tileSize/2;
		 screenY = gp.screenHeight/2 - gp.tileSize/2;
		 
		 solidArea.setBounds(16,41,29,20);
		 solidAreaDefaultX = solidArea.x;
		 solidAreaDefaultY = solidArea.y;
		 
		 setDefaultValues();
		 getPlayerImage();
		 getPlayerAttackImage();
		 getThrowImage();
		 setItems();
	}
	
	//methods
	public void setDefaultValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 4;
		direction = "down";
		
		//player status
		level = 1;
		maxLife = 6;
		life = maxLife;
		maxMana = 4;
		mana = maxMana;
		strength = 1; //strength -> attack
		dexterity = 1; //dex -> defense
		exp = 0;
		nextLevelExp = 5;
		coin = 0;
		currentWeapon = new OBJ_Spear(gp);
		currentShield = new OBJ_Shield_Wood(gp);
		projectile = new OBJ_Starfish(gp);
		attack = getAttack();
		defense = getDefense();
	}
	
	public void setItems() {
		inventory.add(currentWeapon);
		inventory.add(currentShield);
		inventory.add(new OBJ_Key(gp));
	}
	
	public int getAttack() {
		attackArea = currentWeapon.attackArea;
		return strength * currentWeapon.attackValue;
	}
	
	public int getDefense() {
		return dexterity * currentShield.defenseValue;
	}
	
	public void getPlayerImage() {
		
		up0 = setup("/fringehead/FH_Up");
		up1 = setup("/fringehead/FH_Up_1I");
		up2 = setup("/fringehead/FH_Up_1K");
		up3 = setup("/fringehead/FH_Up_1I");
		up4 = setup("/fringehead/FH_Up");
		up5 = setup("/fringehead/FH_Up_2I");
		up6 = setup("/fringehead/FH_Up_2K");
		up7 = setup("/fringehead/FH_Up_2I");
		
		down0 = setup("/fringehead/FH_Down");
		down1 = setup("/fringehead/FH_Down_1I");
		down2 = setup("/fringehead/FH_Down_1K");
		down3 = setup("/fringehead/FH_Down_1I");
		down4 = setup("/fringehead/FH_Down");
		down5 = setup("/fringehead/FH_Down_2I");
		down6 = setup("/fringehead/FH_Down_2K");
		down7 = setup("/fringehead/FH_Down_2I");
		
		left0 = setup("/fringehead/FH_Left_0");
		left1 = setup("/fringehead/FH_Left_1");
		left2 = setup("/fringehead/FH_Left_2");
		left3 = setup("/fringehead/FH_Left_3");
		left4 = setup("/fringehead/FH_Left_4");
		left5 = setup("/fringehead/FH_Left_5");
		left6 = setup("/fringehead/FH_Left_6");
		left7 = setup("/fringehead/FH_Left_7");
		
		right0 = setup("/fringehead/FH_Right_0");
		right1 = setup("/fringehead/FH_Right_1");
		right2 = setup("/fringehead/FH_Right_2");
		right3 = setup("/fringehead/FH_Right_3");
		right4 = setup("/fringehead/FH_Right_4");
		right5 = setup("/fringehead/FH_Right_5");
		right6 = setup("/fringehead/FH_Right_6");
		right7 = setup("/fringehead/FH_Right_7");
		
	}
	
	public void getPlayerAttackImage() {
		if(currentWeapon.name.equals("spear")) {
			attackUp0 = setup("/fringehead/FH_Spear_Up_0", gp.tileSize, (int) (gp.tileSize*1.5));
			attackUp1 = setup("/fringehead/FH_Spear_Up_1", gp.tileSize, (int) (gp.tileSize*1.5));
			attackUp2 = setup("/fringehead/FH_Spear_Up_2", gp.tileSize, (int) (gp.tileSize*1.5));
			attackUp3 = setup("/fringehead/FH_Spear_Up_3", gp.tileSize, (int) (gp.tileSize*1.5));
			attackUp4 = setup("/fringehead/FH_Spear_Up_3", gp.tileSize, (int) (gp.tileSize*1.5));
			attackUp5 = setup("/fringehead/FH_Spear_Up_3", gp.tileSize, (int) (gp.tileSize*1.5));
			attackUp6 = setup("/fringehead/FH_Spear_Up_2", gp.tileSize, (int) (gp.tileSize*1.5));
			attackUp7 = setup("/fringehead/FH_Spear_Up_1", gp.tileSize, (int) (gp.tileSize*1.5));
			
			attackDown0 = setup("/fringehead/FH_Spear_Down_0", gp.tileSize, (int) (gp.tileSize*1.5));
			attackDown1 = setup("/fringehead/FH_Spear_Down_1", gp.tileSize, (int) (gp.tileSize*1.5));
			attackDown2 = setup("/fringehead/FH_Spear_Down_2", gp.tileSize, (int) (gp.tileSize*1.5));
			attackDown3 = setup("/fringehead/FH_Spear_Down_3", gp.tileSize, (int) (gp.tileSize*1.5));
			attackDown4 = setup("/fringehead/FH_Spear_Down_3", gp.tileSize, (int) (gp.tileSize*1.5));
			attackDown5 = setup("/fringehead/FH_Spear_Down_3", gp.tileSize, (int) (gp.tileSize*1.5));
			attackDown6 = setup("/fringehead/FH_Spear_Down_2", gp.tileSize, (int) (gp.tileSize*1.5));
			attackDown7 = setup("/fringehead/FH_Spear_Down_1", gp.tileSize, (int) (gp.tileSize*1.5));
			
			attackLeft0 = setup("/fringehead/FH_Spear_Left_0", (int) (gp.tileSize*1.5), gp.tileSize);
			attackLeft1 = setup("/fringehead/FH_Spear_Left_1", (int) (gp.tileSize*1.5), gp.tileSize);
			attackLeft2 = setup("/fringehead/FH_Spear_Left_2", (int) (gp.tileSize*1.5), gp.tileSize);
			attackLeft3 = setup("/fringehead/FH_Spear_Left_3", (int) (gp.tileSize*1.5), gp.tileSize);
			attackLeft4 = setup("/fringehead/FH_Spear_Left_4", (int) (gp.tileSize*1.5), gp.tileSize);
			attackLeft5 = setup("/fringehead/FH_Spear_Left_3", (int) (gp.tileSize*1.5), gp.tileSize);
			attackLeft6 = setup("/fringehead/FH_Spear_Left_2", (int) (gp.tileSize*1.5), gp.tileSize);
			attackLeft7 = setup("/fringehead/FH_Spear_Left_1", (int) (gp.tileSize*1.5), gp.tileSize);
			
			attackRight0 = setup("/fringehead/FH_Spear_Right_0", (int) (gp.tileSize*1.5), gp.tileSize);
			attackRight1 = setup("/fringehead/FH_Spear_Right_1", (int) (gp.tileSize*1.5), gp.tileSize);
			attackRight2 = setup("/fringehead/FH_Spear_Right_2", (int) (gp.tileSize*1.5), gp.tileSize);
			attackRight3 = setup("/fringehead/FH_Spear_Right_3", (int) (gp.tileSize*1.5), gp.tileSize);
			attackRight4 = setup("/fringehead/FH_Spear_Right_4", (int) (gp.tileSize*1.5), gp.tileSize);
			attackRight5 = setup("/fringehead/FH_Spear_Right_3", (int) (gp.tileSize*1.5), gp.tileSize);
			attackRight6 = setup("/fringehead/FH_Spear_Right_2", (int) (gp.tileSize*1.5), gp.tileSize);
			attackRight7 = setup("/fringehead/FH_Spear_Right_1", (int) (gp.tileSize*1.5), gp.tileSize);
		}
		
		if(currentWeapon.name.equals("axe")) {
			attackUp0 = setup("/fringehead/FH_Axe_Up_0", gp.tileSize, (int) (gp.tileSize*1.5));
			attackUp1 = setup("/fringehead/FH_Axe_Up_1", gp.tileSize, (int) (gp.tileSize*1.5));
			attackUp2 = setup("/fringehead/FH_Axe_Up_2", gp.tileSize, (int) (gp.tileSize*1.5));
			attackUp3 = setup("/fringehead/FH_Axe_Up_3", gp.tileSize, (int) (gp.tileSize*1.5));
			attackUp4 = setup("/fringehead/FH_Axe_Up_3", gp.tileSize, (int) (gp.tileSize*1.5));
			attackUp5 = setup("/fringehead/FH_Axe_Up_3", gp.tileSize, (int) (gp.tileSize*1.5));
			attackUp6 = setup("/fringehead/FH_Axe_Up_2", gp.tileSize, (int) (gp.tileSize*1.5));
			attackUp7 = setup("/fringehead/FH_Axe_Up_1", gp.tileSize, (int) (gp.tileSize*1.5));
			
			attackDown0 = setup("/fringehead/FH_Axe_Down_0", gp.tileSize, (int) (gp.tileSize*1.5));
			attackDown1 = setup("/fringehead/FH_Axe_Down_1", gp.tileSize, (int) (gp.tileSize*1.5));
			attackDown2 = setup("/fringehead/FH_Axe_Down_2", gp.tileSize, (int) (gp.tileSize*1.5));
			attackDown3 = setup("/fringehead/FH_Axe_Down_3", gp.tileSize, (int) (gp.tileSize*1.5));
			attackDown4 = setup("/fringehead/FH_Axe_Down_3", gp.tileSize, (int) (gp.tileSize*1.5));
			attackDown5 = setup("/fringehead/FH_Axe_Down_3", gp.tileSize, (int) (gp.tileSize*1.5));
			attackDown6 = setup("/fringehead/FH_Axe_Down_2", gp.tileSize, (int) (gp.tileSize*1.5));
			attackDown7 = setup("/fringehead/FH_Axe_Down_1", gp.tileSize, (int) (gp.tileSize*1.5));
			
			attackLeft0 = setup("/fringehead/FH_Axe_Left_0", (int) (gp.tileSize*1.5), gp.tileSize);
			attackLeft1 = setup("/fringehead/FH_Axe_Left_1", (int) (gp.tileSize*1.5), gp.tileSize);
			attackLeft2 = setup("/fringehead/FH_Axe_Left_2", (int) (gp.tileSize*1.5), gp.tileSize);
			attackLeft3 = setup("/fringehead/FH_Axe_Left_3", (int) (gp.tileSize*1.5), gp.tileSize);
			attackLeft4 = setup("/fringehead/FH_Axe_Left_4", (int) (gp.tileSize*1.5), gp.tileSize);
			attackLeft5 = setup("/fringehead/FH_Axe_Left_3", (int) (gp.tileSize*1.5), gp.tileSize);
			attackLeft6 = setup("/fringehead/FH_Axe_Left_2", (int) (gp.tileSize*1.5), gp.tileSize);
			attackLeft7 = setup("/fringehead/FH_Axe_Left_1", (int) (gp.tileSize*1.5), gp.tileSize);
			
			attackRight0 = setup("/fringehead/FH_Axe_Right_0", (int) (gp.tileSize*1.5), gp.tileSize);
			attackRight1 = setup("/fringehead/FH_Axe_Right_1", (int) (gp.tileSize*1.5), gp.tileSize);
			attackRight2 = setup("/fringehead/FH_Axe_Right_2", (int) (gp.tileSize*1.5), gp.tileSize);
			attackRight3 = setup("/fringehead/FH_Axe_Right_3", (int) (gp.tileSize*1.5), gp.tileSize);
			attackRight4 = setup("/fringehead/FH_Axe_Right_4", (int) (gp.tileSize*1.5), gp.tileSize);
			attackRight5 = setup("/fringehead/FH_Axe_Right_3", (int) (gp.tileSize*1.5), gp.tileSize);
			attackRight6 = setup("/fringehead/FH_Axe_Right_2", (int) (gp.tileSize*1.5), gp.tileSize);
			attackRight7 = setup("/fringehead/FH_Axe_Right_1", (int) (gp.tileSize*1.5), gp.tileSize);
		}
		
		
	}
	
	public void getThrowImage() {
		throwUp0 = setup("/fringehead/FH_Throw_Up_0");
		throwUp1 = setup("/fringehead/FH_Throw_Up_1");
		throwUp2 = setup("/fringehead/FH_Throw_Up_2");
		throwUp3 = setup("/fringehead/FH_Throw_Up_3");
		throwUp4 = setup("/fringehead/FH_Throw_Up_3");
		throwUp5 = setup("/fringehead/FH_Throw_Up_3");
		throwUp6 = setup("/fringehead/FH_Throw_Up_2");
		throwUp7 = setup("/fringehead/FH_Throw_Up_1");
		
		throwDown0 = setup("/fringehead/FH_Throw_Down_0");
		throwDown1 = setup("/fringehead/FH_Throw_Down_1");
		throwDown2 = setup("/fringehead/FH_Throw_Down_2");
		throwDown3 = setup("/fringehead/FH_Throw_Down_3");
		throwDown4 = setup("/fringehead/FH_Throw_Down_3");
		throwDown5 = setup("/fringehead/FH_Throw_Down_3");
		throwDown6 = setup("/fringehead/FH_Throw_Down_2");
		throwDown7 = setup("/fringehead/FH_Throw_Down_1");
		
		throwLeft0 = setup("/fringehead/FH_Throw_Left_0");
		throwLeft1 = setup("/fringehead/FH_Throw_Left_1");
		throwLeft2 = setup("/fringehead/FH_Throw_Left_2");
		throwLeft3 = setup("/fringehead/FH_Throw_Left_3");
		throwLeft4 = setup("/fringehead/FH_Throw_Left_4");
		throwLeft5 = setup("/fringehead/FH_Throw_Left_3");
		throwLeft6 = setup("/fringehead/FH_Throw_Left_2");
		throwLeft7 = setup("/fringehead/FH_Throw_Left_1");
		
		throwRight0 = setup("/fringehead/FH_Throw_Right_0");
		throwRight1 = setup("/fringehead/FH_Throw_Right_1");
		throwRight2 = setup("/fringehead/FH_Throw_Right_2");
		throwRight3 = setup("/fringehead/FH_Throw_Right_3");
		throwRight4 = setup("/fringehead/FH_Throw_Right_4");
		throwRight5 = setup("/fringehead/FH_Throw_Right_3");
		throwRight6 = setup("/fringehead/FH_Throw_Right_2");
		throwRight7 = setup("/fringehead/FH_Throw_Right_1");
	}
	
	public void update() {
		if(attacking) {
			attacking();
		} else if (throwing ) {
			throwing();
		}
		else if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed) {
			if (keyH.upPressed == true) {direction = "up";} 
			else if (keyH.downPressed == true) {direction = "down";}
			else if (keyH.leftPressed == true) {direction = "left";}
			else if (keyH.rightPressed == true) {direction = "right";}
			
			//check tile collision
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			//check object collision
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			//check npc collision
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			
			//check monster collision
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			contactMonster(monsterIndex);
			
			//check event
			gp.eHandler.checkEvent();
			
			//check interactive tile
			gp.cChecker.checkEntity(this, gp.iTile);
						
			//can move if no collision
			if(!collisionOn && !keyH.enterPressed) {
				if(direction == "up") {worldY -= speed;}
				else if (direction == "down") {worldY += speed;}
				else if(direction == "left") {worldX -= speed;}
				else if (direction == "right") {worldX += speed;}
			}
			
			if(keyH.enterPressed && !attackCanceled) {
				attacking = true;
				spriteCounter = 0;
			}
			
			attackCanceled = false;
			gp.keyH.enterPressed = false;
			
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
		
		if(gp.keyH.shotKeyPressed && !projectile.alive && shotAvaliableCounter == 30 
				&& projectile.haveResource(this)) {
			projectile.set(worldX, worldY, direction, true, this);
			projectile.subtractResource(this);
			gp.projectileList.add(projectile);
			shotAvaliableCounter = 0;
			throwing = true;
			//gp.playSE(10);
		}
		
		if(invincible) {
			invincibleCounter++;
			if(invincibleCounter > gp.FPS) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
		
		if(shotAvaliableCounter < 30) {
			shotAvaliableCounter++;
		}
		
		if(life > maxLife) {
			life = maxLife;
		}
		
		if(mana > maxMana) {
			mana = maxMana;
		}
	}
	
	public void attacking() {
		attackSpriteCounter++;
		
		if(attackSpriteCounter > 3) {
			if (attackSpriteNum == 4) {
				int currentWorldX = worldX;
				int currentWorldY = worldY;
				int solidAreaWidth = solidArea.width;
				int solidAreaHeight = solidArea.height;
				
				switch (direction) {
				case "up": worldY -= attackArea.height; break;
				//case "down": worldY += attackArea.height; break;
				case "left": worldX -= attackArea.width; break;
				case "right": worldX += attackArea.width; break;
				}
				
				solidArea.width = attackArea.width;
				solidArea.height = attackArea.height;
				
				int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
				damageMonster(monsterIndex, attack);
				
				int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
				damageInteractiveTile(iTileIndex);
				
				worldX = currentWorldX;
				worldY = currentWorldY;
				solidArea.width = solidAreaWidth;
				solidArea.height = solidAreaHeight;
			}
			
			if (attackSpriteNum >= 7) {
				attackSpriteNum = 0;
				attacking = false;
			} else {
				attackSpriteNum++;
			}
			attackSpriteCounter = 0;
		}
	}
	
	public void throwing() {
		attackSpriteCounter++;
		
		if(attackSpriteCounter > 3) {
			if (attackSpriteNum >= 7) {
				attackSpriteNum = 0;
				throwing = false;
			} else {
				attackSpriteNum++;
			}
			
			attackSpriteCounter = 0;
		}
	}
	
	public void pickUpObject(int i) {
		if(i != -1) {
			//pickup-only items
			if(gp.obj[i].type == type.PICKUP) {
				gp.obj[i].use(this);
				gp.obj[i] = null;
			}
			
			//inventory items
			else {
			String text;
			
			if(inventory.size() != maxInventorySize) {
				inventory.add(gp.obj[i]);
				gp.playSE(1);
				text = "Got a " + gp.obj[i].name + "!";
			} else {
				text = "Your inventory is full.";
			}
			
			gp.ui.addMessage(text);
			gp.obj[i] = null;
			}
		}
	}
	
	public void interactNPC(int i) {
		if(gp.keyH.enterPressed) {
			if(i != -1) {
				attackCanceled = true;
				gp.gameState = gs.DIALOGUE;
				gp.npc[i].speak();
			}
		}
	}
	
	public void contactMonster(int i) {
		if(i != -1) {
			if(!invincible && !gp.monster[i].dying) {
				int damage = gp.monster[i].attack - defense;
				if (damage < 1) {
					damage = 1;
				}
				life -= damage;
				invincible = true;
				gp.playSE(6);
			}
		}
	}
	
	public void damageMonster(int i, int attack) {
		if (i != -1) {
			if(!gp.monster[i].invincible) {
				gp.playSE(5);
				int damage = attack - gp.monster[i].defense;
				if (damage < 1) {
					damage = 1;
				}
				gp.monster[i].life -= damage;
				gp.ui.addMessage(damage + " damage!");
				gp.monster[i].invincible = true;
				gp.monster[i].damageReaction();
				
				if(gp.monster[i].life <= 0) {
					gp.monster[i].dying = true;
					
					gp.ui.addMessage("Killed the " + gp.monster[i].name + "!");
					gp.ui.addMessage("Exp +" + gp.monster[i].exp);
					
					exp += gp.monster[i].exp;
					
					checkLevelUp();
				}
			}
		}
	}
	
	public void damageInteractiveTile(int i) {
		if(i != -1 && gp.iTile[i].destructable && gp.iTile[i].isCorrectItem(this) && !gp.iTile[i].invincible) {
			gp.iTile[i].playSE();
			gp.iTile[i].life--;
			gp.iTile[i].invincible = true;
			
			generateParticle(gp.iTile[i], gp.iTile[i]);
			
			if(gp.iTile[i].life <= 0) {
				gp.iTile[i] = gp.iTile[i].getDestroyedForm();
			}
		}
	}
	
	public void checkLevelUp() {
		if (exp >= nextLevelExp) {
			level++;
			exp = exp-nextLevelExp;
			nextLevelExp = nextLevelExp*2;
			maxLife += 2;
			strength++;
			dexterity++;
			attack = getAttack();
			defense = getDefense();
			
			gp.playSE(8);
			gp.gameState = gs.DIALOGUE;
			gp.ui.currentDialogue = "You are now level " + level + "!/You feel stronger.";
		}
	}
	
	public void selectItem() {
		int itemIndex = gp.ui.getItemIndexOnSlot();
		
		if (itemIndex < inventory.size()) {
			Entity selectedItem = inventory.get(itemIndex);
			if(selectedItem.type == type.WEAPON) {
				currentWeapon = selectedItem;
				attack = getAttack();
				getPlayerAttackImage();
			} else if (selectedItem.type == type.SHIELD) {
				currentShield = selectedItem;
				defense = getDefense();
			} else if (selectedItem.type == type.CONSUMABLE) {
				selectedItem.use(this);
				inventory.remove(itemIndex);
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		int tempScreenX = screenX;
		int tempScreenY = screenY;
		
		switch(direction) {
		case "up":
			if (attacking) {
				tempScreenY = (int) (screenY - gp.tileSize*0.5);
				if(attackSpriteNum == 0) {image = attackUp0;}
				else if (attackSpriteNum == 1) {image = attackUp1;}
				else if (attackSpriteNum == 2) {image = attackUp2;}
				else if (attackSpriteNum == 3) {image = attackUp3;}
				else if (attackSpriteNum == 4) {image = attackUp4;}
				else if (attackSpriteNum == 5) {image = attackUp5;}
				else if (attackSpriteNum == 6) {image = attackUp6;}
				else {image = attackUp7;}
			} else if (throwing) {
				if(attackSpriteNum == 0) {image = throwUp0;}
				else if (attackSpriteNum == 1) {image = throwUp1;}
				else if (attackSpriteNum == 2) {image = throwUp2;}
				else if (attackSpriteNum == 3) {image = throwUp3;}
				else if (attackSpriteNum == 4) {image = throwUp4;}
				else if (attackSpriteNum == 5) {image = throwUp5;}
				else if (attackSpriteNum == 6) {image = throwUp6;}
				else {image = throwUp7;}
			}else {
				if(spriteNum == 0) {image = up0;}
				else if (spriteNum == 1) {image = up1;}
				else if (spriteNum == 2) {image = up2;}
				else if (spriteNum == 3) {image = up3;}
				else if (spriteNum == 4) {image = up4;}
				else if (spriteNum == 5) {image = up5;}
				else if (spriteNum == 6) {image = up6;}
				else {image = up7;}
			}
			break;
		case "down":
			if (attacking) {
				if(attackSpriteNum == 0) {image = attackDown0;}
				else if (attackSpriteNum == 1) {image = attackDown1;}
				else if (attackSpriteNum == 2) {image = attackDown2;}
				else if (attackSpriteNum == 3) {image = attackDown3;}
				else if (attackSpriteNum == 4) {image = attackDown4;}
				else if (attackSpriteNum == 5) {image = attackDown5;}
				else if (attackSpriteNum == 6) {image = attackDown6;}
				else {image = attackDown7;}
			} else if (throwing) {
				if(attackSpriteNum == 0) {image = throwDown0;}
				else if (attackSpriteNum == 1) {image = throwDown1;}
				else if (attackSpriteNum == 2) {image = throwDown2;}
				else if (attackSpriteNum == 3) {image = throwDown3;}
				else if (attackSpriteNum == 4) {image = throwDown4;}
				else if (attackSpriteNum == 5) {image = throwDown5;}
				else if (attackSpriteNum == 6) {image = throwDown6;}
				else {image = throwDown7;}
			} else {
				if(spriteNum == 0) {image = down0;}
				else if (spriteNum == 1) {image = down1;}
				else if (spriteNum == 2) {image = down2;}
				else if (spriteNum == 3) {image = down3;}
				else if (spriteNum == 4) {image = down4;}
				else if (spriteNum == 5) {image = down5;}
				else if (spriteNum == 6) {image = down6;}
				else {image = down7;}
			}
			break;
		case "left":
			if (attacking) {
				tempScreenX = (int) (screenX - gp.tileSize*0.5 - 5);
				if(attackSpriteNum == 0) {image = attackLeft0;}
				else if (attackSpriteNum == 1) {image = attackLeft1;}
				else if (attackSpriteNum == 2) {image = attackLeft2;}
				else if (attackSpriteNum == 3) {image = attackLeft3;}
				else if (attackSpriteNum == 4) {image = attackLeft4;}
				else if (attackSpriteNum == 5) {image = attackLeft5;}
				else if (attackSpriteNum == 6) {image = attackLeft6;}
				else {image = attackLeft7;}
			} else if (throwing) {
				tempScreenX = screenX - 6;
				if(attackSpriteNum == 0) {image = throwLeft0;}
				else if (attackSpriteNum == 1) {image = throwLeft1;}
				else if (attackSpriteNum == 2) {image = throwLeft2;}
				else if (attackSpriteNum == 3) {image = throwLeft3;}
				else if (attackSpriteNum == 4) {image = throwLeft4;}
				else if (attackSpriteNum == 5) {image = throwLeft5;}
				else if (attackSpriteNum == 6) {image = throwLeft6;}
				else {image = throwLeft7;}
			} else {
				if(spriteNum == 0) {image = left0;}
				else if (spriteNum == 1) {image = left1;}
				else if (spriteNum == 2) {image = left2;}
				else if (spriteNum == 3) {image = left3;}
				else if (spriteNum == 4) {image = left4;}
				else if (spriteNum == 5) {image = left5;}
				else if (spriteNum == 6) {image = left6;}
				else {image = left7;}
			}
			break;
		case "right":
			if (attacking) {
				if(spriteNum == 0) {image = attackRight0;}
				else if (attackSpriteNum == 1) {image = attackRight1;}
				else if (attackSpriteNum == 2) {image = attackRight2;}
				else if (attackSpriteNum == 3) {image = attackRight3;}
				else if (attackSpriteNum == 4) {image = attackRight4;}
				else if (attackSpriteNum == 5) {image = attackRight5;}
				else if (attackSpriteNum == 6) {image = attackRight6;}
				else {image = attackRight7;}
			} else if (throwing) {
				if(spriteNum == 0) {image = throwRight0;}
				else if (attackSpriteNum == 1) {image = throwRight1;}
				else if (attackSpriteNum == 2) {image = throwRight2;}
				else if (attackSpriteNum == 3) {image = throwRight3;}
				else if (attackSpriteNum == 4) {image = throwRight4;}
				else if (attackSpriteNum == 5) {image = throwRight5;}
				else if (attackSpriteNum == 6) {image = throwRight6;}
				else {image = throwRight7;}
			} else {
				if(spriteNum == 0) {image = right0;}
				else if (spriteNum == 1) {image = right1;}
				else if (spriteNum == 2) {image = right2;}
				else if (spriteNum == 3) {image = right3;}
				else if (spriteNum == 4) {image = right4;}
				else if (spriteNum == 5) {image = right5;}
				else if (spriteNum == 6) {image = right6;}
				else {image = right7;}
			}
			break;
		}
		
		if (invincible) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
		}
		
		g2.drawImage(image, tempScreenX, tempScreenY, null);
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

		
	}
}
