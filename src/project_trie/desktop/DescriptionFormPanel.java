package project_trie.desktop;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class DescriptionFormPanel extends JPanel {
	private JTextArea description;
	private JButton submit;
	private JTextField word;

	public DescriptionFormPanel() {
		description = new JTextArea();
		submit = new JButton("submit");
		word = new JTextField();
	//	setBackground(Color.WHITE);
		createForm();
	}
	 public  JButton createButton(String name, int virtualKey) {
	        JButton btn = new JButton(name);
	        InputMap im = btn.getInputMap(virtualKey);
	        ActionMap am = btn.getActionMap();
	        im.put(KeyStroke.getKeyStroke(virtualKey, 0), "clickMe");
	        am.put("clickMe", new AbstractAction() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                JButton btn = (JButton) e.getSource();
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

	public JTextField getWord() {
		return word;
	}
}
