package de.marik.mypackage.tests;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class TestingHighScorePanel extends JFrame {
	private static final long serialVersionUID = -5917005205024021799L;

	public TestingHighScorePanel() {
		super("HighScoreTesting...");
		HighScorePanel hsp = new HighScorePanel();
		add(hsp);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = new Dimension(500, 350);
		setMinimumSize(dim);
		pack();
		setLocationRelativeTo(null);
		
		setVisible(true);

		hsp.toCongratulate();
		
		hsp.newRecord();
		
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new TestingHighScorePanel();
				
			}
		});
	}
}
