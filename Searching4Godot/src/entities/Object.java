package entities;

import java.awt.Graphics;

import physics.Physics;
import players.Player;

public abstract class Object extends Physics{
	
	protected int posX;
	protected Player player;
	protected Environment environment;
	protected boolean active;
	public Object(int x, int y, int posX, int place, Environment environment){
		super(x, y, place, environment);
		
		this.active = true;
		this.environment = environment;
		this.posX = posX;
	}
	
	public abstract void tick();
	public abstract void paint(Graphics g);
	
	public abstract void objectInteraction(Player player);
	public abstract void leftHit(boolean left);
	public abstract void rightHit(boolean right);
	public abstract boolean isStuck();
	public abstract void setStuck(boolean stuck);
	
	public boolean getActive(){return this.active;}
	public void setPlayer(Player player){this.player = player;}
	public int getTotalX(){return (int) (this.x + this.posX);}
	
	public int getPosX(){return this.posX;}
	public void setPosX(int x){
		this.posX = x;
		reSetPosX(this.posX);
	}

}
