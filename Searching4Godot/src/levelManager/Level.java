package levelManager;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.swing.ImageIcon;

import main.Screen;
import players.Enemies;
import players.Player;
import entities.Environment;
import fileManagment.Save;

public class Level {

	Player player;
	Environment environment;
	Enemies enemies;
	LevelManager levelManager;
	
	Image torchlight;
	ImageIcon tl;
	
	public Level(Player player, Environment environment, Enemies enemies, LevelManager levelManager){
		this.player = player;
		this.environment = environment;
		this.enemies = enemies;
		this.levelManager = levelManager;
		
		tl = new ImageIcon(getClass().getResource("/Images/torchlight_filter.png"));
		torchlight = tl.getImage();
	}
	
	public void tick(){
		player.tick();
		environment.tick();
		enemies.tick((int)environment.getX());
		if(environment.finished)finish();
	}
	
	public void paint(Graphics g){
		enemies.paint(g);
		environment.paint(g);
		player.paint(g);
		//g.drawImage(torchlight, 0, (int)(player.getY()-Screen.HEIGHT*1.5), Screen.WIDTH, Screen.HEIGHT*3, null);
	}
	
	public void keyPressed(int k){
		player.keyPressed(k);
	}

	public void keyReleased(int k){
		player.keyReleased(k);
	}
	
	public void finish(){
		levelManager.level++;
		environment = new Environment(levelManager.level);
		enemies = new Enemies(levelManager.level, environment, player);
		player = new Player(Screen.WIDTH/2-Screen.TILESIZE/2, levelManager.getMapReader().getPlayerY(levelManager.level), environment);
		player.setY(levelManager.getMapReader().getPlayerY(levelManager.level));
		for(int i = 0; i < environment.getObjects().size(); i++){
			environment.getObjects().get(i).setPlayer(player);
		}
		levelManager.levels.push(new Level(player, environment,enemies, levelManager));
		try {
			Save.save(LevelManager.level, LevelManager.save);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void reset(){
		environment = new Environment(levelManager.level);
		enemies = new Enemies(levelManager.level, environment, player);
		player = new Player(Screen.WIDTH/2-Screen.TILESIZE/2, levelManager.getMapReader().getPlayerY(levelManager.level), environment);
		player.setY(levelManager.getMapReader().getPlayerY(levelManager.level));
		for(int i = 0; i < environment.getObjects().size(); i++){
			environment.getObjects().get(i).setPlayer(player);
		}
		levelManager.levels.push(new Level(player, environment,enemies, levelManager));
		try {
			Save.save(LevelManager.level, LevelManager.save);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
