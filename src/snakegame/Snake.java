package snakegame;

import java.util.ArrayList;
import java.util.List;
import java.awt.Rectangle;

public class Snake {
	private List<Rectangle> snakeBody;
	
	public Snake() {
		this.snakeBody = new ArrayList<Rectangle>();

		Rectangle body;
		for (int i = 0; i < 4; i++) {
			body = new Rectangle(Game.DIM, Game.DIM);
			body.setLocation((Game.WIDTH / 2 - i) * Game.DIM, (Game.HEIGHT / 2 - i) * Game.DIM);
			this.snakeBody.add(body);
		}
		
 	}

	public List<Rectangle> getSnakeBody() {
		return this.snakeBody;
	}
}
