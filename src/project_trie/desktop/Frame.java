package project_trie.desktop;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Frame extends JFrame {
	public Frame() {
		createFrame();
	}
	private void createFrame(){
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setSize(new Dimension(1000, 600));
		setTitle("Dictionary");
		add(new MainPanel());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
}
