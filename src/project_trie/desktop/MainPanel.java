package project_trie.desktop;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import project_trie.trie.FileManager;
import project_trie.trie.Trie;

public class MainPanel extends JPanel {
	private NavigationPanel navigationPanel;
	private AddWordFormPanel descriptionForm;
	private static final String FILE_NAME = "dictionary.ser";
	private FileManager fileManager;
	private TablePanel tablePanel;
	private Trie dictionary;
	private JTextArea searchedArea;
	private JTextArea description;

	MainPanel() {
		setBackground(Color.WHITE);
		navigationPanel = new NavigationPanel();
		descriptionForm = new AddWordFormPanel();
		fileManager = new FileManager(FILE_NAME);
		dictionary = fileManager.getDictionary();
		searchedArea = navigationPanel.getSearchArea();
		description = descriptionForm.getDescription();
		setLayout(null);
		navigationPanel.setBounds(0, 0, 1000, 50);
		add(navigationPanel);
		navigationPanel.getAddWord().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (searchedArea.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Please enter a word in the text area");
					return;
				}
				// remove Table class if != null
				removeTable(tablePanel);
				createDescriptionForm();
			}
		});
		navigationPanel.getRemoveButton().addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String key = searchedArea.getText();
						dictionary.remove(key);
						JOptionPane.showMessageDialog(null,
								searchedArea.getText()
										+ " has been removed successfully");
						searchedArea.setText("");
						saveChanges();
					}
				});
		navigationPanel.getSearchButton().addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// pass the word from the text area as argument
						// remove(descriptionForm);
						removeTable(tablePanel);
						String key = searchedArea.getText();
						if (!key.isEmpty()) {
							String word = dictionary.get(key);
							// pass list with all words as argument and also the
							// dictionary to fetch the value from it
							tablePanel = new TablePanel(findAllWords(key),
									dictionary);
							add(tablePanel);
							if (word == null) {
								removeTable(tablePanel);
								createDescriptionForm();
								JOptionPane
										.showMessageDialog(null,
												"Please enter some description and press submit");
							}
						} else {
							// if searched area isEmpty show the message
							JOptionPane.showMessageDialog(null,
									"Please enter the word");
						}
						searchedArea.setText("");
					}
				});
	}

	private void removeTable(JPanel table) {
		if (table != null) {
			remove(table);
		}
	}

	private void createDescriptionForm() {
		descriptionForm.getWord().setText(searchedArea.getText());
		description.setText("Enter description here");
		descriptionForm.setBounds(0, 70, 950, 500);
		add(descriptionForm);
		descriptionForm.getSubmit().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				dictionary.add(descriptionForm.getWord().getText(),
						description.getText());
				description.setText("");
				remove(descriptionForm);
				JOptionPane.showMessageDialog(null, searchedArea.getText()
						+ " has been added successfully");
				searchedArea.setText("");
				saveChanges();
				revalidate();
				repaint();
			}
		});
		revalidate();
		repaint();
	}

	private void saveChanges() {
		// save changes everytime when word is added or removed
		try {
			fileManager.serialize(dictionary, "dictionary.ser");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public List<String> findAllWords(String word) {
		List<String> l = new ArrayList<String>();
		for (String s : dictionary.list()) {
			if (s.substring(0, 2).equals(word.substring(0, 2))) {
				l.add(s);
			}
		}
		return l;
	}
}