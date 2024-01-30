package de.marik.mypackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Viewer extends JFrame {
	private static final long serialVersionUID = 1L;
	private static Viewer viewer;
	private final static String version = "0.1";
	private JLabel comment;
	private JLabel taskText;
	private JButton exitButton;
	private JButton doneButton;
	private JTextField answerField;
	private IMyActionListener actionListenerForController;
	private ICommentsDatabase commentsDatabase;
	private ArrayList<String> positiveFeedbackList;

	private Viewer() {
		super("MatheTrainer für Alexandra v" + version);
		commentsDatabase = new PrimitiveCommentsDatabase();
		loadCommentsFromDatabase();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.RED);
		Dimension dim = new Dimension(500, 300);
		setMinimumSize(dim);
//		setSize(dim);
//		setPreferredSize(dim);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(3, 1));
//		mainPanel.setBackground(Color.YELLOW);

		comment = new JLabel("Let's go!");
		comment.setHorizontalAlignment(JLabel.CENTER);
		comment.setFont(new Font("arial", Font.BOLD, 28));
		comment.setForeground(Color.BLUE);
		mainPanel.add(comment);

		Font mainFont = new Font("arial", Font.PLAIN, 28);
		taskText = new JLabel("---");
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
//				System.out.println("pressed");
				int answerInt = 0;
				try {
					answerInt = Integer.parseInt(answerField.getText());
				} catch (Exception e2) {
					answerField.setText("");
					comment.setText("");
					return;
				}
				actionListenerForController.activated(answerInt);
			}
		});
		getRootPane().setDefaultButton(doneButton);
		JPanel middlePanel = new JPanel(new GridBagLayout());
//		GridBagConstraints gbc = new GridBagConstraints();
		middlePanel.add(taskText);
		middlePanel.add(answerField);
		middlePanel.add(spacer);
		middlePanel.add(doneButton);
		mainPanel.add(middlePanel);

		exitButton = new JButton("beenden");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: save the score etc
				System.out.println("Exiting the program!");
				System.exit(0);
			}
		});
//		exitButton.setMaximumSize(new Dimension(55, 100));
//		exitButton.setPreferredSize(new Dimension(55, 100));
		JPanel bottomPanel = new JPanel(new GridBagLayout());
		bottomPanel.add(exitButton, new GridBagConstraints());
		mainPanel.add(bottomPanel);

		add(mainPanel);
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}

	private void loadCommentsFromDatabase() {
		positiveFeedbackList = commentsDatabase.getPositiveFeedback();

	}

	public void setTask(String taskString) {
		taskText.setText(taskString);
	}

	public static Viewer getInstance() {
		if (viewer == null) {
			viewer = new Viewer();
		}
		return viewer;
	}

//	public void setComment(String s) {
//		comment.setText(s);
//		answerField.setText("");
//	}

	public void addMyActionListener(IMyActionListener actionListenerForController) {
		this.actionListenerForController = actionListenerForController;
	}

	public void isCorrect(boolean correctAnswer, String errorMessage) {
		if (correctAnswer) {
			comment.setForeground(Color.GREEN);
			int randomCommentNumber = ThreadLocalRandom.current().nextInt(0, positiveFeedbackList.size());
//			comment.setText("Richtig! :)");
			comment.setText(positiveFeedbackList.get(randomCommentNumber));
		} else {
			comment.setForeground(Color.RED);
//			comment.setText("Falsch :(");
			comment.setText(errorMessage);
		}
		answerField.setText("");
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				answerField.requestFocus();
//			}
//		});
		answerField.requestFocus();
	}
}
