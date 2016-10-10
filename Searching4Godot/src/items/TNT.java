package items;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

import main.Screen;
import players.Player;
import entities.Environment;
import entities.Object;

public class TNT extends Object{

	int range, timer;
	boolean explode;
	Random random;
	
	ImageIcon imageGrab;
	Image image, explosion;
	
	public TNT(int x, int y, int posX, int place, Environment environment) {
		super(x, y, posX, place, environment);
		this.range = 3*Screen.TILESIZE;
		this.timer = 10*60;
		this.explode = false;
		this.active = true;
		random = new Random();
		imageGrab = new ImageIcon(getClass().getResource("/Images/tnt.png"));
		image = imageGrab.getImage();
		imageGrab = new ImageIcon(getClass().getResource("/Images/explosion.png"));
		explosion = imageGrab.getImage();
	}

	public void tick() {
		if(active){
			if(timer > 0){
				timer--;
			}else {
				explode = true;
				this.active = false;
				timer = 60;
			}
		}else{
			if(timer > 0){
				timer--;
			}else{
				this.explode = false;
				this.place = -100;
				this.active = false;
			}
		}
	}

	public void paint(Graphics g) {		
		if(active)g.drawImage(image, (int)x+posX-10, (int)y-10, Screen.TILESIZE+10, Screen.TILESIZE+10, null);
		if(explode)g.drawImage(explosion, (int)x+posX-10-(int)(Screen.TILESIZE*1.5), (int)y-10-(int)(Screen.TILESIZE*1.5), Screen.TILESIZE*4, Screen.TILESIZE*4, null);
	}
	public boolean isExplode(){return this.explode;}
	public int getRange(){
		return this.range - random.nextInt(range);
	}
	
	public void objectInteraction(Player player) {
		
	}
	public void leftHit(boolean left) {}
	public void rightHit(boolean right) {}
	public boolean isStuck() {return false;}
	public void setStuck(boolean stuck) {}

}
