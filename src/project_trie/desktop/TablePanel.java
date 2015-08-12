package project_trie.desktop;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LookAndFeel;

public class TablePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Table table;
	private JButton edit;
	private JButton remove;
	private JButton viewDescription;

	public TablePanel(Table table) {
		setBackground(Color.WHITE);
		setLayout(null);
		edit = new JButton("edit");
		remove = new JButton("remove");
		viewDescription = new JButton("view");
		setBounds(12, 70, 1000, 500);
		this.table = table;
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