package project_trie.desktop;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LookAndFeel;
import javax.swing.table.DefaultTableModel;

import project_trie.trie.Trie;

public class TablePanel extends JPanel {
	private JTable table;
	private JButton edit;
	private JButton remove;
	private JButton viewDescription;

	public TablePanel(List<String> l, Trie t) {
		setBackground(Color.WHITE);
		setLayout(null);
		edit = new JButton("edit");
		remove = new JButton("remove");
		viewDescription = new JButton("view");
		setBounds(12, 70, 1000, 500);
		table = new Table().createTable(l, t);
		JScrollPane scrollPane = new JScrollPane(table,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		// remove scrollpane borders
		LookAndFeel.installBorder(scrollPane, BorderFactory.createEmptyBorder()
				.toString());
		scrollPane.getViewport().setBackground(Color.WHITE);
		scrollPane.setBounds(5, 5, 527, 450);
		add(scrollPane);
		edit.setBounds(430, 470, 100, 30);
		add(edit);
		viewDescription.setBounds(230, 470, 100, 30);
		add(viewDescription);
		getRemove().setBounds(330, 470, 100, 30);
		add(getRemove());
	}

	public JTable getTable() {
		return table;
	}

	public JButton getRemove() {
		return remove;
	}

	public JButton getViewDescription() {
		return viewDescription;
	}

	public JButton getEdit() {
		return edit;
	}
}
