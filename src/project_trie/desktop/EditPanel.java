package project_trie.desktop;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EditPanel extends JPanel {
	private JTextArea editArea;
	private JScrollPane scroll;
	private JButton save;

	public EditPanel() {
		setLayout(null);
		setBounds(540, 5, 400, 500);
		setBackground(Color.RED);
		editArea = new JTextArea();
		editArea.setLineWrap(true);
		editArea.setWrapStyleWord(true);
		scroll = new JScrollPane(editArea);
		scroll.setBounds(5, 0, 370, 400);
		save = new JButton("save");
		save.setBounds(150, 410, 100, 30);
		add(scroll);
		add(save);
	}
	
	

	public JTextArea getEditArea() {
		return editArea;
	}

	public void setEditArea(JTextArea editArea) {
		this.editArea = editArea;
	}

	public JScrollPane getScroll() {
		return scroll;
	}

	public void setScroll(JScrollPane scroll) {
		this.scroll = scroll;
	}

	public JButton getSave() {
		return save;
	}

	public void setSave(JButton save) {
		this.save = save;
	}
}