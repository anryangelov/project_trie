package project_trie.desktop;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NavigationPanel extends JPanel {
	private JTextField searchArea = new JTextField();
	private JButton searchButton = new JButton("search word");
	private JButton addWord = new JButton("add word");
	private JButton showAllWords = new JButton("show all");

	public NavigationPanel() {
		setBackground(Color.DARK_GRAY);
		createPanel();
	}

	private void createPanel() {
		setLayout(null);
		// setPreferredSize(new Dimension(500, 100));
		getSearchArea().setBounds(10, 10, 300, 30);
		add(getSearchArea());
		getSearchButton().setBounds(320, 10, 200, 30);
		add(getSearchButton());
		getAddWord().setBounds(520, 10, 200, 30);
		add(getAddWord());
		getShowAllWords().setBounds(720, 10, 200, 30);
		add(getShowAllWords());
	}
	
	public JButton getAddWord() {
		return addWord;
	}

	public JTextField getSearchArea() {
		return searchArea;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public JButton getRemoveButton() {
		return getShowAllWords();
	}

	public JButton getShowAllWords() {
		return showAllWords;
	}
}
