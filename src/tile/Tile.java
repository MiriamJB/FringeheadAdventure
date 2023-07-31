package tile;

import java.awt.image.BufferedImage;
import java.awt.Polygon;

public class Tile {

	public BufferedImage image;
	public boolean collision = false;
	public Polygon solidArea = new Polygon(new int[] {0, 64, 64, 0}, new int[] {0, 0, 64, 64}, 4);

}
