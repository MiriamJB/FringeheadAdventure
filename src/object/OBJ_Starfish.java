package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Starfish extends Projectile{
	GamePanel gp;

	public OBJ_Starfish(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = "starfish";
		speed = 5;
		maxLife = 60;
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
		up0 = setup("/projectile/Starfish");
		down0 = setup("/projectile/Starfish");
		left0 = setup("/projectile/Starfish");
		right0 = setup("/projectile/Starfish");
	}
	
	public boolean haveResource(Entity user) {
		boolean haveResource = false;
		if(user.mana >= useCost) {
			haveResource = true;
		}
		return haveResource;
	}
	
	public void subtractResource(Entity user) {
		user.mana -= useCost;
	}

}
