package project_trie.desktop;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public WelcomePanel() {
		setLayout(null);
		JLabel label = new JLabel("Welcome");
		label.setFont(new Font("Sherif", Font.BOLD, 20));
		label.setBounds(430, 200, 200, 50);
		add(label);
	}

}
