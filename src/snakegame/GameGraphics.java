package snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GameGraphics extends JPanel implements ActionListener {
	private Timer timer = new Timer(100, this);
	private Snake snake;
	private Fruit fruit;
	private Game game;
	private GameState gameState;

	public GameGraphics(Game game) {
		this.timer.start();
		
		this.game = game;
		this.gameState = GameState.START;
		
		this.snake = game.getSnake();
		this.fruit = game.getFruit();
		
		
		this.addKeyListener(game);
		this.setBackground(Color.BLACK  );
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
	}
	
	public void paintComponent(Graphics gfx) {
		super.paintComponent(gfx);
		
		final int WIDTH = this.game.WIDTH;
		final int HEIGHT = this.game.HEIGHT;
		final int DIM = this.game.DIM;
		
		Graphics2D g2d = (Graphics2D) gfx;
		
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, (WIDTH * DIM), (HEIGHT * DIM));

		if (this.gameState == GameState.START) {
			g2d.setColor(Color.WHITE);
			g2d.drawString("PRESS ANY KEY TO START!", (WIDTH / 2) * DIM - 40, (HEIGHT / 2) * DIM - 20);
			
			return;
		}
		
		if (this.gameState == GameState.RUNNING) {
			g2d.setColor(Color.RED);
			g2d.fillRect(this.fruit.x * DIM, this.fruit.y * DIM, DIM, DIM);
			
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
			g2d.drawString("THE SNAKE HAS EATEN " + this.fruit.getFruitEaten() + " FRUITS", (WIDTH / 2) * DIM - 40, (HEIGHT / 2) * DIM - 20);
			
			return;
		}
		
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
	
	public void setTimer(long milliseconds) {
		this.timer = new Timer(100, this);
	}
	
}
