package project_trie.desktop;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class ButtonAction {
	/*
	 * TextArea activate button when enter is pressed
	 */
	public ButtonAction(JButton btn, JTextArea textArea) {
		textArea.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btn.doClick();
				}
			}
		});
	}
}
