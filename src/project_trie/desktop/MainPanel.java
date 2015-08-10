package project_trie.desktop;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import project_trie.trie.FileManager;
import project_trie.trie.Trie;

public class MainPanel extends JPanel {
	private NavigationPanel navigationPanel;
	private DescriptionFormPanel descriptionForm;
	Trie dictionary;
	private FileManager fileManager;
	private TablePanel tablePanel;
	private JTextField searchedArea;
	private JTextArea description;
	private TablePanel allWordsPanel;

	MainPanel() {
		setBackground(Color.WHITE);
		fileManager = new FileManager();
		dictionary = fileManager.getDictionary();
		allWordsPanel = new TablePanel(showAll(), dictionary);
		navigationPanel = new NavigationPanel();
		descriptionForm = new DescriptionFormPanel();
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
				removeTable(tablePanel);
				add(createDescriptionForm("Enter description here"));
			}
		});

		navigationPanel.getShowAllWords().addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						removeTable(tablePanel);
						createTable("");
						//add(allWordsPanel);
						revalidate();
						repaint();
					}
				});

		navigationPanel.getSearchButton().addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// pass the word from the text area as argument
						// remove(descriptionForm);
						removeTable(tablePanel);
						removeTable(allWordsPanel);
						String key = searchedArea.getText();
						String value = fileManager.dictionary.get(key);
						createTablePanel(key);
						if (!key.isEmpty()) {
							if (value == null) {
								removeTable(tablePanel);
								revalidate();
								repaint();
								add(createDescriptionForm("Enter description here"));
								JOptionPane
										.showMessageDialog(null,
												"Please enter some description and press submit");
							}
						} else {
							// if searched area isEmpty show the message
							JOptionPane.showMessageDialog(null,
									"Please enter the word");
						}
						// searchedArea.setText("");
					}
				});
	}

	private void removeTable(JPanel table) {
		if (table != null) {
			remove(table);
		}
	}

	private void createTablePanel(String key) {
		// pass list with all words as argument and also the
		// dictionary as argument to fetch the value from it
		tablePanel = new TablePanel(findAllWords(key), dictionary);
		add(tablePanel);
		revalidate();
		repaint();
		tablePanel.getRemove().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (BoxChecker.isChecked(tablePanel.getTable()) > -1) {
					removeTable(tablePanel);
					// String key = searchedArea.getText();
					dictionary.remove(key);
					createTablePanel(key);
					JOptionPane.showMessageDialog(null, searchedArea.getText()
							+ " has been removed successfully");
					searchedArea.setText("");
					fileManager.saveChanges();
				} else {
					JOptionPane.showMessageDialog(null, "Please select word");
				}

			}
		});
		JTable table = tablePanel.getTable();
		JTextArea area = new JTextArea();
		// TO-DO find how to print the description in JLable with new Lines!!!
		JLabel descriptionLabel = new JLabel();
		editButton(descriptionLabel, table, area, key);

		tablePanel.getViewDescription().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				descriptionLabel.setBounds(560, 5, 300, 200);
				int data = BoxChecker.isChecked(table);
				if (data > -1) {
					remove(area);
					revalidate();
					repaint();
					remove(descriptionLabel);
					String value = (String) table.getValueAt(data, 2);
					String result = dictionary.printValue(value);
					descriptionLabel.setText(result);
					// descriptionLabel.add((String)table.getValueAt(data, 2));
					tablePanel.add(descriptionLabel);
					revalidate();
					repaint();
				}
			}
		});
	}
	private void createTable(String key) {
		// pass list with all words as argument and also the
		// dictionary as argument to fetch the value from it
		allWordsPanel = new TablePanel(showAll(), dictionary);
		add(allWordsPanel);
		revalidate();
		repaint();
		allWordsPanel.getRemove().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (BoxChecker.isChecked(allWordsPanel.getTable()) > -1) {
					removeTable(allWordsPanel);
					// String key = searchedArea.getText();
					dictionary.remove(key);
					createTablePanel(key);
					JOptionPane.showMessageDialog(null, searchedArea.getText()
							+ " has been removed successfully");
					searchedArea.setText("");
					fileManager.saveChanges();
				} else {
					JOptionPane.showMessageDialog(null, "Please select word");
				}

			}
		});
		JTable table = allWordsPanel.getTable();
		JTextArea area = new JTextArea();
		// TO-DO find how to print the description in JLable with new Lines!!!
		JLabel descriptionLabel = new JLabel();
		edit(descriptionLabel, table, area, key);

		allWordsPanel.getViewDescription().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				descriptionLabel.setBounds(560, 5, 300, 200);
				int data = BoxChecker.isChecked(table);
				if (data > -1) {
					remove(area);
					revalidate();
					repaint();
					remove(descriptionLabel);
					String value = (String) table.getValueAt(data, 2);
					String result = dictionary.printValue(value);
					descriptionLabel.setText(result);
					// descriptionLabel.add((String)table.getValueAt(data, 2));
					allWordsPanel.add(descriptionLabel);
					revalidate();
					repaint();
				}
			}
		});
	}

	private JPanel createDescriptionForm(String text) {
		descriptionForm.getWord().setText(searchedArea.getText());
		description.setText(text);
		descriptionForm.setBounds(0, 70, 950, 500);
		// add(descriptionForm);
		descriptionForm.getSubmit().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// add word and description
				String key = descriptionForm.getWord().getText();
				dictionary.add(key, description.getText());
				fileManager.saveChanges();
				remove(descriptionForm);
				JOptionPane.showMessageDialog(null, searchedArea.getText()
						+ " has been added successfully");
				description.setText("");
				searchedArea.setText("");
				revalidate();
				repaint();
			}
		});
		revalidate();
		repaint();
		return descriptionForm;
	}

	public void editButton(JLabel descriptionLabel, JTable table,
			JTextArea area, String key) {
		tablePanel.getEdit().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tablePanel.remove(descriptionLabel);
				int data = BoxChecker.isChecked(table);
				if (data > -1) {
					JButton save = new JButton("save");
					save.setBounds(860, 220, 70, 25);
					tablePanel.add(save);
					revalidate();
					repaint();
					// TO_DO find how to make editing area scrollabel!!!
					area.setText((String) table.getValueAt(data, 2));
					tablePanel.add(area);
					area.setBounds(560, 5, 400, 200);
					save.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							dictionary.update(
									(String) table.getValueAt(data, 1),
									area.getText());
							fileManager.saveChanges();
							tablePanel.remove(descriptionLabel);
							tablePanel.remove(area);
							tablePanel.remove(save);
							remove(tablePanel);
							createTablePanel(key);
							repaint();
							// createTable(key);
						}
					});
				}
			}
		});
	}
	public void edit(JLabel descriptionLabel, JTable table,
			JTextArea area, String key) {
		allWordsPanel.getEdit().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				allWordsPanel.remove(descriptionLabel);
				int data = BoxChecker.isChecked(table);
				if (data > -1) {
					JButton save = new JButton("save");
					save.setBounds(860, 220, 70, 25);
					allWordsPanel.add(save);
					revalidate();
					repaint();
					// TO_DO find how to make editing area scrollabel!!!
					area.setText((String) table.getValueAt(data, 2));
					allWordsPanel.add(area);
					area.setBounds(560, 5, 400, 200);
					save.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							dictionary.update(
									(String) table.getValueAt(data, 1),
									area.getText());
							fileManager.saveChanges();
							allWordsPanel.remove(descriptionLabel);
							allWordsPanel.remove(area);
							allWordsPanel.remove(save);
							remove(allWordsPanel);
							createTablePanel(key);
							repaint();
							// createTable(key);
						}
					});
				}
			}
		});
	}

	public List<String> findAllWords(String word) {
		List<String> l = new ArrayList<String>();
		for (String s : dictionary.list()) {
			if (s.substring(0, 2).equals(word.substring(0, 2))) {
				if (s.equals(word)) {
					l.add(0, s);
					continue;
				}
				l.add(s);
			}
		}
		return l;
	}

	public List<String> showAll() {
		List<String> l = new ArrayList<String>();
		for (String s : dictionary.list()) {
			l.add(s);
		}
		return l;
	}
}