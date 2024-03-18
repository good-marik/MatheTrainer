package de.marik.mypackage.main;

import java.util.concurrent.ThreadLocalRandom;

public class Division extends Operation {
	private int min = 1;
	private int max = 10;
	private int randomA;
	private int randomB;
	private int multiplied;
	private String taskString;
	
	public Division() {
		super("Division");
	}

	// task: multiplied / randomA = randomB
	@Override
	public int setTask() {
		randomA = ThreadLocalRandom.current().nextInt(min, max + 1);
		randomB = ThreadLocalRandom.current().nextInt(min, max + 1);
		multiplied = randomA * randomB;
		return randomB;
	}

	@Override
	public int getPoints() {
		if (randomA == 1 || randomB == 1 || randomA == 5 ) {
			return 10;
		}
		if ((randomA == 7 || randomA == 9) && randomB !=2 && randomB !=5) {
			return 25;
		}
		// default
		return 13;
	}

	@Override
	public String getTaskString() {
		taskString = multiplied + " : " + randomA + " = ";
		return taskString;
	}

}
