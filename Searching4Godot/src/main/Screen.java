package main;

import gamestatemanager.GameStateManager;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Screen extends JPanel implements Runnable, KeyListener{
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 992;
	public static final int HEIGHT = 640;
	public static final int TILESIZE = 32;
	public static final int FPS = 60;
	
	private Thread thread;
	GameStateManager gsm;
	
	private boolean running;
	private long targetTime = FPS/1000;
	
	private Image doubleBufferImage;
	private Graphics doubleBufferGraphics;
	
	public Screen(){
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		
		addKeyListener(this);
		setFocusable(true);
				
		Start();
	}
	
	public void Start(){
		running = true;
		gsm = new GameStateManager();
		
		thread = new Thread(this);
		thread.start();
		
	}
	
	public void run() {
		long start, elapsed, wait;
		while(running){
			start = System.nanoTime();
			tick();
			repaint();
			elapsed = System.nanoTime() - start;
			wait = targetTime - elapsed / 1000000;
			
			if(wait <= 0){
				wait = 5;
			}
			
			try{
				Thread.sleep(wait);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void tick(){
		gsm.tick();
	}
	
	public void paint(Graphics g){
		doubleBufferImage = createImage(getWidth(), getHeight());
		doubleBufferGraphics = doubleBufferImage.getGraphics();
		paintComponent(doubleBufferGraphics);
		g.drawImage(doubleBufferImage, 0, 0, this);
	}
	
	public void paintComponent(Graphics g){
		gsm.paint(g);
		
		repaint();
	}
	
	public void keyPressed(KeyEvent k) {
		gsm.keyPressed(k.getKeyCode());
	}
	
	public void keyReleased(KeyEvent k) {
		gsm.keyReleased(k.getKeyCode());
	}
	
	public void keyTyped(KeyEvent k) {}
	
}
