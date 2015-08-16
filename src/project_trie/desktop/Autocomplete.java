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
	private JTextField input;
	private List<String> dictionary;
	private DefaultComboBoxModel<String> model;
	private JComboBox<String> combo;

	public Autocomplete(JTextField input, List<String> dictionary) {
		this.input = input;
		this.dictionary = dictionary;
		model = new DefaultComboBoxModel<>();
		combo = new JComboBox<String>(model);
		combo.setPreferredSize(new Dimension(combo.getPreferredSize().width, 0));
		input.setLayout(new BorderLayout());
		input.add(combo, BorderLayout.SOUTH);
		setupAutoComplete(input, dictionary);
	}

	public void setupAutoComplete(JTextField input, List<String> dictionary) {
		input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent event) {
				combo.putClientProperty("", true);
				int keyCode = event.getKeyCode();
				if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN) {
					combo.dispatchEvent(event);
				}
				if (keyCode == KeyEvent.VK_ENTER
						&& input.getText().length() > 0) {
					if (combo.getSelectedItem() != null) {
						combo.addItem(input.getText());
						input.setText(combo.getSelectedItem().toString());
						combo.hidePopup();
					}
					MenuPanel.searchButton.doClick();
				}
			}
		});
		input.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				updateCombo();
			}

			public void removeUpdate(DocumentEvent e) {
				updateCombo();
			}

			public void changedUpdate(DocumentEvent e) {
				updateCombo();
			}

		});
	}

	public void updateAutocomplete(List<String> dictionary) {
		model.removeAllElements();
		this.dictionary = dictionary;
	}

	private void updateCombo() {
		combo.putClientProperty("", true);
		model.removeAllElements();
		String inputedText = input.getText();
		if (input.getText().length() == 1) {
			model.addElement(inputedText);
		}
		if (!inputedText.isEmpty()) {
			for (String word : dictionary) {
				if (word.toLowerCase().startsWith(inputedText.toLowerCase())) {
					model.addElement(word);
				}
			}
		}
		combo.setPopupVisible(model.getSize() > 0);
		if (input.getText().length() == 0) {
			combo.hidePopup();
		}
	}
}