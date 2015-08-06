package project_trie.desktop;

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
		getSearchArea().setBounds(10, 10, 300, 30);
		add(getSearchArea());
		getSearchButton().setBounds(320, 10, 200, 30);
		add(getSearchButton());
		getAddWord().setBounds(520, 10, 200, 30);
		add(getAddWord());
	}

	public JButton getAddWord() {
		return addWord;
	}

	public void setAddWord(JButton addWord) {
		this.addWord = addWord;
	}

	public JTextArea getSearchArea() {
		return searchArea;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public void setSearchButton(JButton searchButton) {
		this.searchButton = searchButton;
	}
}
