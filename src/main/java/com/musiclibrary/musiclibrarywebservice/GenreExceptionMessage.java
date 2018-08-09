/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author Mehdi
 */
@Data
@AllArgsConstructor
public class GenreExceptionMessage 
{
    private String uniqueGenreConstraintMessage;
    
    private String uniqueGenreConstraintDebugMessage; 
}
