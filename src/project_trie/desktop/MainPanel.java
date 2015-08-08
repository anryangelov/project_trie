package project_trie.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import project_trie.trie.FileManager;

public class MainPanel extends JPanel {
	private NavigationPanel navigationPanel;
	private AddWordFormPanel descriptionForm;
	private static final String FILE_NAME = "dictionary.ser";
	private FileManager fileManager;
	private Table tab;

	MainPanel() {
		navigationPanel = new NavigationPanel();
		descriptionForm = new AddWordFormPanel();
		fileManager = new FileManager(FILE_NAME);
		setLayout(null);
		navigationPanel.setBounds(0, 0, 1000, 50);
		add(navigationPanel);
		navigationPanel.getAddWord().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (navigationPanel.getSearchArea().getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Please enter a word in the text area");
					return;
				}
				remove(tab);
				createDescriptionForm();
			}
		});
		navigationPanel.getRemoveButton().addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String key = navigationPanel.getSearchArea().getText();
						fileManager.getDictionary().remove(key);
						JOptionPane.showMessageDialog(null, navigationPanel
								.getSearchArea().getText()
								+ " has been removed successfully");
						navigationPanel.getSearchArea().setText("");
						try {
							fileManager.serialize(fileManager.getDictionary(),
									"dictionary.ser");
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				});
		navigationPanel.getSearchButton().addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// pass the word from the text area as argument
						// remove(descriptionForm);
						String key = navigationPanel.getSearchArea().getText();
						String word = fileManager.getDictionary().get(key);
						tab = new Table(findAllWords(key));
						tab.setBounds(12, 70, 600, 500);
						add(tab);
						navigationPanel.getSearchArea().setText("");
						if (word != null) {
							System.out.println(word);
						} else {
							JOptionPane
									.showMessageDialog(null,
											"Please enter some description and press submit");
							createDescriptionForm();
						}
					}
				});
		descriptionForm.getSubmit().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				fileManager.getDictionary().add(
						navigationPanel.getSearchArea().getText(),
						descriptionForm.getDescription().getText());
				descriptionForm.getDescription().setText("");
				remove(descriptionForm);
				JOptionPane.showMessageDialog(null, navigationPanel
						.getSearchArea().getText()
						+ " has been added successfully");
				navigationPanel.getSearchArea().setText("");
				try {
					fileManager.serialize(fileManager.getDictionary(),
							"dictionary.ser");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				revalidate();
				repaint();
			}
		});
	}

	private void createDescriptionForm() {
		descriptionForm.getWord().setText(
				navigationPanel.getSearchArea().getText());
		descriptionForm.getDescription().setText("Enter description here");
		descriptionForm.setBounds(0, 70, 950, 500);
		add(descriptionForm);
		revalidate();
		repaint();
	}

	public List<String> findAllWords(String word) {
		List<String> l = new ArrayList<String>();
		String[] s = fileManager.getDictionary().toString().split("\n");
		for (int i = 0; i < s.length; i++) {
			if (s[i].substring(0, 2).equals(word.substring(0, 2))) {
				l.add(s[i]);
			}
		}
		return l;
	}
}