package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Spear extends SuperObject{
	
	GamePanel gp;
	
	public OBJ_Spear(GamePanel gp) {
		name = "spear";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/Spear.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
