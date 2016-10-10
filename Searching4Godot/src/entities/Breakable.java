package entities;

import items.TNT;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import main.Screen;
import players.Player;

public class Breakable extends Object{
	
	int health;
	String message;
	ArrayList<TNT>Explosives;

	public Breakable(int x, int y, int posX, int place, Environment environment, ArrayList<TNT> Explosives) {
		super(x, y, posX, place, environment);
		this.health = 8;
		this.message = "HELLO";
		this.Explosives = Explosives;
	}

	
	public void tick() {
		if(active){
			objectInteraction(player);
			physics(false, false);
		}
		if(health <= 0){
			this.active = false;
			this.place = -100;
		}
	}
	public void paint(Graphics g) {
		if(active){
			g.setColor(new Color((int)(health*21.25),(int)(health*13.875),(int)(health*7.5)));
			g.fillRect(this.getTotalX(), (int)this.getY(), Screen.TILESIZE, Screen.TILESIZE);
		}
	}
	public void objectInteraction(Player player) {
		if(isBetween((int)this.y, (int)(player.getY()+(Screen.TILESIZE/2)), (int)this.y+Screen.TILESIZE)){
			if(isBetween((int)player.getX(), getTotalX(), (int)(player.getX()+Screen.TILESIZE*1.5)) && player.getSwipeRight()){
				player.setSwipeRight(false);
				this.health -= 1;
			}
			else if(isBetween((int)player.getX()-Screen.TILESIZE/2, getTotalX()+Screen.TILESIZE, (int)player.getX()) && player.getSwipeLeft()){
				player.setSwipeLeft(false);
				this.health -= 1;
			}
		}
		for(int i = 0; i < this.Explosives.size(); i++){
			if(Explosives.get(i).isExplode()){
				if(isBetween((int)(Explosives.get(i).getY()-Explosives.get(i).getRange()), (int)this.y, (int)(Explosives.get(i).getY()+Explosives.get(i).getRange()))){
					if(isBetween((int)(Explosives.get(i).getTotalX()-Explosives.get(i).getRange()), (int)this.getTotalX(), (int)(Explosives.get(i).getTotalX()+Explosives.get(i).getRange()))){
						this.health = -1;
					}
				}
			}
		}
	}
	public void leftHit(boolean left) {
	}
	public void rightHit(boolean right) {
	}
	public boolean isStuck() {return false;}
	public void setStuck(boolean stuck) {}
	
	public String getMessage(){return this.message;}

}
