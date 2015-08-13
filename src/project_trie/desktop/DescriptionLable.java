package project_trie.desktop;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class DescriptionLable extends JTextArea {
	private static final long serialVersionUID = 1L;
	private Font font = new Font("Serif", Font.ROMAN_BASELINE, 15);

	public DescriptionLable(String text) {
		setBounds(560, 0, 400, 550);
		setText(text);
		setLineWrap(true);
		setWrapStyleWord(true);
		setEditable(false);
		setFont(font);
	}
	
}
