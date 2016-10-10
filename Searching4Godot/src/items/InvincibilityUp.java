package items;

import main.Screen;
import players.Player;

public class InvincibilityUp extends Item{

	int timer, setHealth;
	
	public InvincibilityUp(Player player) {
		super(player);
		timer = 15*Screen.FPS;
	}

	public void effect() {
		this.setHealth = player.getHealth();
	}
	
	public void afterEffect() {
		timer--;
		player.setHealth(setHealth);
		if(timer <= 0){
			this.active = false;
		}
	}

}
