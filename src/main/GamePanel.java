package main;

import javax.swing.JPanel;
import entity.Entity;
import entity.Entity.type;
import entity.Player;
import tile.TileManager;
import tile_interactive.InteractiveTile;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {
	//attributes
		//screen settings
	final int originalTileSize = 64;
	final int scale = 1;
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 20;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
		//world settings
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	
		//FPS
	public final int FPS = 60;
	
		//system
	TileManager tileM = new TileManager(this);
	public KeyHandeler keyH = new KeyHandeler(this);
	Sound music = new Sound();
	Sound se = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public EventHandler eHandler = new EventHandler(this);
	Thread gameThread;
	
		//entity and object
	public Player player = new Player(this,keyH);
	public Entity obj[] = new Entity[20];
	public Entity npc[] = new Entity[10];
	public Entity monster[] = new Entity[20];
	public InteractiveTile iTile[] =  new InteractiveTile[50];
	public InteractiveTile trees[] = new InteractiveTile[310];
	public ArrayList<Entity> projectileList = new ArrayList<>();
	public ArrayList<Entity> particleList = new ArrayList<>();
	ArrayList<Entity> entityList = new ArrayList<>();
	
		//game state
	public enum gs {
		TITLE, PLAY, PAUSE, DIALOGUE, CHARACTER;
	}
	public gs gameState;
	
	
	
	//constructor
	public GamePanel() {
		this.setPreferredSize(new Dimension (screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	//methods
	public void setupGame() {
		gameState = gs.TITLE;
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();
		aSetter.setInteractiveTile();
		aSetter.setTrees();
		//playMusic(0);
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime)/drawInterval;
			lastTime = currentTime;
			
			if (delta >= 1) {
				update();
				repaint();
				delta--;
			}
		}	
	}
		
	public void update() {
		if(gameState == gs.PLAY) {
			player.update();
			
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].update();
				}
			}
			
			for(int i = 0; i<monster.length; i++) {
				if(monster[i] != null) {
					if(monster[i].alive && !monster[i].dying) {
						monster[i].update();
					} else if (!monster[i].alive) {
						monster[i].checkDrop();
						monster[i] = null;
					}
				}
			}
			
			for(int i = 0; i<projectileList.size(); i++) {
				if(projectileList.get(i) != null) {
					if(projectileList.get(i).alive) {
						projectileList.get(i).update();
					} else if (!projectileList.get(i).alive) {
						projectileList.remove(i);
					}
				}
			}
			
			for(int i = 0; i<particleList.size(); i++) {
				if(particleList.get(i) != null) {
					if(particleList.get(i).alive) {
						particleList.get(i).update();
					} else if (!particleList.get(i).alive) {
						particleList.remove(i);
					}
				}
			}
			
			for(int i = 0; i<iTile.length; i++) {
				if(iTile[i] != null) {
					iTile[i].update();
				}
			}
		}
		
		if(gameState == gs.PAUSE) {
			
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		//debug
		long drawStart = 0;
		if (keyH.showDebugText) {
			drawStart = System.nanoTime();
		}
		
		//title screen
		if(gameState == gs.TITLE) {
			ui.draw(g2);
		} 
		//play screen
		else {
			tileM.draw(g2);
			
			entityList.add(player);
			
			for(int i = 0; i<npc.length; i++) {
				if(npc[i] != null) {
					entityList.add(npc[i]);
				}
			}
			for(int i = 0; i<monster.length; i++) {
				if(monster[i] != null) {
					entityList.add(monster[i]);
				}
			}
			for(int i = 0; i < obj.length; i++) {
				if(obj[i] != null) {
					entityList.add(obj[i]);
				}
			}
			for(int i = 0; i<projectileList.size(); i++) {
				if(projectileList.get(i) != null) {
					entityList.add(projectileList.get(i));
				}
			}
			for(int i = 0; i<particleList.size(); i++) {
				if(particleList.get(i) != null) {
					entityList.add(particleList.get(i));
				}
			}
			for(int i = 0; i<iTile.length; i++) {
				if(iTile[i] != null) {
					entityList.add(iTile[i]);
				}
			}
			for(int i = 0; i<trees.length; i++) {
				if(trees[i] != null) {
					entityList.add(trees[i]);
				}
			}
			Collections.sort(entityList, new Comparator<Entity>() {
				public int compare(Entity e1, Entity e2) {
					return Integer.compare(e1.worldY, e2.worldY);
				}
			});
			for(int i = 0; i < entityList.size(); i++) {
				entityList.get(i).draw(g2);
			}
			entityList.clear();
			
			ui.draw(g2);
		}
		
		//debug
		if (keyH.showDebugText) {
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			
			g2.setFont(new Font("Arial", Font.PLAIN, 20));
			g2.setColor(Color.white);
			int x = 10;
			int y = 400;
			int lineHeight = 20;
			
			g2.drawString("WorldX: " + player.worldX, x, y);
			y += lineHeight;
			g2.drawString("WorldY: " + player.worldY, x, y);
			y += lineHeight;
			g2.drawString("Col: " + (player.worldX + player.solidArea.x)/tileSize, x, y);
			y += lineHeight;
			g2.drawString("Row: " + (player.worldY + player.solidArea.y)/tileSize, x, y);
			y += lineHeight;
			
			//g2.drawString("Draw Time: " + passed, x, y);	
		}
		
		g2.dispose();
		
	}
	
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		music.stop();
	}
	
	public void playSE(int i) {
		se.setFile(i);
		se.play();
	}
	
}
