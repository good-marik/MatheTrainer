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
	private Stopwatch localTimer;
	private Stopwatch globalTimer;
	private int gameCounter;

	private Controller(Viewer viewer) {
		this.viewer = viewer;
		addition = new Addition();
		multiplication = new Multiplication();
		division = new Division();
		substraction = new Substraction();

		globalTimer = new Stopwatch();

		// for debugging
		localTimer = new Stopwatch();

		actionListenerForField = new IMyActionListener() {
			@Override
			public void activate(int resultToCheck) {
				double duration = localTimer.stopAndGetSeconds();
				gameCounter++;
				
				System.out.printf("%5.2f", duration);
				System.out.print(" : ");
				System.out.printf("%13s", currentOperation.getTaskDescription() + result);
//				int effectivePoints = (int) (currentOperation.getPoints() / duration);
//				System.out.println(" :   " + currentOperation.getPoints() + " :   " + effectivePoints);
				
				System.out.print(" :   " + currentOperation.getPoints());
				System.out.print( " -> " + calculateTimeDependentScore(currentOperation.getPoints()));
				System.out.println(" ; time:  " + globalTimer.getSeconds());

				if (resultToCheck == result) {
					viewer.isCorrect(true, "");
					addScore();
				} else {
					String errorMessage = "Falsch :( weil " + taskString + result;
					viewer.isCorrect(false, errorMessage);
					substractScore();
				}
				playGame();
			}
		};

		actionListenerForButtons = new IMyActionListener() {
			@Override
			public void activate(int codeNumber) {
				// TODO: change numbers to ENUM
				// TODO: change switchPanel() to a better-named method(s)
				switch (codeNumber) {
				case 1:
					currentOperation = multiplication;
					viewer.switchPanel();
					globalTimer.start();
					localTimer.start();
					playGame();
					break;
				case 2:
					currentOperation = addition;
					viewer.switchPanel();
					globalTimer.start();
					localTimer.start();
					playGame();
					break;
				case 3:
					currentOperation = division;
					viewer.switchPanel();
					globalTimer.start();
					localTimer.start();
					playGame();
					break;
				case 4:
					currentOperation = substraction;
					viewer.switchPanel();
					globalTimer.start();
					localTimer.start();
					playGame();
					break;

				case 9: // HighScore button is pressed
//					System.out.println("high score button is pressed");
					viewer.switchToHighScorePanel();
					break;

				case 50: // EndOfGame button is pressed
					double gameTime = globalTimer.stopAndGetSeconds();
					int normallizedScore = recalculateScore(score, gameTime);
					viewer.checkForRecord(currentOperation, normallizedScore, isEnoughGames(gameCounter));
//					viewer.switchPanel();
					break;

				case 100: // Menu button is pressed
//					System.out.println("menu button is pressed");
					score = 0;
					gameCounter = 0;
					viewer.switchPanel();
					break;
				case 10: // Exit button is pressed
//					System.out.println("exit button is pressed");
					viewer.dispose();
					System.exit(0);
				}
			}
		};

		viewer.setMyActionListeners(actionListenerForField, actionListenerForButtons);
	}

	private boolean isEnoughGames(int counter) {
		return counter >= 10;
	}

	public static Controller getInstance(Viewer viewer) {
		if (controller == null) {
			controller = new Controller(viewer);
		}
		return controller;
	}
	
	public void playGame() {
		result = currentOperation.setTaskAndGetResult();
		taskString = currentOperation.getTaskDescription();
		viewer.setTask(taskString);
		localTimer.start();
	}
	
	private int calculateTimeDependentScore(int points) {
		final double k = 0.1;
		int cycle = (int) globalTimer.getSeconds() / 60;  //starts from 0, each cycle takes 60 seconds
		int recalibratedPoints = (int) ((k * cycle + 1) * points);	//so scaling factors: 1, 1.1, 1.2, 1.3,... for a cycle
		return recalibratedPoints;
	}
	
	private void addScore() {
		int delta = calculateTimeDependentScore(currentOperation.getPoints());
		score = score + delta;
//		System.out.println("+" + delta);
	}

	private void substractScore() {
		int delta = calculateTimeDependentScore(currentOperation.getPoints());
		int penalty = delta / 2; // penalty is 2 times lower, than win points
		score = score - penalty;
//		System.out.println("-" + penalty);
	}

	private int recalculateScore(int score, double gameTime) {
		// no negative scores!
		if (score < 0) {
			score = 0;
		}
		int normScore = (int) (score / gameTime * 100);
		System.out.println("--------------");
		System.out.println("calculating score here!");
		System.out.println("score: " + score);
		System.out.println(gameTime + " seconds");
		System.out.println("normallized score: " + normScore);
		System.out.println("--------------");
		return normScore;
	}

}
