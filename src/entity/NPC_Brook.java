package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class NPC_Brook extends Entity{
	
	public NPC_Brook(GamePanel gp) {
		super(gp);
		
		speed = 1;
		solidArea = new Rectangle(14,10,33,60);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
		setDialogue();
	}

	public void getImage() {
		
		up0 = setup("/brook/B_Up");
		up1 = setup("/brook/B_Up_1I");
		up2 = setup("/brook/B_Up_1K");
		up3 = setup("/brook/B_Up_1I");
		up4 = setup("/brook/B_Up");
		up5 = setup("/brook/B_Up_2I");
		up6 = setup("/brook/B_Up_2K");
		up7 = setup("/brook/B_Up_2I");
		
		down0 = setup("/brook/B_Down");
		down1 = setup("/brook/B_Down_1I");
		down2 = setup("/brook/B_Down_1K");
		down3 = setup("/brook/B_Down_1I");
		down4 = setup("/brook/B_Down");
		down5 = setup("/brook/B_Down_2I");
		down6 = setup("/brook/B_Down_2K");
		down7 = setup("/brook/B_Down_2I");
		
		left0 = setup("/brook/B_Left_0");
		left1 = setup("/brook/B_Left_1");
		left2 = setup("/brook/B_Left_2");
		left3 = setup("/brook/B_Left_3");
		left4 = setup("/brook/B_Left_4");
		left5 = setup("/brook/B_Left_5");
		left6 = setup("/brook/B_Left_6");
		left7 = setup("/brook/B_Left_7");
		
		right0 = setup("/brook/B_Right_0");
		right1 = setup("/brook/B_Right_1");
		right2 = setup("/brook/B_Right_2");
		right3 = setup("/brook/B_Right_3");
		right4 = setup("/brook/B_Right_4");
		right5 = setup("/brook/B_Right_5");
		right6 = setup("/brook/B_Right_6");
		right7 = setup("/brook/B_Right_7");
		
	}
	
	public void setDialogue() {
		dialogues[0] = "Hello, friend!";
		dialogues[1] = "Wow, are you like, part fish? Fringehead fish? /That's so cool!";
		dialogues[2] = "Have you come to this island for a vacation as well? /There's a lot of cool marine wildlife here!";
		dialogues[3] = "I've heard that there's hidden treasure somewhere /on this island... I wonder if we could find it!";
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
		
	}
	
	public void speak() {
		super.speak();
	}
}
