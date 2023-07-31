package main;

import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Spear;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		gp.obj[0] = new OBJ_Key(gp);
		gp.obj[0].worldX = 42 * gp.tileSize;
		gp.obj[0].worldY = 43 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Key(gp);
		gp.obj[1].worldX = 36 * gp.tileSize;
		gp.obj[1].worldY = 8 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Key(gp);
		gp.obj[2].worldX = 19 * gp.tileSize;
		gp.obj[2].worldY = 38 * gp.tileSize;
		
		gp.obj[3] = new OBJ_Door(gp);
		gp.obj[3].worldX = 21 * gp.tileSize;
		gp.obj[3].worldY = 32 * gp.tileSize;
		
		gp.obj[4] = new OBJ_Door(gp);
		gp.obj[4].worldX = 17 * gp.tileSize;
		gp.obj[4].worldY = 32 * gp.tileSize;
		
		gp.obj[5] = new OBJ_Door(gp);
		gp.obj[5].worldX = 17 * gp.tileSize;
		gp.obj[5].worldY = 37 * gp.tileSize;
		
		gp.obj[6] = new OBJ_Chest(gp);
		gp.obj[6].worldX = 10 * gp.tileSize + gp.tileSize/2;
		gp.obj[6].worldY = 23 * gp.tileSize;
		
		gp.obj[7] = new OBJ_Spear(gp);
		gp.obj[7].worldX = 17 * gp.tileSize;
		gp.obj[7].worldY = 21 * gp.tileSize;
	}
}
