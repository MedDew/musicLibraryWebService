/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice.excpetionhandler;

import com.musiclibrary.musiclibrarywebservice.Factory;
import com.musiclibrary.musiclibrarywebservice.ApplicationExceptionMessageConfig;
import com.musiclibrary.musiclibrarywebservice.ExceptionMessage;
import com.musiclibrary.musiclibrarywebservice.ExceptionMessageType;
import com.musiclibrary.musiclibrarywebservice.bean.RequestContext;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
    
    @Autowired
    private ApplicationExceptionMessageConfig exceptionMessageConfig;
    
    @Autowired
    private MessageSource messageSource;
    
    private static Map<String, String> exceptionMessageKeyValue = new HashMap<>();
    
    private static final Map<String, String> uniqueConstraints = new HashMap<>();
    
    private static final String[] exceptionTypeKeys = new String[]{"duplicate", "empty"};
    
    @Autowired
    private Factory factory;
    /*Static initialization of uniqueConstraints
    static 
    {
        Map<String, String> uniqueConstraintMessage = new HashMap<String, String>();

        uniqueConstraintMessage.put( "UK_GenreName", getExceptionMessage().get("UK_GenreName"));
        
        uniqueConstraints = Collections.unmodifiableMap(uniqueConstraintMessage);
    }
    
    
    public static void setUniqueConstraint()
    {
        
        uniqueConstraints.put("UK_GenreName", MessageFormat.format(
                                                                    exceptionMessageConfig.getDuplicateEntryMessage(), 
                                                                    request.getGenreDTO().getGenreName(),
                                                                    "UK_GenreName"
                                                                ));
    }
    */
    
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
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleMySQLIntegrityConstraintViolationException(DataIntegrityViolationException ex)
    {
        ApiError apiError = new ApiError(HttpStatus.CONFLICT);
        System.out.println("Exception : "+ex);
        System.out.println("Exception : "+ex.getMessage());
        System.out.println("isCategory : "+ex.getMessage().contains("Categories"));
        if(request.getGenreDTO() != null)
        {
            System.out.println("DTO : "+request.getGenreDTO()+" "+request.getGenreDTO().getGenreName());
        }
        else if(request.getCategoryDTO() != null)
        {
            System.out.println("DTO : "+request.getCategoryDTO()+" "+request.getCategoryDTO().getCategoryName());
        }
        System.out.println("Unique Constraints : "+ApplicationExceptionMessageConfig.getExceptions());
        
        /*
        String uniqueGenreConstraintViolationMessage = MessageFormat.format(
                                                                        exceptionMessageConfig.getDuplicateEntryGenreMessage().getUniqueGenreConstraintMessage(), 
                                                                        request.getGenreDTO().getGenreName()
                                                                      );
        String uniqueGenreConstraintViolationDebugMessage = MessageFormat.format(
                                                                        exceptionMessageConfig.getDuplicateEntryGenreMessage().getUniqueGenreConstraintDebugMessage(), 
                                                                        request.getGenreDTO().getGenreName(),
                                                                        "UK_GenreName"
                                                                      );
        System.out.println("Properties Message Exception : "+uniqueGenreConstraintViolationMessage);
        
        uniqueConstraints.put(request.getGenreDTO().getGenreName(), uniqueGenreConstraintViolationMessage);
        uniqueConstraints.put("UK_GenreName", uniqueGenreConstraintViolationDebugMessage);
        
        Optional<Entry<String, String>> uniqueGenreMessages = uniqueConstraints.entrySet().stream().filter((c) -> request.getGenreDTO().getGenreName().contains(c.getKey()) ).findAny();
        System.out.println(uniqueGenreMessages);
        
        Optional<Entry<String, String>> uniqueGenreDebugMessages = uniqueConstraints.entrySet().stream().filter((c) -> ex.getMessage().contains(c.getKey()) ).findAny();
        System.out.println(uniqueGenreDebugMessages);
        
        apiError.setMessage(uniqueGenreMessages.get().getValue());
        apiError.setDebugMessage(uniqueGenreDebugMessages.get().getValue());
        uniqueConstraints.entrySet().stream().forEach(e ->  System.out.println(e.getKey() +" ==> "+ e.getValue()) );
        
        for(Map.Entry<String, String> entry : uniqueConstraints.entrySet())
        {
            boolean ok = ex.getMessage().contains(entry.getKey());
            if(ok)
            {
                System.out.println(entry.getKey()+" => "+entry.getValue());
            }
        }
        
        ex.printStackTrace();
        */
        
        //LOOPING THROUGH UNIQUE CONSTRAINTS NAME
        ApplicationExceptionMessageConfig.getExceptions().stream().forEach((uc) -> System.err.println(uc.trim()));
        
        //FILTERING UNIQUE CONSTRAINT MESSAGE FROM THE EXCEPTION MESSAGE IN THE SET OF CONFIGURED CONSTRAINTS  
        Optional<String> uniqueConstraintType = ApplicationExceptionMessageConfig.getExceptions().stream().filter( (uc) -> ex.getMessage().contains(uc.trim())  ).findAny();
        System.err.println(uniqueConstraintType.get());
        
        //FIND THE RIGHT EXCEPTION MESSAGE TYPE TO USE 
        ExceptionMessageType foundExceptionMessageType = ExceptionMessageType.findExceptionMessageType(uniqueConstraintType.get());
        
        //RECOVER THE RIGHT EXCEPTION MESSAGE TYPE
        ExceptionMessage exceptionToUse =  factory.getFactory(foundExceptionMessageType, request, ApplicationExceptionMessageConfig.getExceptionTypes().get(exceptionTypeKeys[0]));
        
         
        apiError.setMessage(exceptionToUse.getUniqueConstraintMessage());
        apiError.setDebugMessage(exceptionToUse.getUniqueConstraintDebugMessage());
        
        
        return buildResponseEntity(apiError);
    }
    
    @ExceptionHandler(EmptyResultDataAccessException.class)
    protected ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex)
    {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        System.out.println(request.getClass().getSuperclass());
        
        //LOOPING THROUGH UNIQUE CONSTRAINTS NAME
        ApplicationExceptionMessageConfig.getExceptions().stream().forEach((uc) -> System.err.println(uc.trim()+" | "+uc.toLowerCase()));
        
        //FILTERING REQUEST URI IN THE SET OF CONFIGURED CONSTRAINTS  
        Optional<String> uriType = ApplicationExceptionMessageConfig.getExceptions().stream().filter( (uc) -> request.getUri().contains(uc.toLowerCase())  ).findAny();
        System.err.println(uriType.get());
        
        //FIND THE RIGHT EXCEPTION MESSAGE TYPE TO USE 
        ExceptionMessageType foundExceptionMessageType = ExceptionMessageType.findExceptionMessageType(uriType.get());
        
        //RECOVER THE RIGHT EXCEPTION MESSAGE TYPE
//        if()
        ExceptionMessage exceptionToUse =  factory.getFactory(foundExceptionMessageType, request, ApplicationExceptionMessageConfig.getExceptionTypes().get(exceptionTypeKeys[1]));
        
        /*String emptyResultMessage = MessageFormat.format(
                                                            exceptionMessageConfig.getEmptyResultGenreMessage().getEmptyGenreResultMessage(), 
                                                            request.getGenreDTO() == null ? request.getPathVariable() : request.getGenreDTO().getId()
                                                        );
        
        
        String emptyResultDebugMessage = MessageFormat.format(
                                                            exceptionMessageConfig.getEmptyResultGenreMessage().getEmptyGenreResultDebugMessage(), 
                                                            request.getGenreDTO() == null ? request.getPathVariable() : request.getGenreDTO().getId()
                                                        );
        
        apiError.setMessage(emptyResultMessage);
        apiError.setDebugMessage(emptyResultDebugMessage);
        */
        apiError.setMessage(exceptionToUse.getEmptyResultMessage());
        apiError.setDebugMessage(exceptionToUse.getEmptyResultDebugMessage());
        return buildResponseEntity(apiError);
    }
}
