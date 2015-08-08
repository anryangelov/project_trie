package project_trie.desktop;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class AddWordFormPanel extends JPanel {
	private JTextArea description = new JTextArea();
	private JButton submit = createButton("submit", KeyEvent.VK_ENTER);
	private JTextArea word = new JTextArea();

	public AddWordFormPanel() {
		setBackground(Color.WHITE);
		createForm();
	}
	 public  JButton createButton(String name, int virtualKey) {
	        JButton btn = new JButton(name);
	        InputMap im = btn.getInputMap(WHEN_IN_FOCUSED_WINDOW);
	        ActionMap am = btn.getActionMap();
	        im.put(KeyStroke.getKeyStroke(virtualKey, 0), "clickMe");
	        am.put("clickMe", new AbstractAction() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	              //  JButton btn = (JButton) e.getSource();
	                btn.doClick();
	            }
	        });
	        return btn;
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
