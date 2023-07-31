package tile_interactive;

import main.GamePanel;

public class IT_LittleTreeStump extends InteractiveTile {
	GamePanel gp;
	
	public IT_LittleTreeStump(GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
		
		worldX = col;
		worldY = row;
		solidArea.setBounds(0, 0, 0, 0);
		
		down0 = setup("/tiles_interactive/Little_Tree_Stump");
	}
}
