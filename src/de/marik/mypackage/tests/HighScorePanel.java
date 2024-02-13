package de.marik.mypackage.tests;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HighScorePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	HighScorePanel() {

		
		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);

//		GridBagConstraints gbc = new GridBagConstraints();
//		gbc.gridwidth = GridBagConstraints.REMAINDER;
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.insets = new Insets(5, 5, 5, 5);
		
		JPanel mainPanel = new JPanel(new BorderLayout(25, 25));
		Dimension panelSize = new Dimension(450, 225);
		mainPanel.setMaximumSize(panelSize);
		mainPanel.setPreferredSize(panelSize);
		mainPanel.setBackground(Color.YELLOW);
		
		Font myDefaultFont = new Font("arial", Font.BOLD, 20);
		JLabel title = new JLabel("Beste Ergebnisse");
		title.setFont(myDefaultFont);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setVerticalAlignment(JLabel.CENTER);
		mainPanel.add(title, BorderLayout.NORTH);

		JPanel tablesPanel = new JPanel(new GridLayout(2, 2));
		tablesPanel.add(new JButton("1"));
		tablesPanel.add(new JButton("2"));
		tablesPanel.add(new JButton("3"));
		tablesPanel.add(new JButton("4"));
		mainPanel.add(tablesPanel, BorderLayout.CENTER);
		
		
		
		add(mainPanel);
		
		
//		changeFont(this, myDefaultFont);

	}

	private static void changeFont(Component component, Font font) {
		component.setFont(font);
		if (component instanceof Container) {
			for (Component child : ((Container) component).getComponents()) {
				changeFont(child, font);
			}
		}
	}

};