package de.marik.mypackage.viewer;

import java.io.Serializable;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private boolean[][] editableCells;
	private Object[][] cellValues;
//	private final Object[] columnNames;
	private int rows;
	private int columns;

	MyTableModel(Object[][] cellValues, Object[] columnNames) {
		super(cellValues, columnNames);
		this.cellValues = cellValues;
//		this.columnNames = columnNames;
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
	
	public void setEntry(int position, String name, int score) {
		cellValues[position-1][1] = name;
		cellValues[position-1][2] = String.valueOf(score);
	}

}
