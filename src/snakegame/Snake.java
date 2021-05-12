package snakegame;

import java.util.ArrayList;
import java.util.List;
import java.awt.Rectangle;

public class Snake {
	private List<Rectangle> snakeBody;
	private Movement snakeDirection;
	
	public Snake() {
		this.snakeBody = new ArrayList<>();

		Rectangle body;
		for (int i = 0; i < 4; i++) {
			body = new Rectangle(Game.DIM, Game.DIM);
			body.setLocation((Game.WIDTH / 2 - i) * Game.DIM, (Game.HEIGHT / 2) * Game.DIM);
			this.snakeBody.add(body);
		}
		
		this.snakeDirection = Movement.FROZEN;
 	}
 	
 	public void movement() {
		if (this.snakeDirection != Movement.FROZEN) {
		    this.elongate();
		    this.snakeBody.remove(this.snakeBody.size() - 1);
	    }
	}
    
	public void elongate() {
		Rectangle snakeHead = this.snakeBody.get(0);
		System.out.println("Snake head at: " + snakeHead.x / Game.DIM + " " + snakeHead.y / Game.DIM);
		Rectangle tmpRect = new Rectangle(Game.DIM, Game.DIM);
			
		if (this.snakeDirection == Movement.UP) tmpRect.setLocation(snakeHead.x, snakeHead.y - Game.DIM);
		if (this.snakeDirection == Movement.DOWN) tmpRect.setLocation(snakeHead.x, snakeHead.y + Game.DIM);
		if (this.snakeDirection == Movement.LEFT) tmpRect.setLocation(snakeHead.x - Game.DIM, snakeHead.y);
		if (this.snakeDirection == Movement.RIGHT) tmpRect.setLocation(snakeHead.x + Game.DIM, snakeHead.y);
			
		this.snakeBody.add(0, tmpRect);
	}
	
	public Rectangle getSnakeHead() {
		return this.snakeBody.get(0);

	}
	public List<Rectangle> getSnakeBody() {
		return this.snakeBody;
	}
	
	public Movement getSnakeDirection() {
		return this.snakeDirection;
	}
	
	public void setSnakeDirection(Movement snakeDirection) {
		//System.out.println("Snake direction changed: " + snakeDirection);
		this.snakeDirection = snakeDirection;
	}
	
}
