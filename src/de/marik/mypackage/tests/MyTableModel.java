package de.marik.mypackage.tests;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel {
	private boolean[][] editableCells;
	private int rows;
	private int columns;

	MyTableModel(int rows, int columns) {
		super(rows, columns);
		this.rows = rows;
		this.columns = columns;
		editableCells = new boolean[rows][columns];
	}

	MyTableModel(Object[][] cellValues, Object[] columnNames) {
		super(cellValues, columnNames);
		rows = cellValues.length;
		columns = columnNames.length;
		editableCells = new boolean[rows][columns];
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
