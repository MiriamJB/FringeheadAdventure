package main;

import entity.NPC_Brook;
import monster.MON_Crab;
import object.OBJ_Axe;
import object.OBJ_Chest;
import object.OBJ_Coin;
import object.OBJ_Door;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_Manafish;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Red;
import object.OBJ_Spear;
import tile_interactive.IT_BigTree;
import tile_interactive.IT_LittleTree;
import tile_interactive.IT_MedTree;

public class AssetSetter {

	GamePanel gp;
	int row = 0;
	int col = 0;
	int treeIndex = 0;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		int i = 0;
		gp.obj[i] = new OBJ_Potion_Red(gp);
		gp.obj[i].worldX = gp.tileSize*38;
		gp.obj[i].worldY = gp.tileSize*29;
		i++;
		gp.obj[i] = new OBJ_Shield_Red(gp);
		gp.obj[i].worldX = (int) (gp.tileSize*16.5);
		gp.obj[i].worldY = (int) (gp.tileSize*15.5);
		i++;
		gp.obj[i] = new OBJ_Axe(gp);
		gp.obj[i].worldX = gp.tileSize*25;
		gp.obj[i].worldY = gp.tileSize*32;
		i++;
	}
	
	public void setNPC() {
		int i = 0;
		gp.npc[i] = new NPC_Brook(gp);
		gp.npc[i].worldX = gp.tileSize*30;
		gp.npc[i].worldY = gp.tileSize*20;
		i++;
	}
	
	public void setMonster() {
		int i = 0;
		gp.monster[i] = new MON_Crab(gp);
		gp.monster[i].worldX = gp.tileSize*25;
		gp.monster[i].worldY = gp.tileSize*33;
		i++;
		gp.monster[i] = new MON_Crab(gp);
		gp.monster[i].worldX = gp.tileSize*23;
		gp.monster[i].worldY = gp.tileSize*33;
		i++;
		gp.monster[i] = new MON_Crab(gp);
		gp.monster[i].worldX = gp.tileSize*27;
		gp.monster[i].worldY = gp.tileSize*33;
		i++;
		gp.monster[i] = new MON_Crab(gp);
		gp.monster[i].worldX = gp.tileSize*25;
		gp.monster[i].worldY = gp.tileSize*31;
		i++;
		gp.monster[i] = new MON_Crab(gp);
		gp.monster[i].worldX = gp.tileSize*23;
		gp.monster[i].worldY = gp.tileSize*31;
		i++;
		gp.monster[i] = new MON_Crab(gp);
		gp.monster[i].worldX = gp.tileSize*27;
		gp.monster[i].worldY = gp.tileSize*31;
		i++;
	}
	
	public void setInteractiveTile() {
		int i = 0;
		gp.iTile[i] = new IT_LittleTree(gp, 21, 25); i++;	
		gp.iTile[i] = new IT_LittleTree(gp, 20, 25); i++;	
		//gp.iTile[i] = new IT_LittleTree(gp, 25, 28); i++;	
		gp.iTile[i] = new IT_LittleTree(gp, 34, 27); i++;	
		gp.iTile[i] = new IT_LittleTree(gp, 35, 27); i++;
		gp.iTile[i] = new IT_LittleTree(gp, 35, 28); i++;	
		gp.iTile[i] = new IT_LittleTree(gp, 20, 14); i++;	
		gp.iTile[i] = new IT_LittleTree(gp, 20, 15); i++;	
		gp.iTile[i] = new IT_LittleTree(gp, 19, 15); i++;	
		gp.iTile[i] = new IT_LittleTree(gp, 12, 19); i++;	
		gp.iTile[i] = new IT_LittleTree(gp, 12, 18); i++;	
		gp.iTile[i] = new IT_LittleTree(gp, 13, 18); i++;	
		gp.iTile[i] = new IT_LittleTree(gp, 14, 18); i++;	
		gp.iTile[i] = new IT_LittleTree(gp, 15, 18); i++;	

	}
	
	public void setTrees() {
		while(row < gp.maxWorldRow) {
			if (gp.tileM.mapTileNum[col][row] == 1) {
				gp.trees[treeIndex] = new IT_BigTree(gp, col, row);
				treeIndex++;
			}
			if (gp.tileM.mapTileNum[col][row] == 2) {
				gp.trees[treeIndex] = new IT_MedTree(gp, col, row);
				treeIndex++;
			}
			col++;
			if(col >= gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
	}
	
	public void setTrees2() {
		int i = 0;
		gp.iTile[i] = new IT_MedTree(gp, 10, 35); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 10, 40); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 11, 11); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 11, 33); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 12, 11); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 12, 19); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 12, 32); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 13, 11); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 13, 19); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 13, 31); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 14, 10); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 14, 19); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 14, 30); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 14, 31); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 15, 19); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 15, 29); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 15, 5); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 16, 7); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 17, 18); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 17, 28); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 18, 17); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 18, 28); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 19, 16); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 19, 26); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 20, 10); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 20, 16); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 20, 26); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 21, 16); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 21, 26); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 22, 22); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 22, 27); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 23, 28); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 24, 29); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 28, 22); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 28, 27); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 29, 27); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 30, 22); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 30, 26); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 31, 26); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 32, 22); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 32, 27); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 33, 27); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 34, 22); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 34, 28); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 35, 29); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 36, 22); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 36, 30); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 37, 26); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 37, 31); i++;	
		gp.iTile[i] = new IT_MedTree(gp, 38, 31); i++;	
//		gp.iTile[i] = new IT_MedTree(gp, 38, 38); i++;	
//		gp.iTile[i] = new IT_MedTree(gp, 32, 27); i++;	
//		gp.iTile[i] = new IT_MedTree(gp, 39, 25); i++;	
//		gp.iTile[i] = new IT_MedTree(gp, 39, 31); i++;
//		gp.iTile[i] = new IT_MedTree(gp, 40, 12); i++;	
//		gp.iTile[i] = new IT_MedTree(gp, 40, 14); i++;	
//		gp.iTile[i] = new IT_MedTree(gp, 40, 16); i++;	
//		gp.iTile[i] = new IT_MedTree(gp, 40, 18); i++;	
//		gp.iTile[i] = new IT_MedTree(gp, 40, 20); i++;	
//		gp.iTile[i] = new IT_MedTree(gp, 40, 24); i++;	
//		gp.iTile[i] = new IT_MedTree(gp, 40, 40); i++;	
//		gp.iTile[i] = new IT_MedTree(gp, 40, 31); i++;	
	}
}
