package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity {
	
	public OBJ_Chest(GamePanel gp) {
		super(gp);
		name = "chest";
		down0 = setup("/objects/Chest");	
	}
}
