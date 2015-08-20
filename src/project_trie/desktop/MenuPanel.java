package project_trie.desktop;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Location of this panel is in the upper part of the window
 * Holds searching area and buttons "search","add word" and 
 * "show all"...Logic of the buttons is located in MainPanel class
 * 
 */
public class MenuPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public static JTextField searchField;
	public static JButton searchButton;
	private JButton addButton;
	private JButton allWordsButton;
	public static Autocomplete autoComplete;

	public MenuPanel(List<String> allWords) {
		setBounds(0, 0, 1500, 50);
		searchField = new JTextField();
		searchButton = new JButton("search");
		addButton = new JButton("add word");
		allWordsButton = new JButton("show all");
		autoComplete = new Autocomplete(searchField, allWords);
		setBackground(Color.DARK_GRAY);
		createPanel();
	}

	private void createPanel() {
		searchField.setPreferredSize(new Dimension(200, 25));
		setPreferredSize(new Dimension(500, 100));
		searchField.setBounds(10, 10, 300, 30);
		add(getSearchField());
		searchButton.setBounds(320, 10, 200, 30);
		searchButton.setForeground(Color.RED);
		add(getSearchButton());
		getAddButton().setBounds(520, 10, 200, 30);
		add(getAddButton());
		getAllWordsButton().setBounds(720, 10, 200, 30);
		add(getAllWordsButton());
	}

	public JButton getAddButton() {
		return addButton;
	}

	public JTextField getSearchField() {
		return searchField;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public JButton getAllWordsButton() {
		return allWordsButton;
	}
}
