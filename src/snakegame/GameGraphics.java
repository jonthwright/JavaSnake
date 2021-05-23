package snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class GameGraphics extends JPanel implements ActionListener {
	private Timer timer;
	private Snake snake;
	private Fruit fruit;
	private GameState gameState;
	private Game game;

	public GameGraphics(Game game) {
		this.timer = new Timer(150, this);
		this.timer.start();
		
		this.game = game;
		this.gameState = GameState.MENU;
		
		this.snake = new Snake();
		this.fruit = new Fruit(this.snake);
		
		this.addKeyListener(game);
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
	}
	
	public void paintComponent(Graphics gfx) {
		super.paintComponent(gfx);
		
		final int WIDTH = this.game.getWindowWidth(), HEIGHT = this.game.getWindowHeight(), DIM = this.game.DIM;

		final Graphics2D g2d = (Graphics2D) gfx;
		g2d.setFont(new Font("Dialog", Font.BOLD,14));
		
		if (this.gameState == GameState.MENU) {
			g2d.setColor(Color.WHITE);
			final String MAIN_MENU = "CONTROLS: ARROW KEYS OR WASD KEYS\n\n\nPRESS [SPACE BAR]\nOR CONTROL KEYS TO START";
			drawCentredString(g2d, MAIN_MENU, 0, -50, WIDTH, HEIGHT, new Font("Dialog", Font.BOLD, 14));

			return;
		}
		
		if (this.gameState == GameState.RUNNING || this.gameState == GameState.PAUSE) {
			g2d.setColor(Color.RED);
			g2d.fillOval(this.fruit.x, this.fruit.y, DIM, DIM);
			
			List<Rectangle> snakeBody = this.snake.getSnakeBody();
			
			g2d.setColor(Color.YELLOW);
			for (int i = 0; i < snakeBody.size(); ++i) {
				g2d.fill(snakeBody.get(i));
				if (i == 0) g2d.setColor(Color.GREEN);
			}
			
			if (this.gameState == GameState.PAUSE) {
				g2d.setColor(Color.WHITE);
				final String PAUSE_TEXT = "GAME PAUSED";
				drawCentredString(g2d, PAUSE_TEXT, 0, -50, WIDTH, HEIGHT, new Font("Dialog", Font.BOLD, 30));
			}
			
			return;
		}
		
		if (this.gameState == GameState.GAMEOVER) {
			g2d.setColor(Color.WHITE);

			final int FRUIT_EATEN = this.fruit.getFruitEaten();

			String gameoverText = "THE SNAKE SCORED " + FRUIT_EATEN + " FRUIT" + ((FRUIT_EATEN != 1) ? "S" : "");
			gameoverText += "\n\n\nPRESS M: GO BACK TO MAIN MENU";
			gameoverText += "\nPRESS R: START A NEW GAME";
			
			drawCentredString(g2d, gameoverText, 0, -50,WIDTH, HEIGHT, new Font("Dialog", Font.BOLD, 14));
		}
	}
	
	private void drawCentredString(final Graphics2D g2d, final String string, int x, int y, int WIDTH, int HEIGHT, Font font) {

		String[] strings = string.split("\n");
		g2d.setFont(font);
		FontMetrics fm = g2d.getFontMetrics(font);

		Rectangle2D rect = fm.getStringBounds(getLongestString(strings), g2d);

		final int TEXT_WIDTH = (int) rect.getWidth(), TEXT_HEIGHT = (int) rect.getHeight();
		int TEXT_POS_X = x + (WIDTH - TEXT_WIDTH) / 2;
		int TEXT_POS_Y = y + (HEIGHT - TEXT_HEIGHT * strings.length) / 2 + fm.getAscent();

		final int LINE_HEIGHT = fm.getHeight();

		for (String line : strings) g2d.drawString(line, TEXT_POS_X, TEXT_POS_Y += LINE_HEIGHT);
	}
	
	private static String getLongestString(final String[] strings) {
		int maxLength = 0;
		String longestString = "";

		for (String s : strings) {
			if (s.length() > maxLength) {
				maxLength = s.length();
				longestString = s;
			}
		}
		
		return longestString;
	}
	
	public void update() {
		if (this.gameState == GameState.RUNNING) {
			if (this.snake.collideFruit(fruit)) {
				this.snake.elongate();
				this.fruit.spawnFruit(this.snake);
				this.game.setTitle("JavaSnake :: Current Score " + fruit.getFruitEaten());
				int timerDelay = this.timer.getDelay();
				
				if (timerDelay > 40 && this.fruit.getFruitEaten() % 5 == 0)
					this.timer.setDelay(timerDelay -
							(timerDelay > 110 ? 20 : timerDelay >= 80 ? 15 : timerDelay >= 60 ? 10 : 5));
			}
			
			if (snake.collideWall() || snake.collideWithSelf()) {
				this.gameState = GameState.GAMEOVER;
				this.game.setTitle("JavaSnake :: Game Over");
			} else {
				this.snake.movement();
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		update();
	}
	
	public void resetGame() {
		this.snake = new Snake();
		this.fruit = new Fruit(this.snake);
		this.timer.setDelay(150);
		
	}
	
	public Snake getSnake() {
		return this.snake;
	}
	
	public void setGameState(GameState gamestate) {
		this.gameState = gamestate;
	}
	
	public GameState getGameState() {
		return this.gameState;
	}
	
	public void setSnakeMovement(Movement newSnakeMov) {
		this.snake.setSnakeMovement(newSnakeMov);
	}
}
