package tile_interactive;

import java.awt.Color;

import entity.Entity;
import main.GamePanel;

public class IT_LittleTree extends InteractiveTile {
	GamePanel gp;	
	
	public IT_LittleTree(GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
		
		type = type.LARGE_TILE;
		worldX = gp.tileSize * col;
		worldY = gp.tileSize * row - gp.tileSize/2;
		solidArea.setBounds(0, 64, 64, 32);
		solidAreaDefaultY = solidArea.y;
		
		down0 = setup("/tiles_interactive/Little_Tree", gp.tileSize, (int)(gp.tileSize*1.5));
		destructable = true;
		life = 3;
		
		//particle
		particleColor = new Color(65, 50, 30);
		particleSize = 6;
		particleSpeed = 1;
		particleMaxLife = 20;
	}
	
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = false;
		if(entity.currentWeapon.name.equals("axe")) {
			isCorrectItem = true;
		}
		return isCorrectItem;
	}
	
	public void playSE() {
		gp.playSE(11);

	}

	
	public InteractiveTile getDestroyedForm() {
		return new IT_LittleTreeStump(gp, worldX, worldY+gp.tileSize/2);
	}

}
