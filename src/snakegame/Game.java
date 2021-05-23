package snakegame;

import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JFrame implements KeyListener {
	private GameGraphics gameGFX;
	public static final int WIDTH = 20, HEIGHT = 20, DIM = 20;
	private Movement prevSnakeMovement;
	
	public Game() {
		this.gameGFX = new GameGraphics(this);
		this.add(gameGFX);

		this.setTitle("JavaSnake :: Main Menu");
		this.setSize(this.DIM * (this.WIDTH + 1) - 4, this.DIM * (this.HEIGHT + 2) - 1);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public int getWindowWidth() {
		return this.DIM * (this.WIDTH + 1) - 4;
	}
	
	public int getWindowHeight() {
		return this.DIM * (this.HEIGHT + 2) - 1;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		final int KEY = e.getKeyCode();
		final Movement SNAKE_DIR = this.gameGFX.getSnake().getSnakeDirection();

		if (this.gameGFX.getGameState() == GameState.MENU && (KEY == KeyEvent.VK_SPACE || KEY == KeyEvent.VK_W
				|| KEY == KeyEvent.VK_UP || KEY == KeyEvent.VK_S || KEY == KeyEvent.VK_DOWN || KEY == KeyEvent.VK_A
				|| KEY == KeyEvent.VK_LEFT || KEY == KeyEvent.VK_D || KEY == KeyEvent.VK_RIGHT)) {
			this.gameGFX.setGameState(GameState.RUNNING);
			this.gameGFX.setSnakeMovement(Movement.RIGHT);
			this.setTitle("JavaSnake :: Current Score 0");

			return;
		}
		
		if (this.gameGFX.getGameState() == GameState.RUNNING) {
			if ((KEY == KeyEvent.VK_W || KEY == KeyEvent.VK_UP) && SNAKE_DIR != Movement.DOWN)
				this.gameGFX.setSnakeMovement(Movement.UP);
			else if ((KEY == KeyEvent.VK_S || KEY == KeyEvent.VK_DOWN) && SNAKE_DIR != Movement.UP)
				this.gameGFX.setSnakeMovement(Movement.DOWN);
			else if ((KEY == KeyEvent.VK_A || KEY == KeyEvent.VK_LEFT) && SNAKE_DIR != Movement.RIGHT)
				this.gameGFX.setSnakeMovement(Movement.LEFT);
			else if ((KEY == KeyEvent.VK_D || KEY == KeyEvent.VK_RIGHT) && SNAKE_DIR != Movement.LEFT)
				this.gameGFX.setSnakeMovement(Movement.RIGHT);
			
			if (KEY == KeyEvent.VK_SPACE || KEY == KeyEvent.VK_P) {
				if (this.gameGFX.getSnake().getSnakeDirection() != Movement.FROZEN && this.gameGFX.getGameState() != GameState.PAUSE) {
					this.setTitle(this.getTitle() + " :: PAUSED");
					this.prevSnakeMovement = this.gameGFX.getSnake().getSnakeDirection();
					this.gameGFX.setSnakeMovement(Movement.FROZEN);
					this.gameGFX.setGameState(GameState.PAUSE);
				}
			}
			
			return;
		}

		if (this.gameGFX.getGameState() == GameState.PAUSE && this.prevSnakeMovement != null
				&& (KEY == KeyEvent.VK_SPACE || KEY == KeyEvent.VK_P)) {
			this.setTitle(this.getTitle().replaceFirst("\s::\sPAUSED", ""));
			this.gameGFX.setSnakeMovement(this.prevSnakeMovement);
			this.gameGFX.setGameState(GameState.RUNNING);

			return;
		}
		
		if (this.gameGFX.getGameState() == GameState.GAMEOVER) {
			this.gameGFX.resetGame();
			if (KEY == KeyEvent.VK_R) {
				this.gameGFX.setGameState(GameState.RUNNING);
				this.gameGFX.setSnakeMovement(Movement.RIGHT);
				this.setTitle("JavaSnake :: Current Score 0");
			} else if (KEY == KeyEvent.VK_M) {
				this.gameGFX.setGameState(GameState.MENU);
				this.setTitle("JavaSnake :: Main Menu");
			}
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyReleased(KeyEvent e) {}
	
}
