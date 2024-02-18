package de.marik.mypackage.tests;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ScorePanel extends JPanel {
	private MyTableModel tableModel;
//	private String title;
//	private String[][] scoresEntry;
//	private String[] columnName;
	private int activePlace = 2; // active cell row + 1
	

	private static final long serialVersionUID = 704313393302539651L;

	ScorePanel(String title, MyTableModel tableModel) {
		super();
		this.tableModel = tableModel;
//		this.title = title;
//		this.scoresEntry = scoresEntry;
//		this.columnName = columnName;
		setLayout(new BorderLayout());

		JLabel label = new JLabel(title);
		label.setFont(new Font("arial", Font.BOLD, 14));
		label.setHorizontalAlignment(JLabel.CENTER);
		add(label, BorderLayout.NORTH);

		JTable table = constructTable(tableModel);
		add(table, BorderLayout.CENTER);
//		System.out.println(getBackground().toString());
//		System.out.println(getBackground().getRGB());
	}

	private JTable constructTable(MyTableModel tableModel) {
		JTable table = new JTable(tableModel) {

//			public boolean isCellEditable(int row, int column) {
//				return (column == 1)&&(row + 1 == activePlace);
//			}
		};
		table.setCellSelectionEnabled(false);
//		table.setFocusable(false);
		table.setRowHeight(20);
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.setBackground(new Color(238, 238, 238));
//		additionTable.setRowMargin(15);
		table.setIntercellSpacing(new Dimension(10, 0));
		table.setShowGrid(false);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		return table;
	}

	public String newRecord(int place) {
		tableModel.setCellEditable(1, 1, true);
		return null;
		
	}

}
