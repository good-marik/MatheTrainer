package de.marik.mypackage.tests;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class HighScorePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private ScorePanel additionPanel;
	private ScorePanel substractionPanel;
	private ScorePanel multiplicationPanel;
	private ScorePanel divisionPanel;

	HighScorePanel() {
		String[][] initialTable = new String[4][3];
		for (int i = 0; i < initialTable.length; i++) {
			initialTable[i][0] = "" + (i + 1);
			initialTable[i][1] = "Alexandra";
			initialTable[i][2] = "666";
		}
		
		String[] columnName = new String[] {"Place", "Name", "Score"};
//		for (int i = 0; i < columnName.length; i++) {
//			columnName[i] = "";
//		}
		
		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);

//		GridBagConstraints gbc = new GridBagConstraints();
//		gbc.gridwidth = GridBagConstraints.REMAINDER;
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.insets = new Insets(5, 5, 5, 5);

		BorderLayout bd = new BorderLayout(0, 0);
		JPanel mainPanel = new JPanel(bd);
		Dimension panelSize = new Dimension(450, 225);
		mainPanel.setMaximumSize(panelSize);
		mainPanel.setPreferredSize(panelSize);
//		mainPanel.setBackground(Color.CYAN);

		Font myDefaultFont = new Font("arial", Font.BOLD, 18);
		JLabel title = new JLabel("Beste Ergebnisse");
		title.setFont(myDefaultFont);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setForeground(Color.BLUE);
//		title.setVerticalAlignment(JLabel.CENTER);
		mainPanel.add(title, BorderLayout.NORTH);

		JPanel tablePanel = new JPanel(new GridLayout(2, 2));
		
		MyTableModel tableModel = new MyTableModel(initialTable, columnName);
		
		additionPanel = new ScorePanel("Addition", tableModel);
		substractionPanel = new ScorePanel("Substraktion", tableModel);
		multiplicationPanel = new ScorePanel("Multiplikation", tableModel);
		divisionPanel = new ScorePanel("Division", tableModel);
		
		tablePanel.add(additionPanel);
		tablePanel.add(substractionPanel);
		tablePanel.add(multiplicationPanel);
		tablePanel.add(divisionPanel);

		mainPanel.add(tablePanel, BorderLayout.CENTER);
		add(mainPanel);
		
		
		
		
//		JOptionPane gratulationPane = new JOptionPane(new JLabel("Ein neuer Rekord! Gratulation!", JLabel.CENTER));
//		JDialog dialog = gratulationPane.createDialog("");
//		dialog.setVisible(true);
		
		
//		System.out.println(bd.getHgap());
//		System.out.println(bd.getVgap());
//		System.out.println(bd.getLayoutAlignmentX(this));
//		System.out.println(bd.getLayoutAlignmentY(this));
//		System.out.println(bd);

//		changeFont(this, myDefaultFont);

	}
	
	public void toCongratulate() {
//		JOptionPane.showMessageDialog(null, new JLabel("Gratulation!", JLabel.LEFT), "Ein neuer Rekord!", JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(null, "Gratulation!", "Ein neuer Rekord!", JOptionPane.INFORMATION_MESSAGE);
	}

	public String newRecord() {
		// as an example
		ScorePanel panelToCorrect = additionPanel;
		
		return panelToCorrect.newRecord(2); //temp argument
	}
	
	



//	private static void changeFont(Component component, Font font) {
//		component.setFont(font);
//		if (component instanceof Container) {
//			for (Component child : ((Container) component).getComponents()) {
//				changeFont(child, font);
//			}
//		}
//	}

};