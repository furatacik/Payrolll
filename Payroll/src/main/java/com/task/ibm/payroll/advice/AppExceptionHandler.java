package com.task.ibm.payroll.advice;
import com.task.ibm.payroll.exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;
public class AppExceptionHandler {
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public Map<String,String> handleInvalidArgument(MethodArgumentNotValidException ex){
	        Map<String,String> errorMap = new HashMap<>();
	        ex.getBindingResult().getFieldErrors().forEach(err->{
	            errorMap.put(err.getField(), err.getDefaultMessage());
	        });
	        return errorMap;
	    }

	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    @ExceptionHandler(EmployeeNotFoundException.class)
	    public Map<String,String> handleInvalidArgument(EmployeeNotFoundException ex){
	        Map<String,String> errorMap = new HashMap<>();
	        errorMap.put("errorMessage: ", ex.getMessage());
	        return errorMap;
	    }
}