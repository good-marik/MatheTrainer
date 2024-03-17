package de.marik.mypackage.main;

import java.util.concurrent.ThreadLocalRandom;

public class Division extends Operation {
	private int min = 1;
	private int max = 10;
	private int randomA;
	private int randomB;
	private int multiplication;
	private String taskString;
	
	public Division() {
		super("Division");
	}

	@Override
	public int setTask() {
		randomA = ThreadLocalRandom.current().nextInt(min, max + 1);
		randomB = ThreadLocalRandom.current().nextInt(min, max + 1);
		multiplication = randomA * randomB;
		return randomB;
	}

	@Override
	public int getPoints() {
		return 1;
	}

	@Override
	public String getTaskString() {
		taskString = multiplication + " : " + randomA + " = ";
		return taskString;
	}

}
