package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin;
import object.OBJ_Heart;
import object.OBJ_Manafish;
import object.OBJ_Rock;

public class MON_Crab extends Entity{
	GamePanel gp;
	
	public MON_Crab(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type.MONSTER;
		name = "crab";
		speed = 1;
		maxLife = 4;
		life = maxLife;
		attack = 3;
		defense = 0;
		exp = 4;
		//projectile = new OBJ_Rock(gp);
		
		solidArea.setBounds(13, 26, 40, 24);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}
	
	public void getImage() {
		up0 = setup("/monster/Crab_0"); 
		up1 = setup("/monster/Crab_1"); 
		up2 = setup("/monster/Crab_2"); 
		up3 = setup("/monster/Crab_1"); 
		up4 = setup("/monster/Crab_0"); 
		up5 = setup("/monster/Crab_1"); 
		up6 = setup("/monster/Crab_2"); 
		up7 = setup("/monster/Crab_1"); 

		down0 = setup("/monster/Crab_0"); 
		down1 = setup("/monster/Crab_1"); 
		down2 = setup("/monster/Crab_2"); 
		down3 = setup("/monster/Crab_1"); 
		down4 = setup("/monster/Crab_0"); 
		down5 = setup("/monster/Crab_1"); 
		down6 = setup("/monster/Crab_2"); 
		down7 = setup("/monster/Crab_1"); 
		
		left0 = setup("/monster/Crab_0"); 
		left1 = setup("/monster/Crab_1"); 
		left2 = setup("/monster/Crab_2"); 
		left3 = setup("/monster/Crab_1"); 
		left4 = setup("/monster/Crab_0"); 
		left5 = setup("/monster/Crab_1"); 
		left6 = setup("/monster/Crab_2"); 
		left7 = setup("/monster/Crab_1"); 
		
		right0 = setup("/monster/Crab_0"); 
		right1 = setup("/monster/Crab_1"); 
		right2 = setup("/monster/Crab_2"); 
		right3 = setup("/monster/Crab_1"); 
		right4 = setup("/monster/Crab_0"); 
		right5 = setup("/monster/Crab_1"); 
		right6 = setup("/monster/Crab_2"); 
		right7 = setup("/monster/Crab_1"); 
	}
	
	public void setAction() {
		actionLockCounter++;
		
		if(actionLockCounter == gp.FPS*2) {
			Random random = new Random();
			int i = random.nextInt(4);
			
			if(i == 0) {direction = "up";}
			else if (i == 1) {direction = "down";}
			else if (i == 2) {direction = "left";}
			else {direction = "right";}
			
			actionLockCounter = 0;
		}
		
		/*	//Enemy projectile function
		 * int i = new Random().nextInt(100);
		 * 
		 * if (i == 0 && !projectile.alive && shotAvaliableCounter == 30) {
		 * projectile.set(worldX, worldY, direction, true, this);
		 * gp.projectileList.add(projectile); shotAvaliableCounter = 0; }
		 */
	}
	
	public void damageReaction() {
		actionLockCounter = 0;
		direction = gp.player.direction;
	}
	
	public void checkDrop() {
		int i = new Random().nextInt(4);
		
		if(i < 2) {
			dropItem(new OBJ_Coin(gp));
		} else if (i == 2) {
			dropItem(new OBJ_Heart(gp));
		} else {
			dropItem(new OBJ_Manafish(gp));
		}
	}
	
}
