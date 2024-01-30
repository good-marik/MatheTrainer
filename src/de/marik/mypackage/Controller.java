package de.marik.mypackage;

import java.util.concurrent.ThreadLocalRandom;

public class Controller implements IMyActionListener {
	private static Controller controller;
	private Viewer viewer;
	private Operation multiplication;
	private int min = 0;
	private int max = 10;
	private int randomA;
	private int randomB;
	private int result;

	private Controller(Viewer viewer) {
		this.viewer = viewer;
		multiplication = new Multiplication();
		viewer.addMyActionListener(this);
	}

	public static Controller getInstance(Viewer viewer) {
		if (controller == null) {
			controller = new Controller(viewer);
		}
		return controller;
	}

	public void start() {
			randomA = ThreadLocalRandom.current().nextInt(min, max + 1);
			randomB = ThreadLocalRandom.current().nextInt(min, max + 1);
			result = multiplication.getResult(randomA, randomB);
//			System.out.println(randomA + " * " + randomB + " = " + result);
			viewer.setTask(randomA + " * " + randomB + " = ");
	}

	@Override
	public void activated(int resultToCheck) {
		if (resultToCheck == result) {
			viewer.isCorrect(true, "");
//			viewer.isCorrect(true);
//			viewer.setComment("Richtig!");
		} else {
			String errorMessage = "Falsch, weil " + randomA + " * " + randomB + " = " + result;
			viewer.isCorrect(false, errorMessage);
//			viewer.isCorrect(false);
//			viewer.setComment("Falsch!");
		}
		start();
	}
}
