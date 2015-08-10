package project_trie.desktop;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class BoxChecker {
	public BoxChecker(JTable tab) {
		TableModel model = tab.getModel();
		model.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				int rows = model.getRowCount();
				for (int i = 0; i < rows; i++) {
					if (model.getValueAt(i, 3).equals(true)) {
						for (int j = 0; j < rows; j++) {
							if (j != i && model.getValueAt(j, 3).equals(true)) {
								model.setValueAt(false, j, 3);
							}

						}
					}
				}
			}
		});
	}

	public static int isChecked(JTable table) {
		for (int i = 0; i < table.getRowCount(); i++) {
			for (int j = 0; j < table.getColumnCount(); j++) {
				if (table.getValueAt(i, 3).equals(true)) {
					return i;
				}
			}
		}
		return -1;
	}
}
