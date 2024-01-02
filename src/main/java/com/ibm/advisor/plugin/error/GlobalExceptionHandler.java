/*
 * IBM Confidential
 * PID 5900-B4I
 * © Copyright IBM Corp. 2023
 *
 */
package com.ibm.advisor.plugin.error;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler{
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> notValid(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        GenericErrorPojo err = new GenericErrorPojo("Invalid input",
                        e.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toSet())
                        .toString()
                        .replaceAll("\\[*]*", ""), status.value() );
        return ResponseEntity.status(status).body(err);
    }
	
	@ExceptionHandler({ PluginNotFoundException.class })
    public ResponseEntity<Object> handlePluginNotFoundException(
      Exception ex, WebRequest request) {
        return new ResponseEntity<>(new GenericErrorPojo("Plugin not found.", "Not able to load requested plugin. Please check the plugin id.", HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler({ JsonProcessingException.class })
    public ResponseEntity<Object> handleJsonProcessingException(
      Exception ex, WebRequest request) {
        return new ResponseEntity<>(new GenericErrorPojo("Input data Json Processing Exception", ex.getLocalizedMessage(), HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler({ InvalidInputException.class })
    public ResponseEntity<Object> handleInvalidInputException(
      Exception ex, WebRequest request) {
        return new ResponseEntity<>(new GenericErrorPojo("Invalid Input Exception", ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }
	
}
