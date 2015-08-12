package project_trie.desktop;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

import project_trie.trie.FileManager;

public class DescriptionFormPanel extends JPanel {
	private JTextArea description;
	private JButton submit;
	private JTextField word;
	private JScrollPane pane;

	public DescriptionFormPanel() {
		description = new JTextArea();
		submit = createButton("submit", KeyEvent.VK_ENTER);
		word = new JTextField();
		pane = new JScrollPane();
	
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
				FileManager.dictionary.add(word.getText(), description.getText());
				FileManager.saveChanges();
				JOptionPane.showMessageDialog(null, word.getText()
						+ " has been added successfully");
				word.setText("");
				description.setText("");
				
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
