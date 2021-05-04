package snakegame;

import javax.swing.JFrame;

public class Game {
	private Snake snake;
	private Fruit fruit;

	private JFrame window;
	
	public static final int WIDTH = 50, HEIGHT = 50, DIM = 20;

	public Game() {
		window = new JFrame();
		window.setTitle("JavaSnake");
		window.setSize(WIDTH * DIM, HEIGHT * DIM);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
