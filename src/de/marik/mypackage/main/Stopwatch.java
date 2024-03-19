package de.marik.mypackage.main;

public class Stopwatch {
	private long startTime;
	private long endTime;
	private boolean isRunning;

	public void start() {
		startTime = System.nanoTime();
		isRunning = true;
	}

	public double stopAndGetSeconds() {
		if (isRunning) {
			endTime = System.nanoTime();
			isRunning = false;
			return (endTime - startTime) / 1e+9;
		} else {
			return 0;
		}
	}
	
	public double getSeconds() {
		if(isRunning) {
			return (System.nanoTime() - startTime) / 1e+9;
		} else {
			return 0;
		}
	}
}