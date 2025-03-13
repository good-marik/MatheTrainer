package de.marik.mypackage.viewer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.marik.mypackage.main.Addition;
import de.marik.mypackage.main.Division;
import de.marik.mypackage.main.Multiplication;
import de.marik.mypackage.main.Operation;
import de.marik.mypackage.main.Substraction;

class HighScorePanelTest {
	private static final int MINSCORE = 70;
	private static final int MAXSCORE = 900;

	private HighScorePanel panel;
	private Operation addition;
	private Operation substraction;
	private Operation multiplication;
	private Operation division;
	private List<Operation> operations;

	@BeforeEach
	void setUp() {
		addition = new Addition();
		substraction = new Substraction();
		multiplication = new Multiplication();
		division = new Division();
		operations = Arrays.asList(addition, substraction, multiplication, division);
		panel = new HighScorePanel();
	}

	@Test
	void testIsANewRecord() {
		for (Operation operation : operations) {
			assertFalse(panel.isANewRecord(operation, 0));
			assertFalse(panel.isANewRecord(operation, MINSCORE - 1));
			assertTrue(panel.isANewRecord(operation, MAXSCORE + 1));
		}
	}
}
