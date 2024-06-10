package com.github.lorenzosmc.common.exception;

public class IllegalUpdateException extends Exception {
	private static final long serialVersionUID = 7836907957109252358L;

	public IllegalUpdateException(String errorMessage) {
		super(errorMessage);
	}
}
