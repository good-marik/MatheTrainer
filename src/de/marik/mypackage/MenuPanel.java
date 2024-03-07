package de.marik.mypackage;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 4302685599041340611L;
	private IMyActionListener controllerListener;

	public MenuPanel() {
		super();
		init();
	}

	public void setMyActionListener(IMyActionListener controllerListener) {
		this.controllerListener = controllerListener;
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
					controllerListener.activated(1);
				}
				if (e.getSource() == additionButton) {
					System.out.println("addition!");
					controllerListener.activated(2);
				}
				if (e.getSource() == divisionButton) {
					System.out.println("division!");
					controllerListener.activated(3);
				}
				if (e.getSource() == substractionButton) {
					System.out.println("substraction!");
					controllerListener.activated(4);
				}
				if (e.getSource() == exitButton) {
					System.out.println("exit!");
					controllerListener.activated(10);
				}
			}
		};

		multiplicationButton.addActionListener(menuListener);
		additionButton.addActionListener(menuListener);
		divisionButton.addActionListener(menuListener);
		substractionButton.addActionListener(menuListener);
		exitButton.addActionListener(menuListener);

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
