package main;

import entity.Entity;
import java.awt.Polygon;
import java.awt.Rectangle;

public class CollisionChecker {

	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}

	public void checkTile(Entity entity) {
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		Rectangle entityShape;
		
		int tileNum1, tileNum2;
		Polygon tileShape1;
		Polygon tileShape2;
		
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			entityShape = new Rectangle(entity.solidArea);
			entityShape.setLocation(entityLeftWorldX, entityTopWorldY - entity.speed);
			
			if(gp.tileM.tile[tileNum1].collision) {
				tileShape1 = gp.tileM.tile[tileNum1].solidArea;
				tileShape1.translate(entityLeftCol*gp.tileSize, entityTopRow*gp.tileSize);
				if(tileShape1.intersects(entityShape)) {
				entity.collisionOn = true;
				}
				tileShape1.translate(-entityLeftCol*gp.tileSize, -entityTopRow*gp.tileSize);
			}
			
			if(gp.tileM.tile[tileNum2].collision) {
				tileShape2 = gp.tileM.tile[tileNum2].solidArea;
				tileShape2.translate(entityRightCol*gp.tileSize, entityTopRow*gp.tileSize);
				if(tileShape2.intersects(entityShape)) {
				entity.collisionOn = true;
				}
				tileShape2.translate(-entityRightCol*gp.tileSize, -entityTopRow*gp.tileSize);
			}
			break;
			
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			entityShape = new Rectangle(entity.solidArea);
			entityShape.setLocation(entityLeftWorldX, entityTopWorldY + entity.speed);
			
			if(gp.tileM.tile[tileNum1].collision) {
				tileShape1 = gp.tileM.tile[tileNum1].solidArea;
				tileShape1.translate(entityLeftCol*gp.tileSize, entityBottomRow*gp.tileSize);
				if(tileShape1.intersects(entityShape)) {
				entity.collisionOn = true;
				}
				tileShape1.translate(-entityLeftCol*gp.tileSize, -entityBottomRow*gp.tileSize);
			}
			
			if(gp.tileM.tile[tileNum2].collision) {
				tileShape2 = gp.tileM.tile[tileNum2].solidArea;
				tileShape2.translate(entityRightCol*gp.tileSize, entityBottomRow*gp.tileSize);
				if(tileShape2.intersects(entityShape)) {
				entity.collisionOn = true;
				}
				tileShape2.translate(-entityRightCol*gp.tileSize, -entityBottomRow*gp.tileSize);
			}
			break;
			
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			entityShape = new Rectangle(entity.solidArea);
			entityShape.setLocation(entityLeftWorldX - entity.speed, entityTopWorldY);
			
			if(gp.tileM.tile[tileNum1].collision) {
				tileShape1 = gp.tileM.tile[tileNum1].solidArea;
				tileShape1.translate(entityLeftCol*gp.tileSize, entityTopRow*gp.tileSize);
				if(tileShape1.intersects(entityShape)) {
				entity.collisionOn = true;
				}
				tileShape1.translate(-entityLeftCol*gp.tileSize, -entityTopRow*gp.tileSize);
			}
			
			if(gp.tileM.tile[tileNum2].collision) {
				tileShape2 = gp.tileM.tile[tileNum2].solidArea;
				tileShape2.translate(entityLeftCol*gp.tileSize, entityBottomRow*gp.tileSize);
				if(tileShape2.intersects(entityShape)) {
				entity.collisionOn = true;
				}
				tileShape2.translate(-entityLeftCol*gp.tileSize, -entityBottomRow*gp.tileSize);
			}
			break;
			
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			entityShape = new Rectangle(entity.solidArea);
			entityShape.setLocation(entityLeftWorldX + entity.speed, entityTopWorldY);
			
			if(gp.tileM.tile[tileNum1].collision) {
				tileShape1 = gp.tileM.tile[tileNum1].solidArea;
				tileShape1.translate(entityRightCol*gp.tileSize, entityTopRow*gp.tileSize);
				if(tileShape1.intersects(entityShape)) {
				entity.collisionOn = true;
				}
				tileShape1.translate(-entityRightCol*gp.tileSize, -entityTopRow*gp.tileSize);
			}
			
			if(gp.tileM.tile[tileNum2].collision) {
				tileShape2 = gp.tileM.tile[tileNum2].solidArea;
				tileShape2.translate(entityRightCol*gp.tileSize, entityBottomRow*gp.tileSize);
				if(tileShape2.intersects(entityShape)) {
				entity.collisionOn = true;
				}
				tileShape2.translate(-entityRightCol*gp.tileSize, -entityBottomRow*gp.tileSize);
			}
			break;
		}
	}
	
	public int checkObject(Entity entity, boolean player) {
		int index = -1;
		
		for(int i = 0; i < gp.obj.length; i++) {
			if(gp.obj[i] != null) {
				//entity's solid area
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				//object's solid area
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;				
			
				switch(entity.direction) {
				case "up":
					entity.solidArea.y -= entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision) {
							entity.collisionOn = true;
						}
						if (player == true) {
							index = i;
						}
					}
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision) {
							entity.collisionOn = true;
						}
						if (player == true) {
							index = i;
						}
					}
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision) {
							entity.collisionOn = true;
						}
						if (player == true) {
							index = i;
						}
					}
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision) {
							entity.collisionOn = true;
						}
						if (player == true) {
							index = i;
						}
					}
					break;
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
			}
		}
		return index;
	}
}
