package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity {
	GamePanel gp;
	
	public OBJ_Heart(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type.PICKUP;
		name = "heart";
		value = 2;
		down0 = setup("/objects/Heart");
		image = setup("/objects/Heart");
		image2 = setup("/objects/Heart_Half");
		image3 = setup("/objects/Heart_Empty");
	}
	
	public void use(Entity entity) {
		gp.playSE(2);
		gp.ui.addMessage("+" + value + " HP");
		entity.life += value;
	}
}
