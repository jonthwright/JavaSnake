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
	
	public void setSnakeMovement(Movement newSnakeMov) {
		//If snake is moving (i.e., not start at game and not frozen), check validity of new snake direction movement
		//Enables snake to automatically start moving once a game has started
		if (this.snakeDirection != Movement.FROZEN) {
			Rectangle head = this.getSnakeHead(), firstBodySec = this.snakeBody.get(1);
			
			//Don't change snake movement to a horizontal movement (x-axis) if vertical movement (y-axis) hasn't changed
			if ((newSnakeMov == Movement.LEFT || newSnakeMov == Movement.RIGHT) && head.y == firstBodySec.y) return;

			//Don't change snake movement to a vertical movement (y-axis) if horizontal movement (x-axis) hasn't changed
			if ((newSnakeMov == Movement.UP || newSnakeMov == Movement.DOWN) && head.x == firstBodySec.x) return;
		}
		
		this.snakeDirection = newSnakeMov;
	}
	
	public boolean collideWall() {
		Rectangle snakeHead = getSnakeHead();
		return snakeHead.x < 0 || snakeHead.x >= Game.WIDTH * Game.DIM || snakeHead.y < 0 || snakeHead.y >= Game.HEIGHT * Game.DIM;
	}
	
	public boolean collideFruit(Fruit fruit) {
		Rectangle snakeHead = getSnakeHead();
		return snakeHead.x == fruit.x && snakeHead.y == fruit.y;
	}
	
	public boolean collideWithSelf() {
		Rectangle head = getSnakeHead();
		
		for (int i = 1; i < this.snakeBody.size(); ++i) {
			Rectangle body = this.snakeBody.get(i);
			if (head.x == body.x && head.y == body.y) return true;
		}
		
		return false;
	}
	
}
