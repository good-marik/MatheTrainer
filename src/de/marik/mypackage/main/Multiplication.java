package de.marik.mypackage.main;

import java.util.concurrent.ThreadLocalRandom;

public class Multiplication extends Operation {
	private int min = 0;
	private int max = 10;
	private int randomA;
	private int randomB;
	private String taskString;

	public Multiplication() {
		super("Multiplikation");
	}

	@Override
	public int setTask() {
		randomA = ThreadLocalRandom.current().nextInt(min, max + 1);
		randomB = ThreadLocalRandom.current().nextInt(min, max + 1);
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
	public String getTaskString() {
		taskString = randomA + " * " + randomB + " = ";
		return taskString;
	}

}
