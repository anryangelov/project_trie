package project_trie.desktop;

import javax.swing.JTextArea;

public class DescriptionArea extends JTextArea {
	public DescriptionArea() {
		setBounds(10, 55, 700, 300);
		setText("Enter description here...");
		setLineWrap(true);
		setWrapStyleWord(true);
	}
}
