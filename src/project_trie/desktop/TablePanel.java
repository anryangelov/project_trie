package project_trie.desktop;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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

	public TablePanel() {
		setLayout(null);
		setBackground(Color.RED);
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
		setBackground(Color.WHITE);
		edit = new JButton("edit");
		remove = new JButton("remove");
		viewDescription = new JButton("view");
		edit.setBounds(430, 470, 100, 30);
		add(edit);
		save = new Button("save");
		add(save);
		viewDescription.setBounds(230, 470, 100, 30);
		add(viewDescription);
		getRemove().setBounds(330, 470, 100, 30);
		add(getRemove());
		fireEdit();
		fireView();
	}

	public void fireEdit() {
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (editPanel != null) {
					remove(editPanel);
				}
				editPanel = new EditArea();
				add(editPanel);
				editPanel.setVisible(true);
				int data = BoxChecker.isChecked(table);
				editPanel.setText((String) table.getTableModel().getValueAt(
						data, 2));
				save.setBounds(840, 420, 100, 30);
				fireSave();
				save.setVisible(true);
			}
		});
	}

	public void fireSave() {
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String key = (String) table.getColumnValue(1);
				String value = editPanel.getText();
				FileManager.dictionary.update(key, value);
				FileManager.saveChanges();
				table.setColumnValue(value, 2);
				table.setColumnValue(false, 3);
				editPanel.setVisible(false);
				save.setVisible(false);
			}
		});
	}

	public void fireView() {
		viewDescription.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = (String) table.getColumnValue(2);
				DescriptionLable lable = new DescriptionLable(text);
				add(lable);
				revalidate();
				repaint();

			}
		});
	}

	public void removeTable() {
		if (scrollPane != null) {
			super.remove(scrollPane);
		}
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