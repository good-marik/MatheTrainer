package de.marik.mypackage.main;

import de.marik.mypackage.viewer.Viewer;

public class Controller {
	private static Controller controller;
	private Viewer viewer;
	private Operation currentOperation;
	private int result;
	private String taskString;
	private IMyActionListener actionListenerForField;
	private IMyActionListener actionListenerForButtons;
	private Addition addition;
	private Multiplication multiplication;
	private Division division;
	private Substraction substraction;
	private int score;
	private long startingTime;

	private Controller(Viewer viewer) {
		this.viewer = viewer;
		addition = new Addition();
		multiplication = new Multiplication();
		division = new Division();
		substraction = new Substraction();

		actionListenerForField = new IMyActionListener() {
			@Override
			public void activate(int resultToCheck) {
				if (resultToCheck == result) {
					viewer.isCorrect(true, "");
					addScore();
				} else {
					String errorMessage = "Falsch :( weil " + taskString + result;
					viewer.isCorrect(false, errorMessage);
				}
				playGame();
			}
		};

		actionListenerForButtons = new IMyActionListener() {
			@Override
			public void activate(int codeNumber) {
				//TODO: change numbers to string in CASE, change switchPanel() to better-named methods
				switch (codeNumber) {
				case 1:
					currentOperation = multiplication;
					viewer.switchPanel();
					startTimer();
					playGame();
					break;
				case 2:
					currentOperation = addition;
					viewer.switchPanel();
					startTimer();
					playGame();
					break;
				case 3:
					currentOperation = division;
					viewer.switchPanel();
					startTimer();
					playGame();
					break;
				case 4:
					currentOperation = substraction;
					viewer.switchPanel();
					startTimer();
					playGame();
					break;

				case 9:		//HighScore button is pressed
//					System.out.println("high score button is pressed");
					viewer.switchToHighScorePanel();
					break;

				case 50:	//EndOfGame button is pressed 
					double gameTime = stopTimerAndGetSeconds();
					System.out.println("calculating scores here!");
					int normallizedScore = recalculateScore(score, gameTime);
					viewer.checkForRecord(currentOperation, normallizedScore);

					//TODO
//					viewer.switchPanel();
					
					break;
				
				case 100:	//Menu button is pressed
//					System.out.println("menu button is pressed");
					score = 0;
					viewer.switchPanel();
					break;
				case 10:	//Exit button is pressed
//					System.out.println("exit button is pressed");
					viewer.dispose();
					System.exit(0);
				}

			}
		};

		viewer.setMyActionListeners(actionListenerForField, actionListenerForButtons);
	}

	private double stopTimerAndGetSeconds() {
		long endTime = System.nanoTime();
		return (endTime - startingTime) / 1e+9;
	}

	private void startTimer() {
		startingTime = System.nanoTime();
	}

	private int recalculateScore(int score, double gameTime) {
		int normScore = 0;
		normScore = (int) (score / gameTime * 100); 
		System.out.println("--------------");
		System.out.println("score: " + score);
		System.out.println(gameTime + " seconds");
		System.out.println("normallized score: " + normScore);
		System.out.println("--------------");
		return normScore;
	}

	private void addScore() {
		// to implement properly!
		score = score + 10;
		System.out.println("Score: " + score);

	}

	public static Controller getInstance(Viewer viewer) {
		if (controller == null) {
			controller = new Controller(viewer);
		}
		return controller;
	}

	public void playGame() {
		result = currentOperation.setTask();
		taskString = currentOperation.getTaskString();
		viewer.setTask(taskString);
	}
}
