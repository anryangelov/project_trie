package project_trie.desktop;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class NavigationPanel extends JPanel {
	private JTextArea searchArea = new JTextArea();
	private JButton searchButton = new JButton("search word");
	private JButton addWord = new JButton("add word");

	public NavigationPanel() {
		createPanel();
	}

	private void createPanel() {
		setLayout(null);
		setPreferredSize(new Dimension(500, 100));
		setBackground(Color.ORANGE);
		searchArea.setBounds(10, 10, 300, 30);
		add(searchArea);
		searchButton.setBounds(320, 10, 200, 30);
		add(searchButton);
		addWord.setBounds(520, 10, 200, 30);
		add(addWord);

	}
}
