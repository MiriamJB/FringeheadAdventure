package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity {
	
	public OBJ_Key(GamePanel gp) {
		super(gp);
		name = "key";
		down0 = setup("/objects/Key");
		solidArea.setBounds(18, 14, 64-18-18, 64-15-15);
		solidAreaDefaultX = 18;
		solidAreaDefaultY = 14;
		description = "[Key]/A one-time use item./Can open doors.";
	}
}
