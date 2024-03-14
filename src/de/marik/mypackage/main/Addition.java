package de.marik.mypackage.main;

import java.util.concurrent.ThreadLocalRandom;

public class Addition extends Operation {
	private int min = 0;
	private int max = 100;
	private int randomA;
	private int randomB;
	private String taskString;

	@Override
	public int setTask() {
		randomA = ThreadLocalRandom.current().nextInt(min, max + 1);
		randomB = ThreadLocalRandom.current().nextInt(min, max - randomA + 1);
		return randomA + randomB;
	}

	@Override
	public int getPoints() {
		return 1;
	}

	@Override
	public String getTaskString() {
		taskString = randomA + " + " + randomB + " = ";
		return taskString;
	}

}
