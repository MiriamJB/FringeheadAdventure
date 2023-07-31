package object;

import entity.Entity;
import main.GamePanel;
import main.GamePanel.gs;

public class OBJ_Coin extends Entity {
	GamePanel gp;
	
	public OBJ_Coin(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type.PICKUP;
		name = "coin";
		value = 1;
		down0 = setup("/objects/Coin");
	}
	
	public void use(Entity entity) {
		gp.playSE(1);
		gp.ui.addMessage("+" + value + " coin");
		gp.player.coin += value;
	}

}
