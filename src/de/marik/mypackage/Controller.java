package de.marik.mypackage;

public class Controller {
	private static Controller controller;
	private Viewer viewer;
	private Operation currentOperation;
	private int result;
	private String taskString;
	private IMyActionListener actionListenerGame;
	private IMyActionListener actionListenerMenu;
	private Addition addition;
	private Multiplication multiplication;
	private Division division;
	private Substraction substraction;

	private Controller(Viewer viewer) {
		this.viewer = viewer;
		addition = new Addition();
		multiplication = new Multiplication();
		division = new Division();
		substraction = new Substraction();

		actionListenerGame = new IMyActionListener() {
			@Override
			public void activated(int resultToCheck) {
				if (resultToCheck == result) {
					viewer.isCorrect(true, "");
				} else {
					String errorMessage = "Falsch :( weil " + taskString + result;
					viewer.isCorrect(false, errorMessage);
				}
				startGame();
			}
		};

		actionListenerMenu = new IMyActionListener() {
			@Override
			public void activated(int codeNumber) {
//				System.out.println("menu number: " + codeNumber);
				switch (codeNumber) {
				case 1:
					currentOperation = multiplication;
					viewer.switchPanels();
					startGame();
					break;
				case 2:
					currentOperation = addition;
					viewer.switchPanels();
					startGame();
					break;
				case 3:
					currentOperation = division;
					viewer.switchPanels();
					startGame();
					break;
				case 4:
					currentOperation = substraction;
					viewer.switchPanels();
					startGame();
					break;

				case 10:
//					System.out.println("Exiting the program!");
					viewer.dispose();
					System.exit(0);
				}

			}
		};

		viewer.addMyActionListeners(actionListenerGame, actionListenerMenu);
	}

	public static Controller getInstance(Viewer viewer) {
		if (controller == null) {
			controller = new Controller(viewer);
		}
		return controller;
	}

	public void startGame() {
		result = currentOperation.setTask();
		taskString = currentOperation.getTaskString();
		viewer.setTask(taskString);
	}
}
