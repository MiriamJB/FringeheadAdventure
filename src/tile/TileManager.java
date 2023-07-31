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
		tile = new Tile[200];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		loadMap("/maps/FH_IslandMap01");
	}

	public void getTileImage() {
		setup(0, "000_Grass", false);
		setup(1, "000_Grass", true, gp.tileSize, (int)(gp.tileSize)); //large tree
		tile[1].solidArea = new Polygon(new int[] {0, 64, 64, 0}, new int[] {0, 0, 64, 64}, 4);
		setup(2, "000_Grass", true, gp.tileSize, (int)(gp.tileSize)); //med tree
		tile[2].solidArea = new Polygon(new int[] {0, 64, 64, 0}, new int[] {32, 32, 64, 64}, 4);
		setup(3, "000_Grass", true, gp.tileSize, (int)(gp.tileSize)); //little tree
		tile[3].solidArea = new Polygon(new int[] {0, 64, 64, 0}, new int[] {32, 32, 64, 64}, 4);
		setup(4, "004_Big_Tree_Stump", false);
		setup(5, "005_Medium_Tree_Stump", false);
		setup(6, "006_Little_Tree_Stump", false);
		
		//PATH///////////////////////////////////////////////////////////////////////////////
		setup(7, "007_Path_S", false);
		setup(8, "008_Path_N", false);
		setup(9, "009_Path_E", false);
		setup(10, "010_Path_W", false);
		setup(11, "011_Path_SE_Corner", false);
		setup(12, "012_Path_SW_Corner", false);
		setup(13, "013_Path_NE_Corner", false);
		setup(14, "014_Path_NW_Corner", false);
		setup(15, "015_Path_SE", false);
		setup(16, "016_Path_SW", false);
		setup(17, "017_Path_NE", false);
		setup(18, "018_Path_NW", false);
		setup(19, "019_Path_NS", false);
		setup(20, "020_Path_EW", false);
		setup(21, "021_Path_SE_Turn", false);
		setup(22, "022_Path_SW_Turn", false);
		setup(23, "023_Path_NE_Turn", false);
		setup(24, "024_Path_NW_Turn", false);
		setup(25, "025_Path_NSE", false);
		setup(26, "026_Path_NSW", false);
		setup(27, "027_Path_SEW", false);
		setup(28, "028_Path_NEW", false);
		setup(29, "029_Path_NSEW", false);
		setup(30, "030_Path_S_End", false);
		setup(31, "031_Path_N_End", false);
		setup(32, "032_Path_E_End", false);
		setup(33, "033_Path_W_End", false);
		setup(34, "034_Path_S_Merge", false);
		setup(35, "035_Path_N_Merge", false);
		setup(36, "036_Path_E_Merge", false);
		setup(37, "037_Path_W_Merge", false);
		setup(38, "038_Path", false);

		//WATER///////////////////////////////////////////////////////////////////////////////
		setup(39, "039_Water_S", true);
		tile[39].solidArea = new Polygon(new int[] {0, 64, 64, 0}, new int[] {14, 14, 64, 64}, 4);
		setup(40, "040_Water_N", true);
		tile[40].solidArea = new Polygon(new int[] {0, 64, 64, 0}, new int[] {0, 0, 50, 50}, 4);
		setup(41, "041_Water_E", true);
		tile[41].solidArea = new Polygon(new int[] {14, 64, 64, 14}, new int[] {0, 0, 64, 64}, 4);
		setup(42, "042_Water_W", true);
		tile[42].solidArea = new Polygon(new int[] {0, 50, 50, 0}, new int[] {0, 0, 64, 64}, 4);
		setup(43, "043_Water_SE_End", true);
		tile[43].solidArea = new Polygon(new int[] {14, 64, 64, 14}, new int[] {14, 14, 64, 64}, 4);
		setup(44, "044_Water_SW_End", true);
		tile[44].solidArea = new Polygon(new int[] {0, 50, 50, 0}, new int[] {14, 14, 64, 64}, 4);
		setup(45, "045_Water_NE_End", true);
		tile[45].solidArea = new Polygon(new int[] {14, 64, 64, 14}, new int[] {0, 0, 50, 50}, 4);
		setup(46, "046_Water_NW_End", true);
		tile[46].solidArea = new Polygon(new int[] {0, 50, 50, 0}, new int[] {0, 0, 50, 50}, 4);
		setup(47, "047_Water_SE", true);
		tile[47].solidArea = new Polygon(new int[] {14, 64, 64, 0, 0, 14}, new int[] {0, 0, 64, 64, 14, 14}, 6);
		setup(48, "048_Water_SW", true);
		tile[48].solidArea = new Polygon(new int[] {0, 50, 50, 64, 64, 0}, new int[] {0, 0, 14, 14, 64, 64}, 6);
		setup(49, "049_Water_NE", true);
		tile[49].solidArea = new Polygon(new int[] {0, 64, 64, 14, 14, 0}, new int[] {0, 0, 64, 64, 50, 50}, 6);
		setup(50, "050_Water_NW", true);
		tile[50].solidArea = new Polygon(new int[] {0, 64, 64, 50, 50, 0}, new int[] {0, 0, 50, 50, 64, 64}, 6);
		setup(51, "051_Water_NS", true);
		tile[51].solidArea = new Polygon(new int[] {14, 50, 50, 14}, new int[] {0, 0, 64, 64}, 4);
		setup(52, "052_Water_EW", true);
		tile[52].solidArea = new Polygon(new int[] {0, 64, 64, 0}, new int[] {14, 14, 50, 50}, 4);
		setup(53, "053_Water_SE_Turn", true);
		tile[53].solidArea = new Polygon(new int[] {14, 64, 64, 50, 50, 14}, new int[] {14, 14, 50, 50, 64, 64}, 6);
		setup(54, "054_Water_SW_Turn", true);
		tile[54].solidArea = new Polygon(new int[] {0, 50, 50, 14, 14, 0}, new int[] {14, 14, 64, 64, 50, 50}, 6);
		setup(55, "055_Water_NW_Turn", true);
		tile[55].solidArea = new Polygon(new int[] {14, 50, 50, 0, 0, 14}, new int[] {0, 0, 50, 50, 14, 14}, 6);
		setup(56, "056_Water_NE_Turn", true);
		tile[56].solidArea = new Polygon(new int[] {14, 50, 50, 64, 64, 14}, new int[] {0, 0, 14, 14, 50, 50}, 6);
		setup(57, "057_Water_NSE", true);
		tile[57].solidArea = new Polygon(new int[] {14, 50, 50, 64, 64, 50, 50, 14}, new int[] {0, 0, 14, 14, 50, 50, 64, 64}, 8);
		setup(58, "058_Water_NSW", true);
		tile[58].solidArea = new Polygon(new int[] {14, 50, 50, 14, 14, 0, 0, 14}, new int[] {0, 0, 64, 64, 50, 50, 14, 14}, 8);
		setup(59, "059_Water_SEW", true);
		tile[59].solidArea = new Polygon(new int[] {0, 64, 64, 50, 50, 14, 14, 0}, new int[] {14, 14, 50, 50, 64, 64, 50, 50}, 8);
		setup(60, "060_Water_NEW", true);
		tile[60].solidArea = new Polygon(new int[] {14, 50, 50, 64, 64, 0, 0, 14}, new int[] {0, 0, 14, 14, 50, 50, 14, 14}, 8);
		setup(61, "061_Water_NSEW", true);
		tile[61].solidArea = new Polygon(new int[] {14, 50, 50, 64, 64, 50, 50, 14, 14, 0, 0, 14}, new int[] {0, 0, 14, 14, 50, 50, 64, 64, 50, 50, 14, 14}, 12);
		setup(62, "062_Water_S_End", true);
		tile[62].solidArea = new Polygon(new int[] {14, 50, 50, 14}, new int[] {0, 0, 50, 50}, 4);
		setup(63, "063_Water_N_End", true);
		tile[63].solidArea = new Polygon(new int[] {14, 50, 50, 14}, new int[] {14, 14, 64, 64}, 4);
		setup(64, "064_Water_E_End", true);
		tile[64].solidArea = new Polygon(new int[] {0, 50, 50, 0}, new int[] {14, 14, 50, 50}, 4);
		setup(65, "065_Water_W_End", true);
		tile[65].solidArea = new Polygon(new int[] {14, 64, 64, 14}, new int[] {14, 14, 50, 50}, 4);
		setup(66, "066_Water_S_Merge", true);
		tile[66].solidArea = new Polygon(new int[] {0, 64, 64, 50, 50, 14, 14, 0}, new int[] {0, 0, 50, 50, 64, 64, 50, 50}, 8);
		setup(67, "067_Water_N_Merge", true);
		tile[67].solidArea = new Polygon(new int[] {14, 50, 50, 64, 64, 0, 0, 14}, new int[] {0, 0, 14, 14, 64, 64, 50, 50}, 8);
		setup(68, "068_Water_E_Merge", true);
		tile[68].solidArea = new Polygon(new int[] {0, 50, 50, 64, 64, 50, 50, 0}, new int[] {0, 0, 14, 14, 50, 50, 64, 64}, 8);
		setup(69, "069_Water_W_Merge", true);
		tile[69].solidArea = new Polygon(new int[] {14, 64, 64, 14, 14, 0, 0, 14}, new int[] {0, 0, 64, 64, 50, 50, 14, 14}, 8);
		setup(70, "070_Water", true);

		//WALL///////////////////////////////////////////////////////////////////////////////
		setup(71, "071_Wall_N", true);
		tile[71].solidArea = new Polygon(new int[] {0, 64, 64, 0}, new int[] {44, 44, 64, 64}, 4);
		setup(72, "072_Wall_N_Shadow", false);
		tile[72].solidArea = new Polygon(new int[] {0, 64, 64, 0}, new int[] {44, 44, 64, 64}, 4);
		setup(73, "073_Wall_N_2", true);
		tile[73].solidArea = new Polygon(new int[] {0, 64, 64, 0}, new int[] {44, 44, 64, 64}, 4);
		setup(74, "074_Wall_N_3", true);
		tile[74].solidArea = new Polygon(new int[] {0, 64, 64, 0}, new int[] {44, 44, 64, 64}, 4);
		setup(75, "075_Wall_E", true);
		tile[75].solidArea = new Polygon(new int[] {0, 18, 18, 0}, new int[] {0, 0, 64, 64}, 4);
		setup(76, "076_Wall_E_2", true);
		tile[76].solidArea = new Polygon(new int[] {0, 18, 18, 0}, new int[] {0, 0, 64, 64}, 4);
		setup(77, "077_Wall_E_3", true);
		tile[77].solidArea = new Polygon(new int[] {0, 18, 18, 0}, new int[] {0, 0, 64, 64}, 4);
		setup(78, "078_Wall_W", true);
		tile[78].solidArea = new Polygon(new int[] {46, 64, 64, 46}, new int[] {0, 0, 64, 64}, 4);
		setup(79, "079_Wall_W_2", true);
		tile[79].solidArea = new Polygon(new int[] {46, 64, 64, 46}, new int[] {0, 0, 64, 64}, 4);
		setup(80, "080_Wall_W_3", true);
		tile[80].solidArea = new Polygon(new int[] {46, 64, 64, 46}, new int[] {0, 0, 64, 64}, 4);
		setup(81, "081_Wall_SE", true);
		tile[81].solidArea = new Polygon(new int[] {0, 64, 64, 0}, new int[] {0, 0, 64, 64}, 4);
		setup(82, "082_Wall_SW", true);
		tile[82].solidArea = new Polygon(new int[] {0, 64, 64, 0}, new int[] {0, 0, 64, 64}, 4);
		setup(83, "083_Wall_NE", true);
		tile[83].solidArea = new Polygon(new int[] {0, 18, 18, 64, 64, 0}, new int[] {0, 0, 44, 44, 64, 64}, 6);
		setup(84, "084_Wall_NW", true);
		tile[84].solidArea = new Polygon(new int[] {46, 64, 64, 0, 0, 46}, new int[] {0, 0, 64, 64, 44, 44}, 6);
		setup(85, "085_Wall_SE_End", true);
		tile[85].solidArea = new Polygon(new int[] {0, 18, 18, 0}, new int[] {0, 0, 64, 64}, 4);
		setup(86, "086_Wall_SE_End_2", true);
		tile[86].solidArea = new Polygon(new int[] {0, 18, 18, 0}, new int[] {0, 0, 64, 64}, 4);
		setup(87, "087_Wall_SE_End_3", true);		
		tile[87].solidArea = new Polygon(new int[] {0, 18, 18, 0}, new int[] {0, 0, 64, 64}, 4);
		setup(88, "088_Wall_SW_End", true);
		tile[88].solidArea = new Polygon(new int[] {46, 64, 64, 46}, new int[] {0, 0, 64, 64}, 4);
		setup(89, "089_Wall_SW_End_2", true);
		tile[89].solidArea = new Polygon(new int[] {46, 64, 64, 46}, new int[] {0, 0, 64, 64}, 4);
		setup(90, "090_Wall_SW_End_3", true);
		tile[90].solidArea = new Polygon(new int[] {46, 64, 64, 46}, new int[] {0, 0, 64, 64}, 4);
		setup(91, "091_Wall_NE_End", true);
		tile[91].solidArea = new Polygon(new int[] {0, 18, 18, 0}, new int[] {44, 44, 64, 64}, 4);
		setup(92, "092_Wall_NE_End_2", true);
		tile[92].solidArea = new Polygon(new int[] {0, 18, 18, 0}, new int[] {44, 44, 64, 64}, 4);
		setup(93, "093_Wall_NW_End", true);
		tile[93].solidArea = new Polygon(new int[] {46, 64, 64, 46}, new int[] {44, 44, 64, 64}, 4);
		setup(94, "094_Wall_NW_End_2", true);
		tile[94].solidArea = new Polygon(new int[] {46, 64, 64, 46}, new int[] {44, 44, 64, 64}, 4);
		setup(95, "095_Wall_Stairs_SE", true);
		tile[95].solidArea = new Polygon(new int[] {46, 64, 64, 46}, new int[] {0, 0, 62, 62}, 4);
		setup(96, "096_Wall_Stairs_SW", true);
		tile[96].solidArea = new Polygon(new int[] {0, 18, 18, 0}, new int[] {0, 0, 62, 62}, 4);
		setup(97, "097_Wall_Stairs_NE", true);
		tile[97].solidArea = new Polygon(new int[] {46, 64, 64, 46}, new int[] {0, 0, 64, 64}, 4);
		setup(98, "098_Wall_Stairs_NE_2", true);
		tile[98].solidArea = new Polygon(new int[] {46, 64, 64, 46}, new int[] {0, 0, 62, 62}, 4);
		setup(99, "099_Wall_Stairs_NE_3", true);
		tile[99].solidArea = new Polygon(new int[] {46, 64, 64, 46}, new int[] {0, 0, 62, 62}, 4);
		setup(100, "100_Wall_Stairs_NW", true);
		tile[100].solidArea = new Polygon(new int[] {0, 18, 18, 0}, new int[] {0, 0, 64, 64}, 4);
		setup(101, "101_Wall_Stairs_NW_2", true);
		tile[101].solidArea = new Polygon(new int[] {0, 18, 18, 0}, new int[] {0, 0, 64, 64}, 4);
		setup(102, "102_Wall_Stairs_NW_3", true);
		tile[102].solidArea = new Polygon(new int[] {0, 18, 18, 0}, new int[] {0, 0, 64, 64}, 4);
		
		setup(103, "103_Wall_S_F", false);
		setup(104, "104_Wall_N_F", false);
		setup(105, "105_Wall_N_2_F", false);
		setup(106, "106_Wall_N_3_F", false);
		setup(107, "107_Wall_E_F", false);
		setup(108, "108_Wall_E_2_F", false);
		setup(109, "109_Wall_E_3_F", false);
		setup(110, "110_Wall_W_F", false);
		setup(111, "111_Wall_W_2_F", false);
		setup(112, "112_Wall_W_3_F", false);
		setup(113, "113_Wall_SE_F", false);
		setup(114, "114_Wall_SW_F", false);
		setup(115, "115_Wall_NE_F", false);
		setup(116, "116_Wall_NW_F", false);
		setup(117, "117_Wall_SE_End_F", false);
		setup(118, "118_Wall_SE_End_2_F", false);
		setup(119, "119_Wall_SE_End_3_F", false);
		setup(120, "120_Wall_SW_End_F", false);
		setup(121, "121_Wall_SW_End_2_F", false);
		setup(122, "122_Wall_SW_End_3_F", false);
		setup(123, "123_Wall_NE_End_F", false);
		setup(124, "124_Wall_NE_End_2_F", false);
		setup(125, "125_Wall_NE_End_3_F", false);
		setup(126, "126_Wall_NW_End_F", false);
		setup(127, "127_Wall_NW_End_2_F", false);
		setup(128, "128_Wall_NW_End_3_F", false);
		
		setup(129, "129_Wall_Shadow", false);
		setup(130, "130_Wall_Shadow_2", false);
		setup(131, "131_Wall_Shadow_3", false);
		setup(132, "132_Wall_Shadow_4", false);
		setup(133, "133_Wall_Stairs_Shadow_S", false);
		setup(134, "134_Wall_Stairs_Shadow_N", true);
		
		setup(135, "135_Wall_S_3", true);
		setup(136, "136_Wall_S_2", true);
		setup(137, "137_Wall_S", true);
		
		//WALL-WATER//////////////////////////////////////////////////////////////////////////////
		setup(138, "138_Wall_Water_N", true);
		setup(139, "139_Wall_Water_E", true);
		setup(140, "140_Wall_Water_E_Reflection", true);
		setup(141, "141_Wall_Water_W", true);
		setup(142, "142_Wall_Water_W_Refection", true);
		setup(143, "143_Wall_Water_NE", true);
		setup(144, "144_Wall_Water_NW", true);
		setup(145, "145_Wall_Water_SE_End", true);
		setup(146, "146_Wall_Water_SE_End_2", true);
		setup(147, "147_Wall_Water_SW_End", true);
		setup(148, "148_Wall_Water_SW_End_2", true);
		setup(149, "149_Wall_Water_NE_End", true);
		setup(150, "150_Wall_Water_NW_End", true);
		setup(151, "151_Wall_Water_NE_End_2", true);
		
		setup(152, "152_Wall_Water_Stairs_SE", true);
		tile[152].solidArea = new Polygon(new int[] {0, 46, 46, 64, 64, 0}, new int[] {48, 48, 0, 0, 64, 64}, 6);
		setup(153, "153_Wall_Water_Stairs_SW", true);
		tile[153].solidArea = new Polygon(new int[] {0, 18, 18, 64, 64, 0}, new int[] {0, 0, 48, 48, 64, 64}, 6);
		setup(154, "154_Wall_Water_Stairs_NE", true);
		tile[154].solidArea = new Polygon(new int[] {0, 64, 64, 46, 46, 0}, new int[] {0, 0, 64, 64, 8, 8}, 6);
		setup(155, "155_Wall_Water_Stairs_NE_2", true);
		tile[155].solidArea = new Polygon(new int[] {0, 64, 64, 46, 46, 0}, new int[] {0, 0, 64, 64, 8, 8}, 6);
		setup(156, "156_Wall_Water_Stairs_NE_3", true);
		tile[156].solidArea = new Polygon(new int[] {0, 64, 64, 46, 46, 0}, new int[] {0, 0, 64, 64, 8, 8}, 6);
		setup(157, "157_Wall_Water_Stairs_NW", true);
		tile[157].solidArea = new Polygon(new int[] {0, 64, 64, 18, 18, 0}, new int[] {0, 0, 8, 8, 64, 64}, 6);
		setup(158, "158_Wall_Water_Stairs_NW_2", true);
		tile[158].solidArea = new Polygon(new int[] {0, 64, 64, 18, 18, 0}, new int[] {0, 0, 8, 8, 64, 64}, 6);
		setup(159, "159_Wall_Water_Stairs_NW_3", true);
		tile[159].solidArea = new Polygon(new int[] {0, 64, 64, 18, 18, 0}, new int[] {0, 0, 8, 8, 64, 64}, 6);
		setup(160, "160_Wall_S_3", true);
		setup(161, "161_Wall_S_2", true);

		setup(162, "162_Wall_Water_Flat_S", true);
		setup(163, "163_Wall_Water_Flat_N", true);
		setup(164, "164_Wall_Water_Flat_E", true);
		setup(165, "165_Wall_Water_Flat_W", true);
		setup(166, "166_Wall_Water_Flat_SE", true);
		setup(167, "167_Wall_Water_Flat_SW", true);
		setup(168, "168_Wall_Water_Flat_NE", true);
		setup(169, "169_Wall_Water_Flat_NW", true);
		setup(170, "170_Wall_Water_Flat_SE_End", true);
		setup(171, "171_Wall_Water_SE_End_2_F", true);
		setup(172, "172_Wall_Water_SE_End_3_F", true);
		setup(173, "173_Wall_Water_SW_End_F", true);
		setup(174, "174_Wall_Water_SW_End_2_F", true);
		setup(175, "175_Wall_Water_SW_End_3_F", true);
		setup(176, "176_Wall_Water_NE_End_F", true);
		setup(177, "177_Wall_Water_NE_End_2_F", true);
		setup(178, "178_Wall_Water_NE_End_3_F", true);
		setup(179, "179_Wall_Water_NW_End_F", true);
		setup(180, "180_Wall_Water_NW_End_2_F", true);
		setup(181, "181_Wall_Water_NW_End_3_F", true);
		
		setup(182, "182_Wall_Water_W_Shadow", true);
		setup(183, "183_Wall_Water_S_Reflection_F", true);
		setup(184, "184_Wall_Water_SE_End_Reflection_F", true);
		setup(185, "185_Wall_Water_SW_End_Refection_F", true);
		setup(186, "186_Wall_Water_SE_End_Refection_", true);
		setup(187, "187_Wall_Water_SW_End_Refection_", true);
		setup(188, "188_Wall_Water_SE_Shadow_Reflection_", true);
		setup(189, "189_Wall_Water_Stairs_Shadow_S", true);
		setup(190, "190_Wall_Water_S_Refection_", true);
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
	
	public void setup(int index, String imageName, boolean collision, int width, int height) {
		UtilityTool uTool = new UtilityTool();
		
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, width, height);
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
					String numbers[] = line.split(" ");
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
			//if (tileNum > 0 && tileNum < 4) {worldY -= gp.tileSize/2;} //large tiles
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
