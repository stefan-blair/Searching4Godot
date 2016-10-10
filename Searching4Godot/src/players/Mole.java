package players;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import main.Screen;
import entities.Environment;

public class Mole extends Enemy{

	boolean goLeft, goRight;
	int counter;
	double speed;
	
	ImageIcon imageGrab;
	Image image;
	
	public Mole(int x, int y, int posX, int place, Player player, Environment environment) {
		super(x, y, posX, place, player, environment);
		counter = 0;
		speed = .2;
		
		imageGrab = new ImageIcon(getClass().getResource("/Images/mole.png"));
		image = imageGrab.getImage();
	}

	public void tick() {
		physics(goLeft, goRight);
		
		if(isYBetween((int)player.getY()-Screen.TILESIZE, (int)player.getY()+Screen.TILESIZE*2, (int)y) && isBetween(getTotalX()-Screen.TILESIZE*5, (int)player.getX(), getTotalX()+Screen.TILESIZE*6)){
			if(player.getX() > getTotalX()){
				goRight = true;
				goLeft = false;
			}
			else if(player.getX() < getTotalX()){
				goLeft = true;
				goRight = false;
			}
		}else{
			goRight = false;
			goLeft = false;
		}
		if(!wallLeft && goLeft)x-=speed;
		else if(!wallRight && goRight)x+=speed;
		
		objectInteraction(player);
	
		afterMath();
	}

	
	public void paint(Graphics g) {
		if(goRight)g.drawImage(image, (int)x+posX-10, (int)y-10, Screen.TILESIZE+10, Screen.TILESIZE+10, null);
		else g.drawImage(image, (int)x+posX+Screen.TILESIZE+10, (int)y-10, -Screen.TILESIZE-10, Screen.TILESIZE+10, null);
	}
	
	public void objectInteraction(Player player) {
	
		if(isBetween((int)player.getY()-5, (int)this.y, (int)player.getY()+Screen.TILESIZE)){
			if(isXBetween((int)player.getX()-Screen.TILESIZE/2, (int)(player.getX()+Screen.TILESIZE*1.5))){
				if(player.getSwipeRight()){
					player.setSwipeRight(false);
					alive = false;
				}else{
					player.setHit(true);
					player.setLeft(true);
					player.setRight(false);
				}
			}
			else if(isXBetween((int)player.getX()-5, (int)player.getX()+5)){
				if(player.getSwipeLeft()){
					player.setSwipeLeft(false);
					alive = false;
				}else{
					player.setHit(true);
					player.setRight(true);
					player.setLeft(false);
				}
			}
		}
		
	}
	
	public void leftHit(boolean left) {}
	public void rightHit(boolean right) {}
	public boolean isStuck() {return false;}
	public void setStuck(boolean stuck) {}

}
