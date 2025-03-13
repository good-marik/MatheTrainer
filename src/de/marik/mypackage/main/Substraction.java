package de.marik.mypackage.main;

import java.util.concurrent.ThreadLocalRandom;

public class Substraction extends Operation {
	private static final int MIN = 0; // the lowest value of a term (minuend or substrahend)
	private static final int MAX = 100; // the highest value of a term (minuend or substrahend)
	// the task here: minuend - substrahend = difference
	private int minuend;
	private int substrahend;
	private String taskDescription;

	public Substraction() {
		super("Substraktion");
	}

	@Override
	public int setTaskAndGetResult() {
		minuend = ThreadLocalRandom.current().nextInt(MIN, MAX + 1);
		substrahend = ThreadLocalRandom.current().nextInt(MIN, minuend + 1);
		taskDescription = minuend + " – " + substrahend + " = ";
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
		return taskDescription;
	}

}
