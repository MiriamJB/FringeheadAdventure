package tile_interactive;

import main.GamePanel;

public class IT_MedTree extends InteractiveTile {
	GamePanel gp;	
	
	public IT_MedTree(GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
		
		type = type.LARGE_TILE;
		worldX = gp.tileSize * col;
		worldY = gp.tileSize * row - gp.tileSize/2;
		
		down0 = setup("/tiles_interactive/Medium_Tree", gp.tileSize, (int)(gp.tileSize*1.5));
		destructable = false;
	}
}
