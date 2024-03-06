package de.marik.mypackage.tests;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel {
	private static final long serialVersionUID = -5751512081464014175L;
	private boolean[][] editableCells;
	private int rows;
	private int columns;

	MyTableModel(Object[][] cellValues, Object[] columnNames) {
		super(cellValues, columnNames);
		rows = cellValues.length;
		columns = columnNames.length;
		editableCells = new boolean[rows][columns];
		
		
		//Debugging
//		for (int i = 0; i < columnNames.length; i++) {
//			for (int j = 0; j < cellValues.length; j++) {
//				editableCells[j][i] = true;
//			}
//		}
		//Debugging
		
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return editableCells[row][column];
	}

	public void setCellEditable(int row, int column, boolean value) {
		editableCells[row][column] = value;
		fireTableCellUpdated(row, column);
	}

}
