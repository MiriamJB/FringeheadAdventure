package tile_interactive;

import entity.Entity;
import main.GamePanel;

public class InteractiveTile extends Entity {
	GamePanel gp;
	public boolean destructable = false;
	
	public InteractiveTile(GamePanel gp) {
		super(gp);
		this.gp = gp;	
	}
	
	public boolean isCorrectItem(Entity entity) {
		return false;
	}
	
	public void playSE() {}
	
	public InteractiveTile getDestroyedForm() {
		return null;
	}
	
	public void update() {
		if(invincible) {
			invincibleCounter++;
			if(invincibleCounter > gp.FPS/3) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
	}
}
