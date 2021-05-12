package snakegame;

import java.awt.*;
import java.util.Random;

public class Fruit {
	private int fruitNum = -1;
	public int x;
	public int y;
	private static Random random = new Random();
	
	public Fruit(Snake snake) {
		this.spawnFruit(snake);
	}
	
	public void spawnFruit(Snake snake) {
		this.fruitNum++;
		boolean onSnake = false;
		//int fruitX = 0;
		//int fruitY = 0;
		
		while (!onSnake) {
			onSnake = true;
			x = random.nextInt(Game.WIDTH - 2) + 1;
			y = random.nextInt(Game.HEIGHT - 2) + 1;
			
			for (Rectangle rect : snake.getSnakeBody()) {
				if (rect.x == x && rect.y == y) onSnake = false;
			}
		}
		
		System.out.println("Fruit at: " + x + " " + y);
	}
	
	public int getFruitNum() {
		return this.fruitNum;
	}
	
}
