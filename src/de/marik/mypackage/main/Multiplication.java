package de.marik.mypackage.main;

import java.util.concurrent.ThreadLocalRandom;

public class Multiplication extends Operation {
	private static final int MIN = 0; // the lowest value of a factor
	private static final int MAX = 10; // the highest value of a factor

	private int randomA; // factor A
	private int randomB; // factor B
	private String taskDescription;

	public Multiplication() {
		super("Multiplikation");
	}

	@Override
	public int setTaskAndGetResult() {
		randomA = ThreadLocalRandom.current().nextInt(MIN, MAX + 1);
		randomB = ThreadLocalRandom.current().nextInt(MIN, MAX + 1);
		taskDescription = randomA + " • " + randomB + " = ";
		return randomA * randomB;
	}

	@Override
	public int getPoints() {
		if (randomA * randomB == 0 || randomA == 1 || randomB == 1) {
			return 10;
		}
		if (randomA == 9 || randomB == 9 || randomA == 7 || randomB == 7) {
			return 20;
		}
		// default
		return 15;
	}

	@Override
	public String getTaskDescription() {
		return taskDescription;
	}

}