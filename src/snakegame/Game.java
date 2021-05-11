package snakegame;

import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements KeyListener {
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
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		Movement snakeDir = snake.getSnakeDirection();
		
		if (snakeDir == Movement.LEFT || snakeDir == Movement.RIGHT) {
			if ((key == KeyEvent.VK_W || key == KeyEvent.VK_UP)) {}
			if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {}
		}
		
		if (snakeDir == Movement.UP || snakeDir == Movement.DOWN) {
			if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {}
			if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {}
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyReleased(KeyEvent e) {}
	
	public Snake getSnake() {
		return this.snake;
	}
	
	public Fruit getFruit() {
		return this.fruit;
	}
	
}
