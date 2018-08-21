/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Mehdi
 */
@Getter
@Setter
public abstract class ExceptionMessage 
{
    private String uniqueConstraintMessage;
    
    private String uniqueConstraintDebugMessage;
    
    private String emptyResultMessage;
    
    private String emptyResultDebugMessage;
}
