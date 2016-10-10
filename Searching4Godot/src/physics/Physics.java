package physics;

import java.io.IOException;

import main.Screen;
import entities.Environment;

public abstract class Physics {

	int width, height, posX;
	protected int place;
	int hitTile;
	protected boolean jumping, falling, onTile, onObject, crouching;
	public boolean wallLeft;
	public boolean wallRight;
	protected boolean wall;
	protected Environment environment;
	protected double velocity, currentVelocity, x, y, jumpRate, fallRate, maxJumpHeight, gravity;
	
	public Physics(double x, double y,int place, Environment environment){
		this.environment = environment;
		this.width = Screen.TILESIZE;
		this.height = Screen.TILESIZE;
		this.place = place;
		posX = 0;
		this.x = x;
		this.y = y;
		jumping = false;
		falling = false;
		onTile = false;
		onObject = false;
		wallLeft = false;
		wallRight = false;
		wall = false;
		hitTile = 0;

		maxJumpHeight = -1.5;
		gravity = .04;
		fallRate = gravity;
		jumpRate = -gravity;
		
		currentVelocity = 0;
		velocity = .04;
	
	}
	
	public void physics(boolean left, boolean right){
		if(jumping){
			velocity = jumpRate;
			if(currentVelocity < maxJumpHeight){
				jumping = false;
			}
		}
		else if(falling){
			velocity = fallRate;
		}
		else {
			velocity = 0;
			currentVelocity = 0;
		}
		
		y += currentVelocity;
		currentVelocity+=velocity;
		if(!jumping){
			for(int i = 0; i < environment.getTiles().size();i++){
				if(isXBetween(environment.getTiles().get(i).getX(), environment.getTiles().get(i).getX()+Screen.TILESIZE)){
					if(isYBetween(environment.getTiles().get(i).getY(), environment.getTiles().get(i).getY()+Screen.TILESIZE, (int)(y+width))){
						if(environment.getTiles().get(i).getState().equals("solid") || this.crouching == false){
							onTile = true;
							hitTile = i;
						}else if(environment.getTiles().get(i).getState().equals("platform") || this.crouching)onTile = false;
					}
				}

				if (i< environment.getObjects().size() && i != place && !(environment.getObjects().get(i).getPlace() == -100)){
					if(isXBetween(environment.getObjects().get(i).getTotalX(), environment.getObjects().get(i).getTotalX()+Screen.TILESIZE))
						if(isYBetween((int)environment.getObjects().get(i).getY(), (int)environment.getObjects().get(i).getY()+Screen.TILESIZE, (int)(y+width))){
							onObject = true;
							hitTile = i;
						}

				}
			}
			if(!onTile && !onObject)falling = true;
			else{
				falling = false;
				currentVelocity = 0;
				if(onTile && environment.getTiles().get(hitTile).getState().equals("solid")){
					this.y = environment.getTiles().get(hitTile).getY()-Screen.TILESIZE;
				}
				else if(onObject){
					try{
						this.y = environment.getObjects().get(hitTile).getY()-Screen.TILESIZE;
					}catch(IndexOutOfBoundsException e){
						
					}
				}
			}
			onTile = false;
			onObject = false;
		}
		for(int i = 0; i < environment.getTiles().size(); i++){
			if(environment.getTiles().get(i).getState().equals("solid")){
				if(isXBetween(environment.getTiles().get(i).getX(), environment.getTiles().get(i).getX()+Screen.TILESIZE)){
					int tileY = environment.getTiles().get(i).getY();
					if(currentVelocity < 0 && y >= tileY+(3*Screen.TILESIZE/4) && y <= tileY + Screen.TILESIZE){
						falling = true;
						jumping = false;
						currentVelocity = 0;
					}
				}
				if(isYBetween(environment.getTiles().get(i).getY(), environment.getTiles().get(i).getY()+Screen.TILESIZE-2, (int)y) || isYBetween(environment.getTiles().get(i).getY()+3, environment.getTiles().get(i).getY()+Screen.TILESIZE, (int)y+Screen.TILESIZE)){
					if(left && isBetween(environment.getTiles().get(i).getX(), (int)x+posX,  environment.getTiles().get(i).getX()+Screen.TILESIZE)){				
						wallLeft = true;
						wall = true;
					}
					if(right && isBetween(environment.getTiles().get(i).getX(), (int)(x +posX+ Screen.TILESIZE),environment.getTiles().get(i).getX()+Screen.TILESIZE)){
						wallRight = true;
						wall = true;
					}
				}
			}if(i < environment.getObjects().size() && i != place && !(environment.getObjects().get(i).getPlace() == -100)){
				if(isXBetween(environment.getObjects().get(i).getTotalX(), environment.getObjects().get(i).getTotalX()+Screen.TILESIZE)){
					int tileY = (int) environment.getObjects().get(i).getY();
					if(currentVelocity < 0 && y >= tileY+(3*Screen.TILESIZE/4) && y <= tileY + Screen.TILESIZE){
						falling = true;
						jumping = false;
						currentVelocity = 0;
					}
				}
				if(isYBetween((int)environment.getObjects().get(i).getY(),(int)environment.getObjects().get(i).getY()+Screen.TILESIZE-5, (int)y) || isYBetween((int)environment.getObjects().get(i).getY()+3,(int) environment.getObjects().get(i).getY()+Screen.TILESIZE, (int)y+Screen.TILESIZE)){
					if(left && isBetween(environment.getObjects().get(i).getTotalX(), (int)x+posX,  environment.getObjects().get(i).getTotalX()+Screen.TILESIZE)){				
						wallLeft = true;
						wall = true;
					}
					if(right && isBetween(environment.getObjects().get(i).getTotalX(), (int)(x +posX+ Screen.TILESIZE),environment.getObjects().get(i).getTotalX()+Screen.TILESIZE)){
						wallRight = true;
						wall = true;
					}
				}
			}
		}
	}
	public void afterMath(){
		if(!wall){
			wallLeft = false;
			wallRight = false;
		}
		wall = false;
	}
	public boolean isXBetween(int a, int b){
		if(this.x+posX + 2 >= a && this.x+posX+2 <= b){			
			return true;
		}
		if(this.x+posX+this.width-2 >= a && this.x+posX+this.width-2 <= b){
			return true;
		}
		else return false;
	}
	public boolean isBetween(int a, int b,  int c){
		if(b >= a && b <= c){			
			return true;
		}
		else return false;
	}
	public boolean isYBetween(int a, int b, int y){
		if(y >= a && y <= b){
			return true;
		}
		else return false;
	}
	
	public boolean isOnScreen(int posX){
		if(this.x+posX >= 0 && this.x+posX <= Screen.WIDTH && this.y >= 0 && this.y <= Screen.HEIGHT)return true;
		else return false;
	}
	public void reSetPosX(int posX){this.posX = posX;}
	public void setY(int y){this.y = y;}
	public void setPhysicsEnvironment(Environment environment){this.environment = environment;}
	public void setGravity(double gravity){
		this.gravity = gravity;
		fallRate = gravity;
		jumpRate = -gravity;
	}
	public boolean isFalling(){return this.falling;}
	public double getX(){return this.x;}
	public double getY(){return this.y;}
	public int getPlace(){return this.place;}

}
