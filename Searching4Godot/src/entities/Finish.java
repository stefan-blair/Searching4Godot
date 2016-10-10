package entities;

import java.awt.Color;
import java.awt.Graphics;

import main.Screen;
import players.Player;

public class Finish extends Object{
		
	public Finish(int x, int y, int posX, int place, Player player, Environment environment) {
		super(x, y, posX, place, environment);
	}

	public void tick() {
		if(player!=null){
			objectInteraction(player);
		}
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect(getTotalX(),(int)y, Screen.TILESIZE, Screen.TILESIZE);
	}

	public void objectInteraction(Player player) {
		if(isYBetween((int)this.y-5, (int)this.y+Screen.TILESIZE+5, (int)player.getY())){
			if(isBetween(getTotalX()-5, (int)player.getX(), getTotalX()+Screen.TILESIZE+5)){
				environment.finished = true;
			}
		}
	}
	public void leftHit(boolean left) {}
	public void rightHit(boolean right) {}
	public boolean isStuck() {return false;}
	public void setStuck(boolean stuck) {}

}
