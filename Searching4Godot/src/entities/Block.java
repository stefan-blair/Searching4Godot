package entities;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import main.Screen;
import players.Player;

public class Block extends Object{
	
	public boolean goLeft, goRight, stuck;
	double speed;
	ImageIcon i;
	Image image;
	
	public Block(double x, double y, int place, Environment environment) {
		super((int)x, (int)y, 0, place, environment);
		goLeft = false;
		goRight = false;
		stuck = false;
		speed = 0;
		i = new ImageIcon(getClass().getResource("/Images/slide_tile.png"));
		image = i.getImage();
	}
	
	public void tick(){
		if(isOnScreen(posX))physics(goLeft, goRight);

		if(goRight && !wallRight && !stuck)x+=speed;
		if(goLeft && !wallLeft && !stuck)x-=speed;
		if(player != null)objectInteraction(player);
		afterMath();
	}
	
	public void paint(Graphics g){
		g.drawImage(image, (int)x+posX, (int)y, Screen.TILESIZE, Screen.TILESIZE, null);
	}
	
	public void leftHit(boolean goLeft){this.goLeft = goLeft;}
	public void rightHit(boolean goRight){this.goRight = goRight;}
	public void setX(int x){this.x = x;}
	public void setStuck(boolean stuck){this.stuck = stuck;}
	public boolean isStuck(){return this.stuck;}
	public double getSpeed(){return this.speed;}
	public void setTotalX(int x){}
	public void setSpeed(double speed){this.speed = speed;}

	public void objectInteraction(Player player) {
		if(!player.isGrab()) this.setStuck(false);
		if(isYBetween((int)this.y-3,(int)this.y+Screen.TILESIZE-4, (int)player.getY()) || isYBetween((int)this.y+5, (int)this.y+Screen.TILESIZE, (int)player.getY()+Screen.TILESIZE)){
			if(!this.isStuck() && isBetween(this.getTotalX(), (int)player.getX(),this.getTotalX()+Screen.TILESIZE+1)){				
				if(player.isGrab()){
					this.stuck = true;
				}else{
					this.goLeft = true;
					this.goRight = false;
					this.speed = player.getSpeed();
				}
			}
			else if(!this.isStuck() && isBetween(this.getTotalX()-1, (int)(player.getX()+Screen.TILESIZE),this.getTotalX()+Screen.TILESIZE)){
				if(player.isGrab()){
					this.stuck = true;
				}else{
					this.goLeft = false;
					this.goRight = true;
					this.speed = player.getSpeed();
				}
			}else{
				this.goLeft = false;
				this.goRight = false;
				this.speed = 0;
			}
		}else{
			this.speed = 0;
		}
		
		if(this.isStuck()){
			if(this.getTotalX() > player.getX()){
				goRight = true;
				this.x = (int)(player.getX()-environment.getX()+Screen.TILESIZE);
			}
			else{
				goLeft = true;
				this.x = (int)(player.getX()-environment.getX()-Screen.TILESIZE);
			}
			if((wallLeft && player.getLeft()) || (wallRight && player.getRight()) ){
				this.stuck = false;
				goLeft = false;
				goRight = false;
			}
		}		
	}
	
}
