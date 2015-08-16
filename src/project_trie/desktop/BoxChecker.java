package project_trie.desktop;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class BoxChecker {
	public BoxChecker(JTable table) {
		TableModel model = table.getModel();
		model.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				int rows = model.getRowCount();
				for (int i = 0; i < rows; i++) {
					if (table.isRowSelected(i)) {
						for (int j = 0; j < rows; j++) {
							if (j != i && model.getValueAt(j, 3).equals(true)) {
								model.setValueAt(false, j, 3);
								break;
							}
						}
					}
				}
			}
		});
	}
}
