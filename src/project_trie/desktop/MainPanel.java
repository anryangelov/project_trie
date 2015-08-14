package project_trie.desktop;

import java.awt.CardLayout;
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
		tabPanel = new TablePanel();
		wellcomePanel = new WelcomePanel();
		setupPanel();
	}

	public void setupPanel() {
		bottom.setLayout(cl);
		add(menu);
		bottom.setBounds(0, 50, 1000, 800);
		bottom.add(wellcomePanel, "welcome");
		bottom.add(descrPanel, "descriptionForm");
		bottom.add(tabPanel, "tablePanel");
		menu.getSearchButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String word = menu.getSearchField().getText();
				if (!dictionary.has(word)) {
					descrPanel.getWord().setText(word);
					menu.getSearchField().setText("");
					cl.show(bottom, "descriptionForm");
				} else {
					tabPanel.removeTable();
					List<String> l = new ArrayList<>(1);
					l.add(word);
					tabPanel.addTable(new Table(l));
					cl.show(bottom, "tablePanel");
					menu.getSearchField().setText("");
				}
			}
		});
		menu.getAllWordsButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabPanel.removeLable();
				revalidate();
				repaint();
				tabPanel.removeTable();
				tabPanel.addTable(new Table(dictionary.list()));
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
}
