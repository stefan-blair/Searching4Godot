package main;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import fileManagment.Save;

public class Main {
	public static void main(String[] args){
		JFrame frame = new JFrame();
		Screen screen = new Screen();
		frame.setTitle("Mining");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(992,640);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());
		frame.add(screen, BorderLayout.CENTER);
		screen.requestFocusInWindow();
		frame.pack();
		Save.handleDirectory();
	}

}
