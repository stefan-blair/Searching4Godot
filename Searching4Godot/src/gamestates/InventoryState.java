package gamestates;

import fileManagment.FontManager;
import gamestatemanager.AbstractGameState;
import gamestatemanager.GameStateManager;
import items.Item;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import main.Screen;
import players.Player;

public class InventoryState extends AbstractGameState{
	
	ArrayList<Item> items;
	Player player;
	Font bold, plain;
	int selected;
	
	public InventoryState(GameStateManager gsm, Player player, ArrayList<Item> arrayList){
		super(gsm);
		this.player = player;
		bold = FontManager.getFont("8-BIT WONDER", "Plain", 35);
		plain = FontManager.getFont("8-BIT WONDER", "Bold", 35);
		this.items = arrayList;
		selected = 0;
	}
	
	public void tick(){
		
	}
	
	public void paint(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(0, 0, Screen.WIDTH, Screen.HEIGHT);
		int x = 100;
		int y = 0;
		for(int i = 0; i < items.size(); i++){
			if(i == selected){
				g.setColor(Color.green);
				g.setFont(bold);
			}else{
				g.setColor(Color.black);
				g.setFont(plain);
			}
			g.drawString(items.get(i).getClass().getSimpleName(), x, 50+40*y);
			y++;
			if(y > 14){
				y = 0;
				x += 200;
			}
		}
	}
	
	public void keyPressed(int k){
		if(k == KeyEvent.VK_BACK_SPACE)gsm.GameStates.pop();
		if(k == KeyEvent.VK_W || k == KeyEvent.VK_UP){
			if(selected > 0)selected--;
			else selected = items.size()-1;
			
		}
		if(k == KeyEvent.VK_S || k == KeyEvent.VK_DOWN){
			if(selected < items.size()-1)selected++;
			else selected = 0;
		}
		if(k == KeyEvent.VK_ENTER){
			player.addActiveItem(items.get(selected));
			player.removeItem(selected);
			selected--;
		}
	}

	public void keyReleased(int k){
		
	}
	
}
