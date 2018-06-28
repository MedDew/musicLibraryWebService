/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice.controller;

import com.musiclibrary.musiclibraryapi.dto.UserDTO;
import com.musiclibrary.musiclibrarywebservice.service.UserManager;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mehdi
 */
@RestController
public class UserController 
{
    @Autowired
    private UserManager userService;
    
     @GetMapping("/users")
    public List<UserDTO>  getUsers()
    {
        List<UserDTO> users = userService.getUsers();
        /*
        List<UserDTO> userDTOList = new ArrayList<>();
        users.forEach(u -> {
            UserDTO userDTO = new UserDTO();
            
            userDTO.setId(u.getId());
            
            //Converting LocalDateTime To String 
//            DONE DIRECTLY IN REPOSITORY
//            u.setConvertedCreationDateToString(u.getCreationDate());
//            String creationDate = u.getConvertedCreationDateToString();
               
            userDTO.setCreationDate(u.getConvertedCreationDateToString());
            
            userDTO.setFirstName(u.getFirstName());
            userDTO.setIsLogged(u.getIsLogged());
            userDTO.setLastName(u.getLastName());
            
            //Converting LocalDateTime To String 
//            DONE DIRECTLY IN REPOSITORY
//            u.setConvertedLastLoginDateToString(u.getLastLogginDate());
//            String lastLoginDate = u.getConvertedLastLoginDateToString();
            
            userDTO.setLastLoginDate(u.getConvertedLastLoginDateToString());
            
            userDTOList.add(userDTO);
        });
        */
        
        return users;
    }
}
