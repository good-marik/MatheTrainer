package de.marik.mypackage.main;

import java.util.concurrent.ThreadLocalRandom;

public class Division extends Operation {
	private final int min = 1; // the lowest value of a quotient/denominator
	private final int max = 10; // the highest value of a quotient/denominator
	// the task here is
	// numerator : denominator = quotient
	private int denominator;
	private int quotient;
	private int numerator;
	private String taskDescription;

	public Division() {
		super("Division");
	}

	@Override
	public int setTaskAndGetResult() {
		denominator = ThreadLocalRandom.current().nextInt(min, max + 1);
		quotient = ThreadLocalRandom.current().nextInt(min, max + 1);
		numerator = denominator * quotient;
		return quotient;
	}

	@Override
	public int getPoints() {
		if (denominator == 1 || quotient == 1 || denominator == 5) {
			return 10;
		}
		if ((denominator == 7 || denominator == 9) && quotient != 2 && quotient != 5) {
			return 25;
		}
		// default
		return 13;
	}

	@Override
	public String getTaskDescription() {
		taskDescription = numerator + " : " + denominator + " = ";
		return taskDescription;
	}

}
