package project_trie.desktop;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class DescriptionLable extends JTextArea {
	private static final long serialVersionUID = 1L;
	private Font font = new Font("Serif", Font.ROMAN_BASELINE, 17);

	public DescriptionLable(String text,String word) {
		setBackground(Color.ORANGE);
		setBounds(540, 10, 700, 600);
		setText(word.toUpperCase()+"\n\n"+text);
		setLineWrap(true);
		setWrapStyleWord(true);
		setEditable(false);
		setFont(font);
	}
}
