package project_trie.desktop;

import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTable;

public class Table extends JPanel{
	  Object rowData[][] = { { "Row1-Column1", "Row1-Column2", "Row1-Column3" },
		        { "Row2-Column1", "Row2-Column2", "Row2-Column3" } };
		     Object columnNames[] = { "Column One", "Column Two", "Column Three" };
		  JTable table = new JTable(rowData, columnNames);

		    public Table() {
		    	table.setShowGrid(false);
		    	add(table);
		    	//super(rowData,columnNames);

			}
}
