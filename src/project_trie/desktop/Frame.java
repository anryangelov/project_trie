package project_trie.desktop;

import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 1270;
	private static final int HEIGHT = 670;
	private static final String TITLE = "Dictionary";

	public Frame() throws ClassNotFoundException, IOException {
		// setDefaultLookAndFeelDecorated(true);
		// try {
		// UIManager.setLookAndFeel(UIManager
		// .getCrossPlatformLookAndFeelClassName());
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		setSize(new Dimension(WIDTH, HEIGHT));
		setTitle(TITLE);
		add(new MainPanel());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}
}
