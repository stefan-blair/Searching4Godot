package players;

import items.Item;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import main.Screen;
import physics.Physics;
import entities.Environment;

public class Player extends Physics{

	boolean left, right, grab, swipeLeft, swipeRight, hit;
	double dx, width, height, speed;
	double currentVelocity, velocity, maxJumpHeight, fallRate, jumpRate;
	int health, attackTimer, hitTimer;
	ArrayList<Item> items, activeItems;
	Environment environment;
	
	ImageIcon i;
	Image image;

	public Player(double x, double y, Environment environment){
		super(x, y, -241,environment);
		items = new ArrayList<Item>();
		activeItems = new ArrayList<Item>();
		attackTimer = 0;
		hitTimer = 0;
		dx = 0;
		grab = false;
		this.environment = environment;
		width = Screen.TILESIZE;
		height = Screen.TILESIZE;
		hit = false;
		speed = 1;
		health = 10;
		
		i = new ImageIcon(getClass().getResource("/Images/godoat.png"));
		image = i.getImage();
	}
	
	public void itemManagment(){
		if(activeItems.size() > 0){
			for(int i = 0; i < activeItems.size(); i++){
				activeItems.get(i).afterEffect();
				if(!activeItems.get(i).getActive())activeItems.remove(i);
			}
		}
	}
	public void tick(){
		physics(this.left, this.right);	
		if(right && !wallRight)dx = -1 * speed;
		else if(left && !wallLeft)dx = speed;
		else dx = 0;
		environment.setX(environment.getX()+dx);
		if(swipeLeft || swipeRight)attackTimer++;
		if(attackTimer >= 60){
			swipeLeft = false;
			swipeRight = false;
			attackTimer = 0;
		}
		if(hit){
			hitTimer++;
			if(hitTimer >= 20){
				hitTimer = 0;
				hit = false;
				left = false;
				right = false;
				setHealth(this.health-1);
			}
		}
		
		afterMath();
		itemManagment();
	}
	
	public void paint(Graphics g){
		if(!hit){
			if(left)g.drawImage(this.image, (int)x-(Screen.TILESIZE/2)+Screen.TILESIZE, (int)y-(Screen.TILESIZE), -(int)(Screen.TILESIZE*2), (int)(Screen.TILESIZE*2.5),null);
			else g.drawImage(this.image, (int)x-(Screen.TILESIZE/2), (int)y-(Screen.TILESIZE), (int)(Screen.TILESIZE*2), (int)(Screen.TILESIZE*2.5),null);
		}
		else{
			g.setColor(Color.red);
			g.fillRect((int)this.x, (int)this.y, (int)width, (int)height);
		}
		if(swipeLeft){
			g.setColor(Color.red);
			g.fillRect((int)this.x-Screen.TILESIZE, (int)this.y, (int)width, (int)height);
		}
		else if(swipeRight){
			g.setColor(Color.red);
			g.fillRect((int)this.x+Screen.TILESIZE, (int)this.y, (int)width, (int)height);
		}
		g.setColor(Color.green);
		g.fillRect(Screen.WIDTH-(10*20)-10, 10, health*20, 30);
	}
	
	public void keyPressed(int k){
		if(k == KeyEvent.VK_A)left = true;
		if(k == KeyEvent.VK_D)right = true;
		if(k == KeyEvent.VK_LEFT)swipeLeft = true;
		if(k == KeyEvent.VK_RIGHT)swipeRight = true;
		if(k == KeyEvent.VK_DOWN)grab = true;
		if(k == KeyEvent.VK_S)crouching = true;
		if(k == KeyEvent.VK_W|| k == KeyEvent.VK_SPACE){
			if(!jumping && !falling)jumping = true;
		}

	}
	
	public void keyReleased(int k){
		if(k == KeyEvent.VK_A)left = false;
		if(k == KeyEvent.VK_D)right = false;
		if(k == KeyEvent.VK_S)crouching = false;
		if(k == KeyEvent.VK_DOWN)grab = false;
	}	
	
	//Getters and Setters
	public void addItem(Item item){
		this.items.add(item);
	}
	public void addActiveItem(Item item){
		item.effect();
		this.activeItems.add(item);
	}
	public void removeItem(int i){
		this.items.remove(i);
	}
	public void removeActiveItem(int i){
		this.activeItems.remove(i);
	}
	public ArrayList<Item> getItems(){return this.items;}
	
	public void setHit(boolean hit){this.hit = hit;}
	public void setRight(boolean right){this.right = right;}
	public void setLeft(boolean left){this.left = left;}
	public void setSwipeLeft(boolean swipeLeft){this.swipeLeft = swipeLeft;}
	public void setSwipeRight(boolean swipeRight){this.swipeRight = swipeRight;}
	public void setEnvironment(Environment environment){this.environment = environment;}
	public boolean getSwipeRight(){return this.swipeRight;}
	public boolean getSwipeLeft(){return this.swipeLeft;}
	public boolean isGrab(){return this.grab;}
	public boolean getLeft(){return this.left;}
	public boolean getRight(){return this.right;}
	public double getSpeed(){return this.speed;}
	public void setSpeed(double speed){this.speed = speed;}
	public void setHealth(int health){this.health=health;}
	public int getHealth() {return this.health;}
	
}
