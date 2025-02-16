package de.marik.mypackage.viewer;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import de.marik.mypackage.main.Button;
import de.marik.mypackage.main.IActionListenerForButtons;
import de.marik.mypackage.main.IActionListenerForMainField;
import de.marik.mypackage.main.Operation;

public class Viewer extends JFrame {
	private static final long serialVersionUID = -5557289890702169461L;
	private static final String VERSION = "1.2";
	private static final String PROGRAMTITLE = "MatheTrainer für Alexandra v" + VERSION;
	private final String menuPanelString = "Menu";
	private final String gamePanelString = "Game";
	private final String highScorePanelString = "HighScore";
	private final String startingMessage = "Let's go!";
	private final ArrayList<String> positiveFeedbackList;
	private final CardLayout cardLayout;
	private final JPanel multiPanel;
	private final MenuPanel menuPanel;
	private final HighScorePanel highScorePanel;

	private static Viewer viewer;
	private IActionListenerForMainField actionListenerForField;
	private IActionListenerForButtons actionListenerForButtons;
	private int lastCommentNumber;
	private JLabel comment;
	private JLabel taskText;
	private JTextField answerField;

	private Viewer() {
		super(PROGRAMTITLE);
		positiveFeedbackList = new CommentsPrimitiveDatabase().getList();

		menuPanel = new MenuPanel();
		highScorePanel = new HighScorePanel();
		cardLayout = new CardLayout();
		multiPanel = new JPanel(cardLayout);
		multiPanel.add(menuPanel, menuPanelString);
		multiPanel.add(getGamePanel(), gamePanelString);
		multiPanel.add(highScorePanel, highScorePanelString);
		this.add(multiPanel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = new Dimension(500, 350);
		setMinimumSize(dim);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static Viewer getInstance() {
		if (viewer == null) {
			viewer = new Viewer();
		}
		return viewer;
	}

	public void setMyActionListeners(IActionListenerForMainField actionListenerForField,
			IActionListenerForButtons actionListenerForButtons) {
		this.actionListenerForField = actionListenerForField;
		this.actionListenerForButtons = actionListenerForButtons;
		menuPanel.setMyActionListener(actionListenerForButtons);
		highScorePanel.setMyActionListener(actionListenerForButtons);
	}

	public void setTask(String taskString) {
		taskText.setText(taskString);
		answerField.setText("");
		answerField.requestFocus();
	}

	public void isCorrect(boolean isCorrectAnswer, String errorMessage) {
		if (isCorrectAnswer) {
			comment.setForeground(new Color(0, 128, 0));
			int randomCommentNumber;
			do {
				randomCommentNumber = ThreadLocalRandom.current().nextInt(0, positiveFeedbackList.size());
			} while (randomCommentNumber == lastCommentNumber);
			comment.setText(positiveFeedbackList.get(randomCommentNumber));
		} else {
			comment.setForeground(new Color(200, 0, 0));
			comment.setText(errorMessage);
		}
		answerField.setText("");
		answerField.requestFocus();
	}

	public void checkForRecord(Operation operation, int score, boolean isEnoughGames) {
		if (!isEnoughGames) {
			showMessage("Keine Punkte wurde gerechnet", "Versuch mal länger zu spielen :)");
			return;
		}
		if (highScorePanel.isANewRecord(operation, score)) {
			showMessage("Gratulation!", "Ein neuer Rekord!");
			highScorePanel.setNewRecord(score);
		} else {
			showMessage("Nicht schlecht, aber ...", "Deine Punkte: " + score);
		}
	}

	public void switchToGame() {
		setDefaultComment();
		cardLayout.show(multiPanel, gamePanelString);
	}

	public void switchToMenu() {
		cardLayout.show(multiPanel, menuPanelString);
	}

	public void switchToHighScore() {
		cardLayout.show(multiPanel, highScorePanelString);
	}

	private JPanel getGamePanel() {
		JPanel gamePanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.insets = new Insets(20, 0, 20, 0);

		comment = new JLabel();
		comment.setFont(new Font("arial", Font.BOLD, 28));
		setDefaultComment();
		gamePanel.add(comment, gbc);

		Font mainFont = new Font("arial", Font.PLAIN, 28);
		taskText = new JLabel();
		taskText.setFont(mainFont);
		answerField = new JTextField(3);
		answerField.setFont(mainFont);
		JLabel spacer = new JLabel(" ");
		spacer.setFont(mainFont);
		JButton doneButton = new JButton("eingabe");
		doneButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int answerInt = 0;
				try {
					answerInt = Integer.parseInt(answerField.getText());
				} catch (Exception ex) {
					// if not a proper integer
					answerField.setText(""); // deleting input line
					comment.setText(" "); // hiding comments
					return;
				}
				actionListenerForField.activate(answerInt);
			}
		});
		getRootPane().setDefaultButton(doneButton);
		JPanel middlePanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.insets = new Insets(0, 5, 0, 5); // spaces (margins, padding) from left and right only
		middlePanel.add(taskText, gbc2);
		middlePanel.add(answerField, gbc2);
		middlePanel.add(spacer, gbc2);
		middlePanel.add(doneButton, gbc2);
		gamePanel.add(middlePanel, gbc);

		JButton exitButton = new JButton("beenden");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// debugging
				actionListenerForButtons.activate(Button.ENDOFGAME);
			}
		});
		gamePanel.add(exitButton, gbc);

		return gamePanel;
	}

	private void setDefaultComment() {
//		System.out.println(this.getWidth() + " x " + this.getHeight());
		comment.setForeground(Color.BLUE);
		comment.setText(startingMessage);
	}

	private void showMessage(String title, String message) {
		JLabel label = new JLabel(message, SwingConstants.CENTER);
		JOptionPane.showMessageDialog(this, label, title, JOptionPane.PLAIN_MESSAGE);
	}

}
