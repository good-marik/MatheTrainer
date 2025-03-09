package de.marik.mypackage.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class AdditionTest {
	private Operation operation;
	private static final int MINNUMBER = 0;
	private static final int MAXNUMBER = 100;
	private static final int MINPOINTS = 15;
	private static final int MAXPOINTS = 25;

	@BeforeEach
	void setUp() {
		operation = new Addition();
	}

	@RepeatedTest(20)
	void testGetTaskDescription() {
		operation.setTaskAndGetResult();
		String taskDescription = operation.getTaskDescription();
		Pattern pattern = Pattern.compile("(\\d+) \\+ (\\d+) = ");
		Matcher matcher = pattern.matcher(taskDescription);
		// Validate task description
		assertTrue(matcher.matches(), "Task description format is incorrect");
	}

	@RepeatedTest(20)
	void testSetTaskAndGetResult() {
		int result = operation.setTaskAndGetResult();

		// Extract numbers from task description
		String[] task = operation.getTaskDescription().split(" ");
		int a = Integer.parseInt(task[0]);
		int b = Integer.parseInt(task[2]);

		// Validate number range
		assertTrue(a >= MINNUMBER && a <= MAXNUMBER, "'a' is out of range");
		assertTrue(b >= MINNUMBER && b <= MAXNUMBER, "'b' is out of range");

		// Validate results
		assertEquals(a + b, result, "The result of calculations is incorrect");
	}

	@RepeatedTest(20)
	void testGetPoint() {
		int points = operation.getPoints();
		assertTrue(points >= MINPOINTS && points <= MAXPOINTS, "points are out of range");
	}

	@Test
	void testGetOperationName() {
		assertEquals(operation.getOperationName(), "Addition");
	}

}
