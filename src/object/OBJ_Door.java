package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door extends Entity{
	
	public OBJ_Door(GamePanel gp) {
		super(gp);
		name = "door";
		down0 = setup("/objects/Door_Closed");
		collision = true;
		solidArea.setBounds(0, 17, 64, 64-17);
		solidAreaDefaultY = 17;
	}

}
