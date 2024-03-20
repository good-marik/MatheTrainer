package de.marik.mypackage.main;

public abstract class Operation {
	final String operationName;

	protected Operation(String operationName) {
		this.operationName = operationName;
	}

	public abstract int setTaskAndGetResult();

	public abstract int getPoints();

	public abstract String getTaskDescription();

	public String getOperationName() {
		return operationName;
	}
}
