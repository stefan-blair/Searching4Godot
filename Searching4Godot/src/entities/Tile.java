package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import main.Screen;

public class Tile {

	int x, y, posX, posY, height;
	String state;
	boolean solid, platform;
	
	Image image;
	ImageIcon i;
	
	public Tile(int x, int y, String state){
		this.x = x;
		this.y = y;
		this.state = state;
		
		if(state.equals("solid")){
			solid = true;
			i = new ImageIcon(getClass().getResource("/Images/base_tile.jpg"));
			image = i.getImage();
		}
		if(state.equals("platform")){
			platform = true;
			i = new ImageIcon(getClass().getResource("/Images/platform.png"));
			image = i.getImage();
		}
		if(solid)height = Screen.TILESIZE;
		else if(platform)height = Screen.TILESIZE/4;
	}
	
	public void paint(Graphics g){
		if(solid)g.drawImage(image, x+posX, y+posY, Screen.TILESIZE, height, null);
		else g.drawImage(image, x+posX, y+posY, Screen.TILESIZE, (int)(height*1.5), null);
	}
	
	//Getters and Setters
	
	public int getPosX(){return this.posX;}
	public int getPosY(){return this.posY;}
	public String getState(){
		if(solid)return "solid";
		if(platform)return "platform";
		
		return (String)null;
	}
	public int getX(){return this.x+this.posX;}
	public int getY(){return this.y+this.posY;}
	
	public void setPosX(int x){this.posX = x;}
	public void setPosY(int y){this.posY = y;}
	
}
