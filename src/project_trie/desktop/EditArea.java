package project_trie.desktop;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EditArea extends JTextArea {
	private JTextArea editArea;
	private JScrollPane scroll;
	private JButton save;

	public EditArea() {
		setBounds(540, 15, 400, 400);
	//	editArea = new JTextArea();
		setLineWrap(true);
		setWrapStyleWord(true);
		//setVisible(false);
	}
}