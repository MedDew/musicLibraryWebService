/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 *
 * @author Mehdi
 */
@Entity
public class User 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = true)
    private LocalDateTime creationDate;
    
    @Column(length = 30, nullable = false)
    private String firstName;
    
    @Column(length = 30, nullable = false)
    private String lastName;
    
    @Column(nullable = true)
    private LocalDateTime lastLogginDate;
    
    @Column(nullable = true)
    private Boolean isLogged;
    
    @Transient
    private String convertedCreationDateToString;
    
    @Transient
    private LocalDateTime convertedCreationDateToLocalDateTime;

    @Transient
    private LocalDateTime convertedLastLoginDateToLocalDateTime;
    
    @Transient
    private String convertedLastLoginDateToString;

    public User(LocalDateTime creationDate, String firstName, String lastName, LocalDateTime lastLogginDate, Boolean isLogged) 
    {
        this.creationDate = creationDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastLogginDate = lastLogginDate;
        this.isLogged = isLogged;
    }
    
    public User()
    {
        
    }

    public String getConvertedLastLoginDateToString() 
    {
        return convertedLastLoginDateToString;
    }

    public void setConvertedLastLoginDateToString(LocalDateTime dateToConvert) 
    {
        String convertedDate = convertFromLocalDateTimeToString(dateToConvert);
        this.convertedLastLoginDateToString = convertedDate;
    }
    
    public LocalDateTime getConvertedLastLoginDateToLocalDateTime() 
    {
        return convertedLastLoginDateToLocalDateTime;
    }

    public void setConvertedLastLoginDateToLocalDateTime(String dateToConvert) 
    {
        LocalDateTime convertedDate = convertFromStringToLocalDateTime(dateToConvert);
        this.convertedLastLoginDateToLocalDateTime = convertedDate;
    }
    
    public LocalDateTime getConvertedCreationDateToLocalDateTime() 
    {
        return convertedCreationDateToLocalDateTime;
    }

    public void setConvertedCreationDateToLocalDateTime(String dateToConvert) 
    {
        LocalDateTime convertedDate = convertFromStringToLocalDateTime(dateToConvert);
        this.convertedCreationDateToLocalDateTime = convertedDate;
    }
    
    public String getConvertedCreationDateToString() 
    {
        return convertedCreationDateToString;
    }

    public void setConvertedCreationDateToString(LocalDateTime dateToConvert) 
    {
        String convertedDate = convertFromLocalDateTimeToString(dateToConvert);
        this.convertedCreationDateToString = convertedDate;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getLastLogginDate() {
        return lastLogginDate;
    }

    public void setLastLogginDate(LocalDateTime lastLogginDate) {
        this.lastLogginDate = lastLogginDate;
    }

    public Boolean getIsLogged() {
        return isLogged;
    }

    public void setIsLogged(Boolean isLogged) {
        this.isLogged = isLogged;
    }
    
    private LocalDateTime convertFromStringToLocalDateTime(String dateToConvert)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateToConvert, formatter);
        
        return localDateTime;
    }
    
    private String convertFromLocalDateTimeToString(LocalDateTime dateToConvert)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String localDateTime = null;
        if(dateToConvert != null)
        {
            localDateTime = dateToConvert.format(formatter);
        }
        else
        {
            LocalDateTime dt = LocalDateTime.now();
            localDateTime = dt.format(formatter);
        }
        
        return localDateTime;
    }    
}
