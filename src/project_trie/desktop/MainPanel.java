package project_trie.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import project_trie.trie.FileMenager;
import project_trie.trie.Trie;

public class MainPanel extends JPanel {
	NavigationPanel navigationPanel = new NavigationPanel();
	AddWordFormPanel descriptionForm = new AddWordFormPanel();
	Trie dictionary = new Trie();

	MainPanel() {
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
				descriptionForm.getWord().setText(navigationPanel.getSearchArea().getText());;
				descriptionForm.getDescription().setText("Enter description here");
				descriptionForm.setBounds(0, 70, 950, 500);
				add(descriptionForm);
				revalidate();
				repaint();
			}
		});
		descriptionForm.getSubmit().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dictionary.add(navigationPanel.getSearchArea().getText(),
						descriptionForm.getDescription().getText());
				descriptionForm.getDescription().setText("");
				remove(descriptionForm);
				JOptionPane.showMessageDialog(null, navigationPanel
						.getSearchArea().getText()
						+ " has been added successfully");
				navigationPanel.getSearchArea().setText("");
				try {
					FileMenager.serialize(dictionary, "dictionary.ser");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				revalidate();
				repaint();
			}
		});
	}
}
