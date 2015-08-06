package project_trie.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainPanel extends JPanel {
	NavigationPanel navigationPanel = new NavigationPanel();
	AddWordFormPanel descriptionForm = new AddWordFormPanel();

	MainPanel() {
		setLayout(null);
		navigationPanel.setBounds(0, 0, 1000, 50);
		add(navigationPanel);
		navigationPanel.getAddWord().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				descriptionForm.setBounds(0, 70, 950, 500);
				add(descriptionForm);
				revalidate();
				repaint();
			}
		});
		descriptionForm.getSubmit().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				remove(descriptionForm);
				JOptionPane.showMessageDialog(null, "word has been added successfully");
				revalidate();
				repaint();

			}
		});
	}
}
