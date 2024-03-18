package de.marik.mypackage.main;

import java.util.concurrent.ThreadLocalRandom;

public class Addition extends Operation {
	private int min = 0;
	private int max = 100;
	private int randomA;
	private int randomB;
	private String taskString;
	
	public Addition() {
		super("Addition");
	}

	@Override
	public int setTask() {
		randomA = ThreadLocalRandom.current().nextInt(min, max + 1);
		randomB = ThreadLocalRandom.current().nextInt(min, max - randomA + 1);
		return randomA + randomB;
	}

	@Override
	public int getPoints() {
		if ( (randomA <= 10 || randomB <= 10) && ((randomA % 10 + randomB & 10) <= 10 ) ) {
			return 15;
		}
		if ((randomA % 10 + randomB % 10) > 10) {
			return 25;
		}
		// default
		return 22;
	}

	@Override
	public String getTaskString() {
		taskString = randomA + " + " + randomB + " = ";
		return taskString;
	}

}
