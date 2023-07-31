package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Red extends Entity {
	
	public OBJ_Shield_Red(GamePanel gp) {
		super(gp);
		type = type.SHIELD;
		name = "wood shield";
		down0 = setup("/objects/Red_Shield");
		defenseValue = 2;
		description = "[Red Shield]/It's better because it's red./Defense 2";
	}

}
