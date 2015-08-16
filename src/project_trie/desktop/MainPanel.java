package project_trie.desktop;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
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
	private TablePanel tabPanel;
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
					tabPanel = new TablePanel();
					bottom.add(tabPanel, "tablePanel");
					String word = menu.getSearchField().getText();
					tabPanel.removeComponent(tabPanel.getScrollPane());
					tabPanel.removeComponent(tabPanel.getLabel());
					tabPanel.removeComponent(tabPanel.getEditPanel());
					revalidate();
					if (!dictionary.has(word)) {
						if (word.length() == 1) {
							tabPanel.addTable(new Table(getWords(word)),true);
							tabPanel.setPreferredSize(new Dimension(1500, 1000));
							cl.show(bottom, "tablePanel");
						} else {
							descrPanel.getWord().setText(word);
							cl.show(bottom, "descriptionForm");
						}
					} else {
						List<String> l = new ArrayList<>(1);
						l.add(word);
						tabPanel.addTable(new Table(l),true);
						cl.show(bottom, "tablePanel");
					}
					menu.getSearchField().setText("");
				}else{
					JOptionPane.showMessageDialog(null,
							"Please enter some word");
				}
			}
		});
		menu.getAllWordsButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabPanel = new TablePanel();
				bottom.add(tabPanel, "tablePanel");
				tabPanel.removeComponent(tabPanel.getLabel());
				tabPanel.removeComponent(tabPanel.getScrollPane());
				revalidate();
				repaint();
				tabPanel.addTable(new Table(dictionary.list()),false);
				cl.show(bottom, "tablePanel");
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
		List<String> wordsList = new ArrayList<>();
		for (String words : FileManager.dataBase.list()) {
			if (words.startsWith(letter)) {
				wordsList.add(words);
			}
		}
		return wordsList;
	}
}
