package project_trie.desktop;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class DescriptionLable extends JTextArea {
	private static final long serialVersionUID = 1L;
	private Font font = new Font("Serif", Font.ROMAN_BASELINE, 15);

	public DescriptionLable(String text) {
		if (text.length() > 1100) {
			JOptionPane.showMessageDialog(null, "reduce text description");
		} else {
			setBounds(560, 5, 400, 450);
			setText(text);
			setLineWrap(true);
			setWrapStyleWord(true);
			setEditable(false);
			setFont(font);
		}
	}
}
