package tile_interactive;

import main.GamePanel;

public class IT_BigTree extends InteractiveTile{
	GamePanel gp;	
	
	public IT_BigTree(GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
		
		type = type.LARGE_TILE;
		worldX = gp.tileSize * col;
		worldY = gp.tileSize * row - gp.tileSize/2;
		
		down0 = setup("/tiles_interactive/Big_Tree", gp.tileSize, (int)(gp.tileSize*1.5));
		destructable = false;
	}
}
