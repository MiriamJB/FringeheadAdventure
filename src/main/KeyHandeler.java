package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel.gs;

public class KeyHandeler implements KeyListener{
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, 
	showDebugText, enterPressed, shotKeyPressed;
	
	public KeyHandeler(GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(gp.gameState == gs.TITLE) {
			titleState(code);
		} else if(gp.gameState == gs.PLAY) {
			playState(code);
		} else if (gp.gameState == gs.PAUSE) {
			pauseState(code);
		} else if (gp.gameState == gs.DIALOGUE) {
			dialogueState(code);
		} else if (gp.gameState == gs.CHARACTER) {
			characterState(code);
		}
	}
	
	public void titleState(int code) {
		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = 2;
			}
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			if(gp.ui.commandNum > 2) {
				gp.ui.commandNum = 0;
			}
		}
		if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
			if(gp.ui.commandNum == 0) {
				gp.gameState = gs.PLAY;
				//gp.playMusic(0);
			} else if (gp.ui.commandNum == 1) {
				//load game
			} else if (gp.ui.commandNum == 2) {
				System.exit(0);
			}
		}
	}
	
	public void playState(int code) {
		if(code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = true;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
		}
		if(code == KeyEvent.VK_P) {
			gp.gameState = gs.PAUSE;
		}
		if(code == KeyEvent.VK_C) {
			gp.gameState = gs.CHARACTER;
		}
		if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
			enterPressed = true;
		}
		if(code == KeyEvent.VK_F) {
			shotKeyPressed = true;
		}
		//debug
		if(code == KeyEvent.VK_T) {
			if(showDebugText == true) {
				showDebugText = false;
			}else if(showDebugText == false) {
				showDebugText = true;
			}
		}
	}
	
	public void pauseState(int code) {
		if(code == KeyEvent.VK_P) {
			gp.gameState = gs.PLAY;
		}
	}
	
	public void dialogueState (int code) {
		if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
			gp.gameState = gs.PLAY;
		}
	}
	
	public void characterState (int code) {
		if(code == KeyEvent.VK_C) {
			gp.gameState = gs.PLAY;
		}
		if(code == KeyEvent.VK_W) {
			if(gp.ui.slotRow == 0) {
				gp.ui.slotRow = 3;
			} else {
				gp.ui.slotRow--;
			}
			gp.playSE(9);
		}
		if(code == KeyEvent.VK_A) {
			if (gp.ui.slotCol == 0) {
				gp.ui.slotCol = 4;
			} else {
				gp.ui.slotCol--;
			}
			gp.playSE(9);
		}
		if(code == KeyEvent.VK_S) {
			if (gp.ui.slotRow == 3) {
				gp.ui.slotRow = 0;
			} else {
				gp.ui.slotRow++;
			}
			gp.playSE(9);
		}
		if(code == KeyEvent.VK_D) {
			if (gp.ui.slotCol == 4) {
				gp.ui.slotCol = 0;
			} else {
				gp.ui.slotCol++;
			}
			gp.playSE(9);
		}
		if(code == KeyEvent.VK_ENTER) {
			gp.player.selectItem();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}	
		if(code == KeyEvent.VK_F) {
			shotKeyPressed = false;
		}
	}

}
