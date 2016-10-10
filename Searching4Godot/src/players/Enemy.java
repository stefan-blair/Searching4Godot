package players;

import java.awt.Graphics;

import entities.Environment;
import entities.Object;

public abstract class Enemy extends Object{

	public boolean alive;
	int health, range;
	Player player;
	
	public Enemy(int x, int y, int posX, int place, Player player, Environment environment) {
		super(x, y, posX, place, environment);
		this.player = player;
		alive = true;
	}
	
	public abstract void tick();
	public abstract void paint(Graphics g);
	
	public void setAlive(boolean alive){this.alive = alive;}
	public boolean isAlive(){return alive;}

	

	
	
}
