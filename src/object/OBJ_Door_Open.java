package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Door_Open extends SuperObject{
	
	GamePanel gp;
	
	public OBJ_Door_Open(GamePanel gp) {
		name = "open door";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/Door_Open.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
