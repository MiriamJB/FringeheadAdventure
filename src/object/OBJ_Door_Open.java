package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door_Open extends Entity{
		
	public OBJ_Door_Open(GamePanel gp) {
		super(gp);
		name = "open door";
		down0 = setup("objects/Door_Open");
	}

}
