package de.marik.mypackage.main;

import java.util.concurrent.ThreadLocalRandom;

public class Substraction extends Operation {
	private final int min = 0; // the lowest value of a term (minuend or substrahend)
	private final int max = 100; // the highest value of a term (minuend or substrahend)
	private int minuend;
	private int substrahend;
	// the task here is
	// minuend - substrahend = difference
	private String taskDescription;

	public Substraction() {
		super("Substraktion");
	}

	@Override
	public int setTaskAndGetResult() {
		minuend = ThreadLocalRandom.current().nextInt(min, max + 1);
		substrahend = ThreadLocalRandom.current().nextInt(min, minuend + 1);
		return minuend - substrahend;
	}

	@Override
	public int getPoints() {
		if (minuend <= 10 || minuend == substrahend) {
			return 10;
		}
		if (substrahend <= 10) {
			return 20;
		}
		if (minuend % 10 < substrahend % 10) {
			return 40;
		}
		// default
		return 30;
	}

	@Override
	public String getTaskDescription() {
		taskDescription = minuend + " – " + substrahend + " = ";
		return taskDescription;
	}

}
