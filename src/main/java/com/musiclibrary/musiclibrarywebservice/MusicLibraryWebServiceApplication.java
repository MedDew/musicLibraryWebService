/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice;

import java.util.Date;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @author Mehdi
 */
@SpringBootApplication
//@EnableWebMvc
public class MusicLibraryWebServiceApplication 
{
    
//    @Autowired
//    private DispatcherServlet dispatcherServlet;
    
//    @PostConstruct
//    public void init(){
//        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));   // It will set UTC timezone
//        System.out.println("Spring boot application running in UTC timezone :"+new Date());   // It will print UTC timezone
//    }
    
    public static void main(String [] args)
    {
        SpringApplication.run(MusicLibraryWebServiceApplication.class, args);
    }
    
//    @Bean
//    public CommandLineRunner getCommandLineRunner(ApplicationContext context) 
//    {
//        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
//        return args -> {};
//    }
}
