package project_trie.desktop;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class BoxChecker {
	public BoxChecker(JTable tab) {
		TableModel tableModel = tab.getModel();
		tableModel.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				int rows = tableModel.getRowCount();
				for (int i = 0; i < rows; i++) {
					if (tableModel.getValueAt(i, 2).equals(true)) {
						for (int j = 0; j < rows; j++) {
							if (j != i
									&& tableModel.getValueAt(j, 2).equals(true)) {
								tableModel.setValueAt(false, j, 2);
							}
						}
					}
				}
			}
		});
	}
}
