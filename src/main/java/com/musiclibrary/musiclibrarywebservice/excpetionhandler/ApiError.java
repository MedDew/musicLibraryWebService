/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice.excpetionhandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;
import com.musiclibrary.musiclibrarywebservice.excpetionhandler.ApiError.LowerCaseClassNameResolver;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

/**
 *
 * @author Mehdi
 */
@Data
//@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.CUSTOM, property = "error", visible = true)
//@JsonTypeIdResolver(LowerCaseClassNameResolver.class)
public class ApiError 
{
    private HttpStatus status;
    
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    
    private String message;

    private String debugMessage;
    
    private List<ApiSubError> subErrors;
        
    private ApiError()
    {
        timestamp = LocalDateTime.now();
    }
    
    public ApiError(HttpStatus status)
    {
        this();
        this.status = status;
    }
    
    public ApiError(HttpStatus status, Throwable ex)
    {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }
    
    public ApiError(HttpStatus status, String message,Throwable ex)
    {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }
    
    public void addSubError(ApiSubError subError)
    {
        if(subErrors == null)
        {
            subErrors = new ArrayList<ApiSubError>();
        }
        
        subErrors.add(subError);
    }
    
    private void addValidationError(String object, String field, Object rejectedValue, String message) 
    {
        addSubError(new ApiValidationError(object, field, rejectedValue, message));
    }
    
    private void addValidationError(FieldError fieldError) 
    {
        this.addValidationError(
                                fieldError.getObjectName(),
                                fieldError.getField(),
                                fieldError.getRejectedValue(),
                                fieldError.getDefaultMessage()
                               );
    }
    
    void addValidationErrors(List<FieldError> fieldErrors) 
    {
        fieldErrors.forEach(this::addValidationError);
    }
    
    private void addValidationError(String object, String message) 
    {
        addSubError(new ApiValidationError(object, message));
    }
    
    void addValidationErrorss(List<ObjectError> globalErrors) 
    {
        globalErrors.forEach(this::addValidationError);
    }
    
    private void addValidationError(ObjectError objectError) 
    {
        this.addValidationError(
                                objectError.getObjectName(),
                                objectError.getDefaultMessage()
                               );
    }
    
    abstract class ApiSubError 
    {
        
    }
    
    @Data
    @EqualsAndHashCode(callSuper = false)
    @AllArgsConstructor
    class ApiValidationError extends ApiSubError
    {
        private String object;
        private String field;
        private Object rejectedValue;
        private String message;
        
        ApiValidationError(String object, String message) 
        {
            this.object = object;
            this.message = message;
        }
    }
    
    class LowerCaseClassNameResolver extends TypeIdResolverBase
    {
        @Override
        public String idFromValue(Object value) 
        {
            return value.getClass().getSimpleName().toLowerCase();
        }

        @Override
        public String idFromValueAndType(Object value, Class<?> suggestedType) 
        {
            return idFromValue(value);
        }

        @Override
        public JsonTypeInfo.Id getMechanism() 
        {
            return JsonTypeInfo.Id.CUSTOM;
        }
    }
}
