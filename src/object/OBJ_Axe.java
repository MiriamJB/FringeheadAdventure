package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity {
	public OBJ_Axe(GamePanel gp) {
		super(gp);
		type = type.WEAPON;
		name = "axe";
		down0 = setup("/objects/Axe");
		attackValue = 2;
		attackArea.width = 40;
		attackArea.height = 40;
		description = "[Axe]/Can cut wood, and can cut /enemies even better./Attack 2";
	}
	
}
