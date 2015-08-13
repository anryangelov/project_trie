package project_trie.desktop;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Autocomplete {
	List<String> items;
	private DefaultComboBoxModel<String> model;
	private JComboBox<String> combo;

	public Autocomplete(JTextField txtInput, List<String> dictionary) {
		setupAutoComplete(txtInput, dictionary);
	}

	public void setupAutoComplete(JTextField txtInput, List<String> dictionary) {
		model = new DefaultComboBoxModel<>();
		combo = new JComboBox<String>(model);
		combo.setPreferredSize(new Dimension(combo.getPreferredSize().width, 0));
		for (String word : dictionary) {
			model.addElement(word);
		}
		combo.setSelectedItem(null);
		txtInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				combo.putClientProperty("", true);
				if (e.getKeyCode() == KeyEvent.VK_ENTER
						|| e.getKeyCode() == KeyEvent.VK_UP
						|| e.getKeyCode() == KeyEvent.VK_DOWN) {
					e.setSource(combo);
					combo.dispatchEvent(e);
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						if (combo.getSelectedItem() != null) {
							combo.addItem(txtInput.getText());
							txtInput.setText(combo.getSelectedItem().toString());
							MenuPanel.searchButton.doClick();
							combo.setPopupVisible(false);
						}
					}
				}
			}
		});
		txtInput.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				updateList();
			}

			public void removeUpdate(DocumentEvent e) {
				updateList();
			}

			public void changedUpdate(DocumentEvent e) {
				updateList();
			}

			private void updateList() {
				combo.putClientProperty("", true);
				model.removeAllElements();
				String input = txtInput.getText();
				if (!input.isEmpty()) {
					for (String word : dictionary) {
						if (word.toLowerCase().startsWith(input.toLowerCase())) {
							model.addElement(word);
						}
					}
					model.addElement(input);
				}
				combo.setPopupVisible(model.getSize() > 0);
			}
		});
		txtInput.setLayout(new BorderLayout());
		txtInput.add(combo, BorderLayout.SOUTH);
	}
}