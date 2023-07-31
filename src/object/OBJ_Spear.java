package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Spear extends Entity{
		
	public OBJ_Spear(GamePanel gp) {
		super(gp);
		type = type.WEAPON;
		name = "spear";
		down0 = setup("/objects/Spear");
		attackValue = 1;
		attackArea.width = 50;
		attackArea.height = 50;
		description = "[Spear]/A hand-crafted spear./Attack 1";
	}

}
