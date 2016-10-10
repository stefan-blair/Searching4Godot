package gamestatemanager;

import gamestates.MainGameState;
import gamestates.MenuState;

import java.awt.Graphics;
import java.util.Stack;

import levelManager.LevelManager;

public class GameStateManager {
	
	public Stack<AbstractGameState>GameStates;
	
	public GameStateManager(){
		GameStates = new Stack<AbstractGameState>();
		GameStates.push(new MenuState(this));
	}
	
	public void tick(){
		GameStates.peek().tick();
	}
	
	public void paint(Graphics g){
		GameStates.peek().paint(g);
	}
	
	public void keyPressed(int k){
		GameStates.peek().keyPressed(k);
	}
	
	public void keyReleased(int k){
		GameStates.peek().keyReleased(k);
	}
	
	public void resetLevel(){
		GameStates.pop();
		GameStates.push(new MainGameState(this, 1));
	}
	
}
