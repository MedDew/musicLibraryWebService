/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice;

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
    
    @Bean
    public GenreExceptionMessage getDuplicateEntryGenreMessage()
    {
        String uniqueGenreConstraintMessage = environment.getProperty("genrename.unique.constraint.message");
        String uniqueGenreConstraintDebugMessage = environment.getProperty("genrename.unique.constraint.debugMessage");
        
        GenreExceptionMessage genreExceptionMessage = new GenreExceptionMessage();
        genreExceptionMessage.setUniqueGenreConstraintMessage(uniqueGenreConstraintMessage);
        genreExceptionMessage.setUniqueGenreConstraintDebugMessage(uniqueGenreConstraintDebugMessage);
        return genreExceptionMessage;
    }
    
    @Bean
    public GenreExceptionMessage getEmptyResultGenreMessage()
    {
        String emptyGenreResultMessage = environment.getProperty("genrename.empty.result.message");
        String emptyGenreResultDebuMessage = environment.getProperty("genrename.empty.result.debugMessage");
        
        GenreExceptionMessage genreExceptionMessage = new GenreExceptionMessage();
        genreExceptionMessage.setEmptyGenreResultMessage(emptyGenreResultMessage);
        genreExceptionMessage.setEmptyGenreResultDebugMessage(emptyGenreResultDebuMessage);
        return genreExceptionMessage;
    }
}
