package project_trie.desktop;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;

public class TablePanelTest extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel bottom;
	private JPanel jp;
	private CardLayout cl;
	private JButton next;
	private JButton prev;
	private int counter = 0;
	private int listSize;

	public TablePanelTest(List<String> l) {
		setLayout(null);
		listSize = l.size();
		bottom = new JPanel();
		jp = new JPanel();
		cl = new CardLayout();
		next = new JButton("next");
		prev = new JButton("prev");
		List<List<String>> list = splitList(l);
		jp.setLayout(null);
		bottom.setLayout(cl);
		setBackground(Color.ORANGE);
		bottom.setBounds(0, 50, 1500, 800);
		createPanels(list);
		add(bottom);
		firePrev();
		fireNext();
	}

	public void createPanels(List<List<String>> list) {
		int a = 1;
		for (int i = 0; i < list.size(); i++) {
			Table table = new Table(list.get(i), a);
			jp.add(table);
			table.setBounds(0, 50, 527, 370);
			TablePanel tablePanel = new TablePanel();
			tablePanel.addTable(table, true);
			bottom.add(tablePanel, i + 1 + "");
			a += list.get(i).size();
		}
		if (listSize > 10) {
			prev.setBounds(20, 20, 70, 23);
			next.setBounds(450, 20, 70, 23);
			add(next);
			add(prev);
		}
	}

	private void fireNext() {
		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (counter < listSize) {
					counter++;
					cl.show(bottom, counter + "");
				}
			}
		});
	}

	private void firePrev() {
		prev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (counter > 0) {
					counter--;
					cl.show(bottom, counter + "");
				}
			}
		});
	}

	private List<List<String>> splitList(List<String> l) {
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
		if (li.size() < 10) {

			list.add(li);
		}
		return list;
	}
}
