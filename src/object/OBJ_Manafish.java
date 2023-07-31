package object;

import java.awt.Color;

import entity.Entity;
import main.GamePanel;

public class OBJ_Manafish extends Entity {
	GamePanel gp;

	public OBJ_Manafish(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type.PICKUP;
		name = "manafish";
		value = 1;
		down0= setup("/objects/Starfish_Full");
		image = setup("/objects/Starfish_Full");
		image2 = setup("/objects/Starfish_Empty");
		
		//particle
		particleColor = new Color(0, 100, 200);
		particleSize = 10;
		particleSpeed = 1;
		particleMaxLife = 20;
	}
	
	public void use(Entity entity) {
		gp.playSE(2);
		gp.ui.addMessage("+" + value + " starfish");
		entity.mana += value;
	}

	
}
