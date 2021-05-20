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
	private Game game;
	private GameState gameState;

	public GameGraphics(Game game, int timerTime) {
		this.timer = new Timer(timerTime, this);
		this.timer.start();
		
		this.game = game;
		this.gameState = GameState.START;
		
		this.snake = game.getSnake();
		this.fruit = game.getFruit();
		
		this.addKeyListener(game);
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
	}
	
	public void paintComponent(Graphics gfx) {
		super.paintComponent(gfx);
		
		final int WIDTH = this.game.getWindowWidth(), HEIGHT = this.game.getWindowHeight(), DIM = this.game.DIM;

		Graphics2D g2d = (Graphics2D) gfx;
		g2d.setFont(new Font("Dialog", Font.BOLD,14));
		
		if (this.gameState == GameState.START) {
			g2d.setColor(Color.WHITE);

			String mainMenu = "CONTROLS: ARROW KEYS OR WASD KEYS\n\n\nPRESS [SPACE BAR]\nOR CONTROL KEYS TO START";
			drawCentredString(g2d, mainMenu, 0, 0, WIDTH, HEIGHT);
			return;
		}
		
		if (this.gameState == GameState.RUNNING) {
			g2d.setColor(Color.RED);
			g2d.fillOval(this.fruit.x, this.fruit.y, DIM, DIM);
			
			List<Rectangle> snakeBody = this.snake.getSnakeBody();
			
			g2d.setColor(Color.YELLOW);
			for (int i = 0; i < snakeBody.size(); ++i) {
				g2d.fill(snakeBody.get(i));
				if (i == 0) g2d.setColor(Color.GREEN);
			}
			
			return;
		}
		
		if (this.gameState == GameState.GAMEOVER) {
			g2d.setColor(Color.WHITE);

			final int FRUIT_EATEN = this.fruit.getFruitEaten();

			String gameoverText = "THE SNAKE SCORED " + FRUIT_EATEN + " FRUIT" + ((FRUIT_EATEN != 1) ? "S" : "");
			drawCentredString(g2d, gameoverText, 0, 0, WIDTH, HEIGHT);
			return;
		}
	}
	
	private void drawCentredString(Graphics2D g2d, final String string, int x, int y, int WIDTH, int HEIGHT) {

		String[] strings = string.split("\n");
		FontMetrics fm = g2d.getFontMetrics(g2d.getFont());

		Rectangle2D rect = fm.getStringBounds(getLongestString(strings), g2d);

		final int TEXT_WIDTH = (int) rect.getWidth(), TEXT_HEIGHT = (int) rect.getHeight();
		int TEXT_POS_X = x + (WIDTH - TEXT_WIDTH) / 2;
		int TEXT_POS_Y = y + (HEIGHT - TEXT_HEIGHT * strings.length) / 2 + fm.getAscent();

		final int LINE_HEIGHT = fm.getHeight();
		for (String line : strings)
			g2d.drawString(line, TEXT_POS_X, TEXT_POS_Y += LINE_HEIGHT);
	}
	
	private static String getLongestString(String[] array) {
		int maxLength = 0;
		String longestString = "";
		for (String s : array) {
			if (s.length() > maxLength) {
				maxLength = s.length();
				longestString = s;
			}
		}
		
		return longestString;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		game.update();
	}
	
	public void setGameState(GameState gs) {
		this.gameState = gs;
	}
	
	public GameState getGameState() {
		return this.gameState;
	}
	
	public void setTimer(int milliseconds) {
		this.timer.setDelay(milliseconds);
	}
	
}
