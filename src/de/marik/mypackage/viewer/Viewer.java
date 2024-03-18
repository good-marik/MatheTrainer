package de.marik.mypackage.viewer;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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

import de.marik.mypackage.main.ICommentsDatabase;
import de.marik.mypackage.main.IMyActionListener;
import de.marik.mypackage.main.Operation;
import de.marik.mypackage.main.PrimitiveCommentsDatabase;

public class Viewer extends JFrame {
	private static final long serialVersionUID = 4225066973345513036L;
	private final static String version = "0.7";
	private final static String programTitle = "MatheTrainer für Alexandra v" + version;
	private final static String startingMessage = "Let's go!";
	private static Viewer viewer;
	private JLabel comment;
	private JLabel taskText;
	private JButton exitButton;
	private JButton doneButton;
	private JTextField answerField;
	private IMyActionListener actionListenerForField;
	private IMyActionListener actionListenerForButtons;
	private ICommentsDatabase commentsDatabase;
	private ArrayList<String> positiveFeedbackList;
	private CardLayout cardLayout;
	private JPanel multiPanel;
	private MenuPanel menuPanel;
	private HighScorePanel highScorePanel;

	private Viewer() {
		super(programTitle);
		commentsDatabase = new PrimitiveCommentsDatabase();
		loadCommentsFromDatabase();
		cardLayout = new CardLayout();
		multiPanel = new JPanel(cardLayout);
		menuPanel = new MenuPanel();
		multiPanel.add(menuPanel);
		multiPanel.add(getGamePanel());
		highScorePanel = new HighScorePanel();
		multiPanel.add(highScorePanel);
		
		add(multiPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = new Dimension(500, 350);
		setMinimumSize(dim);
//		setSize(dim);
//		setPreferredSize(dim);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private JPanel getGamePanel() {
		JPanel gamePanel = new JPanel(new GridLayout(3, 1));
		comment = new JLabel();
		comment.setFont(new Font("arial", Font.BOLD, 28));
		comment.setHorizontalAlignment(JLabel.CENTER);
		setDefaultComment();
		gamePanel.add(comment);

		Font mainFont = new Font("arial", Font.PLAIN, 28);
		taskText = new JLabel();
//		taskText.setHorizontalAlignment(JLabel.CENTER);
		taskText.setFont(mainFont);
		answerField = new JTextField(3);
		answerField.setFont(mainFont);
		JLabel spacer = new JLabel(" ");
		spacer.setFont(mainFont);
		
		doneButton = new JButton("eingabe");
		doneButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				System.out.println("ENTER pressed");
				int answerInt = 0;
				try {
					answerInt = Integer.parseInt(answerField.getText());
				} catch (Exception e2) {
					answerField.setText("");
					comment.setText("");
					return;
				}
				actionListenerForField.activate(answerInt);
			}
		});
		getRootPane().setDefaultButton(doneButton);
		
		JPanel middlePanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 5, 0, 5);  // spaces (inserts) from left and right only
		middlePanel.add(taskText, gbc);
		middlePanel.add(answerField, gbc);
		middlePanel.add(spacer, gbc);
		middlePanel.add(doneButton, gbc);
		gamePanel.add(middlePanel);

		exitButton = new JButton("beenden");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				actionListenerForButtons.activate(50);

//				toCongratulate();
//				switchPanel();
			}
		});
//		exitButton.setMaximumSize(new Dimension(55, 100));
//		exitButton.setPreferredSize(new Dimension(55, 100));
		JPanel bottomPanel = new JPanel(new GridBagLayout());
		bottomPanel.add(exitButton);
		gamePanel.add(bottomPanel);
		
		return gamePanel;
	}

	private void setDefaultComment() {
		comment.setForeground(Color.BLUE);
		comment.setText(startingMessage);
	}
	
	private void loadCommentsFromDatabase() {
		positiveFeedbackList = commentsDatabase.getPositiveFeedback();
	}
	
	private void toCongratulate() {
//		JOptionPane.showMessageDialog(null, "Ein neuer Rekord!", "Gratulation!", JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(null, new JLabel("Ein neuer Rekord!", JLabel.CENTER), "Gratulation!", JOptionPane.PLAIN_MESSAGE);
	}
	
	private void toShowScore(int score) {
		String text = "Deine Punkten: " + score;
//		JOptionPane.showMessageDialog(null, text, "", JOptionPane.PLAIN_MESSAGE);
		JOptionPane.showMessageDialog(null, new JLabel(text, JLabel.CENTER), "", JOptionPane.PLAIN_MESSAGE);
	}

	public void setTask(String taskString) {
		taskText.setText(taskString);
		answerField.setText("");
		answerField.requestFocus();
	}

	public static Viewer getInstance() {
		if (viewer == null) {
			viewer = new Viewer();
		}
		return viewer;
	}

	public void setMyActionListeners(IMyActionListener actionListenerForField, IMyActionListener actionListenerForButtons) {
		this.actionListenerForField = actionListenerForField;
		this.actionListenerForButtons = actionListenerForButtons;
		menuPanel.setMyActionListener(actionListenerForButtons);
		highScorePanel.setMyActionListener(actionListenerForButtons);
	}

	public void isCorrect(boolean correctAnswer, String errorMessage) {
		if (correctAnswer) {
			comment.setForeground(new Color(0, 128, 0));
			int randomCommentNumber = ThreadLocalRandom.current().nextInt(0, positiveFeedbackList.size());
			comment.setText(positiveFeedbackList.get(randomCommentNumber));
		} else {
			comment.setForeground(new Color(204, 0, 0));
			comment.setText(errorMessage);
		}
		answerField.setText("");
		answerField.requestFocus();
	}

	public void switchPanel() {
		setDefaultComment();
		cardLayout.next(multiPanel);
	}
	
	public void switchToHighScorePanel() {
		cardLayout.last(multiPanel);
	}

	public void checkForRecord(Operation operation, int score) {
		switchPanel();
		if (highScorePanel.isANewRecord(operation, score)) {
			toCongratulate();
			highScorePanel.setNewRecord(score);
		} else {
			toShowScore(score);
		}
		
	}
	
}
