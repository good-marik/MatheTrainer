package de.marik.mypackage.viewer;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel {
	private static final long serialVersionUID = 3456248862367551347L;
	private static final Object[] columnNames = new String[] { "Place", "Name", "Score" };
	private final String title;
	private boolean[][] editableCells;

	public MyTableModel(Object[][] cellValues, String title) {
		super(cellValues, columnNames);
		this.title = title;
		int rows = cellValues.length;
		int columns = cellValues[0].length;
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

	public String getTitel() {
		return title;
	}

}
