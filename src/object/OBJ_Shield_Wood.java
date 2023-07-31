package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Wood extends Entity{

	public OBJ_Shield_Wood(GamePanel gp) {
		super(gp);
		type = type.SHIELD;
		name = "wood shield";
		down0 = setup("/objects/Wooden_Shield");
		defenseValue = 1;
		description = "[Wood Shield]/A wooden shield with metalic/accents./Defense 1";
	}

}
