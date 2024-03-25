package de.marik.mypackage.viewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.DefaultCellEditor;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class ScorePanel extends JPanel {
	private static final long serialVersionUID = -4887163524285865335L;
	private static final Font captionFont = new Font("arial", Font.BOLD, 14);
	private static final Font tableFont = new Font("arial", Font.PLAIN, 12);
	private static final Font highlightedFont = new Font("arial", Font.BOLD, 12);
	private static final int NOROW = 666;

	private MyTableModel tableModel;
	private JTable table;
	private int activeRow;

	ScorePanel(MyTableModel tableModel) {
		super();
		this.tableModel = tableModel;
		setLayout(new BorderLayout());
		JLabel label = new JLabel(tableModel.getTitel(), SwingConstants.CENTER);
		label.setFont(captionFont);
		label.setForeground(Color.BLUE);
		this.add(label, BorderLayout.NORTH);
		table = getTable(tableModel);
		this.add(table, BorderLayout.CENTER);
	}

	private JTable getTable(MyTableModel tableModel) {
		JTable table = new JTable(tableModel);
		table.setCellSelectionEnabled(true);
		table.setFocusable(true);
		table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		table.setRowHeight(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(320);
		table.setBackground(new Color(238, 238, 238));
		table.setIntercellSpacing(new Dimension(10, 0));
		table.setShowGrid(false); // GRID!
		table.setFont(tableFont);
		table.setDefaultRenderer(Object.class, new MyCellRenderer());
		DefaultCellEditor myCellEditor = new MyCellEditor();
		myCellEditor.setClickCountToStart(1);
		table.setDefaultEditor(Object.class, myCellEditor);
		activeRow = NOROW;
//		table.requestFocus();
		return table;
	}

	public String newRecord(int row) {
		int column = 1;
		table.setValueAt("", row, column);
		tableModel.setCellEditable(row, column, true); // temp agruments
		table.setValueAt(table.getValueAt(row, 0), row, 0); // for re-drawing with other color
		activeRow = row;
		table.requestFocus();
		table.editCellAt(row, column);

//		tableModel.setCellEditable(row, column, false);
//		System.out.println("already done?");

//		ListSelectionModel rowSelection = table.getSelectionModel();
//		int rowLead = rowSelection.getLeadSelectionIndex();
//
//		ListSelectionModel columnSelection = table.getColumnModel().getSelectionModel();
//		int columnLead = columnSelection.getLeadSelectionIndex();
//		
//		System.out.println(rowLead + " " + columnLead);
//		System.out.println(table.getEditingRow() + " " + table.getEditingColumn());

		// TODO!
		return null; // temp argument
	}

	public void stopEditing() {
		if (activeRow != NOROW) {
			// debugging
//			System.out.println(tableModel.getValueAt(activeRow, 1).getClass());
//			System.out.println("current name: " + tableModel.getValueAt(activeRow, 1));
//			System.out.println(((String) tableModel.getValueAt(activeRow, 1)).isEmpty());
			tableModel.setCellEditable(activeRow, 1, false);
			activeRow = NOROW;
		}
	}

	private class MyCellEditor extends DefaultCellEditor {
		private static final long serialVersionUID = -2194532624303284544L;

		public MyCellEditor() {
			super(new JTextField());
		}

		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {

			JTextField textField = (JTextField) super.getTableCellEditorComponent(table, value, isSelected, row,
					column);

//		        textField.setText(value.toString());

			textField.setFont(highlightedFont);
			textField.setForeground(Color.RED);
			textField.setHorizontalAlignment(SwingConstants.CENTER);
//		        System.out.println("entering name....");
			return textField;
		}
	}

	private class MyCellRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 8875380934534437162L;

		public MyCellRenderer() {
			super();
//			setOpaque(true);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			setText(value.toString());
			setFont(tableFont);

			if (row == activeRow) {
				setFont(highlightedFont);
				setForeground(Color.RED);
			} else {
				setFont(tableFont);
				setForeground(Color.BLACK);
			}

			int alignment = 0;
			switch (column) {
			case 0:
				alignment = SwingConstants.RIGHT;
				break;
			case 1:
				alignment = SwingConstants.CENTER;
				break;
			case 2:
				alignment = SwingConstants.LEFT;
				break;
			}
			setHorizontalAlignment(alignment);

//			if (column == 0 || column == 1) {
//				setHorizontalAlignment(SwingConstants.CENTER);
//			} else {
//				setHorizontalAlignment(SwingConstants.LEFT);
//			}

//			if (isSelected) {
//				setBackground(Color.YELLOW);
//			} else {
//				setBackground(Color.LIGHT_GRAY);
//			}

			return this;
		}

	}

}
