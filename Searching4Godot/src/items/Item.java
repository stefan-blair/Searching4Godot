package items;

import players.Player;

public abstract class Item {
	
	Player player;
	boolean active;
	
	public Item(Player player){
		this.player = player;
		active = true;
	}
	
	public abstract void effect();
	public abstract void afterEffect();
	
	public void setPlayer(Player player){this.player = player;}
	public boolean getActive(){return this.active;}

}
