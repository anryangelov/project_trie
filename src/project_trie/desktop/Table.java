package project_trie.desktop;

import java.awt.Font;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import project_trie.trie.FileManager;
import project_trie.trie.Trie;

public class Table extends JTable {
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;

	public Table(List<String> allWords) {
		tableModel = new DefaultTableModel(0, 0) {
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
		getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
		String header[] = new String[] { "NO", "Word", "Description", "" };
		getTableModel().setColumnIdentifiers(header);
		setModel(getTableModel());
		getColumnModel().getColumn(0).setPreferredWidth(40);
		for (int i = 0; i < allWords.size(); i++) {
			getTableModel()
					.addRow(new Object[] { i + 1, allWords.get(i),
							FileManager.dictionary.get(allWords.get(i)), false });
			setRowHeight(i, 40);
		}
		getColumnModel().getColumn(1).setPreferredWidth(145);
		getColumnModel().getColumn(2).setPreferredWidth(250);
		setPreferredScrollableViewportSize(getPreferredSize());
		new BoxChecker(this);
	}

	public <T> T getColumnValue(int column) {
		int data = BoxChecker.isChecked(this);
		@SuppressWarnings("unchecked")
		T value = (T) tableModel.getValueAt(data, column);
		return value;
	}

	public <T> void setColumnValue(T value, int column) {
		int data = BoxChecker.isChecked(this);
		tableModel.setValueAt(value, data, column);
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}
}