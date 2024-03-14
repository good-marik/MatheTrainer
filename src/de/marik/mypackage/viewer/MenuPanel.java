package de.marik.mypackage.viewer;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.marik.mypackage.main.IMyActionListener;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 4302685599041340611L;
	private IMyActionListener controllersListener;

	public MenuPanel() {
		super();
		init();
	}

	public void setMyActionListener(IMyActionListener controllersListener) {
		this.controllersListener = controllersListener;
	}

	private void init() {
		setLayout(new GridBagLayout());

		JButton multiplicationButton = new JButton("Mutliplizieren *");
		JButton additionButton = new JButton("Addieren +");
		JButton divisionButton = new JButton("Dividieren :");
		JButton substractionButton = new JButton("Substrahieren –");
		JButton exitButton = new JButton("Program Beenden");
		JButton highScoreButton = new JButton("Beste Ergebnisse");

		exitButton.setForeground(new Color(0, 0, 153));

		ActionListener menuListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == multiplicationButton) {
					System.out.println("multiplication!");
					controllersListener.activate(1);
				}
				if (e.getSource() == additionButton) {
					System.out.println("addition!");
					controllersListener.activate(2);
				}
				if (e.getSource() == divisionButton) {
					System.out.println("division!");
					controllersListener.activate(3);
				}
				if (e.getSource() == substractionButton) {
					System.out.println("substraction!");
					controllersListener.activate(4);
				}
				if (e.getSource() == highScoreButton) {
					System.out.println("high score!");
					controllersListener.activate(9);
				}
				if (e.getSource() == exitButton) {
					System.out.println("exit!");
					controllersListener.activate(10);
				}
			}
		};

		multiplicationButton.addActionListener(menuListener);
		additionButton.addActionListener(menuListener);
		divisionButton.addActionListener(menuListener);
		substractionButton.addActionListener(menuListener);
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
		
		add(multiplicationButton, gbc);
		add(additionButton, gbc);
		add(divisionButton, gbc);
		add(substractionButton, gbc);
		add(highScoreButton, gbcSpecial);
		add(exitButton, gbcSpecial);
	}
}
