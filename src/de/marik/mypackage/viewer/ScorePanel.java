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
	private static final Font regularFont = new Font("arial", Font.PLAIN, 12);
	private static final Font highlightingFont = new Font("arial", Font.BOLD, 12);
	private static final int DUMMYROW = 666; // dummy highlighted row

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

	public void newRecord(int row) {
		int column = 1;
		table.setValueAt("", row, column);
		tableModel.setCellEditable(row, column, true);
		tableModel.fireTableCellUpdated(row, 0); // highlighting the position number
		table.editCellAt(row, column);
		table.requestFocus();
		activeRow = row;
	}

	public void stopEditing() {
		if (activeRow != DUMMYROW) {
			tableModel.setCellEditable(activeRow, 1, false);
			activeRow = DUMMYROW;
		}
	}

	private JTable getTable(MyTableModel tableModel) {
		JTable table = new JTable(tableModel);
		table.setCellSelectionEnabled(true);
		table.setFocusable(true);
		table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		table.setBackground(new Color(238, 238, 238));
		table.setRowHeight(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(320);
		table.setIntercellSpacing(new Dimension(10, 0));
		table.setShowGrid(false); // show grid lines
		table.setFont(regularFont);
		table.setDefaultRenderer(Object.class, new MyCellRenderer());
		DefaultCellEditor myCellEditor = new MyCellEditor();
		myCellEditor.setClickCountToStart(1);
		table.setDefaultEditor(Object.class, myCellEditor);
		activeRow = DUMMYROW;
//		table.requestFocus();
		return table;
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
			textField.setFont(highlightingFont);
			textField.setForeground(Color.RED);
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			return textField;
		}
	}

	private class MyCellRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 8875380934534437162L;

		public MyCellRenderer() {
			super();
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			setText(value.toString());
			if (row == activeRow) {
				setFont(highlightingFont);
				setForeground(Color.RED);
			} else {
				setFont(regularFont);
				setForeground(Color.BLACK);
			}
			int alignmentCode = 0; // default: CENTER
			switch (column) {
			case 0:
				alignmentCode = SwingConstants.RIGHT;
				break;
			case 1:
				alignmentCode = SwingConstants.CENTER;
				break;
			case 2:
				alignmentCode = SwingConstants.LEFT;
				break;
			}
			setHorizontalAlignment(alignmentCode);
			return this;
		}
	}

}