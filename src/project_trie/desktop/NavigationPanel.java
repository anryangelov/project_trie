package project_trie.desktop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class NavigationPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField searchArea = new JTextField();
	static JButton searchButton = new JButton("search");
	private JButton addWord = new JButton("add word");
	private JButton showAllWords = new JButton("show all");

	public NavigationPanel(List<String> keywords) {
		searchArea.setFocusTraversalKeysEnabled(false);
		Autocomplete.setupAutoComplete(searchArea, keywords);
		setBackground(Color.DARK_GRAY);
		createPanel();
	}

	private void createPanel() {
		setLayout(null);
		searchArea.setPreferredSize(new Dimension(200, 25));
		setPreferredSize(new Dimension(500, 100));
		searchArea.setBounds(10, 10, 300, 30);
		add(getSearchArea());
		searchButton.setBounds(320, 10, 200, 30);
		searchButton.setForeground(Color.RED);
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
