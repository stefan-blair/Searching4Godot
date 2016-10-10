package fileManagment;

import items.AntiGravity;
import items.ItemBlock;
import items.SpeedBoost;
import items.TNT;
import items.TNTitem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import main.Screen;
import players.Bat;
import players.Enemy;
import players.Mole;
import players.Player;
import entities.Block;
import entities.Breakable;
import entities.Crumbler;
import entities.Environment;
import entities.Finish;
import entities.Object;
import entities.Stalagtites;
import entities.Tile;

public class MapReader {
	
	int level;
	
	public MapReader(int level){
		this.level = level;
	}

	public ArrayList<entities.Object> getObjects(Environment environment, ArrayList<TNT> explosives){
		ArrayList<entities.Object>readObjects = new ArrayList<entities.Object>();
		ArrayList<String>lines = new ArrayList<String>();
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/Maps/"+level+".txt")));
			String line = null;
			while((line = reader.readLine()) != null){
				lines.add(line);
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int place = 0;
		for(int y = 0; y < lines.size(); y++){
			for(int x = 0; x < lines.get(y).length(); x++){
				if(String.valueOf(lines.get(y).charAt(x)).equals("3")){
					readObjects.add(new Block(x*Screen.TILESIZE, y*Screen.TILESIZE, place, environment));
					place++;
				}
				else if(String.valueOf(lines.get(y).charAt(x)).equals("4")){
					readObjects.add(new Stalagtites(x * Screen.TILESIZE, y*Screen.TILESIZE,(int)environment.getX(), place, environment));
					place++;
				}
				else if(String.valueOf(lines.get(y).charAt(x)).equals("5")){
					readObjects.add(new Crumbler(x * Screen.TILESIZE, y*Screen.TILESIZE, (int)environment.getX(), place, environment));
					place++;
				}
				else if(String.valueOf(lines.get(y).charAt(x)).equals("8")){
					readObjects.add(new Breakable(x * Screen.TILESIZE, y*Screen.TILESIZE, (int)environment.getX(), place, environment, explosives));
					place++;
				}
				else if(String.valueOf(lines.get(y).charAt(x)).equals("F")){
					readObjects.add(new Finish(x*Screen.TILESIZE, y*Screen.TILESIZE, (int)environment.getX(), -100, null, environment));
					place++;
				}
				else if(String.valueOf(lines.get(y).charAt(x)).equals("G")){
					readObjects.add(new ItemBlock(x*Screen.TILESIZE, y*Screen.TILESIZE, (int)environment.getX(), -100, environment, new AntiGravity(null)));
					place++;
				}
				else if(String.valueOf(lines.get(y).charAt(x)).equals("S")){
					readObjects.add(new ItemBlock(x*Screen.TILESIZE, y*Screen.TILESIZE, (int)environment.getX(), -100, environment, new SpeedBoost(null)));
					place++;
				}
				else if(String.valueOf(lines.get(y).charAt(x)).equals("X")){
					readObjects.add(new ItemBlock(x*Screen.TILESIZE, y*Screen.TILESIZE, (int)environment.getX(), -100, environment, new TNTitem(null, environment)));
					place++;
				}
			}
		}
		return readObjects;
	}
	public ArrayList<Tile> getTiles(){
		ArrayList<Tile>readTiles = new ArrayList<Tile>();
		ArrayList<String>lines = new ArrayList<String>();
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/Maps/"+level+".txt")));
			String line = null;
			while((line = reader.readLine()) != null){
				lines.add(line);
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int y = 0; y < lines.size(); y++){
			for(int x = 0; x < lines.get(y).length(); x++){
				if(String.valueOf(lines.get(y).charAt(x)).equals("1"))readTiles.add(new Tile(x*Screen.TILESIZE, y*Screen.TILESIZE, "solid"));
				else if(String.valueOf(lines.get(y).charAt(x)).equals("2"))readTiles.add(new Tile(x * Screen.TILESIZE, y*Screen.TILESIZE, "platform"));
			}
		}
		return readTiles;
	}
	public int getPlayerY(int level){
		int place = 0;
		ArrayList<String>lines = new ArrayList<String>();
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/Maps/"+level+".txt")));
			String line = null;
			while((line = reader.readLine()) != null){
				lines.add(line);
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for(int y = 0; y < lines.size(); y++){
			for(int x = 0; x < lines.get(y).length(); x++){
				if(String.valueOf(lines.get(y).charAt(x)).equals("P"))
					place = y*Screen.TILESIZE;
			}
		}
			
		return place;
	}
	public ArrayList<Enemy> getEnemies(int posX, Environment environment, Player player){
		ArrayList<Enemy>readEnemies = new ArrayList<Enemy>();
		ArrayList<String>lines = new ArrayList<String>();
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/Maps/"+level+".txt")));
			String line = null;
			while((line = reader.readLine()) != null){
				lines.add(line);
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int y = 0; y < lines.size(); y++){
			for(int x = 0; x < lines.get(y).length(); x++){
				if(String.valueOf(lines.get(y).charAt(x)).equals("6"))readEnemies.add(new Mole(x*Screen.TILESIZE, y*Screen.TILESIZE, posX, -1, player, environment));
				else if(String.valueOf(lines.get(y).charAt(x)).equals("7"))readEnemies.add(new Bat(x*Screen.TILESIZE, y*Screen.TILESIZE, posX, -1, player, environment));
			}
		}
		return readEnemies;
	}
}

