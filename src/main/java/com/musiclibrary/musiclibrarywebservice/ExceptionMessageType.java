/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice;

/**
 *
 * @author Mehdi
 */
public enum ExceptionMessageType 
{
    GENRE("Genres"),//GenreException | "UK_GenreName" 
    CATEGORY("Categories");//CategoryException | "UK_CategoryName"
    
    private String exceptionMessageTypeName;
    
    ExceptionMessageType(String name)
    {
        this.exceptionMessageTypeName = name;
    }
    
    public static ExceptionMessageType findExceptionMessageType(String type)
    {
        ExceptionMessageType foundExceptionMessageType = null;
        for(ExceptionMessageType ex : ExceptionMessageType.values())
        {
            System.out.println("ExceptionMessageType : "+ex);
            System.out.println("ExceptionMessageType 2  : "+ex.GENRE);
            System.out.println("ExceptionMessageType 2  : "+ex.GENRE.getClass().getName());
            System.out.println("ExceptionMessageType 2  : "+ex.GENRE.toString().getClass().getName());
            System.out.println("ExceptionMessageType 2  : "+GENRE.toString().getClass().getTypeName());
            System.out.println("ExceptionMessageType 2  : "+GENRE.toString().getClass().getSimpleName());
            System.out.println(type.equals(ex.GENRE.toString()));
            if(type.equals(ex.GENRE.toString()))
            {
                System.out.println(" FOUND Genre Type : "+ex.GENRE.name());
                System.out.println(" FOUND Genre Type : "+GENRE.getClass().getCanonicalName());
                
                foundExceptionMessageType = ex.GENRE;
                break;
            }
            else if(type.equals(ex.CATEGORY.toString()))
            {
                System.out.println(" FOUND Category Type : "+ex.CATEGORY.name());
                foundExceptionMessageType = ex.CATEGORY;
            }
        }
        
        return foundExceptionMessageType;
    }
    
    public static String firstCharToLowerCase(String exceptionMessageType)
    {
        String exceptionMessage1stCharTypeToLowerCase = exceptionMessageType.substring(0, 1).toLowerCase() + exceptionMessageType.substring(1);
        return exceptionMessage1stCharTypeToLowerCase;
    }

    @Override
    public String toString() {
        return this.exceptionMessageTypeName;
    }
}
