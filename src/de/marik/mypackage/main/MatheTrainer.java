package de.marik.mypackage.main;

import javax.swing.SwingUtilities;

import de.marik.mypackage.viewer.Viewer;

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
