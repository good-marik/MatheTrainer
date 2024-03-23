package de.marik.mypackage.main;

import de.marik.mypackage.viewer.Viewer;

public class Controller {
	private static final int MinimumNumberOfRounds = 10;
	private static Controller controller;
	private Viewer viewer;
	private Addition addition;
	private Multiplication multiplication;
	private Division division;
	private Substraction substraction;
	private Stopwatch globalTimer;
	private int gameScore;
	private int roundsCounter;
	private Operation currentOperation;
	private int currentAnswer;
	private Stopwatch localTimer; // for debugging

	private Controller(Viewer viewer) {
		this.viewer = viewer;
		addition = new Addition();
		multiplication = new Multiplication();
		division = new Division();
		substraction = new Substraction();
		globalTimer = new Stopwatch();
		localTimer = new Stopwatch(); // for debugging only
		viewer.setMyActionListeners(getActionListenerForMainField(), getActionListenerForButtons());
	}

	public static Controller getInstance(Viewer viewer) {
		if (controller == null) {
			controller = new Controller(viewer);
		}
		return controller;
	}

	private IActionListenerForMainField getActionListenerForMainField() {
		IActionListenerForMainField actionListener = new IActionListenerForMainField() {
			@Override
			public void activate(int resultToCheck) {
				roundsCounter++;

				// for debugging
				double duration = localTimer.stopAndGetSeconds();
				System.out.printf("round: %2d, ", roundsCounter);
				System.out.printf("time: %5.2f s, ", duration);
				System.out.printf("%13s ", currentOperation.getTaskDescription() + currentAnswer);
				System.out.printf(": %2d ", currentOperation.getPoints());
				System.out.printf("-> %2d : ", calculateTimeDependentScore(currentOperation.getPoints()));
				System.out.printf("total time: %6.2f%n", globalTimer.getSeconds());
//				int effectivePoints = (int) (currentOperation.getPoints() / duration);
//				System.out.println(" :   " + currentOperation.getPoints() + " :   " + effectivePoints);

				if (resultToCheck == currentAnswer) {
					viewer.isCorrect(true, "");
					addScore();
				} else {
					String commentingMessage = "Falsch :( weil " + currentOperation.getTaskDescription()
							+ currentAnswer;
					viewer.isCorrect(false, commentingMessage);
					substractScore();
				}
				startNewRound();
			}
		};
		return actionListener;
	}

	private IActionListenerForButtons getActionListenerForButtons() {
		IActionListenerForButtons actionListener = new IActionListenerForButtons() {
			@Override
			public void activate(Button button) {
				switch (button) {
				case MULTIPLICATION:
					currentOperation = multiplication;
					startGame();
					break;
				case ADDITION:
					currentOperation = addition;
					startGame();
					break;
				case DIVISION:
					currentOperation = division;
					startGame();
					break;
				case SUBSTRACTION:
					currentOperation = substraction;
					startGame();
					break;
				case HIGHSCORE:
					viewer.switchToHighScore();
					break;
				case ENDOFGAME:
					viewer.switchToHighScore();
					double gameTime = globalTimer.stopAndGetSeconds();
					int normallizedScore = normallizeScore(gameScore, gameTime);
					viewer.checkForRecord(currentOperation, normallizedScore, isEnoughGames(roundsCounter));
					break;
				case MENU:
					gameScore = 0;
					roundsCounter = 0;
					viewer.switchToMenu();
					break;
				case EXIT:
					viewer.dispose();
					System.exit(0);
				}
			}
		};
		return actionListener;
	}

	private boolean isEnoughGames(int counter) {
		return counter >= MinimumNumberOfRounds;
	}

	private void startGame() {
		gameScore = 0;
		roundsCounter = 0;
		globalTimer.start();
		viewer.switchToGame();
		startNewRound();
	}

	private void startNewRound() {
		currentAnswer = currentOperation.setTaskAndGetResult();
		viewer.setTask(currentOperation.getTaskDescription());
		localTimer.start(); // for debugging
	}

	private int calculateTimeDependentScore(int points) {
		final double k = 0.1;
		int cycle = (int) globalTimer.getSeconds() / 60; // starts from 0, each cycle takes 60 seconds
		int recalibratedPoints = (int) ((k * cycle + 1) * points); // so scaling factors are 1.0, 1.1, 1.2, 1.3, for
																	// corresponding cycles...
		return recalibratedPoints;
	}

	private void addScore() {
		int delta = calculateTimeDependentScore(currentOperation.getPoints());
		gameScore = gameScore + delta;
//		System.out.println("+" + delta);
	}

	private void substractScore() {
		int delta = calculateTimeDependentScore(currentOperation.getPoints());
		int penalty = delta / 2; // penalty is 2 times lower, than the corresponding win points
		gameScore = gameScore - penalty;
//		System.out.println("-" + penalty);
	}

	private int normallizeScore(int score, double time) {
		// no negative scores!
		if (score < 0) {
			score = 0;
		}
		int normScore = (int) (score / time * 100);
		System.out.println("--------------");
		System.out.println("score: " + score);
		System.out.println(time + " seconds");
		System.out.println("normallized score: " + normScore);
		System.out.println("--------------");
		return normScore;
	}

}
