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
import de.marik.mypackage.main.Operation;

public class HighScorePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private ScorePanel additionPanel;
	private ScorePanel substractionPanel;
	private ScorePanel multiplicationPanel;
	private ScorePanel divisionPanel;

	private MyTableModel additionTableModel;
	private MyTableModel substractionTableModel;
	private MyTableModel multiplicationTableModel;
	private MyTableModel divisionTableModel;

	private String[][] additionTable;
	private String[][] substractionTable;
	private String[][] multiplicationTable;
	private String[][] divisionTable;

	private JButton exitButton;
	private IMyActionListener controllersListener;

	private ScorePanel activeScorePanel;
	private MyTableModel activeTableModel;

	HighScorePanel() {

		// default Tables
		additionTable = new String[][] { { "1", "Best+", "212" }, { "2", "dummy+", "60" }, { "3", "dummy+", "30" },
				{ "4", "dummy+", "0" } };

		substractionTable = new String[][] { { "1", "Best-", "1201" }, { "2", "dummy-", "61" }, { "3", "dummy-", "31" },
				{ "4", "dummy-", "1" } };

		multiplicationTable = new String[][] { { "1", "Best*", "212" }, { "2", "dummy*", "60" },
				{ "3", "dummy*", "30" }, { "4", "dummy*", "12" } };

		divisionTable = new String[][] { { "1", "Best/", "212" }, { "2", "dummy/", "60" }, { "3", "dummy/", "30" },
				{ "4", "dummy/", "10" } };

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

		additionTableModel = new MyTableModel(additionTable, "Addition");
		substractionTableModel = new MyTableModel(substractionTable, "Substraktion");
		multiplicationTableModel = new MyTableModel(multiplicationTable, "Multiplikation");
		divisionTableModel = new MyTableModel(divisionTable, "Division");

		additionPanel = new ScorePanel(additionTableModel);
		substractionPanel = new ScorePanel(substractionTableModel);
		multiplicationPanel = new ScorePanel(multiplicationTableModel);
		divisionPanel = new ScorePanel(divisionTableModel);

		tablePanel.add(additionPanel);
		tablePanel.add(substractionPanel);
		tablePanel.add(multiplicationPanel);
		tablePanel.add(divisionPanel);

		this.add(tablePanel, gbc);

		exitButton = new JButton("Menu");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (activeScorePanel != null) {
					activeScorePanel.stopEditing();
					activeTableModel.toPrint();
				}
				controllersListener.activate(100);
			}
		});
		this.add(exitButton, gbc);
	}

	public void setMyActionListener(IMyActionListener controllersListener) {
		this.controllersListener = controllersListener;
	}

	public boolean isANewRecord(Operation operation, int score) {
		setActiveTableModelAndScorePanel(operation);
		int row = activeTableModel.getRowCount() - 1;
		while (row >= 0) {
			int tableScore = Integer.parseInt((String) activeTableModel.getValueAt(row, 2));
//					System.out.println(tablesScore);
			if (score > tableScore) {
				return true;
			}
			row--;
		}
		return false;
	}

	public int setNewRecord(int score) {
		int row = activeTableModel.getRowCount() - 1;
		activeTableModel.setValueAt("", row, 1);
		activeTableModel.setValueAt("" + score, row, 2);
		row--;
		int tableScore = Integer.parseInt((String) activeTableModel.getValueAt(row, 2));
		while (row >= 0 && score > tableScore) {
			String tempName = (String) activeTableModel.getValueAt(row, 1);
			String tempScore = (String) activeTableModel.getValueAt(row, 2);
			activeTableModel.setValueAt((String) activeTableModel.getValueAt(row + 1, 1), row, 1);
			activeTableModel.setValueAt((String) activeTableModel.getValueAt(row + 1, 2), row, 2);
			activeTableModel.setValueAt(tempName, row + 1, 1);
			activeTableModel.setValueAt(tempScore, row + 1, 2);
			row--;
			if (row >= 0) {
				tableScore = Integer.parseInt((String) activeTableModel.getValueAt(row, 2));
			}
		}
		activeScorePanel.newRecord(++row);

		return 0; // TODO
	}

	private void setActiveTableModelAndScorePanel(Operation operation) {
		System.out.println("current operation: " + operation.getName());
		switch (operation.getName()) {
		case "Addition":
			activeTableModel = additionTableModel;
			activeScorePanel = additionPanel;
			break;
		case "Substraktion":
			activeTableModel = substractionTableModel;
			activeScorePanel = substractionPanel;
			break;
		case "Multiplikation":
			activeTableModel = multiplicationTableModel;
			activeScorePanel = multiplicationPanel;
			break;
		case "Division":
			activeTableModel = divisionTableModel;
			activeScorePanel = divisionPanel;
			break;
		default:
			System.out.println("unknonwn operation!");
			System.exit(0);
		}

	}

}
