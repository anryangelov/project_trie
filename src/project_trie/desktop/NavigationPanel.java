package project_trie.desktop;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class NavigationPanel extends JPanel {
	private JTextArea searchArea = new JTextArea();
	private JButton searchButton = new JButton("search word");
	private JButton addWord = new JButton("add word");
	private JButton removeButton = new JButton("remove");

	public NavigationPanel() {
		createPanel();
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


	private void createPanel() {
		setLayout(null);
		setPreferredSize(new Dimension(500, 100));
		getSearchArea().setBounds(10, 10, 300, 30);
		add(getSearchArea());
		getSearchButton().setBounds(320, 10, 200, 30);
		add(getSearchButton());
		getAddWord().setBounds(520, 10, 200, 30);
		add(getAddWord());
		removeButton.setBounds(720, 10, 200, 30);
		add(removeButton);
	}

	public JButton getAddWord() {
		return addWord;
	}

	public JTextArea getSearchArea() {
		return searchArea;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public JButton getRemoveButton() {
		return removeButton;
	}
}
