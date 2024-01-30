package de.marik.mypackage;

public class Multiplication extends Operation {
	int result;

	@Override
	public int getResult(int a, int b) {
		return result = a * b;
	}

	@Override
	public int getPoints(int a, int b) {
		return 1;
	}

}
