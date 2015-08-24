package project_trie.desktop;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import project_trie.trie.FileManager;
import project_trie.trie.Trie;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	static JPanel bottom;
	static CardLayout cl;
	private FileManager fm;
	private Trie dictionary;
	private DescriptionFormPanel descrPanel;
	private MenuPanel menu;
	private JPanel wellcomePanel;

	public MainPanel() {
		setLayout(null);
		bottom = new JPanel();
		cl = new CardLayout();
		fm = new FileManager();
		dictionary = fm.getDictionary();
		descrPanel = new DescriptionFormPanel();
		menu = new MenuPanel(dictionary.list());
		wellcomePanel = new WelcomePanel();
		setupPanel();
	}

	public void setupPanel() {
		bottom.setLayout(cl);
		add(menu);
		bottom.setBounds(0, 50, 1500, 800);
		bottom.add(wellcomePanel, "welcome");
		bottom.add(descrPanel, "descriptionForm");
		menu.getSearchButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (menu.getSearchField().getText().length() > 0) {
					String word = menu.getSearchField().getText();
					if (!dictionary.has(word)) {
						if (word.length() == 1) {
							List<String> words = getWords(word);
							if (!words.isEmpty()) {
								bottom.add(
										new TablePanelHolder(getWords(word)),
										"tpt");
								cl.show(bottom, "tpt");
							} else {
								new MessageDialog("", "      Invalid word");
							}
						} else {
							descrPanel.getWord().setText(word);
							cl.show(bottom, "descriptionForm");
						}
					} else {
						List<String> l = new ArrayList<>(1);
						l.add(word);
						TablePanel tab = new TablePanel();
						tab.addTable(new Table(l,1), true);
						bottom.add(tab, "tab");
						cl.show(bottom, "tab");
					}
					menu.getSearchField().setText("");
				} else {
					new MessageDialog("", "Please enter some word");
				}
			}
		});
		menu.getAllWordsButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bottom.add(new TablePanelHolder(dictionary.list()), "tpt");
				cl.show(bottom, "tpt");
			}
		});
		menu.getAddButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(bottom, "descriptionForm");
			}
		});
		add(bottom);
	}

	private List<String> getWords(String letter) {
		/*List<String> wordsList = new ArrayList<>();
		for (String words : FileManager.dataBase.list()) {
			if (words.startsWith(letter)) {
				wordsList.add(words);
			}
		}
		return wordsList;*/
		return FileManager.dataBase.list(letter); // much faster
	}

	public JPanel getWellcomePanel() {
		return wellcomePanel;
	}
}