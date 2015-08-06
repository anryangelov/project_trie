package project_trie.desktop;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MainPanel extends JPanel {
	private JTextArea searchArea = new JTextArea();
	private JButton searchButton = new JButton("search word");
	private JButton addWord = new JButton("add word");
	
	MainPanel() {
		setLayout(null);
		setBackground(Color.ORANGE);
		searchArea.setBounds(10, 10, 300, 30);
		add(searchArea);
		searchButton.setBounds(320,10, 200, 30);
		add(searchButton);
		addWord.setBounds(520,10, 200, 30);
		add(addWord);
	}
}
