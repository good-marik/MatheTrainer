package de.marik.mypackage.main;

import java.util.concurrent.ThreadLocalRandom;

public class Substraction extends Operation {
	private int min = 0;
	private int max = 100;
	private int randomA;
	private int randomB;
	private String taskString;
	
	public Substraction() {
		super("Substraktion");
	}

	@Override
	public int setTask() {
		randomA = ThreadLocalRandom.current().nextInt(min, max + 1);
		randomB = ThreadLocalRandom.current().nextInt(min, randomA + 1);
		return randomA - randomB;
	}

	@Override
	public int getPoints() {
		if (randomA <= 10 || randomA == randomB) {
			return 10;
		}
		if (randomB <= 10) {
			return 20;
		}
		if (randomA % 10 < randomB % 10) {
			return 40;
		}
		// default
		return 30;
	}

	@Override
	public String getTaskString() {
		taskString = randomA + " – " + randomB + " = ";
		return taskString;
	}

}
