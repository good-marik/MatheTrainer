package de.marik.mypackage.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class DivisionTest {
	private Operation operation;
	private static final int MINNUMERATOR = 1;
	private static final int MAXNUMERATOR = 100;
	private static final int MINDENOMINATOR = 1;
	private static final int MAXDENOMINATOR = 10;

	private static final int MINPOINTS = 10;
	private static final int MAXPOINTS = 25;

	@BeforeEach
	void setUp() throws Exception {
		operation = new Division();
	}

	@RepeatedTest(20)
	void testGetTaskDescription() {
		operation.setTaskAndGetResult();
		String taskDescription = operation.getTaskDescription();
		Pattern pattern = Pattern.compile("(\\d+) : (\\d+) = ");
		Matcher matcher = pattern.matcher(taskDescription);
		// Validate task description
		assertTrue(matcher.matches(), "Task description format is incorrect");
	}

	@RepeatedTest(20)
	void testSetTaskAndGetResult() {
		int result = operation.setTaskAndGetResult();

		// Extract numbers from task description
		String[] task = operation.getTaskDescription().split(" ");
		int numerator = Integer.parseInt(task[0]);
		int denominator = Integer.parseInt(task[2]);

		// Validate number range
		assertTrue(numerator >= MINNUMERATOR && numerator <= MAXNUMERATOR, "numerator is out of range");
		assertTrue(denominator >= MINDENOMINATOR && denominator <= MAXDENOMINATOR, "denominator is out of range");

		// Validate results
		assertEquals(numerator / denominator, result, "The result of calculations is incorrect");
	}
	
	@RepeatedTest(20)
	void testGetPoint() {
		int points = operation.getPoints();
		assertTrue(points >= MINPOINTS && points <= MAXPOINTS, "points are out of range");
	}

	@Test
	void testGetOperationName() {
		assertEquals(operation.getOperationName(), "Division");
	}

}
