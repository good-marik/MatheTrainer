package de.marik.mypackage;

public class MatheTrainer {

	public static void main(String[] args) {

		Viewer viewer = Viewer.getInstance();
		Controller controller = Controller.getInstance(viewer);
		controller.start();

	}
}
