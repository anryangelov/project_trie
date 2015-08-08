package project_trie.desktop;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class AddWordFormPanel extends JPanel {
	private JTextArea description = new JTextArea();
	private JButton submit = new JButton("submit");
	private JTextArea word = new JTextArea();

	public AddWordFormPanel() {
		createForm();
	}

	public void createForm() {
		setLayout(null);
		submit.setToolTipText("Press the button to save the word");
		getWord().setBounds(10, 20, 200, 32);
		add(getWord());
		getDescription().setBounds(10, 55, 800, 300);
		add(getDescription());
		submit.setBounds(720, 370, 90, 30);
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

	public JTextArea getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word.setText(word);
	}
}
