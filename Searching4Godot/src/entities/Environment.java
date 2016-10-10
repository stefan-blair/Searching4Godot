package entities;

import items.TNT;

import java.awt.Graphics;
import java.util.ArrayList;

import main.Screen;
import fileManagment.MapReader;

public class Environment {

	private double x;
	private ArrayList<Tile> Tiles;
	private ArrayList<Object>Objects;
	private ArrayList<TNT>Explosives;
	private MapReader reader;
	public boolean finished;

	public Environment(int level){
		System.out.println(level);
		x = Screen.WIDTH/2-Screen.TILESIZE/2;
		Explosives = new ArrayList<TNT>();
		reader = new MapReader(level);
		Tiles = reader.getTiles();
		Objects = reader.getObjects(this, Explosives);
		finished = false;
	}
	
	public void tick(){
		for(int i = 0; i < Explosives.size(); i++){
			Explosives.get(i).tick();
			Explosives.get(i).setPosX((int)this.x);
		}
		for(int i = 0; i < Tiles.size(); i++)Tiles.get(i).setPosX((int)this.x);
		for(int i = 0; i < Objects.size(); i++){
			Objects.get(i).setPosX((int)this.x);
			Objects.get(i).tick();
		}
	}
	
	public void paint(Graphics g){
		for(int i = 0; i < Explosives.size(); i++)Explosives.get(i).paint(g);
		for(int i = 0; i <Tiles.size(); i++)Tiles.get(i).paint(g);
		for(int i = 0; i <Objects.size(); i++)Objects.get(i).paint(g);
	}

	//Getters and Setters
	public double getX(){return this.x;}
	public ArrayList<Tile> getTiles(){return this.Tiles;}
	public ArrayList<Object> getObjects(){return this.Objects;}
	public ArrayList<TNT>getExplosives(){return this.Explosives;}
	public void setX(double x){this.x = x;}
	
}
