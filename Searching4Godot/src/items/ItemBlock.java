package items;

import java.awt.Color;
import java.awt.Graphics;

import main.Screen;
import players.Player;
import entities.Environment;
import entities.Object;

public class ItemBlock extends Object{

	Item item;
	boolean active;
	
	public ItemBlock(int x, int y, int posX, int place, Environment environment, Item item) {
		super(x, y, posX, place, environment);
		this.item = item;
		active = true;
	}

	
	public void tick(){
		if(active)objectInteraction(player);
	}

	
	public void paint(Graphics g) {
		if(active){
			g.setColor(Color.orange);
			g.fillRect(getTotalX(), (int) y, Screen.TILESIZE, Screen.TILESIZE);
		}
	}

	
	public void objectInteraction(Player player) {
		if(isBetween((int)player.getY(), (int)y, (int)player.getY()+Screen.TILESIZE)||isBetween((int)player.getY(), (int)y+Screen.TILESIZE, (int)player.getY()+Screen.TILESIZE)){
			if(isBetween((int)player.getX(), getTotalX(), (int)player.getX()+Screen.TILESIZE*2) && player.getSwipeRight()){
				player.setSwipeRight(false);
				player.addItem(item);
				active = false;
			}
			else if(isBetween((int)player.getX()-Screen.TILESIZE, getTotalX()+Screen.TILESIZE, (int)player.getX()) && player.getSwipeLeft()){
				player.setSwipeLeft(false);
				player.addItem(item);
				active = false;
			}
		}
	}
	
	public Item getItem(){return this.item;}

	public void leftHit(boolean left) {}
	public void rightHit(boolean right) {}
	public boolean isStuck() {return false;}
	public void setStuck(boolean stuck) {}

}
