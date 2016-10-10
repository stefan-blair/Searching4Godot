package gamestates;

import fileManagment.FontManager;
import fileManagment.Save;
import gamestatemanager.AbstractGameState;
import gamestatemanager.GameStateManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;



import java.io.IOException;

import javax.swing.JOptionPane;

import main.Screen;

public class MenuState extends AbstractGameState{

	//Variables
	private String[] options = {"Start","Options","Controls","Exit"};
	private String[] startOptions = {"Save 1", "Save 2", "Save 3", "Back"};
	private String[] saveOptions = {"Continue", "New Game","Back"};
	private int list, listPosition, save;
	private boolean controlState, optionState;
	private ControlState controls;
	private Font bold, plain;
	//Variables
	
	public MenuState(GameStateManager gsm){
		super(gsm);
		list = 0;
		listPosition = 0;
		save = 1;
		controls = new ControlState();
		controlState = false;
		optionState = false;
		plain = FontManager.getFont("8-BIT WONDER", "Plain", 52);
		bold = FontManager.getFont("8-BIT WONDER", "Bold", 72);
		
	}
	
	public void tick() {
	
	}
	
	public void paint(Graphics g) {
		
		g.setColor(Color.cyan);
		g.fillRect(0, 0, Screen.WIDTH, Screen.HEIGHT);
		//Controls
		if(controlState)controls.paint(g);
		//Menu
		else if(list == 0)for(int i = 0; i < options.length; i++){
			if(i == listPosition){
				g.setColor(Color.green);
				g.setFont(bold);
			}
			else{
				g.setColor(Color.black);
				g.setFont(plain);
			}
			g.drawString(options[i], 420-(options[i].length()*15), 180+(100*i));
		}
		//Start
		else if(list == 1)for(int i = 0; i < startOptions.length; i++){
			if(i == listPosition){
				g.setColor(Color.green);
				g.setFont(bold);
			}
			else{
				g.setColor(Color.black);
				g.setFont(plain);
			}
			g.drawString(startOptions[i], 400-(startOptions[i].length()*15), 200+(100*i));
		}
		//Save
		else if(list == 2)for(int i = 0; i < saveOptions.length; i++){
			if(i == listPosition){
				g.setColor(Color.green);
				g.setFont(bold);
			}
			else{
				g.setColor(Color.black);
				g.setFont(plain);
			}
			g.drawString(saveOptions[i], 400-(saveOptions[i].length()*15), 200+(100*i));
		}
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_W || k == KeyEvent.VK_UP){
			if(list == 0){
				if(listPosition > 0)listPosition--;
				else listPosition = options.length-1;
			}
			else if(list == 1){
				if(listPosition > 0)listPosition--;
				else listPosition = startOptions.length-1;
			}
			else if(list == 2){
				if(listPosition > 0)listPosition--;
				else listPosition = saveOptions.length-1;
			}
		}
		if(k == KeyEvent.VK_S || k == KeyEvent.VK_DOWN){
			if(list == 0){
				if(listPosition < options.length-1)listPosition++;
				else listPosition = 0;
			}
			else if(list == 1){
				if(listPosition < startOptions.length-1)listPosition++;
				else listPosition = 0;
			}
			else if(list == 2){
				if(listPosition < saveOptions.length-1)listPosition++;
				else listPosition = 0;
			}
			
		}
		
		//Options
		if(k == KeyEvent.VK_ENTER){
			if(list == 0 && !controlState){
				if(options[listPosition].equals("Start"))list++;
				else if(options[listPosition].equals("Controls"))controlState = true;
				else if(options[listPosition].equals("Exit"))System.exit(1);
			}
			else if(list == 1){
				if(listPosition == 0){
					list++;
					save = 1;
				}
				else if(listPosition == 1){
					list++;
					save = 2;
				}
				else if(listPosition == 2){
					list++;
					save = 3;
				}
				else if(listPosition == 3){
					list--;
				}
			}
			else if(list == 2){
				if(listPosition == 0){
					gsm.GameStates.push(new MainGameState(gsm, save));
				}
				if(listPosition == 1){
					int reply = JOptionPane.showConfirmDialog(null, "By starting a new game, you will clear pre-saved data.  Proceed?", "WARNING!!!", JOptionPane.YES_NO_OPTION);
					if(reply == JOptionPane.YES_OPTION){
						try {
							Save.save(1, save);
						}catch (IOException e) {e.printStackTrace();}
						gsm.GameStates.push(new MainGameState(gsm, save));
					}
				}
				if(listPosition == 2){
					list--;
				}
			}
			else if(controlState)controlState = false;
			listPosition = 0;
		}
		
	}
	
	public void keyReleased(int k) {
		
		
	}
	
}
