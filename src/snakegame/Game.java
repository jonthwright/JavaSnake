package snakegame;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Game implements KeyListener {
	private Snake snake;
	private Fruit fruit;

	private JFrame window;
	private GameGraphics gameGFX;
	private long inputSleep;
	
	public static final int WIDTH = 50, HEIGHT = 50, DIM = 20;

	public Game() {
		this.window = new JFrame();
		
		this.snake = new Snake();
		this.fruit = new Fruit(this.snake);
		this.inputSleep = 100;
		
		this.gameGFX = new GameGraphics(this);
		this.window.add(gameGFX);

		this.window.setTitle("JavaSnake");
		this.window.setSize(WIDTH * DIM + 2, HEIGHT * DIM + DIM + 4);
		this.window.setVisible(true);
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void update() {
		if (this.gameGFX.getGameState() == GameState.RUNNING) {
			if (this.collideFruit()) {
				this.snake.elongate();
				this.fruit.spawnFruit(this.snake);
				
				if (this.inputSleep > 20 && this.fruit.getFruitEaten() % 5 == 0) {
					this.inputSleep -= 20;
					this.gameGFX.setTimer(this.inputSleep);
				}
			}
			
			if (this.collideWall() || this.collideSelf()) {
				this.gameGFX.setGameState(GameState.GAMEOVER);
			}
			
			this.snake.movement();
		}
	}
	
	public boolean collideWall() {
		Rectangle snakeHead = this.snake.getSnakeHead();
		return snakeHead.x < 0 || snakeHead.x >= WIDTH * DIM || snakeHead.y < 0 || snakeHead.y >= HEIGHT * DIM;
	}
	
	public boolean collideFruit() {
		Rectangle snakeHead = this.snake.getSnakeHead();
		return snakeHead.x / Game.DIM == fruit.x && snakeHead.y / Game.DIM == fruit.y;
	}
	
	public boolean collideSelf() {
		List<Rectangle> snakeBody = this.snake.getSnakeBody();
		Rectangle head = snake.getSnakeHead();
		for (int i = 1; i < snakeBody.size(); ++i) {
			Rectangle body = snakeBody.get(i);
			if (head.x == body.x && head.y == body.y) return true;
		}
		
		return false;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		final int KEY = e.getKeyCode();
		final Movement SNAKE_DIR = this.snake.getSnakeDirection();

		if (KEY == KeyEvent.VK_SPACE) {
			this.gameGFX.setGameState(GameState.RUNNING);
			this.snake.setSnakeDirection(Movement.RIGHT);
			return;
		}
		
		if (gameGFX.getGameState() == GameState.RUNNING) {
			if ((KEY == KeyEvent.VK_W || KEY == KeyEvent.VK_UP) && SNAKE_DIR != Movement.DOWN)
				this.snake.setSnakeDirection(Movement.UP);
			else if ((KEY == KeyEvent.VK_S || KEY == KeyEvent.VK_DOWN) && SNAKE_DIR != Movement.UP)
				this.snake.setSnakeDirection(Movement.DOWN);
			else if ((KEY == KeyEvent.VK_A || KEY == KeyEvent.VK_LEFT) && SNAKE_DIR != Movement.RIGHT)
				this.snake.setSnakeDirection(Movement.LEFT);
			else if ((KEY == KeyEvent.VK_D || KEY == KeyEvent.VK_RIGHT) && SNAKE_DIR != Movement.LEFT)
				this.snake.setSnakeDirection(Movement.RIGHT);
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
