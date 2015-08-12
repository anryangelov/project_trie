package project_trie.desktop;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField searchArea;
	static JButton searchButton;
	private JButton addWord;
	private JButton showAllWords;

	public MenuPanel(List<String> keywords) {
		setBounds(0, 0, 1000, 50);
		searchArea = new JTextField();
		searchButton = new JButton("search");
		addWord = new JButton("add word");
		showAllWords = new JButton("show all");
		searchArea.setFocusTraversalKeysEnabled(false);
		new Autocomplete(searchArea, keywords);
		setBackground(Color.DARK_GRAY);
		createPanel();
	}

	private void createPanel() {
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
