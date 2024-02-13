package de.marik.mypackage.tests;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class TestingHighScorePanel extends JFrame {
	private static final long serialVersionUID = -5917005205024021799L;

	public TestingHighScorePanel() {
		super("HighScoreTesting...");
		add(new HighScorePanel());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = new Dimension(500, 300);
		setMinimumSize(dim);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new TestingHighScorePanel();
			}
		});
	}
}
