package project_trie.desktop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddWordFormPanel extends JPanel {

	private JTextArea description = new JTextArea();
	private JButton submit = new JButton("submit");
	private JTextField word = new JTextField();

	public AddWordFormPanel() {
		createForm();
	}

	public void createForm() {
		setLayout(null);
		// setBackground(Color.BLUE);
		word.setBounds(10, 20, 200, 32);
		add(word);
		getDescription().setBounds(10, 55, 800, 300);
		add(getDescription());
		getSubmit().setBounds(720, 370, 90, 30);
		add(getSubmit());

	}

	public JButton getSubmit() {
		return submit;
	}

	public JTextArea getDescription() {
		return description;
	}

	public void setDescription(JTextArea description) {
		this.description = description;
	}
}
