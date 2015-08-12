package project_trie.desktop;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import project_trie.trie.FileManager;
import project_trie.trie.Trie;

public class NewMainPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel bottom = new JPanel();
	private CardLayout cl = new CardLayout();
	private FileManager fm = new FileManager();
	private Trie dictionary = fm.getDictionary();
	private DescriptionFormPanel descrPanel = new DescriptionFormPanel();
	private MenuPanel menu = new MenuPanel(dictionary.list());
	private TablePanel tabPanel = new TablePanel();
	private JPanel wellcomePanel = new JPanel();

	public NewMainPanel() {
		setLayout(null);
		bottom.setLayout(cl);
		add(menu);
		bottom.setBounds(0, 50, 1000, 800);
		wellcomePanel.add(new JLabel("Wellcome"), BorderLayout.CENTER);
		bottom.add(wellcomePanel, "wellcome");
		bottom.add(descrPanel, "descriptionForm");
		bottom.add(tabPanel, "tablePanel");
		menu.getSearchButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabPanel.removeTable();
				List<String> l = new ArrayList<>(1);
				l.add(menu.getSearchArea().getText());
				tabPanel.addTable(new Table(l));
				cl.show(bottom, "tablePanel");
				menu.getSearchArea().setText("");
			}
		});
		menu.getShowAllWords().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
