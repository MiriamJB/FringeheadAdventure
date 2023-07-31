package tile;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[352];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		loadMap("/maps/worldmap02.txt");
	}

	public void getTileImage() {
		//GRASS//////////////////////////////////////////////////////////////////////////
		setup(0, "Grass", false);
		
		//PATH///////////////////////////////////////////////////////////////////////////
		setup(30, "Path", false);
		setup(31, "Path_S", false);
		setup(32, "Path_N", false);
		setup(33, "Path_E", false);
		setup(34, "Path_W", false);
		setup(35, "Path_SE_Corner", false);
		setup(36, "Path_SW_Corner", false);
		setup(37, "Path_NE_Corner", false);
		setup(38, "Path_NW_Corner", false);
		setup(39, "Path_SE", false);
		setup(40, "Path_SW", false);
		setup(41, "Path_NE", false);
		setup(42, "Path_NW", false);
		setup(43, "Path_NS", false);
		setup(44, "Path_EW", false);
		setup(45, "Path_SE_Turn", false);
		setup(46, "Path_SW_Turn", false);
		setup(47, "Path_NE_Turn", false);
		setup(48, "Path_NW_Turn", false);
		setup(49, "Path_NSE", false);
		setup(50, "Path_NSW", false);
		setup(51, "Path_SEW", false);
		setup(52, "Path_NEW", false);
		setup(53, "Path_NSEW", false);
		setup(54, "Path_S_End", false);
		setup(55, "Path_N_End", false);
		setup(56, "Path_E_End", false);
		setup(57, "Path_W_End", false);
		setup(58, "Path_S_Merge", false);
		setup(59, "Path_N_Merge", false);
		setup(60, "Path_E_Merge", false);
		setup(61, "Path_W_Merge", false);
		
		//WATER////////////////////////////////////////////////////////////////////////////
		setup(100, "Water", true);
		setup(101, "Water_S", true);
		tile[101].solidArea = new Polygon(new int[] {0, 64, 64, 0}, new int[] {14, 14, 64, 64}, 4);
		setup(102, "Water_N", true);
		tile[102].solidArea = new Polygon(new int[] {0, 64, 64, 0}, new int[] {0, 0, 50, 50}, 4);
		setup(103, "Water_E", true);
		tile[103].solidArea = new Polygon(new int[] {14, 64, 64, 14}, new int[] {0, 0, 64, 64}, 4);
		setup(104, "Water_W", true);
		tile[104].solidArea = new Polygon(new int[] {0, 50, 50, 0}, new int[] {0, 0, 64, 64}, 4);
		setup(105, "Water_SE_End", true);
		tile[105].solidArea = new Polygon(new int[] {14, 64, 64, 14}, new int[] {14, 14, 64, 64}, 4);
		setup(106, "Water_SW_End", true);
		tile[106].solidArea = new Polygon(new int[] {0, 50, 50, 0}, new int[] {14, 14, 64, 64}, 4);
		setup(107, "Water_NE_End", true);
		tile[107].solidArea = new Polygon(new int[] {14, 64, 64, 14}, new int[] {0, 0, 50, 50}, 4);
		setup(108, "Water_NW_End", true);
		tile[108].solidArea = new Polygon(new int[] {0, 50, 50, 0}, new int[] {0, 0, 50, 50}, 4);
		setup(109, "Water_SE", true);
		tile[109].solidArea = new Polygon(new int[] {14, 64, 64, 0, 0, 14}, new int[] {0, 0, 64, 64, 14, 14}, 6);
		setup(110, "Water_SW", true);
		tile[110].solidArea = new Polygon(new int[] {0, 50, 50, 64, 64, 0}, new int[] {0, 0, 14, 14, 64, 64}, 6);
		setup(111, "Water_NE", true);
		tile[111].solidArea = new Polygon(new int[] {0, 64, 64, 14, 14, 0}, new int[] {0, 0, 64, 64, 50, 50}, 6);
		setup(112, "Water_NW", true);
		tile[112].solidArea = new Polygon(new int[] {0, 64, 64, 50, 50, 0}, new int[] {0, 0, 50, 50, 64, 64}, 6);
		setup(113, "Water_NS", true);
		tile[113].solidArea = new Polygon(new int[] {14, 50, 50, 14}, new int[] {0, 0, 64, 64}, 4);
		setup(114, "Water_EW", true);
		tile[114].solidArea = new Polygon(new int[] {0, 64, 64, 0}, new int[] {14, 14, 50, 50}, 4);
		setup(115, "Water_SE_Turn", true);
		tile[115].solidArea = new Polygon(new int[] {14, 64, 64, 50, 50, 14}, new int[] {14, 14, 50, 50, 64, 64}, 6);
		setup(116, "Water_SW_Turn", true);
		tile[116].solidArea = new Polygon(new int[] {0, 50, 50, 14, 14, 0}, new int[] {14, 14, 64, 64, 50, 50}, 6);
		setup(117, "Water_NE_Turn", true);
		tile[117].solidArea = new Polygon(new int[] {14, 50, 50, 64, 64, 14}, new int[] {0, 0, 14, 14, 50, 50}, 6);
		setup(118, "Water_NW_Turn", true);
		tile[118].solidArea = new Polygon(new int[] {14, 50, 50, 0, 0, 14}, new int[] {0, 0, 50, 50, 14, 14}, 6);
		setup(119, "Water_NSE", true);
		tile[119].solidArea = new Polygon(new int[] {14, 50, 50, 64, 64, 50, 50, 14}, new int[] {0, 0, 14, 14, 50, 50, 64, 64}, 8);
		setup(120, "Water_NSW", true);
		tile[120].solidArea = new Polygon(new int[] {14, 50, 50, 14, 14, 0, 0, 14}, new int[] {0, 0, 64, 64, 50, 50, 14, 14}, 8);
		setup(121, "Water_SEW", true);
		tile[121].solidArea = new Polygon(new int[] {0, 64, 64, 50, 50, 14, 14, 0}, new int[] {14, 14, 50, 50, 64, 64, 50, 50}, 8);
		setup(122, "Water_NEW", true);
		tile[122].solidArea = new Polygon(new int[] {14, 50, 50, 64, 64, 0, 0, 14}, new int[] {0, 0, 14, 14, 50, 50, 14, 14}, 8);
		setup(123, "Water_NSEW", true);
		tile[123].solidArea = new Polygon(new int[] {14, 50, 50, 64, 64, 50, 50, 14, 14, 0, 0, 14}, new int[] {0, 0, 14, 14, 50, 50, 64, 64, 50, 50, 14, 14}, 12);
		setup(124, "Water_S_End", true);
		tile[124].solidArea = new Polygon(new int[] {14, 50, 50, 14}, new int[] {0, 0, 50, 50}, 4);
		setup(125, "Water_N_End", true);
		tile[125].solidArea = new Polygon(new int[] {14, 50, 50, 14}, new int[] {14, 14, 64, 64}, 4);
		setup(126, "Water_E_End", true);
		tile[126].solidArea = new Polygon(new int[] {0, 50, 50, 0}, new int[] {14, 14, 50, 50}, 4);
		setup(127, "Water_W_End", true);
		tile[127].solidArea = new Polygon(new int[] {14, 64, 64, 14}, new int[] {14, 14, 50, 50}, 4);
		setup(128, "Water_S_Merge", true);
		tile[128].solidArea = new Polygon(new int[] {0, 64, 64, 50, 50, 14, 14, 0}, new int[] {0, 0, 50, 50, 64, 64, 50, 50}, 8);
		setup(129, "Water_N_Merge", true);
		tile[129].solidArea = new Polygon(new int[] {14, 50, 50, 64, 64, 0, 0, 14}, new int[] {0, 0, 14, 14, 64, 64, 50, 50}, 8);
		setup(130, "Water_E_Merge", true);
		tile[130].solidArea = new Polygon(new int[] {0, 50, 50, 64, 64, 50, 50, 0}, new int[] {0, 0, 14, 14, 50, 50, 64, 64}, 8);
		setup(131, "Water_W_Merge", true);
		tile[131].solidArea = new Polygon(new int[] {14, 64, 64, 14, 14, 0, 0, 14}, new int[] {0, 0, 64, 64, 50, 50, 14, 14}, 8);
		
		//WALL///////////////////////////////////////////////////////////////////////////////
		setup(201, "Wall_S", true);
		setup(202, "Wall_N", true);
		tile[202].solidArea = new Polygon(new int[] {0, 64, 64, 0}, new int[] {44, 44, 64, 64}, 4);
		setup(203, "Wall_E", true);
		tile[203].solidArea = new Polygon(new int[] {0, 18, 18, 0}, new int[] {0, 0, 64, 64}, 4);
		setup(204, "Wall_W", true);
		tile[204].solidArea = new Polygon(new int[] {46, 64, 64, 46}, new int[] {0, 0, 64, 64}, 4);
		setup(205, "Wall_SE", true);
		tile[205].solidArea = new Polygon(new int[] {0, 64, 64, 0}, new int[] {0, 0, 64, 64}, 4);
		setup(206, "Wall_SW", true);
		tile[206].solidArea = new Polygon(new int[] {0, 64, 64, 0}, new int[] {0, 0, 64, 64}, 4);
		setup(207, "Wall_NE", true);
		tile[207].solidArea = new Polygon(new int[] {0, 18, 18, 64, 64, 0}, new int[] {0, 0, 44, 44, 64, 64}, 6);
		setup(208, "Wall_NW", true);
		tile[208].solidArea = new Polygon(new int[] {46, 64, 64, 0, 0, 46}, new int[] {0, 0, 64, 64, 44, 44}, 6);
		setup(209, "Wall_SE_End", true);
		tile[209].solidArea = new Polygon(new int[] {0, 18, 18, 0}, new int[] {0, 0, 64, 64}, 4);
		setup(210, "Wall_SW_End", true);
		tile[210].solidArea = new Polygon(new int[] {46, 64, 64, 46}, new int[] {0, 0, 64, 64}, 4);
		setup(211, "Wall_NE_End", true);
		tile[211].solidArea = new Polygon(new int[] {0, 18, 18, 0}, new int[] {44, 44, 64, 64}, 4);
		setup(212, "Wall_NW_End", true);
		tile[212].solidArea = new Polygon(new int[] {46, 64, 64, 46}, new int[] {44, 44, 64, 64}, 4);
		setup(214, "Wall_SE_End_2", true);
		tile[214].solidArea = new Polygon(new int[] {0, 18, 18, 0}, new int[] {0, 0, 64, 64}, 4);
		setup(216, "Wall_SW_End_2", true);
		tile[216].solidArea = new Polygon(new int[] {46, 64, 64, 46}, new int[] {0, 0, 64, 64}, 4);
		setup(217, "Wall_NE_End_2", true);
		tile[217].solidArea = new Polygon(new int[] {0, 18, 18, 0}, new int[] {44, 44, 64, 64}, 4);
		setup(219, "Wall_FLat_NW_End_3", false);
		
		setup(221, "Wall_Flat_S", false);
		setup(222, "Wall_N", false);
		setup(223, "Wall_Flat_E", false);
		setup(224, "Wall_W", false);
		setup(225, "Wall_Flat_SE", false);
		setup(226, "Wall_Flat_SW", false);
		setup(227, "Wall_Flat_NE", false);
		setup(228, "Wall_NW", false);
		setup(229, "Wall_Flat_SE_End", false);
		setup(230, "Wall_Flat_SW_End", false);
		setup(231, "Wall_Flat_NE_End", false);
		setup(232, "Wall_NW_End", false);
		setup(233, "Wall_Flat_SE_End_2", false);
		setup(234, "Wall_Flat_SE_End_3", false);
		setup(235, "Wall_Flat_SW_End_2", false);
		setup(236, "Wall_Flat_SW_End_3", false);
		setup(237, "Wall_Flat_NE_End_2", false);
		setup(238, "Wall_Flat_NE_End_3", false);
		setup(239, "Wall_FLat_NW_End_2", false);
		
		setup(241, "Wall_Stairs_Shadow_N", true);
		setup(242, "Wall_Stairs_Shadow_S", false);
		setup(245, "Wall_Stairs_SE", true);
		tile[245].solidArea = new Polygon(new int[] {46, 64, 64, 46}, new int[] {0, 0, 62, 62}, 4);
		setup(246, "Wall_Stairs_SW", true);
		tile[246].solidArea = new Polygon(new int[] {0, 18, 18, 0}, new int[] {0, 0, 62, 62}, 4);
		setup(247, "Wall_Stairs_NE", true);
		tile[247].solidArea = new Polygon(new int[] {46, 64, 64, 46}, new int[] {0, 0, 64, 64}, 4);
		setup(248, "Wall_Stairs_NW", true);
		tile[248].solidArea = new Polygon(new int[] {0, 18, 18, 0}, new int[] {0, 0, 64, 64}, 4);
		setup(251, "Wall_Stairs_NE_2", true);
		tile[251].solidArea = new Polygon(new int[] {46, 64, 64, 46}, new int[] {0, 0, 62, 62}, 4);
		setup(252, "Wall_Stairs_NE_3", true);
		tile[252].solidArea = new Polygon(new int[] {46, 64, 64, 46}, new int[] {0, 0, 62, 62}, 4);
		setup(253, "Wall_Stairs_NW_2", true);
		tile[253].solidArea = new Polygon(new int[] {0, 18, 18, 0}, new int[] {0, 0, 64, 64}, 4);
		setup(254, "Wall_Stairs_NW_3", true);
		tile[254].solidArea = new Polygon(new int[] {0, 18, 18, 0}, new int[] {0, 0, 64, 64}, 4);

		//WALL-WATER//////////////////////////////////////////////////////////////////////////////
		setup(261, "Wall_Water_S_Refection_", true);
		setup(262, "Wall_Water_N", true);
		setup(263, "Wall_Water_E", true);
		setup(264, "Wall_Water_W", true);
		setup(265, "Wall_Water_E_Reflection", true);
		setup(266, "Wall_Water_W_Refection", true);
		setup(267, "Wall_Water_NE", true);
		setup(268, "Wall_Water_NW", true);
		setup(269, "Wall_Water_SE_End", true);
		setup(270, "Wall_Water_SW_End", true);
		setup(271, "Wall_Water_NE_End", true);
		setup(272, "Wall_Water_NW_End", true);
		setup(273, "Wall_Water_SE_End_Refection_", true);
		setup(274, "Wall_Water_SE_End_2", true);
		setup(275, "Wall_Water_SW_End_Refection_", true);
		setup(276, "Wall_Water_SW_End_2", true);
		setup(277, "Wall_Water_NE_End_2", true);
		setup(279, "Wall_Water_FLat_NW_End_3", true);
		
		setup(281, "Wall_Water_Flat_S", true);
		setup(282, "Wall_Water_Flat_N", true);
		setup(283, "Wall_Water_Flat_E", true);
		setup(284, "Wall_Water_Flat_W", true);
		setup(285, "Wall_Water_Flat_SE", true);
		setup(286, "Wall_Water_Flat_SW", true);
		setup(287, "Wall_Water_Flat_NE", true);
		setup(288, "Wall_Water_Flat_NW", true);
		setup(289, "Wall_Water_Flat_SE_End", true);
		setup(290, "Wall_Water_Flat_SW_End", true);
		setup(291, "Wall_Water_Flat_NE_End", true);
		setup(292, "Wall_Water_Flat_NW_End", true);
		setup(293, "Wall_Water_Flat_SE_End_2", true);
		setup(294, "Wall_Water_Flat_SE_End_3", true);
		setup(295, "Wall_Water_Flat_SW_End_2", true);
		setup(296, "Wall_Water_Flat_SW_End_3", true);
		setup(297, "Wall_Water_Flat_NE_End_2", true);
		setup(298, "Wall_Water_Flat_NE_End_3", true);
		setup(299, "Wall_Water_FLat_NW_End_2", true);
		
		setup(301, "Wall_Water_Flat_S_Reflection_", true);
		setup(302, "Wall_Water_Stairs_Shadow_S", true);
		setup(303, "Wall_Water_W_Shadow", true);
		setup(304, "Wall_Water_SE_Shadow_Reflection_", true);
		setup(305, "Wall_Water_Stairs_SE", true);
		tile[305].solidArea = new Polygon(new int[] {0, 46, 46, 64, 64, 0}, new int[] {48, 48, 0, 0, 64, 64}, 6);
		setup(306, "Wall_Water_Stairs_SW", true);
		tile[306].solidArea = new Polygon(new int[] {0, 18, 18, 64, 64, 0}, new int[] {0, 0, 48, 48, 64, 64}, 6);
		setup(307, "Wall_Water_Stairs_NE", true);
		tile[307].solidArea = new Polygon(new int[] {0, 64, 64, 46, 46, 0}, new int[] {0, 0, 64, 64, 8, 8}, 6);
		setup(308, "Wall_Water_Stairs_NW", true);
		tile[308].solidArea = new Polygon(new int[] {0, 64, 64, 18, 18, 0}, new int[] {0, 0, 8, 8, 64, 64}, 6);
		setup(309, "Wall_Water_Flat_SE_End_Reflection", true);
		setup(310, "Wall_Water_Flat_SW_End_Refection", true);
		setup(311, "Wall_Water_Stairs_NE_2", true);
		tile[311].solidArea = new Polygon(new int[] {0, 64, 64, 46, 46, 0}, new int[] {0, 0, 64, 64, 8, 8}, 6);
		setup(312, "Wall_Water_Stairs_NE_3", true);
		tile[312].solidArea = new Polygon(new int[] {0, 64, 64, 46, 46, 0}, new int[] {0, 0, 64, 64, 8, 8}, 6);
		setup(313, "Wall_Water_Stairs_NW_2", true);
		tile[313].solidArea = new Polygon(new int[] {0, 64, 64, 18, 18, 0}, new int[] {0, 0, 8, 8, 64, 64}, 6);
		setup(314, "Wall_Water_Stairs_NW_3", true);
		tile[314].solidArea = new Polygon(new int[] {0, 64, 64, 18, 18, 0}, new int[] {0, 0, 8, 8, 64, 64}, 6);

		setup(321, "Wall_S_2", true);
		setup(322, "Wall_N_2", true);
		tile[322].solidArea = new Polygon(new int[] {0, 64, 64, 0}, new int[] {44, 44, 64, 64}, 4);
		setup(323, "Wall_E_2", true);
		tile[323].solidArea = new Polygon(new int[] {0, 18, 18, 0}, new int[] {0, 0, 64, 64}, 4);
		setup(324, "Wall_W_2", true);
		tile[324].solidArea = new Polygon(new int[] {46, 64, 64, 46}, new int[] {0, 0, 64, 64}, 4);
		setup(325, "Wall_S_3", true);
		setup(326, "Wall_N_3", true);
		tile[326].solidArea = new Polygon(new int[] {0, 64, 64, 0}, new int[] {44, 44, 64, 64}, 4);
		setup(327, "Wall_E_3", true);
		tile[327].solidArea = new Polygon(new int[] {0, 18, 18, 0}, new int[] {0, 0, 64, 64}, 4);
		setup(328, "Wall_W_3", true);
		tile[328].solidArea = new Polygon(new int[] {46, 64, 64, 46}, new int[] {0, 0, 64, 64}, 4);
		setup(329, "Wall_SE_End_3", true);		
		tile[329].solidArea = new Polygon(new int[] {0, 18, 18, 0}, new int[] {0, 0, 64, 64}, 4);
		setup(330, "Wall_SW_End_3", true);
		tile[330].solidArea = new Polygon(new int[] {46, 64, 64, 46}, new int[] {0, 0, 64, 64}, 4);
		setup(331, "Wall_Shadow", false);
		setup(332, "Wall_Shadow_2", false);
		setup(333, "Wall_Shadow_3", false);
		setup(334, "Wall_Shadow_4", false);
		setup(335, "Wall_N_Shadow", false);
		
		//EXTRA///////////////////////////////////////////////////////////////////////////////////
		setup(341, "Wall_Flat_S", true);
		tile[341].solidArea = new Polygon(new int[] {0, 64, 64, 0}, new int[] {0, 0, 20, 20}, 4);
		setup(342, "Wall_E_2.5", true);
		tile[342].solidArea = new Polygon(new int[] {0, 18, 18, 0}, new int[] {0, 0, 64, 64}, 4);
		setup(343, "Wall_Flat_E", true);
		tile[343].solidArea = new Polygon(new int[] {0, 18, 18, 0}, new int[] {0, 0, 64, 64}, 4);
		setup(344, "Wall_E_3.5", true);
		tile[344].solidArea = new Polygon(new int[] {0, 18, 18, 0}, new int[] {0, 0, 64, 64}, 4);
		setup(345, "Wall_Flat_SE", true);
		tile[345].solidArea = new Polygon(new int[] {0, 64, 64, 18, 18, 0}, new int[] {0, 0, 20, 20, 64, 64}, 6);
		setup(346, "Wall_Flat_SW", true);
		tile[346].solidArea = new Polygon(new int[] {0, 64, 64, 46, 46, 0}, new int[] {0, 0, 64, 64, 20, 20}, 6);
		setup(347, "Wall_Flat_NE", true);
		tile[347].solidArea = new Polygon(new int[] {0, 18, 18, 64, 64, 0}, new int[] {0, 0, 44, 44, 64, 64}, 6);
		setup(351, "Wall_Flat_NE_End", true);
		tile[351].solidArea = new Polygon(new int[] {0, 18, 18, 0}, new int[] {44, 44, 64, 64}, 4);
	}
	
	public void setup(int index, String imageName, boolean collision) {
		UtilityTool uTool = new UtilityTool();
		
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(row < gp.maxWorldRow) {
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {
					String numbers[] = line.split("	");
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
					col++;
				}
				
				if (col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		} catch(Exception e) {
		}
	}
	
	public void draw(Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;
		//int x = 0;
		//int y = 0;
		
		while(worldRow < gp.maxWorldRow) {
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
					worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
					worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
					worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, null);
			}
			
			worldCol++;
			//x += gp.tileSize;
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				//x = 0;
				worldRow++;
				//y += gp.tileSize;
			}
		}
	}
}
