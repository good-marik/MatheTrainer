package de.marik.mypackage.main;

import java.util.concurrent.ThreadLocalRandom;

public class Addition extends Operation {
	private static final int MIN = 0; // the lowest value of a summand
	private static final int MAX = 100; // the highest value of a summand

	private int randomA; // summand A
	private int randomB; // summand B
	private String taskDescription;

	public Addition() {
		super("Addition");
	}

	@Override
	public int setTaskAndGetResult() {
		randomA = ThreadLocalRandom.current().nextInt(MIN, MAX + 1);
		randomB = ThreadLocalRandom.current().nextInt(MIN, MAX - randomA + 1);
		taskDescription = randomA + " + " + randomB + " = ";
		return randomA + randomB;
	}

	@Override
	public int getPoints() {
		if ((randomA <= 10 || randomB <= 10) && ((randomA % 10 + randomB % 10) <= 10)) {
			return 15; // easy task
		}
		if ((randomA % 10 + randomB % 10) > 10) {
			return 25; // difficult task
		}
		return 22; // normal task
	}

	@Override
	public String getTaskDescription() {
		return taskDescription;
	}

}
