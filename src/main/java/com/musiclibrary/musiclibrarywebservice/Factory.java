/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice;

import com.musiclibrary.musiclibrarywebservice.bean.RequestContext;
import java.text.MessageFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mehdi
 */
@Component
@PropertySource("classpath:exceptionMessage_en.properties")
public class Factory 
{
    @Autowired
    private Environment environment;
    
    
    public ExceptionMessage getFactory(ExceptionMessageType exMsgType, RequestContext requestContext)
    {
        System.out.println("exMsgType.toString() "+exMsgType.toString());
        ExceptionMessage exceptionToUse = null;
        switch(exMsgType)
        {
            case  GENRE :// 
                System.err.println("Instantiate GenreExceptionMessage");
                exceptionToUse = new GenreExceptionMessage();
                
                //FORMAT GENRE UNIQUE CONSTRAINT MESSAGE
                String genreUniqueConstraintMessage = MessageFormat.format(
                                                                            environment.getProperty("genrename.unique.constraint.message"), 
                                                                            requestContext.getGenreDTO().getGenreName()
                                                                          );
                
                
                exceptionToUse.setUniqueConstraintMessage(genreUniqueConstraintMessage);
                
                //FORMAT GENRE UNIQUE CONSTRAINT DEBUG MESSAGE
                String genreUniqueConstraintDebugMessage = MessageFormat.format(
                                                                                environment.getProperty("genrename.unique.constraint.debugMessage"), 
                                                                                requestContext.getGenreDTO().getGenreName(),
                                                                                "UK_GenreName"
                                                                               );
                
                exceptionToUse.setUniqueConstraintDebugMessage(genreUniqueConstraintDebugMessage);
 
                //FORMAT GENRE EMPTY RESULT MESSAGE
                String genreEmptyResultMessage = MessageFormat.format(
                                                                        environment.getProperty("genrename.empty.result.message"), 
                                                                        requestContext.getGenreDTO() == null ? requestContext.getPathVariable() : requestContext.getGenreDTO().getId()
                                                                     );
                exceptionToUse.setEmptyResultMessage(genreEmptyResultMessage);
                
                //FORMAT GENRE EMPTY RESULT DEBUG MESSAGE
                String genreEmptyResultDebugMessage = MessageFormat.format(
                                                                            environment.getProperty("genrename.empty.result.debugMessage"), 
                                                                            requestContext.getGenreDTO() == null ? requestContext.getPathVariable() : requestContext.getGenreDTO().getId()
                                                                          );
                exceptionToUse.setEmptyResultDebugMessage(genreEmptyResultDebugMessage);
            break;
            
            case CATEGORY ://"UK_CategoryName"  
                System.err.println("Instantiate CategoryExceptionMessage");
                exceptionToUse = new CategoryExceptionMessage();
                
                //FORMAT CATEGORY UNIQUE CONSTRAINT MESSAGE
                String categoryUniqueConstraintMessage = MessageFormat.format(
                                                                                environment.getProperty("categoryname.unique.constraint.message"), 
                                                                                requestContext.getCategoryDTO().getCategoryName()
                                                                             );
                exceptionToUse.setUniqueConstraintMessage(categoryUniqueConstraintMessage);
                
                //FORMAT CATEGORY UNIQUE CONSTRAINT DEBUG MESSAGE
                String categoryUniqueConstraintDebugMessage = MessageFormat.format(
                                                                                    environment.getProperty("categoryname.unique.constraint.debugMessage"), 
                                                                                    requestContext.getCategoryDTO().getCategoryName(),
                                                                                    "UK_CategoryName"
                                                                                  );
                
                exceptionToUse.setUniqueConstraintDebugMessage(categoryUniqueConstraintDebugMessage);
                
                //FORMAT CATEGORY EMPTY RESULT MESSAGE
                String categoryEmptyResultMessage = MessageFormat.format(
                                                                            environment.getProperty("categoryname.empty.result.message"), 
                                                                            requestContext.getCategoryDTO() == null ? requestContext.getPathVariable() : requestContext.getCategoryDTO().getId()
                                                                        );
                exceptionToUse.setEmptyResultMessage(categoryEmptyResultMessage);
                
                //FORMAT CATEGORY EMPTY RESULT DEBUG MESSAGE
                String categoryEmptyResultDebugMessage = MessageFormat.format(
                                                                                environment.getProperty("categoryname.empty.result.debugMessage"),
                                                                                requestContext.getCategoryDTO() == null ? requestContext.getPathVariable() : requestContext.getCategoryDTO().getId()
                                                                             );
                exceptionToUse.setEmptyResultDebugMessage(categoryEmptyResultDebugMessage);
            break;
        }
        return exceptionToUse;
    }
}
