/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 *
 * @author Mehdi
 */
@Configuration
@PropertySource("classpath:exceptionMessage_en.properties")
public class ApplicationExceptionMessageConfig 
{
    @Autowired
    private Environment environment;
    
    private static final Set<String> uniqueConstraints = new HashSet<>(Arrays.asList("UK_GenreName", "UK_CategoryName"));
    
    public static Set<String> getUniqueConstraints()
    {
        return ApplicationExceptionMessageConfig.uniqueConstraints;
    }
    
    /**
    * This method works correctly only when handling unique constraint on Genre Object
    * Namely just 1 object
    * 
    * @deprecated As of <product> <version>, because ... use
    * If there are more than 1 object to handle unique constraint on 
    * we have to provide a bean for each object handling that exception
    *             USE {@link #factory.getFactory(foundExceptionMessageType, request)} instead.
    */
    @Deprecated
    @Bean
    public GenreExceptionMessage getDuplicateEntryGenreMessage()
    {
        String uniqueGenreConstraintMessage = environment.getProperty("genrename.unique.constraint.message");
        String uniqueGenreConstraintDebugMessage = environment.getProperty("genrename.unique.constraint.debugMessage");
        
        GenreExceptionMessage genreExceptionMessage = new GenreExceptionMessage();
//        genreExceptionMessage.setUniqueGenreConstraintMessage(uniqueGenreConstraintMessage);
//        genreExceptionMessage.setUniqueGenreConstraintDebugMessage(uniqueGenreConstraintDebugMessage);
        return genreExceptionMessage;
    }
    
    /**
    * This method works correctly only when handling unique constraint on Genre Object
    * Namely just 1 object
    * 
    * @deprecated As of <product> <version>, because ... use
    * If there are more than 1 object to handle unique constraint on 
    * we have to provide a bean for each object handling that exception
    *             USE {@link #factory.getFactory(foundExceptionMessageType, request)} instead.
    */
    @Deprecated
    @Bean
    public GenreExceptionMessage getEmptyResultGenreMessage()
    {
        String emptyGenreResultMessage = environment.getProperty("genrename.empty.result.message");
        String emptyGenreResultDebuMessage = environment.getProperty("genrename.empty.result.debugMessage");
        
        GenreExceptionMessage genreExceptionMessage = new GenreExceptionMessage();
//        genreExceptionMessage.setEmptyGenreResultMessage(emptyGenreResultMessage);
//        genreExceptionMessage.setEmptyGenreResultDebugMessage(emptyGenreResultDebuMessage);
        return genreExceptionMessage;
    }
}
