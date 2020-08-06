import java.awt.Color;
import java.awt.Graphics;

public class Food {

	private int xCoor;
	private int yCoor;
	private int height;
	private int width;

	public Food(int xCoor, int yCoor, int tileSize) {
		super();
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		this.height = tileSize;
		this.width = tileSize;
	}

	public void tick() {

	}

	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(xCoor * width, yCoor * height, width, height);
	}

	public int getxCoor() {
		return xCoor;
	}

	public void setxCoor(int xCoor) {
		this.xCoor = xCoor;
	}

	public int getyCoor() {
		return yCoor;
	}

	public void setyCoor(int yCoor) {
		this.yCoor = yCoor;
	}
}
