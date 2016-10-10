package players;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import main.Screen;
import entities.Environment;

public class Bat extends Enemy{

	int leftX, rightX, farDown, tarX, tarY;
	double dx, dy, flightTime, elapsedTime;
	boolean targeting, flying, left, right;
	
	ImageIcon imageGrab;
	Image image;
	
	public Bat(int x, int y, int posX, int place, Player player, Environment environment) {
		super(x, y, posX, place, player, environment);
		setGravity(0);
		flightTime = 4.5*60;
		elapsedTime = flightTime;
		flying = false;
		
		ArrayList<Integer> xList = new ArrayList<Integer>();
		ArrayList<Integer> yList = new ArrayList<Integer>();
		leftX = 0;
		rightX = Screen.WIDTH;
		farDown = Screen.HEIGHT;
		
		for(int i = 0; i < environment.getTiles().size(); i++){
			if(environment.getTiles().get(i).getX() == this.x){
				yList.add(environment.getTiles().get(i).getY());
			}
			if(environment.getTiles().get(i).getY() == this.y){
				xList.add(environment.getTiles().get(i).getX());
			}
		}
		for(int i = 0; i < xList.size()-1; i++){
			if(xList.get(i) > leftX && xList.get(i) < this.x){
				leftX = xList.get(i);
				rightX = xList.get(i+1);
			}
		}
		for(int i = 0; i < yList.size(); i++){
			if(yList.get(i) < farDown && yList.get(i) > y)farDown = yList.get(i);
		}
		
		imageGrab = new ImageIcon(getClass().getResource("/Images/bat.png"));
		image = imageGrab.getImage();
		
		leftX -= x;
		rightX -= x;
		
	}
	public void tick(){
		if(!player.isFalling() && !flying && player.getX() >= getTotalLeft() && player.getX() <= getTotalRight() && player.getY() <= farDown && player.getY() >= y){
			targeting = true;
		}
		if(targeting){
			tarX = (int)(player.getX()-environment.getX());
			tarY = (int)player.getY();
			dx = (getTotalX()-tarX)/flightTime;
			dy = (tarY-y)/flightTime;
			targeting = false;
			flying = true;
			System.out.println(dx+", "+dy);
		}
		if(flying){
			elapsedTime--;
			if(dx < 0){
				left = true;
				right = false;
			}
			else {
				right = true;
				left = false;
			}
			physics(left, right);
			tarX = (int)(player.getX()-environment.getX());
			tarY = (int)player.getY();
			dx = -1*(x-tarX)/elapsedTime;
			dy = (tarY-y)/elapsedTime;
			if(!wallRight && right)this.x += dx;
			if(!wallLeft && left)this.x += dx;
			this.y += dy;
			objectInteraction(player);
			afterMath();
		}
		
	}
	public void paint(Graphics g) {
		if(right)g.drawImage(image, (int)x+posX-10, (int)y-10, Screen.TILESIZE+10, Screen.TILESIZE+10, null);
		else g.drawImage(image, (int)x+posX+Screen.TILESIZE+10, (int)y-10,Screen.TILESIZE+10,Screen.TILESIZE+10, null);
	}
	public void objectInteraction(Player player) {
		
		if(isBetween((int)player.getY()-Screen.TILESIZE, (int)this.y, (int)player.getY()+Screen.TILESIZE*2)){
			if(isXBetween((int)player.getX()+Screen.TILESIZE/2, (int)(player.getX()+Screen.TILESIZE*2))){
				if(player.getSwipeRight()){
					player.setSwipeRight(false);
					alive = false;
				}else{
					player.setHit(true);
					player.setLeft(true);
					player.setRight(false);
					alive = false;
				}
			}
			else if(isBetween((int)player.getX()-Screen.TILESIZE, (int)this.x+Screen.TILESIZE,(int)player.getX()+Screen.TILESIZE/2)){
				if(player.getSwipeLeft()){
					player.setSwipeLeft(false);
					alive = false;
				}else{
					player.setHit(true);
					player.setRight(true);
					player.setLeft(false);
					alive = false;
				}
			}
		}
	}
	public int getTotalLeft(){
		return this.leftX + (int)getTotalX();
	}
	public int getTotalRight(){
		return this.rightX + (int)getTotalX();
	}
	public void setPosX(int x){
		this.posX = x;
		reSetPosX(this.posX);
	}
	public void leftHit(boolean left) {}
	public void rightHit(boolean right) {}
	public boolean isStuck() {return false;}
	public void setStuck(boolean stuck) {}

}
