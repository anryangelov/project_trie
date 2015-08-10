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
import javax.swing.JScrollPane;
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
	private JScrollPane scroll;

	MainPanel() {
		setBackground(Color.WHITE);
		fileManager = new FileManager();
		dictionary = fileManager.getDictionary();
		navigationPanel = new NavigationPanel(dictionary.list());
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
						createTablePanel("", showAll());
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
						String key = searchedArea.getText();
						String value = fileManager.dictionary.get(key);
						createTablePanel(key, findAllWords(key));
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

	private void createTablePanel(String key, List<String> list) {
		// pass list with all words as argument and also the
		// dictionary as argument to fetch the value from it
		tablePanel = new TablePanel(list, dictionary);
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
					createTablePanel(key, list);
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
		editButton(descriptionLabel, table, area, key, list);

		tablePanel.getViewDescription().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				descriptionLabel.setBounds(560, 5, 400, 450);
				int data = BoxChecker.isChecked(table);
				if (data > -1) {
					remove(area);
					revalidate();
					repaint();
					remove(descriptionLabel);
					String value = (String) table.getValueAt(data, 2);
					System.out.println(value.length());
					descriptionLabel.setText("<html><p>" + value
							+ "</p></html>");
					// descriptionLabel.add((String)table.getValueAt(data, 2));
					tablePanel.add(descriptionLabel);
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
				if (descriptionForm.getDescription().getText().length() < 1100) {
					dictionary.add(key, description.getText());
					fileManager.saveChanges();
					remove(descriptionForm);
					JOptionPane.showMessageDialog(null, searchedArea.getText()
							+ " has been added successfully");
					description.setText("");
					searchedArea.setText("");
					revalidate();
					repaint();
				} else {
					JOptionPane.showMessageDialog(null,
							"Please reduce the description lenght");
				}
			}
		});
		revalidate();
		repaint();
		return descriptionForm;
	}

	public void editButton(JLabel descriptionLabel, JTable table,
			JTextArea area, String key, List<String> list) {
		tablePanel.getEdit().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tablePanel.remove(descriptionLabel);
				int data = BoxChecker.isChecked(table);
				if (data > -1) {
					JButton save = new JButton("save");
					tablePanel.add(save);
					revalidate();
					repaint();
					save.setBounds(860, 220, 70, 25);
					area.setText((String) table.getValueAt(data, 2));
					area.setLineWrap(true);
					area.setWrapStyleWord(true);
					scroll = new JScrollPane(area);
					tablePanel.add(scroll);
					scroll.setBounds(560, 5, 400, 200);
					save.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							if (area.getText().length() < 1100) {
								dictionary.update(
										(String) table.getValueAt(data, 1),
										area.getText());
								fileManager.saveChanges();
								tablePanel.remove(descriptionLabel);
								tablePanel.remove(area);
								tablePanel.remove(save);
								remove(tablePanel);
								createTablePanel(key, list);
								repaint();
							} else {
								JOptionPane.showMessageDialog(null,
										"Please reduce description lenght");
							}
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