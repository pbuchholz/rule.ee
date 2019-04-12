package de.bu.rulee.model;

public class RuleEvaluationException extends Exception {

	private static final long serialVersionUID = -8258282367817219691L;

	public RuleEvaluationException(String message, Exception cause) {
		super(message, cause);
	}

	public RuleEvaluationException(Exception source) {
		super(source);
	}

}
