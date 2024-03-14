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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class ScorePanel extends JPanel {
	private static final long serialVersionUID = 704313393302539651L;
	private MyTableModel tableModel;
//	private String title;
//	private String[][] scoresEntry;
//	private String[] columnName;
//	private int activePlace = 2; // active cell row + 1
	private JTable table;
	private static Font captionFont = new Font("arial", Font.BOLD, 14);
	private static Font tableFont = new Font("arial", Font.PLAIN, 12);
	private static Font highlightedFont = new Font("arial", Font.BOLD, 12);

	ScorePanel(String title, MyTableModel tableModel) {
		super();
		this.tableModel = tableModel;
		setLayout(new BorderLayout());

		JLabel label = new JLabel(title);
		label.setFont(captionFont);
		label.setHorizontalAlignment(JLabel.CENTER);
		add(label, BorderLayout.NORTH);

		table = constructTable(tableModel);
		add(table, BorderLayout.CENTER);
//		System.out.println(getBackground().toString());
//		System.out.println(getBackground().getRGB());
	}

	private JTable constructTable(MyTableModel tableModel) {
		table = new JTable(tableModel) {

//			public boolean isCellEditable(int row, int column) {
//				return (column == 1)&&(row + 1 == activePlace);
//			}
		};
		table.setCellSelectionEnabled(true);
		table.setFocusable(true);

		table.setRowHeight(20);
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.setBackground(new Color(238, 238, 238));
//		additionTable.setRowMargin(15);
		table.setIntercellSpacing(new Dimension(10, 0));
		table.setShowGrid(false); // GRID!
		table.setFont(tableFont);

//		DefaultTableCellRenderer centeringRenderer = new DefaultTableCellRenderer();
//		centeringRenderer.setHorizontalAlignment(JLabel.CENTER);
//		table.getColumnModel().getColumn(0).setCellRenderer(centeringRenderer);
//		table.getColumnModel().getColumn(1).setCellRenderer(centeringRenderer);

		table.setDefaultRenderer(Object.class, new MyCellRenderer());

//		((DefaultCellEditor) table.getDefaultEditor(Object.class)).setClickCountToStart(1);
		
		DefaultCellEditor myCellEditor = new MyCellEditor();
		myCellEditor.setClickCountToStart(1);
		table.setDefaultEditor(Object.class, myCellEditor);
//		table.requestFocus();
		
		

		return table;
	}

	public String newRecord(int place) {
		int row = 1;
		int column = 1;

		table.setValueAt("", row, column);
		tableModel.setCellEditable(row, column, true); // temp agruments
		table.editCellAt(row, column);
//		table.requestFocus();
		
//		tableModel.setCellEditable(row, column, false);
		System.out.println("already done?");
		
		
		ListSelectionModel rowSelection = table.getSelectionModel();
		int rowLead = rowSelection.getLeadSelectionIndex();

		ListSelectionModel columnSelection = table.getColumnModel().getSelectionModel();
		int columnLead = columnSelection.getLeadSelectionIndex();
		
		System.out.println(rowLead + " " + columnLead);
		System.out.println(table.getEditingRow() + " " + table.getEditingColumn());

		return null; // temp argument

	}

	private static class MyCellEditor extends DefaultCellEditor {
		
		private static final long serialVersionUID = 3635861511428924236L;

		public MyCellEditor() {
			super(new JTextField());
		}
		
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
			      int row, int column) {
			
		    JTextField textField = (JTextField) super.getTableCellEditorComponent(table, value, isSelected,
		            row, column);

//		        textField.setText(value.toString());
		        
		        textField.setFont(highlightedFont);
		        textField.setForeground(Color.RED);
		        textField.setHorizontalAlignment(SwingConstants.CENTER);
//		        System.out.println("entering name....");
		        return textField;
		}

	}

	private static class MyCellRenderer extends DefaultTableCellRenderer {
		public MyCellRenderer() {
			super();
//			setOpaque(true);
		}
		private static final long serialVersionUID = 5806223741957573550L;

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			
			setText(value.toString());
			setFont(tableFont);
			
			if(row == 1) {
				setFont(highlightedFont);
				setForeground(Color.RED);
			} else {
				setFont(tableFont);
				setForeground(Color.BLACK);
			}
			
			if(column == 0||column == 1) {
				setHorizontalAlignment(SwingConstants.CENTER);
			} else {
				setHorizontalAlignment(SwingConstants.LEFT);
			}
			
//			if (isSelected) {
//				setBackground(Color.YELLOW);
//			} else {
//				setBackground(Color.LIGHT_GRAY);
//			}
			
			return this;
		}

	}

}
