package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Key extends SuperObject {
	
	GamePanel gp;

	public OBJ_Key(GamePanel gp) {
		name = "key";
		solidArea.setBounds(18, 14, 64-18-18, 64-15-15);
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/Key.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
