package de.marik.mypackage.main;

import java.util.concurrent.ThreadLocalRandom;

public class Addition extends Operation {
	private final int min = 0; // the lowest value of a summand
	private final int max = 100; // the highest value of a summand
	private int randomA; // summand A
	private int randomB; // summand B
	private String taskDescription;

	public Addition() {
		super("Addition");
	}

	@Override
	public int setTaskAndGetResult() {
		randomA = ThreadLocalRandom.current().nextInt(min, max + 1);
		randomB = ThreadLocalRandom.current().nextInt(min, max - randomA + 1);
		return randomA + randomB;
	}

	@Override
	public int getPoints() {
		if ((randomA <= 10 || randomB <= 10) && ((randomA % 10 + randomB % 10) <= 10)) {
			return 15;
		}
		if ((randomA % 10 + randomB % 10) > 10) {
			return 25;
		}
		// default
		return 22;
	}

	@Override
	public String getTaskDescription() {
		taskDescription = randomA + " + " + randomB + " = ";
		return taskDescription;
	}

}
