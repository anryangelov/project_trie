package project_trie.desktop;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

/*
 * this class is extension of TablePanel class
 * The Idea is to remove scrollPane's and to
 * spread the words in separate tables and 
 * switch between them by pressing next and prev buttons
 */
public class TablePanelHolder extends JPanel {
	private static final long serialVersionUID = 1L;
	public static JPanel bottom;
	private JPanel jp;
	public static CardLayout cl;
	private JButton next;
	private JButton prev;
	private int counter = 1;
	private int listSize;
	private int pagesCount;
	public static TablePanelHolder tph;

	public TablePanelHolder(List<String> l) {
		tph = this;
		setLayout(null);
		listSize = l.size();
		bottom = new JPanel();
		jp = new JPanel();
		cl = new CardLayout();
		next = new JButton("next");
		prev = new JButton("back");
		prev.setVisible(false);
		List<List<String>> list = splitList(l);
		pagesCount = list.size();
		jp.setLayout(null);
		bottom.setLayout(cl);
		setBackground(Color.ORANGE);
		bottom.setBounds(0, 50, 1500, 800);
		createPanels(list);
		add(bottom);
		firePrev();
		fireNext();
	}

	/*
	 * createPanels method use splitList method to create n number of panels
	 * using TablePanel class This is the main idea of this class
	 */
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
			prev.setBounds(5, 20, 70, 23);
			next.setBounds(460, 20, 70, 23);
			add(next);
			add(prev);
		}
	}

	private void fireNext() {
		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				counter++;
				if (counter == pagesCount) {
					next.setVisible(false);
				}
				if (counter > 1) {
					prev.setVisible(true);
				}
				cl.next(bottom);
			}
		});
	}

	private void firePrev() {
		prev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				counter--;
				if (counter < pagesCount) {
					next.setVisible(true);
				}
				if (counter == 1) {
					prev.setVisible(false);
				}
				cl.previous(bottom);
			}
		});
	}

	/*
	 * splitList method split all words in dictionary in separated list's with
	 * capacity 10 words.Actually this is the size of the Table
	 */
	private List<List<String>> splitList(List<String> l) {
		List<String> li = new ArrayList<>();
		List<List<String>> list = new ArrayList<>();
		for (int i = 0, a = 0; i < l.size(); i++, a++) {
			li.add(l.get(i));
			if (a == 9) {
				list.add(li);
				li = new ArrayList<String>();
				a = -1;
			}
		}
		if (li.size() < 10 && li.size() > 0) {
			list.add(li);
		}
		return list;
	}
}
