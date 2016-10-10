package gamestates;

import entities.Environment;
import gamestatemanager.AbstractGameState;
import gamestatemanager.GameStateManager;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import levelManager.Level;
import levelManager.LevelManager;
import main.Screen;
import players.Enemies;
import players.Player;

public class MainGameState extends AbstractGameState{
	
	Player player;
	Environment environment;
	LevelManager levelManager;
	Enemies enemies;
	public int save;
	
	public MainGameState(GameStateManager gsm, int save){
		super(gsm);
		this.save = save;
		levelManager = new LevelManager(save);
		environment = new Environment(levelManager.level);
		player = new Player(Screen.WIDTH/2-Screen.TILESIZE/2, levelManager.getMapReader().getPlayerY(levelManager.level), environment);
		enemies = new Enemies(levelManager.level, environment, player);
		for(int i = 0; i < environment.getObjects().size(); i++){
			environment.getObjects().get(i).setPlayer(player);
			if(environment.getObjects().get(i).getClass().getSimpleName().equals("ItemBlock"))((items.ItemBlock) environment.getObjects().get(i)).getItem().setPlayer(player);
		}
		levelManager.levels.push(new Level(player, environment, enemies, levelManager));
	}
	
	public void tick(){
		levelManager.tick();
		if(player.getHealth() <= 0 || player.getY() >= Screen.HEIGHT){
			System.out.println(player.getHealth()+", "+player.getY()+", "+Screen.HEIGHT);
			gsm.GameStates.push(new DeadState(gsm, levelManager, this));
		}
	}
	
	public void paint(Graphics g){
		g.setColor(Color.black);
		g.fillRect(0, 0, Screen.WIDTH, Screen.HEIGHT);
		levelManager.paint(g);
	}
	
	public void keyPressed(int k){
		levelManager.keyPressed(k);
		if(k == KeyEvent.VK_P)gsm.GameStates.push(new PauseState(gsm, levelManager, this));
		if(k == KeyEvent.VK_E)gsm.GameStates.push(new InventoryState(gsm, player, player.getItems()));
	}
	
	public void keyReleased(int k){
		levelManager.keyReleased(k);
	}
	
	public Player getPlayer(){return this.player;}
	public Environment getEnvironment(){return this.environment;}
	public Enemies getEnemies(){return this.enemies;}
	
}