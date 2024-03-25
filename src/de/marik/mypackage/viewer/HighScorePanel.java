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
	private static final long serialVersionUID = 6016203455657528034L;
	private static final String filename = "scores";

	private final ScorePanel additionPanel;
	private final ScorePanel substractionPanel;
	private final ScorePanel multiplicationPanel;
	private final ScorePanel divisionPanel;

	private ScorePanel activeScorePanel;
	private MyTableModel additionTableModel;
	private MyTableModel substractionTableModel;
	private MyTableModel multiplicationTableModel;
	private MyTableModel divisionTableModel;
	private MyTableModel activeTableModel;

	private IActionListenerForButtons controllersListener;

	public HighScorePanel() {
		File file = new File(filename);
		loadHighScoreTables(file);

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.insets = new Insets(5, 5, 5, 5);
		JLabel title = new JLabel("Beste Ergebnisse");
		title.setFont(new Font("arial", Font.BOLD, 18));
		this.add(title, gbc);

		JPanel tablePanel = new JPanel(new GridLayout(2, 2));
		Dimension size = new Dimension(450, 210);
		tablePanel.setPreferredSize(size);
		additionPanel = new ScorePanel(additionTableModel);
		substractionPanel = new ScorePanel(substractionTableModel);
		multiplicationPanel = new ScorePanel(multiplicationTableModel);
		divisionPanel = new ScorePanel(divisionTableModel);
		tablePanel.add(additionPanel);
		tablePanel.add(substractionPanel);
		tablePanel.add(multiplicationPanel);
		tablePanel.add(divisionPanel);
		this.add(tablePanel, gbc);

		JButton exitButton = new JButton("Menu");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (activeScorePanel != null) {
					activeScorePanel.stopEditing();
					saveHighScoreTables(file);
				}
				controllersListener.activate(Button.MENU);
			}
		});
		this.add(exitButton, gbc);
	}

	public void setMyActionListener(IActionListenerForButtons controllersListener) {
		this.controllersListener = controllersListener;
	}

	public boolean isANewRecord(Operation operation, int score) {
		setActiveTableModelAndScorePanel(operation);
		int lastRow = activeTableModel.getRowCount() - 1;
		int tableScore = Integer.parseInt((String) activeTableModel.getValueAt(lastRow, 2));
		// debugging
		System.out.println("---------------checking for a new record----------------------------");
		System.out.println("tableScore: " + tableScore);
		System.out.println("my score: " + score);
		System.out.println("a new record? - " + (score > tableScore));
		return score > tableScore;
	}

	public void setNewRecord(int score) {
		int row = activeTableModel.getRowCount() - 1;
		activeTableModel.setValueAt("", row, 1);
		activeTableModel.setValueAt(Integer.toString(score), row, 2);
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

	private void loadHighScoreTables(File file) {
		// debugging
		System.out.println(filename + " exists? - " + file.isFile());
		if (file.isFile()) {
			readHighScoreTablesFromFile(file);
		} else {
			generateDefaultHighScoreTables(file);
		}
	}

	private void generateDefaultHighScoreTables(File file) {
		// default High Score Tables
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
		// debugging: do not delete method saveHighScoreTables()!
		System.out.println("saving file is accomplished? : " + saveHighScoreTables(file));
	}

	private void readHighScoreTablesFromFile(File file) {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
			additionTableModel = (MyTableModel) in.readObject();
			substractionTableModel = (MyTableModel) in.readObject();
			multiplicationTableModel = (MyTableModel) in.readObject();
			divisionTableModel = (MyTableModel) in.readObject();
		} catch (IOException e) {
			System.out.println("I/O problem here!!!");
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Expected TableModel is not found or damaged!!! Loading default tables...");
			generateDefaultHighScoreTables(file);
		}
	}

	private boolean saveHighScoreTables(File file) {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
			out.writeObject(additionTableModel);
			out.writeObject(substractionTableModel);
			out.writeObject(multiplicationTableModel);
			out.writeObject(divisionTableModel);
			return true;
		} catch (IOException e) {
			System.out.println("Problems with saving High Score Tables!!!");
			return false;
		}
	}

	private void setActiveTableModelAndScorePanel(Operation operation) {
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
			System.out.println("unknown operation!");
			System.exit(0);
		}
	}

}
