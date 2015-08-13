package project_trie.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import project_trie.trie.FileManager;

public class DescriptionFormPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextArea description;
	private JButton submit;
	private JTextField word;

	public DescriptionFormPanel() {
		description = new JTextArea();
		submit = new JButton("submit");
		word = new JTextField();
		createForm();
	}

	public void createForm() {
		setLayout(null);
		submit.setToolTipText("Press the button to save the word");
		getWord().setBounds(10, 20, 200, 32);
		add(getWord());
		getDescription().setBounds(10, 55, 700, 300);
		description.setText("Enter description here...");
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		add(getDescription());
		submit.setBounds(620, 370, 90, 30);
		add(getSubmit());
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FileManager.dictionary.add(word.getText(),
						description.getText());
				FileManager.saveChanges();
				JOptionPane.showMessageDialog(null, word.getText()
						+ " has been added successfully");
				word.setText("");
				description.setText("Enter description here..");
				MainPanel.cl.first(MainPanel.bottom);
			}
		});
	}

	public JButton getSubmit() {
		return submit;
	}

	public JTextArea getDescription() {
		return description;
	}

	public JTextField getWord() {
		return word;
	}
}
