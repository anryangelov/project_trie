package project_trie.desktop;

import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class Frame extends JFrame {
	private static final long serialVersionUID = 1L;

	public Frame() throws ClassNotFoundException, IOException {
		createFrame();
	}

	private void createFrame() throws ClassNotFoundException, IOException {
		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setSize(new Dimension(1000, 600));
		setTitle("Dictionary");
		add(new MainPanel());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}
}
