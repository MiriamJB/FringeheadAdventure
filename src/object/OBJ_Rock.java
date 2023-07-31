package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Rock extends Projectile {
	GamePanel gp;

	public OBJ_Rock(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = "rock";
		speed = 8;
		maxLife = 40;
		life = maxLife;
		attack = 2;
		useCost = 1;
		alive = false;
		
		solidArea.setBounds(10, 10, 64-20, 64-20);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}
	
	public void getImage() {
		up0 = setup("/projectile/Rock");
		down0 = setup("/projectile/Rock");
		left0 = setup("/projectile/Rock");
		right0 = setup("/projectile/Rock");
	}
}
