import javax.swing.JFrame;

public class Main {

	public Main() {
		JFrame frame = new JFrame();
		GamePanel gamePanel = new GamePanel();

		frame.add(gamePanel);

		frame.setTitle("Snake Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new Main();
	}
}
