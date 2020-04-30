package ro.msg.learning.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ro.msg.learning.service.strategy.exception.UnsuccessfulStrategyException;

@ControllerAdvice
public class ExceptionAdvice {

	@ResponseBody
	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String employeeNotFoundHandler(ProductNotFoundException e) {
		return e.getMessage();
	}
	
	@ResponseBody
	@ExceptionHandler(UnsuccessfulStrategyException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String orderNotPossibleHandler(UnsuccessfulStrategyException e) {
		return e.getMessage();
	}
}
