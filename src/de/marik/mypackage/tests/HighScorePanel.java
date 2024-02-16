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
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class HighScorePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	

	HighScorePanel() {
		String[][] initialTable = new String[4][3];
		for (int i = 0; i < initialTable.length; i++) {
			initialTable[i][0] = "N";
			initialTable[i][1] = "Alexandra";
			initialTable[i][2] = "666000";
		}
		
		String[] columnName = new String[3];
		for (int i = 0; i < columnName.length; i++) {
			columnName[i] = "";
		}
		
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
		mainPanel.setBackground(Color.YELLOW);

		Font myDefaultFont = new Font("arial", Font.BOLD, 18);
		JLabel title = new JLabel("Beste Ergebnisse");
		title.setFont(myDefaultFont);
		title.setHorizontalAlignment(JLabel.CENTER);
//		title.setVerticalAlignment(JLabel.CENTER);
		mainPanel.add(title, BorderLayout.NORTH);

		JPanel tablesPanel = new JPanel(new GridLayout(2, 2));
		JPanel additionPanel = new JPanel(new BorderLayout());
		additionPanel.setBackground(Color.CYAN);
		
		JLabel additionLabel = new JLabel("Addition");
		additionLabel.setFont(new Font("arial", Font.BOLD, 16));
		additionLabel.setHorizontalAlignment(JLabel.CENTER);
		additionPanel.add(additionLabel, BorderLayout.NORTH);
		
		JTable additionTable = new JTable(initialTable, columnName);
		additionTable.setRowHeight(20);
		additionTable.getColumnModel().getColumn(0).setPreferredWidth(15);
//		additionTable.setRowMargin(15);
		additionTable.setIntercellSpacing(new Dimension(10,0));
//		additionTable.setShowGrid(false);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		additionTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );

		
		additionPanel.add(additionTable, BorderLayout.CENTER);
		
		
		tablesPanel.add(additionPanel);
		tablesPanel.add(new JButton("2"));
		tablesPanel.add(new JButton("3"));
		tablesPanel.add(new JButton("4"));
		mainPanel.add(tablesPanel, BorderLayout.CENTER);

		add(mainPanel);
//		System.out.println(bd.getHgap());
//		System.out.println(bd.getVgap());
//		System.out.println(bd.getLayoutAlignmentX(this));
//		System.out.println(bd.getLayoutAlignmentY(this));
//		System.out.println(bd);

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