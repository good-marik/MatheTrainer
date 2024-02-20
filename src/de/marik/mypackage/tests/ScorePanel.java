package de.marik.mypackage.tests;

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
import javax.swing.table.DefaultTableCellRenderer;

public class ScorePanel extends JPanel {
	private MyTableModel tableModel;
//	private String title;
//	private String[][] scoresEntry;
//	private String[] columnName;
//	private int activePlace = 2; // active cell row + 1
	private JTable table;
	private static Font captionFont = new Font("arial", Font.BOLD, 14);
	private static Font tableFont = new Font("arial", Font.PLAIN, 12);

	private static final long serialVersionUID = 704313393302539651L;

	ScorePanel(String title, MyTableModel tableModel) {
		super();
		this.tableModel = tableModel;
		setLayout(new BorderLayout());

		JLabel label = new JLabel(title);
		label.setFont(captionFont);
		label.setHorizontalAlignment(JLabel.CENTER);
		add(label, BorderLayout.NORTH);

		JTable table = constructTable(tableModel);
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
		table.setCellSelectionEnabled(false);
//		table.setFocusable(false);
		table.setRowHeight(20);
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.setBackground(new Color(238, 238, 238));
//		additionTable.setRowMargin(15);
		table.setIntercellSpacing(new Dimension(10, 0));
		table.setShowGrid(true); // GRID!
		table.setFont(tableFont);
		
//		DefaultTableCellRenderer centeringRenderer = new DefaultTableCellRenderer();
//		centeringRenderer.setHorizontalAlignment(JLabel.CENTER);
//		table.getColumnModel().getColumn(0).setCellRenderer(centeringRenderer);
//		table.getColumnModel().getColumn(1).setCellRenderer(centeringRenderer);

		table.setDefaultRenderer(Object.class, new MyCellRenderer());
		
		((DefaultCellEditor) table.getDefaultEditor(Object.class)).setClickCountToStart(1);
		
//		table.setDefaultEditor(Object.class, new MyCellEditor());
		
		

		return table;
	}

	public String newRecord(int place) {
		tableModel.setCellEditable(1, 1, true); // temp agruments

		return null; // temp argument

	}

	private static class MyCellEditor extends DefaultCellEditor {

		public MyCellEditor(JTextField textField) {
			super(textField);
			// TODO Auto-generated constructor stub
		}

	
	}
	
	private static class MyCellRenderer extends DefaultTableCellRenderer {
//		public MyCellRenderer() {
////			super();
//			setOpaque(true);
//		}
		
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			
//			System.out.println(table.getValueAt(row, column));
			
			setText(value.toString());
			setFont(tableFont);
			
			if(column == 0||column == 1) {
				setHorizontalAlignment(JLabel.CENTER);
			} else {
				setHorizontalAlignment(JLabel.LEFT);
			}
			
//			if (isSelected) {
//				setBackground(Color.RED);
//			} else {
//				setBackground(Color.YELLOW);
//			}
			
			
//			if (table.getValueAt(row, column) == "Alexandra") {
//				setBackground(Color.RED);
//
//			} else {
//				setBackground(Color.YELLOW);
//			}
			
			
			return this;
		}
		
	}

}
