package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;

import levelManager.Level;
import levelManager.LevelManager;
import main.Screen;
import fileManagment.Save;
import gamestatemanager.AbstractGameState;
import gamestatemanager.GameStateManager;

public class DeadState extends AbstractGameState{

		//Variables
		private String[] options = {"Restart Level","Menu"};
		private int listPosition;
		private LevelManager levelManager;
		private MainGameState mainGameState;
		//Variables
		
		public DeadState(GameStateManager gsm, LevelManager levelManager, MainGameState mainGameState){
			super(gsm);
			listPosition = 0;
			this.levelManager = levelManager;
			this.mainGameState = mainGameState;
		}
		
		public void tick() {
		
		}
		
		public void paint(Graphics g) {
			
			g.setColor(Color.cyan);
			g.fillRect(0, 0, Screen.WIDTH, Screen.HEIGHT);
			for(int i = 0; i < options.length; i++){
				if(i == listPosition){
					g.setColor(Color.green);
					g.setFont(new Font("Arial", Font.ITALIC, 100));
				}
				else{
					g.setColor(Color.black);
					g.setFont(new Font("Arial", Font.PLAIN, 72));
				}
				g.drawString(options[i], 450-(options[i].length()*15), 100+(100*i));
			}		
			
		}
		//"Resume","Restart Level","Save", "Options","Controls","Menu"
		public void keyPressed(int k) {
			if(k == KeyEvent.VK_W || k == KeyEvent.VK_UP){
				if(listPosition > 0)listPosition--;
				else listPosition = options.length-1;
			}
			if(k == KeyEvent.VK_S || k == KeyEvent.VK_DOWN){
				if(listPosition < options.length-1)listPosition++;
				else listPosition = 0;
			}
			
			if(k == KeyEvent.VK_ENTER){
				if(listPosition == 0){
					gsm.GameStates.pop();
					gsm.resetLevel();
				}
				else if(listPosition == 1)gsm.GameStates.push(new MenuState(gsm));
			}
			
		}
		
		public void keyReleased(int k) {
			
			
		}

}
