//package project_trie.desktop;
//
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//import java.util.List;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JTable;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;
//import project_trie.trie.FileManager;
//import project_trie.trie.Trie;
//
//public class MainPanel extends JPanel {
//	private static final long serialVersionUID = 1L;
//	private MenuPanel navigationPanel;
//	private DescriptionFormPanel descriptionForm;
//	private Trie dictionary;
//	private FileManager fileManager;
//	private TablePanel tablePanel;
//	private JTextField searchedArea;
//	private JTextArea description;
//	private EditPanel editPanel;
//	private Font font = new Font("Serif", Font.ROMAN_BASELINE, 15);
//
//	MainPanel() {
//		setLayout(null);
//		setBackground(Color.WHITE);
//		fileManager = new FileManager();
//		dictionary = fileManager.getDictionary();
//		navigationPanel = new MenuPanel(dictionary.list());
//		descriptionForm = new DescriptionFormPanel();
//		searchedArea = navigationPanel.getSearchArea();
//		description = descriptionForm.getDescription();
//		navigationPanel.setBounds(0, 0, 1000, 50);
//		editPanel = new EditPanel();
//		tablePanel = new TablePanel();
//		add(navigationPanel);
//		navigationPanel.getAddWord().addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if (searchedArea.getText().isEmpty()) {
//					JOptionPane.showMessageDialog(null,
//							"Please enter a word in the text area");
//					return;
//				}
//				removeTable(tablePanel);
//				// remove(editPanel);
//				add(createDescriptionForm("Enter description here"));
//				revalidate();
//				repaint();
//			}
//		});
//
//		navigationPanel.getShowAllWords().addActionListener(
//				new ActionListener() {
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						tablePanel.removeAll();
//						remove(descriptionForm);
//						Table table = new Table(showAll(), dictionary);
//						createTablePanel(table);
//					}
//				});
//
//		navigationPanel.getSearchButton().addActionListener(
//				new ActionListener() {
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						// pass the word from the text area as argument
//						// remove(descriptionForm);
//						tablePanel.removeTable();
//						String key = searchedArea.getText();
//						String value = fileManager.dictionary.get(key);
//						Table table = new Table(findAllWords(key), dictionary);
//						createTablePanel(table);
//						// createTablePanel(table);
//						if (!key.isEmpty()) {
//							if (value == null) {
//								removeTable(tablePanel);
//								revalidate();
//								repaint();
//								add(createDescriptionForm("Enter description here"));
//								JOptionPane
//										.showMessageDialog(null,
//												"Please enter some description and press submit");
//							}
//						} else {
//							// if searched area isEmpty show the message
//							JOptionPane.showMessageDialog(null,
//									"Please enter the word");
//						}
//						searchedArea.setText("");
//					}
//				});
//	}
//
//	private void removeTable(JPanel table) {
//		if (table != null) {
//			remove(table);
//		}
//	}
//
//	private void createTablePanel(Table table) {
//		tablePanel.addTable(table);
//		add(tablePanel);
//		revalidate();
//		repaint();
//		tablePanel.getRemove().addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				int data = BoxChecker.isChecked(table);
//				if (data > -1) {
//					String word = (String) table.getTableModel().getValueAt(data, 1);
//					dictionary.remove(word);
//					table.getTableModel().removeRow(data);
//					fileManager.saveChanges();
//				} else {
//					JOptionPane.showMessageDialog(null, "Please select word");
//				}
//
//			}
//		});
//		DescriptionLable descriptionLabel = new DescriptionLable();
//		editButton(descriptionLabel, table);
//
//		tablePanel.getViewDescription().addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				int data = BoxChecker.isChecked(table);
//				if (data > -1) {
//					remove(editPanel.getScroll());
//					revalidate();
//					repaint();
//					remove(descriptionLabel);
//					String value = (String) table.getValueAt(data, 2);
//					descriptionLabel.setText(value);
//					// descriptionLabel.add((String)table.getValueAt(data, 2));
//					tablePanel.add(descriptionLabel);
//					revalidate();
//					repaint();
//				} else {
//					JOptionPane.showMessageDialog(null, "Please select word");
//				}
//			}
//		});
//	}
//
//	private JPanel createDescriptionForm(String text) {
//		descriptionForm.getWord().setText(searchedArea.getText());
//		description.setText(text);
//		descriptionForm.setBounds(0, 70, 950, 500);
//		// add(descriptionForm);
//		descriptionForm.getSubmit().addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// add word and description
//				String key = descriptionForm.getWord().getText();
//				if (descriptionForm.getDescription().getText().length() < 1100) {
//					dictionary.add(key, description.getText());
//					fileManager.saveChanges();
//					remove(descriptionForm);
//					revalidate();
//					repaint();
//					JOptionPane.showMessageDialog(null, searchedArea.getText()
//							+ " has been added successfully");
//					description.setText("");
//					searchedArea.setText("");
//				} else {
//					JOptionPane.showMessageDialog(null,
//							"Please reduce the description lenght");
//				}
//			}
//		});
//		return descriptionForm;
//	}
//
//	public void editButton(JTextArea descriptionLabel, Table table) {
//		tablePanel.getEdit().addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				tablePanel.remove(descriptionLabel);
//				int data = BoxChecker.isChecked(table);
//				if (data > -1) {
//					editPanel.getEditArea().setText(
//							(String) table.getValueAt(data, 2));
//					tablePanel.add(editPanel);
//					revalidate();
//					repaint();
//					editPanel.getSave().addActionListener(new ActionListener() {
//						@Override
//						public void actionPerformed(ActionEvent e) {
//							if (editPanel.getEditArea().getText().length() < 1100) {
//								dictionary.update(
//										(String) table.getValueAt(data, 1),
//										editPanel.getEditArea().getText());
//								fileManager.saveChanges();
//								tablePanel.remove(descriptionLabel);
//								tablePanel.remove(editPanel.getEditArea());
//								tablePanel.remove(editPanel.getSave());
//								remove(tablePanel);
//								createTablePanel(table);
//								repaint();
//							} else {
//								JOptionPane.showMessageDialog(null,
//										"Please reduce description lenght");
//							}
//						}
//					});
//				}
//			}
//		});
//	}
//
//	public List<String> findAllWords(String word) {
//		List<String> l = new ArrayList<String>();
//		for (String s : dictionary.list()) {
//			// if (s.substring(0, 2).equals(word.substring(0, 2))) {
//			if (s.equals(word)) {
//				l.add(s);
//				continue;
//				// }
//				// l.add(s);
//			}
//		}
//		return l;
//	}
//
//	public List<String> showAll() {
//		List<String> l = new ArrayList<String>();
//		for (String s : dictionary.list()) {
//			l.add(s);
//		}
//		return l;
//	}
//}