package de.marik.mypackage.viewer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class MyTableModelTest {
	private static final int MAX = 10;
	private MyTableModel tableModel;

	@Test
	void testGetTitle() {
		Object[][] cells = new Object[MAX][MAX];
		tableModel = new MyTableModel(cells, "TestTitle");
		assertEquals("TestTitle", tableModel.getTitel());
	}
	
	@RepeatedTest(10)
	void testIsCellEditable() {
		Object[][] cells = new Object[MAX][MAX];
		tableModel = new MyTableModel(cells, "TestTitle");
		Random random = new Random();
		int row = random.nextInt(MAX);
		int column = random.nextInt(MAX);
		tableModel.setCellEditable(row, column, false);
		assertFalse(tableModel.isCellEditable(row, column));
		tableModel.setCellEditable(row, column, true);
		assertTrue(tableModel.isCellEditable(row, column));
		tableModel.setCellEditable(row, column, false);
		assertFalse(tableModel.isCellEditable(row, column));
	}
}
