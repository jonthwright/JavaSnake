package snakegame;

import java.awt.*;
import java.util.Random;

public class Fruit {
	private int fruitEaten = -1;
	public int x;
	public int y;
	private static Random random = new Random();
	
	public Fruit(Snake snake) {
		this.spawnFruit(snake);
	}
	
	public void spawnFruit(Snake snake) {
		this.fruitEaten++;
		boolean onSnake = false;
		
		while (!onSnake) {
			onSnake = true;
			x = random.nextInt(Game.WIDTH - 1) * Game.DIM;
			y = random.nextInt(Game.HEIGHT - 1) * Game.DIM;
			
			for (Rectangle sbod : snake.getSnakeBody()) {
				if (sbod.x == x && sbod.y == y) onSnake = false;
			}
		}
	}
	
	public int getFruitEaten() {
		return this.fruitEaten;
	}
	
}
