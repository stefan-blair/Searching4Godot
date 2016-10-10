package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import fileManagment.FontManager;

public class ControlState {

	String[] controls = {"W or SPACE = Jump", "A = Go Left", "D = Go Right", "S = Fall Through Platforms",
						"LEFT Arrow Key = Attack Left", "RIGHT Arrow Key = Attack Right", "DOWN Arrow Key = Grab Nearby Objects",
						"P = Pause", "E = Inventory"};
	Font plain = FontManager.getFont("8-BIT WONDER", "Plain", 20);
	Font bold = FontManager.getFont("8-BIT WONDER", "Bold", 25);
	
	public void paint(Graphics g){
		g.setColor(Color.black);
		g.setFont(plain);
		for(int i = 0; i < controls.length; i++){
			g.drawString(controls[i], 100, 75+50*i);
		}
		g.setFont(bold);
		g.drawString("Press ENTER to return to menu", 200, 550);
		
	}
	
}
