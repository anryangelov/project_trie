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
	static JPanel bottom = new JPanel();
	static CardLayout cl = new CardLayout();
	private FileManager fm = new FileManager();
	private Trie dictionary = fm.getDictionary();
	private DescriptionFormPanel descrPanel = new DescriptionFormPanel();
	private MenuPanel menu = new MenuPanel(dictionary.list());
	private TablePanel tabPanel = new TablePanel();
	private JPanel wellcomePanel = new WelcomePanel();

	public MainPanel() {
		setLayout(null);
		bottom.setLayout(cl);
		add(menu);
		bottom.setBounds(0, 50, 1000, 800);
		bottom.add(wellcomePanel, "welcome");
		bottom.add(descrPanel, "descriptionForm");
		bottom.add(tabPanel, "tablePanel");
		menu.getSearchButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String word = menu.getSearchArea().getText();
				if (!dictionary.has(word)) {
					descrPanel.getWord().setText(word);
					menu.getSearchArea().setText("");
					cl.show(bottom, "descriptionForm");
				} else {
					tabPanel.removeTable();
					List<String> l = new ArrayList<>(1);
					l.add(word);
					tabPanel.addTable(new Table(l));
					cl.show(bottom, "tablePanel");
					menu.getSearchArea().setText("");
				}
			}
		});
		menu.getShowAllWords().addActionListener(new ActionListener() {
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
		menu.getAddWord().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(bottom, "descriptionForm");
			}
		});
		add(bottom);
	}
}
