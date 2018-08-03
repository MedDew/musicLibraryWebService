/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice.excpetionhandler;

import com.musiclibrary.musiclibrarywebservice.bean.RequestContext;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//import org.springframework.web.servlet.support.RequestContext;

/**
 *
 * @author Mehdi
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler  extends ResponseEntityExceptionHandler
{
    
    @Autowired
    private RequestContext request;
    
    /**
     * Handles EntityNotFoundException. Created to encapsulate errors with more detail than javax.persistence.EntityNotFoundException.
     *
     * @param ex the EntityNotFoundException
     * @return the ApiError object
     */
    @ExceptionHandler( NoResultException.class )//value =  {EntityNotFoundException.class,
    protected ResponseEntity<Object> handleEntityNotFound(NoResultException ex)//EntityNotFoundException
    {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage()+" => Music with Id  : {"+request.getPathVariable()+"} not found");
        return buildResponseEntity(apiError);
//        return null;
    }
    
    /**
     * Handle javax.persistence.EntityNotFoundException
     */
    @ExceptionHandler(javax.persistence.EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(javax.persistence.EntityNotFoundException ex) 
    {
        return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, ex));
    }
    
    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) 
    {
        return new ResponseEntity<>(apiError, apiError.getStatus());
//        return null;
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) 
    {
        String message = "Malformed Json Request";
        ApiError apiError = new ApiError(status.BAD_REQUEST, message, ex);
         return buildResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) 
    {
        ApiError apiError = new ApiError(status.BAD_REQUEST);
        apiError.setMessage("Validation error");
        apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
        apiError.addValidationErrorss(ex.getBindingResult().getGlobalErrors());
        
        return buildResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) 
    {
//        ApiError apiError = new ApiError(status.BAD_REQUEST);
//        apiError.setMessage(String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL()));
//        apiError.setDebugMessage(ex.getMessage());
//        return buildResponseEntity(apiError);
        
        
        return super.handleNoHandlerFoundException(ex, headers, status, request); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) 
    {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(" method is not supported for this request. Supported methods are ");
        ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
        
        ApiError apiError = new ApiError(status.METHOD_NOT_ALLOWED, builder.toString(), ex);
        
        return buildResponseEntity(apiError);
//        return super.handleHttpRequestMethodNotSupported(ex, headers, status, request); //To change body of generated methods, choose Tools | Templates.
    }
}
