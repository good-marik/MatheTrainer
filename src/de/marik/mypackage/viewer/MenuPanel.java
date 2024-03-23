package de.marik.mypackage.viewer;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.marik.mypackage.main.Button;
import de.marik.mypackage.main.IActionListenerForButtons;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 4302685599041340611L;
	private IActionListenerForButtons controllersListener;

	public MenuPanel() {
		super();
		init();
	}

	public void setMyActionListener(IActionListenerForButtons controllersListener) {
		this.controllersListener = controllersListener;
	}

	private void init() {
		setLayout(new GridBagLayout());

		JButton additionButton = new JButton("Addieren +");
		JButton substractionButton = new JButton("Substrahieren –");
		JButton multiplicationButton = new JButton("Mutliplizieren *");
		JButton divisionButton = new JButton("Dividieren :");
		JButton exitButton = new JButton("Program Beenden");
		JButton highScoreButton = new JButton("Beste Ergebnisse");

		exitButton.setForeground(new Color(0, 0, 153));

		ActionListener menuListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == multiplicationButton) {
					System.out.println("multiplication!");
					controllersListener.activate(Button.MULTIPLICATION);
				}
				if (e.getSource() == additionButton) {
					System.out.println("addition!");
					controllersListener.activate(Button.ADDITION);
				}
				if (e.getSource() == divisionButton) {
					System.out.println("division!");
					controllersListener.activate(Button.DIVISION);
				}
				if (e.getSource() == substractionButton) {
					System.out.println("substraction!");
					controllersListener.activate(Button.SUBSTRACTION);
				}
				if (e.getSource() == highScoreButton) {
					System.out.println("high score!");
					controllersListener.activate(Button.HIGHSCORE);
				}
				if (e.getSource() == exitButton) {
					System.out.println("exit!");
					controllersListener.activate(Button.EXIT);
				}
			}
		};

		additionButton.addActionListener(menuListener);
		substractionButton.addActionListener(menuListener);
		multiplicationButton.addActionListener(menuListener);
		divisionButton.addActionListener(menuListener);
		exitButton.addActionListener(menuListener);
		highScoreButton.addActionListener(menuListener);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);
		GridBagConstraints gbcSpecial = new GridBagConstraints();
		//	gbcSpecial.gridwidth = GridBagConstraints.REMAINDER;
		//	gbcSpecial.fill = GridBagConstraints.HORIZONTAL;
		gbcSpecial.insets = new Insets(20, 5, 5, 5);
		
		add(additionButton, gbc);
		add(substractionButton, gbc);
		add(multiplicationButton, gbc);
		add(divisionButton, gbc);
		add(highScoreButton, gbcSpecial);
		add(exitButton, gbcSpecial);
	}
}
