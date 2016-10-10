package levelManager;

import java.awt.Graphics;
import java.io.IOException;
import java.util.Stack;

import fileManagment.MapReader;
import fileManagment.Save;

public class LevelManager {

	public Stack<Level>levels;
	public static int level, save;
	MapReader mapReader;
	
	public LevelManager(int save){
		levels = new Stack<Level>();
		try {
			level = Save.readSave(save);
		}catch (NumberFormatException e) {e.printStackTrace();
		}catch (IOException e) {e.printStackTrace();}
		mapReader = new MapReader(level);
		this.save = save;
	}
	
	public void tick(){
		levels.peek().tick();
	}
	
	public void paint(Graphics g){
		levels.peek().paint(g);
	}
	
	public void keyPressed(int k){
		levels.peek().keyPressed(k);
	}
	
	public void keyReleased(int k){
		levels.peek().keyReleased(k);
	}
	
	public MapReader getMapReader(){
		return this.mapReader;
	}
	
	public void reset(){
		levels.peek().reset();
	}
	
}
