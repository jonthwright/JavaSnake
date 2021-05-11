package snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGraphics extends JPanel implements ActionListener {
	private Timer timer = new Timer(100, this);
	private Snake snake;
	private Fruit fruit;
	private Game game;
	
	public GameGraphics(Game game) {
		timer.start();
		this.game = game;
		this.snake = game.getSnake();
		this.fruit = game.getFruit();
		
	}
	
	public void paintComponent(Graphics gfx) {
		super.paintComponent(gfx);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
	}
}
