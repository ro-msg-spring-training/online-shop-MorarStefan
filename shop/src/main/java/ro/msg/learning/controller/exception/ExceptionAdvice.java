package ro.msg.learning.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvice {

	@ResponseBody
	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String employeeNotFoundHandler(ProductNotFoundException e) {
		return e.getMessage();
	}
	
	@ResponseBody
	@ExceptionHandler(OrderNotPossibleException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String orderNotPossibleHandler(OrderNotPossibleException e) {
		return e.getMessage();
	}
}
