package project_trie.desktop;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import project_trie.trie.FileManager;

public class TablePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private int height;
	private Table table;
	private JButton edit;
	private JButton remove;
	private JButton viewDescription;
	private JScrollPane scrollPane;
	private DescriptionLable label;
	private EditPanel editPanel;
	private TablePanel tp = this;

	public TablePanel() {
		setLayout(null);
		setBackground(Color.ORANGE);
		setBounds(12, 70, 1300, 550);
		edit = new JButton("edit");
		viewDescription = new JButton("view");
		setBackground(Color.WHITE);
		remove = new JButton("remove");
		height = 500;
	}

	public void addTable(Table table, boolean setHeight) {
		setBackground(Color.ORANGE);
		this.table = table;
		if (setHeight) {
			height = (int) getTable().getPreferredSize().getHeight();
		}
		if (height > 500) {
			height = 500;
		}
		scrollPane = new JScrollPane(this.table,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(5, 10, 527, height + 22);
		add(scrollPane);
		edit.setBounds(430, height + 40, 100, 30);
		add(edit);
		viewDescription.setBounds(210, height + 40, 100, 30);
		add(viewDescription);
		remove.setBounds(320, height + 40, 100, 30);
		add(remove);
		fireEdit();
		fireView(this.table);
		fireRemove();
	}

	public void fireRemove() {
		remove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeComponent(label);
				removeComponent(editPanel);
				revalidate();
				repaint();
				if (table.isRowSelected()) {
					String key = table.getColumnValue(1);
					table.removeRow();
					FileManager.dataBase.remove(key);
					FileManager.saveChanges();
					Autocomplete.updateAutocomplete(FileManager.dataBase.list());
				}
				if (table.getRowCount() == 0) {
					MainPanel.cl.first(MainPanel.bottom);
				}
			}

		});
	}

	public void fireEdit() {
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeComponent(label);
				removeComponent(editPanel);
				if (table.isRowSelected()) {
					editPanel = new EditPanel(table.getColumnValue(1), table
							.getColumnValue(2));
					add(editPanel);
					fireSave();
					revalidate();
					repaint();
				}
			}
		});
	}

	public void fireSave() {
		new ButtonAction(editPanel.getSaveButton(), editPanel.getEditArea());
		editPanel.getSaveButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String key = table.getColumnValue(1);
				String keyFromEditPAnel = editPanel.getWordField().getText();
				String value = editPanel.getEditArea().getText();
				if (value.length() > 1100) {
					new MessageDialog("", "reduce text description with "
							+ (value.length() - 1100) + " symbols");
				} else {
					removeComponent(editPanel);
					revalidate();
					repaint();
					if (key != null) {
						if (keyFromEditPAnel.equals(key)) {
							FileManager.dataBase.update(key, value);
							table.setColumnValue(value, 2);
						} else {
							FileManager.dataBase.remove(key);
							FileManager.dataBase.add(keyFromEditPAnel, value);
							table.setColumnValue(keyFromEditPAnel, 1);
							table.setColumnValue(value, 2);
							Autocomplete
									.updateAutocomplete(FileManager.dataBase
											.list());
						}
						table.setColumnValue(false, 3);
						FileManager.saveChanges();
					}
				}
			}
		});
	}

	public void fireView(Table table) {
		viewDescription.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.isRowSelected()) {
					removeComponent(label);
					removeComponent(editPanel);
					label = new DescriptionLable(table.getColumnValue(2), table
							.getColumnValue(1));
					add(label);
					revalidate();
					repaint();
				}
			}
		});
	}

	public void removeComponent(Component component) {
		if (component != null) {
			remove(component);
		}
	}

	public DescriptionLable getLabel() {
		return label;
	}

	public EditPanel getEditPanel() {
		return editPanel;
	}

	public Table getTable() {
		return table;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}
}