package project_trie.desktop;

import java.awt.Font;

import javax.swing.JTextArea;

public class DescriptionLable extends JTextArea {
	private static final long serialVersionUID = 1L;
	private Font font = new Font("Serif", Font.ROMAN_BASELINE, 15);

	public DescriptionLable() {
		setBounds(560, 5, 400, 450);
		setLineWrap(true);
		setWrapStyleWord(true);
		setEditable(false);
		setFont(font);
	}
}
