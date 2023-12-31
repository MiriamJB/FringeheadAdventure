package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;

public class Particle extends Entity {
	Entity target;
	Color color;
	int size;
	int xd;
	int yd;
	
	public Particle(GamePanel gp, Entity target, Color color, int size, int speed, int maxLife, int xd, int yd) {
		super(gp);
		
		this.target = target;
		this.color = color;
		this.size = size;
		this.speed = speed;
		this.maxLife = maxLife;
		this.xd = xd;
		this.yd = yd;
		
		life = maxLife;
		int offset = gp.tileSize/2 - size/2;;
		worldX = target.worldX + offset;
		worldY = target.worldY + offset;
		if (target.type == type.LARGE_TILE) {
			worldY += gp.tileSize/2;

		}
	}
	
	public void update() {
		life--;
		
		if(life < maxLife/2) {
			yd++;
			size--;
		}
		
		worldX += xd*speed;
		worldY += yd*speed;
		
		if (life < 0) {
			alive = false;
		}
	}

	public void draw(Graphics2D g2) {
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		g2.setColor(color);
		g2.fillRect(screenX, screenY, size, size);
	}
}
