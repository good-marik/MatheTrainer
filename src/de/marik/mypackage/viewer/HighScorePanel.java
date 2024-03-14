package de.marik.mypackage.viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.marik.mypackage.main.IMyActionListener;

public class HighScorePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private ScorePanel additionPanel;
	private ScorePanel substractionPanel;
	private ScorePanel multiplicationPanel;
	private ScorePanel divisionPanel;
	private JButton exitButton;
	private IMyActionListener controllersListener;

	HighScorePanel() {
		
		String[][] initialTable = new String[][] {
			{"1", "Best!", "120"},
			{"2", "dummy", "60"},
			{"3", "dummy", "30"},
			{"4", "dummy", "10"},
			};

//		String[][] initialTable = new String[4][3];
//		for (int i = 0; i < initialTable.length; i++) {
//			initialTable[i][0] = "" + (i + 1);
//			initialTable[i][1] = "Alexandra";
//			initialTable[i][2] = "666";
//		}
		
		String[] columnName = new String[] { "Place", "Name", "Score" };

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
//		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);

		JLabel title = new JLabel("Beste Ergebnisse");
		title.setFont(new Font("arial", Font.BOLD, 18));
		title.setForeground(Color.BLUE);
//		title.setHorizontalAlignment(SwingConstants.CENTER);

		this.add(title, gbc);

		JPanel tablePanel = new JPanel(new GridLayout(2, 2));
		Dimension panelSize = new Dimension(450, 210);
		tablePanel.setPreferredSize(panelSize);

		MyTableModel tableModel = new MyTableModel(initialTable, columnName);
		additionPanel = new ScorePanel("Addition", tableModel);
		substractionPanel = new ScorePanel("Substraktion", tableModel);
		multiplicationPanel = new ScorePanel("Multiplikation", tableModel);
		divisionPanel = new ScorePanel("Division", tableModel);

		tablePanel.add(additionPanel);
		tablePanel.add(substractionPanel);
		tablePanel.add(multiplicationPanel);
		tablePanel.add(divisionPanel);

		this.add(tablePanel, gbc);

		exitButton = new JButton("Menu");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controllersListener.activate(100);
			}
		});
		this.add(exitButton, gbc);
	}

	public String newRecord() {
		// as an example
		ScorePanel panelToCorrect = additionPanel;
		return panelToCorrect.newRecord(2); // temp argument
	}

	public void setMyActionListener(IMyActionListener controllersListener) {
		this.controllersListener = controllersListener;
	}
};