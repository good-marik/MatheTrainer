package de.marik.mypackage.viewer;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel {

	private boolean[][] editableCells;
	private int rows;
	private int columns;
	private final static Object[] columnNames = new String[] { "Place", "Name", "Score" };
	private String title;

	MyTableModel(Object[][] cellValues, String title) {
		super(cellValues, columnNames);
		this.title = title;
		rows = cellValues.length;
		columns = columnNames.length;
		editableCells = new boolean[rows][columns];

//		 Debugging
//		for (int i = 0; i < columnNames.length; i++) {
//			for (int j = 0; j < cellValues.length; j++) {
//				editableCells[j][i] = true;
//			}
//		}
//		 Debugging
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return editableCells[row][column];
	}

	public void setCellEditable(int row, int column, boolean value) {
		editableCells[row][column] = value;
		fireTableCellUpdated(row, column);
	}

//	public void setEntry(int position, String name, int score) {
//		cellValues[position - 1][1] = name;
//		cellValues[position - 1][2] = String.valueOf(score);
//	}

	public String getTitel() {
		return title;
	}

//	public Operation getOperation() {
//		return operation;
//	}

	public void toPrint() {
		System.out.println("*");
		int rows = getRowCount();
		int columns = getColumnCount();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				System.out.print(getValueAt(i, j) + ", ");
			}
			System.out.println();
		}

		System.out.println("*");
	}

}
