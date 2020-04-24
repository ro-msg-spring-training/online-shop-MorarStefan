package ro.msg.learning.controller.exception;

public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProductNotFoundException(Integer id) {
		super("Could not find product with id: " + id);
	}
}
