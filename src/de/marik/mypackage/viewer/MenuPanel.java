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
	private static final String ADD = "Addieren +";
	private static final String SUBSTRACT = "Substrahieren –";
	private static final String MULTIPLY = "Mutliplizieren *";
	private static final String DIVIDE = "Dividieren :";
	private static final String EXIT = "Program Beenden";
	private static final String HIGHSCORE = "Beste Ergebnisse";

	private IActionListenerForButtons controllersListener;

	MenuPanel() {
		super();
		init();
	}

	public void setMyActionListener(IActionListenerForButtons controllersListener) {
		this.controllersListener = controllersListener;
	}

	private void init() {
		setLayout(new GridBagLayout());
		JButton additionButton = new JButton(ADD);
		JButton substractionButton = new JButton(SUBSTRACT);
		JButton multiplicationButton = new JButton(MULTIPLY);
		JButton divisionButton = new JButton(DIVIDE);
		JButton highScoreButton = new JButton(HIGHSCORE);
		JButton exitButton = new JButton(EXIT);
		exitButton.setForeground(new Color(0, 0, 153));

		ActionListener menuListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (e.getActionCommand()) {
				case ADD:
					controllersListener.activate(Button.ADDITION);
					break;
				case SUBSTRACT:
					controllersListener.activate(Button.SUBSTRACTION);
					break;
				case MULTIPLY:
					controllersListener.activate(Button.MULTIPLICATION);
					break;
				case DIVIDE:
					controllersListener.activate(Button.DIVISION);
					break;
				case HIGHSCORE:
					controllersListener.activate(Button.HIGHSCORE);
					break;
				case EXIT:
					controllersListener.activate(Button.EXIT);
					break;
				}
			}
		};

		additionButton.addActionListener(menuListener);
		substractionButton.addActionListener(menuListener);
		multiplicationButton.addActionListener(menuListener);
		divisionButton.addActionListener(menuListener);
		highScoreButton.addActionListener(menuListener);
		exitButton.addActionListener(menuListener);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);
		GridBagConstraints gbcSpecial = new GridBagConstraints();
		gbcSpecial.insets = new Insets(25, 5, 5, 5);

		add(additionButton, gbc);
		add(substractionButton, gbc);
		add(multiplicationButton, gbc);
		add(divisionButton, gbc);
		add(highScoreButton, gbcSpecial);
		add(exitButton, gbcSpecial);
	}
}
