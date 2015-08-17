package project_trie.desktop;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TablePanelTest extends JPanel {

	JPanel bottom = new JPanel();
	JPanel jp = new JPanel();
	CardLayout cl = new CardLayout();
	JButton next;
	JButton prev;
	JButton view = new JButton("view");
	int c = 0;

	public TablePanelTest(List<String> l) {
		setBackground(Color.ORANGE);
		List<String> li = new ArrayList<>();
		List<List<String>> list = new ArrayList<>();
		for (int i = 0, a = 0; i < l.size(); i++, a++) {

			li.add(l.get(i));
			if (a == 10) {
				list.add(li);
				li = new ArrayList<String>();
				a = -1;
			}
		}
		list.add(li);
		setLayout(null);
		jp.setLayout(null);
		bottom.setLayout(cl);
		bottom.setBounds(0, 50, 1500, 800);
		int a = 1;
		for (int i = 0; i < list.size(); i++) {
			Table table = new Table(list.get(i), a);
			jp.add(table);
			view.setBounds(200, 400, 100, 30);
			table.setBounds(0, 50, 527, 370);
			jp.add(view);
			TablePanel tp = new TablePanel();
			tp.addTable(table, true);
			bottom.add(tp, i + 1 + "");
			a += list.get(i).size();
		}
		if (l.size() > 0) {
			next = new JButton("next");
			prev = new JButton("prev");
			prev.setBounds(20, 20, 70, 23);
			next.setBounds(450, 20, 70, 23);
			add(next);
			add(prev);
			revalidate();
			repaint();
		}

		add(bottom);
		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (c < l.size()) {
					c++;
					cl.show(bottom, c + "");
				}
			}
		});
		prev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (c > 0) {
					c--;
					cl.show(bottom, c + "");
				}
			}
		});

	}
}
