package de.marik.mypackage;

import javax.swing.SwingUtilities;

public class MatheTrainer {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Viewer viewer = Viewer.getInstance();
				Controller.getInstance(viewer);
			}
		});
	}
}
