package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Door extends SuperObject{
	
	GamePanel gp;
	
	public OBJ_Door(GamePanel gp) {
		name = "door";
		collision = true;
		solidArea.setBounds(0, 17, 64, 64-17);
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/Door_Closed.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
