package snakegame;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class Game implements KeyListener {
	private Snake snake;
	private Fruit fruit;
	private int timerTime = 150;

	private JFrame window;
	private GameGraphics gameGFX;
	public static final int WIDTH = 20, HEIGHT = 20, DIM = 20;
	
	public Game() {
		this.window = new JFrame();
		
		this.snake = new Snake();
		this.fruit = new Fruit(this.snake);
		
		this.gameGFX = new GameGraphics(this, timerTime);
		this.window.add(gameGFX);

		this.window.setTitle("JavaSnake");
		this.window.setSize(this.DIM * (this.WIDTH + 1) - 4, this.DIM * (this.HEIGHT + 2) - 1);
		this.window.setVisible(true);
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public int getWindowWidth() {
		return this.DIM * (this.WIDTH + 1) - 4;
	}
	
	public int getWindowHeight() {
		return this.DIM * (this.HEIGHT + 2) - 1;
	}
	
	public void update() {
		if (this.gameGFX.getGameState() == GameState.RUNNING) {
			if (this.collideFruit()) {
				this.snake.elongate();
				this.fruit.spawnFruit(this.snake);
				
				if (this.timerTime > 40 && this.fruit.getFruitEaten() % 5 == 0)
					this.gameGFX.setTimer(this.timerTime -= this.timerTime > 110 ? 20 : this.timerTime >= 80 ? 15 : 10);
			}
			
			if (collideWall() || collideWithSelf()) {
				gameGFX.setGameState(GameState.GAMEOVER);
			} else {
				this.snake.movement();
			}
		}
	}
	
	private boolean collideWall() {
		Rectangle snakeHead = this.snake.getSnakeHead();
		return snakeHead.x < 0 || snakeHead.x >= WIDTH * DIM || snakeHead.y < 0 || snakeHead.y >= HEIGHT * DIM;
	}
	
	private boolean collideFruit() {
		Rectangle snakeHead = this.snake.getSnakeHead();
		return snakeHead.x == fruit.x && snakeHead.y == fruit.y;
	}
	
	private boolean collideWithSelf() {
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

		if (this.gameGFX.getGameState() == GameState.START && (KEY == KeyEvent.VK_SPACE || KEY == KeyEvent.VK_W
				|| KEY == KeyEvent.VK_UP || KEY == KeyEvent.VK_S || KEY == KeyEvent.VK_DOWN || KEY == KeyEvent.VK_A
				|| KEY == KeyEvent.VK_LEFT || KEY == KeyEvent.VK_D || KEY == KeyEvent.VK_RIGHT)) {
			this.gameGFX.setGameState(GameState.RUNNING);
			this.snake.setSnakeDirection(Movement.RIGHT);
			return;
		}
		
		if (this.gameGFX.getGameState() == GameState.RUNNING) {
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
