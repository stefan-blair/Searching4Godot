package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import main.Screen;
import players.Player;

public class Crumbler extends Object{

	boolean shaking, fall;
	int timer, reTimer, saveX, saveY, respawnRate, shakeTime;
	
	ImageIcon imageGrab;
	Image image;
	
	public Crumbler(int x, int y, int posX, int place, Environment environment) {
		super(x, y, posX, place, environment);
		this.saveX = x;
		this.saveY = y;
		respawnRate = 5*60;
		init();
		imageGrab = new ImageIcon(getClass().getResource("/Images/crummbler.png"));
		image = imageGrab.getImage();
	}

	public void init(){
		fall = false;
		shaking = false;
		timer = 0;
		reTimer = 0;
		this.x = saveX;
		this.y = saveY;
		falling = false;
	}
	
	public void tick() {
		if(player != null)objectInteraction(player);
		if(shaking){
			timer++;
			if(timer > 15){
				timer = 0;
				fall = true;
			}
		}
		if(fall){
			physics(false, false);
			afterMath();
			reTimer++;
			if(reTimer > respawnRate){
				init();
			}
		}
	}

	public void paint(Graphics g) {
		g.drawImage(image, (int)getTotalX(), (int)this.y, null);
	}
	public void objectInteraction(Player player) {
		if(isBetween((int)this.y-5, (int)player.getY()+Screen.TILESIZE, (int)this.y+Screen.TILESIZE)){
			if(isBetween(getTotalX(), (int)player.getX(), getTotalX()+Screen.TILESIZE) || isBetween(getTotalX(), (int)player.getX()+Screen.TILESIZE, getTotalX()+Screen.TILESIZE)){
				shaking = true;
			}
		}
	}
	
	public void leftHit(boolean left) {}
	public void rightHit(boolean right) {}
	public boolean isStuck() {return false;}
	public void setStuck(boolean stuck) {}

}
