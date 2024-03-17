package de.marik.mypackage.main;

public abstract class Operation {
	final String name;

	protected Operation(String name) {
		this.name = name;
	}

	public abstract int setTask();

	public abstract int getPoints();

	public abstract String getTaskString();

	public String getName() {
		return name;
	}
}
