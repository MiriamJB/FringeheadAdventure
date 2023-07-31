package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import entity.Entity;
import main.GamePanel.gs;
import object.OBJ_Heart;
import object.OBJ_Manafish;

public class UI {
	GamePanel gp;
	Graphics2D g2;
	Font maruMonica;
	BufferedImage heart_full, heart_half, heart_empty, manafish_full, manafish_empty;
	public boolean messageOn = false;
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	public boolean gameFinished = false;
	public String currentDialogue = "";
	public int commandNum = 0;
	public int slotCol = 0;
	public int slotRow = 0;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		try {
			InputStream is = getClass().getResourceAsStream("/fonts/maruMonica.ttf");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//create HUD object
		Entity heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_empty = heart.image3;
		Entity manafish = new OBJ_Manafish(gp);
		manafish_full = manafish.image;
		manafish_empty = manafish.image2;
	}
	
	public void addMessage(String text) {
		message.add(text);
		messageCounter.add(0);
		
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		g2.setFont(maruMonica);
		g2.setColor(Color.white);
		
		if(gp.gameState == gs.TITLE) {
			drawTitleScreen();
		}else if(gp.gameState == gs.PLAY) {
			drawPlayerLife();
			drawMessage();
		} else if(gp.gameState == gs.PAUSE) {
			drawPlayerLife();
			drawPauseScreen();
		} else if(gp.gameState == gs.DIALOGUE) {
			drawPlayerLife();
			drawDialogueScreen();
		} else if (gp.gameState == gs.CHARACTER) {
			drawCharacterScreen();
			drawInventory();
		}
	}
	
	public void drawPlayerLife() {
		int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		int i = 0;

		//draw max life
		while (i < gp.player.maxLife/2) {
			g2.drawImage(heart_empty, x, y, null);
			i++;
			x += gp.tileSize;
		}
		
		x = gp.tileSize/2;
		y = gp.tileSize/2;
		i = 0;
		
		//draw current life
		while (i < gp.player.life) {
			g2.drawImage(heart_half, x, y, null);
			i++;
			if (i < gp.player.life) {
				g2.drawImage(heart_full, x, y, null);
			}
			i++;
			x += gp.tileSize;
		}
		
		//draw manafish
		x = gp.tileSize/2;
		y = (int) (gp.tileSize*1.5);
		i = 0;
		
		while (i < gp.player.maxMana) {
			g2.drawImage(manafish_empty, x, y, null);
			i++;
			x += 43;
		}
		
		x = gp.tileSize/2;
		y = (int) (gp.tileSize*1.5);
		i = 0;
		
		while (i < gp.player.mana) {
			g2.drawImage(manafish_full, x, y, null);
			i++;
			x += 43;
		}
	}
	
	public void drawMessage() {
		int messageX = gp.tileSize;
		int messageY = gp.screenHeight - gp.tileSize;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
		
		for(int i = 0; i < message.size(); i++) {
			if(message.get(i) != null) {
				g2.setColor(Color.black);
				g2.drawString(message.get(i), messageX+2, messageY+2);
				
				g2.setColor(Color.white);
				g2.drawString(message.get(i), messageX, messageY);
				
				int counter = messageCounter.get(i) + 1; //these 2 lines act like messageCounter++
				messageCounter.set(i, counter);
				messageY -= 50;
				
				if(messageCounter.get(i) > gp.FPS*3) {
					message.remove(i);
					messageCounter.remove(i);
				}
			}
		}
	}
	
	public void drawTitleScreen() {
		//background color
		g2.setColor(Color.black);
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		//title name
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
		String text = "Fringehead Adventure";
		int x = getXforCenteredText(text);
		int y = gp.tileSize*3;
		
		g2.setColor(Color.gray);
		g2.drawString(text, x+4, y+4);
		
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		//image
		x = (int) (gp.screenWidth/2 - gp.tileSize*1.5);
		y += gp.tileSize;
		g2.drawImage(gp.player.down0, x, y, gp.tileSize*3, gp.tileSize*3, null);
		
		//menu
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
		
		text = "NEW GAME";
		x = getXforCenteredText(text);
		y += gp.tileSize*4.5;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x - gp.tileSize, y);
		}
		
		text = "LOAD GAME";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x - gp.tileSize, y);
		}
		
		text = "QUIT";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 2) {
			g2.drawString(">", x - gp.tileSize, y);
		}
	}
	
	public void drawPauseScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,100F));
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight/2 - gp.tileSize/2;
		
		g2.drawString(text, x, y);
	}
	
	public void drawDialogueScreen() {
		int x = gp.tileSize*2;
		int y = gp.tileSize/2;
		int width = gp.screenWidth - gp.tileSize*4;
		int height = gp.tileSize*3;
		drawSubWindow(x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));
		x += gp.tileSize;
		y += gp.tileSize;
		
		for(String line : currentDialogue.split("/")) {
			g2.drawString(line, x, y);
			y += 40;
		}
	}
	
	public void drawCharacterScreen() {
		//frame creation
		final int frameX = gp.tileSize * 3;
		final int frameY = gp.tileSize;
		final int frameWidth = gp.tileSize*5;
		final int frameHeight = gp.tileSize*10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		//text
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(40F));
		
		int textX = frameX + 25;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 53;
		
		//names
		g2.drawString("STATS:", textX, textY);
		textY += lineHeight;
		g2.drawString("Level", textX, textY);
		textY += lineHeight;
		g2.drawString("HP", textX, textY);
		textY += lineHeight;
		g2.drawString("Strength", textX, textY);
		textY += lineHeight;
		g2.drawString("Dexterity", textX, textY);
		textY += lineHeight;
		g2.drawString("Attack", textX, textY);
		textY += lineHeight;
		g2.drawString("Defense", textX, textY);
		textY += lineHeight;
		g2.drawString("Exp", textX, textY);
		textY += lineHeight;
		g2.drawString("Coin", textX, textY);
		textY += lineHeight;
		g2.drawString("Weapon", textX, textY);
		textY += lineHeight;
		g2.drawString("Shield", textX, textY);
		textY += lineHeight;
		
		//values
		int tailX = frameX + frameWidth - 25;
		textY = frameY + gp.tileSize + lineHeight;
		String value;
		
		value = String.valueOf(gp.player.level);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.strength);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.dexterity);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.attack);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.defense);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.exp + "/" + gp.player.nextLevelExp);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.coin);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		g2.drawImage(gp.player.currentWeapon.down0, tailX - gp.tileSize + 10, textY-45, null);
		textY += gp.tileSize;
		
		g2.drawImage(gp.player.currentShield.down0, tailX - gp.tileSize + 13, textY-60, null);
	}
	
	public void drawInventory() {
		//frame
		int frameX = gp.tileSize * 12;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize * 6;
		int frameHeight = gp.tileSize * 5;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		//slots
		final int slotXstart = frameX + 20;
		final int slotYstart = frameY + 20;
		int slotX = slotXstart;
		int slotY = slotYstart;
		int slotSize = gp.tileSize + 3;
		
		//draw inventory items
		for(int i = 0; i < gp.player.inventory.size(); i++) {
			//equip indicator
			if(gp.player.inventory.get(i) == gp.player.currentWeapon || 
					gp.player.inventory.get(i) == gp.player.currentShield) {
				g2.setColor(new Color(240,190,90,125));
				g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
			}
			
			g2.drawImage(gp.player.inventory.get(i).down0, slotX, slotY, null);
			
			slotX += slotSize;
			
			if(i == 4 || i == 9 || i == 14) {
				slotX = slotXstart;
				slotY += slotSize;
			}
			
		}
		
		//cursor
		int cursorX = slotXstart + (slotSize * slotCol) + 1;
		int cursorY = slotYstart + (slotSize * slotRow) + 1;
		int cursorWidth = gp.tileSize - 2;
		int cursorHeight = gp.tileSize - 2;
		//draw cursor
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(3));
		g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
		
		//description frame
		frameY += frameHeight + 10;
		frameHeight = gp.tileSize*3;
		//description text
		int textX = frameX + 25;
		int textY = frameY + 45;
		g2.setFont(g2.getFont().deriveFont(28F));
		
		int itemIndex = getItemIndexOnSlot();
		
		if(itemIndex < gp.player.inventory.size()) {
			drawSubWindow(frameX, frameY, frameWidth, frameHeight);

			for(String line : gp.player.inventory.get(itemIndex).description.split("/")) {
				g2.drawString(line, textX, textY);
				textY += 32;
			}
		}
	}
	
	public int getItemIndexOnSlot() {
		return slotCol + slotRow*5;
	}
	
	public void drawSubWindow(int x, int y, int width, int height) {
		Color c = new Color(0,0,0,210);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = Color.white;
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}
	
	public int getXforCenteredText(String text) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
	
	public int getXforAlignToRightText(String text, int tailX) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;
	}

}
