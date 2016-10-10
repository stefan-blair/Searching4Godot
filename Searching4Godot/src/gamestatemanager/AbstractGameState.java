package gamestatemanager;

import java.awt.Graphics;

public abstract class AbstractGameState {

	public GameStateManager gsm;
	
	public AbstractGameState(GameStateManager gsm){
		this.gsm = gsm;
	}
	
	public abstract void tick();
	public abstract void paint(Graphics g);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
	
}
