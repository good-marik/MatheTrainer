package de.marik.mypackage;

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

public class Viewer extends JFrame {
	private static final long serialVersionUID = 4225066973345513036L;
	private final static String version = "0.5";
	private final static String programTitle = "MatheTrainer für Alexandra v" + version;
	private final static String startingMessage = "Let's go!";
	private static Viewer viewer;
	private JLabel comment;
	private JLabel taskText;
	private JButton exitButton;
	private JButton doneButton;
	private JTextField answerField;
	private IMyActionListener actionListenerGame;
	private ICommentsDatabase commentsDatabase;
	private ArrayList<String> positiveFeedbackList;
	private CardLayout cardLayout;
	private JPanel multiPanel;
	private MenuPanel menuPanel;

	private Viewer() {
		super(programTitle);
		commentsDatabase = new PrimitiveCommentsDatabase();
		loadCommentsFromDatabase();
		cardLayout = new CardLayout();
		multiPanel = new JPanel(cardLayout);
		menuPanel = new MenuPanel();
		multiPanel.add(menuPanel);
		multiPanel.add(getGamePanel());
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
				System.out.println("ENTER pressed");
				int answerInt = 0;
				try {
					answerInt = Integer.parseInt(answerField.getText());
				} catch (Exception e2) {
					answerField.setText("");
					comment.setText("");
					return;
				}
				actionListenerGame.activated(answerInt);
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
				// TODO: save the score etc
				toCongratulate();
				System.out.println("back to menu");
				switchPanels();
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
//		JOptionPane.showMessageDialog(null, new JLabel("Gratulation!", JLabel.LEFT), "Ein neuer Rekord!", JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(null, "Gratulation!", "Ein neuer Rekord!", JOptionPane.INFORMATION_MESSAGE);
	}

	public void setTask(String taskString) {
		taskText.setText(taskString);
		answerField.requestFocus();
	}

	public static Viewer getInstance() {
		if (viewer == null) {
			viewer = new Viewer();
		}
		return viewer;
	}

	public void addMyActionListeners(IMyActionListener actionListenerGame, IMyActionListener actionListenerMenu) {
		this.actionListenerGame = actionListenerGame;
		menuPanel.setMyActionListener(actionListenerMenu);
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

	public void switchPanels() {
		setDefaultComment();
		cardLayout.next(multiPanel);
	}
}
