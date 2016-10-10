package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import main.Screen;
import players.Player;

public class Stalagtites extends Object{
	
	boolean stuck;
	int lowY;
	
	ImageIcon i;
	Image image;
	
	public Stalagtites(int x, int y, int posX, int place,Environment environment) {
		super(x, y, posX, place, environment);
		i = new ImageIcon(getClass().getResource("/Images/stalag.png"));
		image = i.getImage();
		
		stuck = true;
		
		ArrayList<Integer>yList = new ArrayList<Integer>();
		lowY = Screen.HEIGHT;
		
		for(int i = 0; i < environment.getTiles().size(); i++){
			if(environment.getTiles().get(i).getX() == x)yList.add(environment.getTiles().get(i).getY());
		}
		for(int i = 0; i < yList.size(); i++){
			if(yList.get(i) < lowY && yList.get(i) >= y)lowY = yList.get(i);
		}
	}

	
	public void tick() {
		if(!stuck)physics(false, false);
		objectInteraction(player);
		if(!stuck)afterMath();
	}

	
	public void paint(Graphics g) {

		g.drawImage(this.image, getTotalX(), (int)y, Screen.TILESIZE, Screen.TILESIZE*2, null);
	}

	
	public void objectInteraction(Player player) {
		
		if(player.getY() >= this.y && player.getY() <= lowY){
			if(isBetween((int)(this.getTotalX()-(Screen.TILESIZE*3)), (int) player.getX(), (int)(this.getTotalX()+(Screen.TILESIZE*4)))){
				stuck = false;
			}
		}
		if(isYBetween((int)this.y-5, (int)this.y+Screen.TILESIZE, (int)player.getY())&&falling){
			if(isBetween(getTotalX()-5, (int)player.getX()+Screen.TILESIZE, getTotalX()+Screen.TILESIZE+5)){
				player.setHit(true);
				player.setLeft(true);
				player.setRight(false);
			}
			else if(isBetween(getTotalX()-5, (int)player.getX(), getTotalX()+Screen.TILESIZE+5)){
				player.setHit(true);
				player.setLeft(false);
				player.setRight(true);
			}
		}
		
	}

	
	public void leftHit(boolean left) {}
	public void rightHit(boolean right) {}
	public boolean isStuck() {return false;}
	public void setStuck(boolean stuck) {}

}
