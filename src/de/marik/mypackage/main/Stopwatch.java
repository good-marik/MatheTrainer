package de.marik.mypackage.main;

public class Stopwatch {
	private long startTime;
	private long endTime;
	private boolean isRunning; // is this stopwatch has been started?

	public void start() {
		startTime = System.nanoTime();
		isRunning = true;
	}

	public double stopAndGetSeconds() {
		if (isRunning) {
			endTime = System.nanoTime();
			isRunning = false;
			return (endTime - startTime) / 1e+9;
		}
		return 0.0;
	}

	public double getSeconds() {
		if (isRunning) {
			return (System.nanoTime() - startTime) / 1e+9;
		}
		return 0.0;
	}
}