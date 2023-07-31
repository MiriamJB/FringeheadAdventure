package object;

import entity.Entity;
import main.GamePanel;
import main.GamePanel.gs;

public class OBJ_Potion_Red extends Entity{
	GamePanel gp;
	
	public OBJ_Potion_Red(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type.CONSUMABLE;
		name = "red potion";
		value = 5;
		down0 = setup("/objects/Red_Potion");
		description = "[Red Potion] /Tastes like cranberries and /almonds. /+" + value + " HP";
	}
	
	public void use(Entity entity) {
		gp.gameState = gs.DIALOGUE;
		gp.ui.currentDialogue = "You drink the " + name + "!/" + value + " HP has been restored.";
		entity.life += value;		
		gp.playSE(2);
	}
}
