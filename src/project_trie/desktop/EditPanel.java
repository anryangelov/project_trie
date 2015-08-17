package project_trie.desktop;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EditPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextArea editArea;
	private JScrollPane sp;
	private JButton save;
	private Font font = new Font("Serif", Font.ROMAN_BASELINE, 15);

	public EditPanel(String text) {
		setLayout(null);
		setBounds(540, 10, 700, 540);
		setBackground(Color.ORANGE);
		editArea = new JTextArea();
		//editArea.setBackground(Color.WHITE);
		editArea.setText(text);
		editArea.setLineWrap(true);
		editArea.setWrapStyleWord(true);
		editArea.setFont(font);
		sp = new JScrollPane(editArea);
		sp.setBounds(0, 0, getWidth(), getHeight() - 80);
		save = new JButton("save");
		save.setBounds(0, getHeight()-71, 100, 30);
		add(sp);
		add(save);
	}

	public JButton getSaveButton() {
		return save;
	}

	public JTextArea getEditArea() {
		return editArea;
	}
}
