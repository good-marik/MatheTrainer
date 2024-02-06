package de.marik.mypackage;

public class Controller implements IMyActionListener {
	private static Controller controller;
	private Viewer viewer;
	private Operation currentOperation;
	private int result;
	private String taskString;

	private Controller(Viewer viewer) {
		this.viewer = viewer;
		currentOperation = new Addition();
		viewer.addMyActionListener(this);
	}

	public static Controller getInstance(Viewer viewer) {
		if (controller == null) {
			controller = new Controller(viewer);
		}
		return controller;
	}

	public void start() {
		result = currentOperation.setTask();
		taskString = currentOperation.getTaskString();
		viewer.setTask(taskString);
	}

	@Override
	public void activated(int resultToCheck) {
		if (resultToCheck == result) {
			viewer.isCorrect(true, "");
		} else {
			String errorMessage = "Falsch, weil " + taskString + result;
			viewer.isCorrect(false, errorMessage);
		}
		start();
	}
}
