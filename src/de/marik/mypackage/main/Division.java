package de.marik.mypackage.main;

import java.util.concurrent.ThreadLocalRandom;

public class Division extends Operation {
	private static final int MIN = 1; // the lowest value of a quotient/denominator
	private static final int MAX = 10; // the highest value of a quotient/denominator
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
		denominator = ThreadLocalRandom.current().nextInt(MIN, MAX + 1);
		quotient = ThreadLocalRandom.current().nextInt(MIN, MAX + 1);
		numerator = denominator * quotient;
		taskDescription = numerator + " : " + denominator + " = ";
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
		return taskDescription;
	}

}
