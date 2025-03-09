package de.marik.mypackage.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StopwatchTest {
	private Stopwatch stopwatch;

	@BeforeEach
	void setUp() {
		stopwatch = new Stopwatch();
	}

	@Test
	void testStopwatchMeasuresTimeCorrectly() throws InterruptedException {
		stopwatch.start();
		Thread.sleep(100); // Sleep for 100 milliseconds
		double elapsedTime = stopwatch.stopAndGetSeconds();

		assertTrue(elapsedTime >= 0.1 && elapsedTime < 0.2, "Elapsed time should be approximately 0.1 seconds");
	}

	@Test
	void testGetSecondsWhileRunning() throws InterruptedException {
		stopwatch.start();
		Thread.sleep(50); // Sleep for 50 milliseconds
		double elapsedTime = stopwatch.getSeconds();

		assertTrue(elapsedTime >= 0.05 && elapsedTime < 0.15,
				"Elapsed time should be at least 0.05 seconds but less than 0.15");
	}

	@Test
	void testStopAndGetSecondsWithoutStart() {
		double elapsedTime = stopwatch.stopAndGetSeconds();
		assertEquals(0, elapsedTime, "Elapsed time should be 0 if stopwatch was never started");
	}
}