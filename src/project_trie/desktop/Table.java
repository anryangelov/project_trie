package project_trie.desktop;

import java.awt.Font;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import project_trie.trie.Trie;

public class Table {
	public JTable createTable(List<String> allWords, Trie dictionary) {
		JTable table = new JTable() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 0 || column == 1 || column == 2 ? false : true;
			}

			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return Integer.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				default:
					return Boolean.class;
				}
			}
		};
		table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
		DefaultTableModel defaulthTableModel = new DefaultTableModel(0, 0);
		// add header of the table
		String header[] = new String[] { "NO", "Word", "Description", "" };
		// add header in table model
		defaulthTableModel.setColumnIdentifiers(header);
		table.setModel(defaulthTableModel);
		// add row dynamically into the table
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		for (int i = 0; i < allWords.size(); i++) {
			defaulthTableModel.addRow(new Object[] { i + 1, allWords.get(i),
					dictionary.get(allWords.get(i)), false });
			table.setRowHeight(i, 40);
		}
		table.getColumnModel().getColumn(1).setPreferredWidth(145);
		table.getColumnModel().getColumn(2).setPreferredWidth(250);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		// table.setEnabled(false);
		table.setColumnSelectionInterval(0, 3);
		new BoxChecker(table);
		return table;
	}
}
