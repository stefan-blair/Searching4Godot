package players;

import java.awt.Graphics;
import java.util.ArrayList;
import entities.Environment;
import fileManagment.MapReader;

public class Enemies {

	private int x;
	private ArrayList<Enemy>enemies;
	private MapReader reader;
	
	public Enemies(int level, Environment environment, Player player){
		x = (int)environment.getX();
		reader = new MapReader(level);
		enemies = reader.getEnemies(x, environment, player);
	}
	public void tick(int x){
		for(int i = 0; i < enemies.size(); i++){
			enemies.get(i).setPosX(x);
			enemies.get(i).tick();
			if(!enemies.get(i).isAlive())enemies.remove(i);
		}		
	}
	public void paint(Graphics g){
		for(int i = 0; i < enemies.size(); i++)enemies.get(i).paint(g);
	}
	
	public ArrayList<Enemy> getEnemies(){return enemies;}
	
}
