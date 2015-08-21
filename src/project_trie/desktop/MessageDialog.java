package project_trie.desktop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/*
 * This class create automatically closing window
 * Timer class set the period;
 */
public class MessageDialog extends JFrame {
	private static final long serialVersionUID = 1L;

	public MessageDialog() {
	}

	public MessageDialog(String word, String message) {
		setPreferredSize(new Dimension(340, 100));
		JOptionPane pane;
		if (message.equals("")) {
			pane = new JOptionPane("Dictionary not contains word" + " " + '"'
					+ word + '"', JOptionPane.INFORMATION_MESSAGE);
		} else {
			pane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
		}
		JDialog dialog = pane.createDialog(this, "Title");
		dialog.setLocationRelativeTo(null);
		Timer timer = new Timer(3000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
			}
		});
		timer.start();
		dialog.setVisible(true);
	}

	public boolean isMessageAnswerPositive(String message) {
		return JOptionPane.showConfirmDialog(null, message, "",
				JOptionPane.YES_NO_OPTION) == 0;
	}
}
