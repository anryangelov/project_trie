package project_trie.desktop;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MainPanel extends JPanel {
	NavigationPanel np = new NavigationPanel();
	
	MainPanel() {
		setLayout(null);
		np.setBounds(0, 0, 1000, 50);
		add(np);
	}
}
