package project_trie.desktop;

import java.awt.Component;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.LookAndFeel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class Table extends JPanel {
	private JTable tbl;
	private JButton edit = new JButton("edit");

	public Table(List<String> l) {
		setLayout(null);
		add(createTable(l));
		add(edit);
	}

	private void resizeColumnWidth(JTable table) {
		final TableColumnModel columnModel = table.getColumnModel();
		for (int column = 0; column < table.getColumnCount(); column++) {
			int width = 70;
			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				Component comp = table.prepareRenderer(renderer, row, column);
				width = Math.max(comp.getPreferredSize().width, width);
			}
			columnModel.getColumn(column).setPreferredWidth(width);
		}
	}

	public JScrollPane createTable(List<String> l) {
		tbl = new JTable() {
			private static final long serialVersionUID = 1L;

			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				default:
					return Boolean.class;
				}
			}
		};
		  tbl.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		// add header of the table
		String header[] = new String[] { "Word", "Description", "Check" };
		// add header in table model
		dtm.setColumnIdentifiers(header);
		getTbl().setModel(dtm);
		// add row dynamically into the table
		tbl.getColumnModel().getColumn(0).setPreferredWidth(100);
		 tbl.getColumnModel().getColumn(1).setPreferredWidth(200);
		for (int i = 0; i < l.size(); i++) {
			dtm.addRow(new Object[] {
					l.get(i).substring(0, l.get(i).indexOf(' ')),
					l.get(i).substring(l.get(i).indexOf('-') + 1), false });
			getTbl().setRowHeight(i, 40);
		}
		// resize columns by the content inside
		//resizeColumnWidth(getTbl());
		disableCheckBox(tbl);
		getTbl().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		getTbl().setPreferredScrollableViewportSize(getTbl().getPreferredSize());
		JScrollPane scrollPane = new JScrollPane(getTbl(),
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		// remove scrollpane borders
		LookAndFeel.installBorder(scrollPane, BorderFactory.createEmptyBorder()
				.toString());
		scrollPane.setBounds(0, 0, 400, 500);
		return scrollPane;
	}

	private void disableCheckBox(JTable tab) {
		TableModel tableModel = tab.getModel();
		tableModel.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				int rows = tableModel.getRowCount();
				for (int i = 0; i < rows; i++) {
					if (tableModel.getValueAt(i, 2).equals(true)) {
						for (int j = 0; j < rows; j++) {
							if (j == i) {
								continue;
							}
							if (tableModel.getValueAt(j, 2).equals(true)) {
								tableModel.setValueAt(false, j, 2);
							}
						}
					}
				}
			}
		});
	}

	public JTable getTbl() {
		return tbl;
	}
}
