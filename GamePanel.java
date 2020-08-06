import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener {

	/**
	 * 
	 */

	private Thread thread;
	private BodyPart bodyPart;
	private List<BodyPart> snake = new ArrayList<BodyPart>();
	private Food partOfFood;
	private List<Food> food = new ArrayList<Food>();
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;

	private Random random = new Random();

	private boolean right = true, left = false, up = false, down = false;

	private boolean isRunning;

	private int xCoor = 10, yCoor = 10, size = 5, ticks = 0;

	// contructor
	public GamePanel() {

		setFocusable(true);
		addKeyListener(this);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		this.start();

	}

	public void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	public void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void tick() {
		if (snake.size() == 0) {
			bodyPart = new BodyPart(xCoor, yCoor, 10);
			snake.add(bodyPart);
		}
		ticks++;
		if (ticks > 250000) {
			if (right)
				xCoor++;
			if (left)
				xCoor--;
			if (up)
				yCoor--;
			if (down)
				yCoor++;

			ticks = 0;

			bodyPart = new BodyPart(xCoor, yCoor, 10);
			snake.add(bodyPart);

			if (snake.size() > size) {
				snake.remove(0);
			}
		}

		if (food.size() <= 0) {
			int xCoor = random.nextInt(49);
			int yCoor = random.nextInt(49);
			partOfFood = new Food(xCoor, yCoor, 20);
			food.add(partOfFood);
		}

		// SNAKE EAT FOOD
		for (int i = 0; i < food.size(); i++) {
			if (xCoor == food.get(i).getxCoor() && yCoor == food.get(i).getyCoor()) {
				size++;
				food.remove(i);
				i++;
			}
		}

		if (xCoor < 0 || xCoor > 49 || yCoor < 0 || yCoor > 49) {
			System.out.println("Game Over");
			stop();
		}

		// SNAKE EAT SEFT
		for (int i = 0; i < snake.size(); i++) {
			if (xCoor == snake.get(i).getxCoor() && yCoor == snake.get(i).getyCoor()) {
				if (i != snake.size() - 1) {
					System.out.println("GAME OVER");
					stop();
				}
			}
		}

	}

	@Override
	public void paint(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		for (int i = 0; i < WIDTH / 10; i++) {
			g.drawLine(i * 10, 0, i * 10, HEIGHT);
		}

		for (int i = 0; i < HEIGHT / 10; i++) {
			g.drawLine(i * 10, 0, i * 10, HEIGHT);
		}

		// DRAW SNAKE
		drawSnake(g);

		// DRAW FOOD
		drawFood(g);

	}

	void drawSnake(Graphics g) {
		for (int i = 0; i < snake.size(); i++) {
			snake.get(i).draw(g);
		}
	}

	void drawFood(Graphics g) {
		for (int i = 0; i < food.size(); i++) {
			food.get(i).draw(g);
		}
	}

	@Override
	public void run() {
		while (isRunning) {
			tick();
			repaint();
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_RIGHT && !left) {
			right = true;
			up = false;
			down = false;
		}
		if (key == KeyEvent.VK_LEFT && !right) {
			left = true;
			up = false;
			down = false;
		}
		if (key == KeyEvent.VK_UP && !down) {
			up = true;
			left = false;
			right = false;

		}
		if (key == KeyEvent.VK_DOWN && !up) {
			down = true;
			left = false;
			right = false;

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
