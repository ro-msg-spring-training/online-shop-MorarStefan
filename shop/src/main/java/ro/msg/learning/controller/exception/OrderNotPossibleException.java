package ro.msg.learning.controller.exception;

public class OrderNotPossibleException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OrderNotPossibleException() {
		super("Could not create the order based on the chosen strategy.");
	}
}
