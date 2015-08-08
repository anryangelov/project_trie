package project_trie.desktop;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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

import project_trie.trie.Trie;

public class TablePanel extends JPanel {
	private JTable table;
	private JButton edit = new JButton("edit");

	public TablePanel(List<String> l, Trie t) {
		setLayout(null);
		setBackground(Color.WHITE);
		setBounds(12, 70, 700, 500);
		add(createTable(l, t));
		add(edit);
	}

	public JScrollPane createTable(List<String> allWords, Trie dictionary) {
		table = new JTable() {
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
		table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
		DefaultTableModel defaulthTableModel = new DefaultTableModel(0, 0);
		// add header of the table
		String header[] = new String[] { "Word", "Description", "Check" };
		// add header in table model
		defaulthTableModel.setColumnIdentifiers(header);
		table.setModel(defaulthTableModel);
		// add row dynamically into the table
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		for (int i = 0; i < allWords.size(); i++) {
			defaulthTableModel.addRow(new Object[] { allWords.get(i),
					dictionary.get(allWords.get(i)), false });
			table.setRowHeight(i, 40);
		}
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		new BoxChecker(table);
		JScrollPane scrollPane = new JScrollPane(table,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		// remove scrollpane borders
		LookAndFeel.installBorder(scrollPane, BorderFactory.createEmptyBorder()
				.toString());
		scrollPane.getViewport().setBackground(Color.WHITE);
		scrollPane.setBounds(0, 0, 475, 200);
		return scrollPane;
	}

}
