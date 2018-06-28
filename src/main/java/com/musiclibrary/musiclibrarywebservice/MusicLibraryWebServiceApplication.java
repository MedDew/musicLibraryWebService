/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice;

import java.util.Date;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Mehdi
 */
@SpringBootApplication
public class MusicLibraryWebServiceApplication 
{
    
//    @PostConstruct
//    public void init(){
//        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));   // It will set UTC timezone
//        System.out.println("Spring boot application running in UTC timezone :"+new Date());   // It will print UTC timezone
//    }
    
    public static void main(String [] args)
    {
        SpringApplication.run(MusicLibraryWebServiceApplication.class, args);
    }
}
