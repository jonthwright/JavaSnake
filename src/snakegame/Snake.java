package snakegame;

import java.util.ArrayList;
import java.util.List;
import java.awt.Rectangle;

public class Snake {
	private List<Rectangle> snakeBody;
	private Movement snakeDirection;
	
	public Snake() {
		this.snakeBody = new ArrayList<Rectangle>();

		Rectangle body;
		for (int i = 0; i < 4; i++) {
			body = new Rectangle(Game.DIM, Game.DIM);
			body.setLocation((Game.WIDTH / 2 - i) * Game.DIM, (Game.HEIGHT / 2) * Game.DIM);
			this.snakeBody.add(body);
		}
		
 	}

	public List<Rectangle> getSnakeBody() {
		return this.snakeBody;
	}
	
	public Movement getSnakeDirection() {
		return this.snakeDirection;
	}
	
	public void elongate() {
	
	}
	
	public void collision() {
	
	}
}
