package items;

import main.Screen;
import players.Player;

public class AntiGravity extends Item{

	int timer;
	
	public AntiGravity(Player player){
		super(player);
		timer = 15*Screen.FPS;
	}
	
	public void effect(){
		System.out.println("Anti-Gravity!!!");
		player.setGravity(.01);
	}
	
	public void afterEffect(){
		timer--;
		if(timer <= 0){
			player.setGravity(.04);
			this.active = false;
		}
	}
	
}
