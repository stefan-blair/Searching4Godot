package items;

import main.Screen;
import players.Player;
import entities.Environment;

public class TNTitem extends Item{

	Environment environment;
	
	public TNTitem(Player player, Environment environment) {
		super(player);
		
		this.environment = environment;
	}

	public void effect() {
		
		environment.getExplosives().add(new TNT((int)(player.getX()-environment.getX()), (int)player.getY(), (int) environment.getX(), 0, environment));
	}

	public void afterEffect() {
		this.active = false;
	}

}
