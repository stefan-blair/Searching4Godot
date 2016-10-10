package items;

import main.Screen;
import players.Player;

public class SpeedBoost extends Item{

	int timer;
	
	public SpeedBoost(Player player) {
		super(player);
		timer = 15*Screen.FPS;
	}
	
	public void effect() {
		player.setSpeed(2.5);
	}
	public void afterEffect() {
		timer--;
		if(timer <= 0){
			player.setSpeed(1);
			this.active = false;
		}
	}

}
