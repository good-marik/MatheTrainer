package de.marik.mypackage.viewer;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.marik.mypackage.main.Button;
import de.marik.mypackage.main.IActionListenerForButtons;
import de.marik.mypackage.main.Operation;

public class HighScorePanel extends JPanel {
	private static String filename = "scores";
	private File file;
	
	private ScorePanel additionPanel;
	private ScorePanel substractionPanel;
	private ScorePanel multiplicationPanel;
	private ScorePanel divisionPanel;

	private MyTableModel additionTableModel;
	private MyTableModel substractionTableModel;
	private MyTableModel multiplicationTableModel;
	private MyTableModel divisionTableModel;

	private ScorePanel activeScorePanel;
	private MyTableModel activeTableModel;

	private JButton exitButton;
	private IActionListenerForButtons controllersListener;

	HighScorePanel() {

		loadHighScoreTables();

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
//		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);

		JLabel title = new JLabel("Beste Ergebnisse");
		title.setFont(new Font("arial", Font.BOLD, 18));
//		title.setForeground(Color.BLUE);
//		title.setHorizontalAlignment(SwingConstants.CENTER);

		this.add(title, gbc);

		JPanel tablePanel = new JPanel(new GridLayout(2, 2));
		Dimension panelSize = new Dimension(450, 210);
		tablePanel.setPreferredSize(panelSize);

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
					// debugging!
//					System.out.println("printing active TableModel");
//					activeTableModel.toPrint();
					saveHighScoreTables(file);
				}
				controllersListener.activate(Button.MENU);
			}
		});
		this.add(exitButton, gbc);
	}

	private void loadHighScoreTables() {
		file = new File(filename);
		System.out.println(filename + " exists? - " + file.isFile());

		if (file.isFile()) {
			readHighScoreTablesFromFile(file);
		} else {
			generateDefaultHighScoreTables(file);
		}

	}

	private void generateDefaultHighScoreTables(File file) {
		// default Tables
		String[][] additionTable = new String[][] { { "1", "Meister Yoda", "700" }, { "2", "Schlaufuchs", "350" },
				{ "3", "Ehrgeiziges Gespenst", "200" }, { "4", "Глупыш", "70" } };

		String[][] substractionTable = new String[][] { { "1", "Meister Yoda", "800" }, { "2", "Schlaufuchs", "400" },
				{ "3", "Ehrgeiziges Gespenst", "200" }, { "4", "Глупыш", "80" } };

		String[][] multiplicationTable = new String[][] { { "1", "Meister Yoda", "900" }, { "2", "Schlaufuchs", "450" },
				{ "3", "Ehrgeiziges Gespenst", "250" }, { "4", "Глупыш", "90" } };

		String[][] divisionTable = new String[][] { { "1", "Meister Yoda", "900" }, { "2", "Schlaufuchs", "450" },
				{ "3", "Ehrgeiziges Gespenst", "250" }, { "4", "Глупыш", "90" } };

		additionTableModel = new MyTableModel(additionTable, "Addition");
		substractionTableModel = new MyTableModel(substractionTable, "Substraktion");
		multiplicationTableModel = new MyTableModel(multiplicationTable, "Multiplikation");
		divisionTableModel = new MyTableModel(divisionTable, "Division");
		
		System.out.println("saving file is accomplished? : " + saveHighScoreTables(file));
	}
	
	private boolean saveHighScoreTables(File file) {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
			out.writeObject(additionTableModel);
			out.writeObject(substractionTableModel);
			out.writeObject(multiplicationTableModel);
			out.writeObject(divisionTableModel);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	private void readHighScoreTablesFromFile(File file) {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
			additionTableModel = (MyTableModel) in.readObject();
			substractionTableModel = (MyTableModel) in.readObject();
			multiplicationTableModel = (MyTableModel) in.readObject();
			divisionTableModel = (MyTableModel) in.readObject();
		} catch (IOException e) {
			System.out.println("I/O problem here!!!!!!!!!");
//			e.printStackTrace();
			return; 
		} catch (ClassNotFoundException c) {
			System.out.println("Expected TableModel is not found or damaged!");
//			c.printStackTrace();
			return;
		}
	}

	public void setMyActionListener(IActionListenerForButtons controllersListener) {
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

	public void setNewRecord(int score) {
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
	}

	private void setActiveTableModelAndScorePanel(Operation operation) {
		System.out.println("current operation: " + operation.getOperationName());
		switch (operation.getOperationName()) {
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
