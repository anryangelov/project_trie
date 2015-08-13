package project_trie.desktop;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import project_trie.trie.FileManager;

public class TablePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Table table;
	private JButton edit;
	private JButton remove;
	private JButton viewDescription;
	private JScrollPane scrollPane;
	private JButton save;
	private EditArea editPanel;
	private DescriptionLable label;

	public TablePanel() {
		setLayout(null);
		setBackground(Color.WHITE);
		setBounds(12, 70, 1000, 1000);
	}

	public void addTable(Table table) {
		this.table = table;
		scrollPane = new JScrollPane(this.table,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getViewport().setBackground(Color.WHITE);
		scrollPane.setBounds(5, 5, 527, 450);
		add(scrollPane);
		save = new Button("save");
		edit = new JButton("edit");
		edit.setBounds(430, 470, 100, 30);
		add(edit);
		viewDescription = new JButton("view");
		viewDescription.setBounds(230, 470, 100, 30);
		add(viewDescription);
		remove = new JButton("remove");
		remove.setBounds(330, 470, 100, 30);
		add(remove);
		fireEdit();
		fireView();
		fireRemove();
	}

	public void fireRemove() {
		remove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeEditArea();
				removeLable();
				removeSave();
				revalidate();
				repaint();
				String key = table.getColumnValue(1);
				table.removeRow();
				FileManager.dictionary.remove(key);
				FileManager.saveChanges();
			}
		});
	}

	public void fireEdit() {
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeLable();
				editPanel = new EditArea();
				add(editPanel);
				editPanel.setVisible(true);
				editPanel.setText(table.getColumnValue(2));
				save.setBounds(840, 420, 100, 30);
				fireSave();
				add(save);
				revalidate();
				repaint();
			}
		});
	}

	public void fireSave() {
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String key = table.getColumnValue(1);
				String value = editPanel.getText();
				if (value.length() > 1100) {
					JOptionPane.showMessageDialog(null,
							"reduce text description");
				} else {
					if (key != null) {
						FileManager.dictionary.update(key, value);
						FileManager.saveChanges();
						table.setColumnValue(value, 2);
						table.setColumnValue(false, 3);
						editPanel.setVisible(false);
						remove(save);
					}
				}
			}
		});
	}

	public void fireView() {
		viewDescription.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeLable();
				removeSave();
				removeEditArea();
				String text = table.getColumnValue(2);
				System.out.println(text);
				label = new DescriptionLable(text);
				add(label);
				revalidate();
				repaint();
			}
		});
	}

	public void removeLable() {
		if (label != null) {
			remove(label);
		}
	}

	public void removeSave() {
		if (save != null) {
			remove(save);
		}
	}

	public void removeEditArea() {
		if (editPanel != null) {
			remove(editPanel);
		}
	}

	public void removeTable() {
		if (scrollPane != null) {
			super.remove(scrollPane);
		}
	}
}