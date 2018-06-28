/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice.service;

import com.musiclibrary.musiclibraryapi.dto.UserDTO;
import com.musiclibrary.musiclibrarywebservice.entity.User;
import com.musiclibrary.musiclibrarywebservice.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mehdi
 */
@Service
@Transactional
public class UserManagerService implements UserManager
{
    @Autowired
    private UserRepository userRepo;

    @Override
    public List<UserDTO> getUsers() 
    {
        List<User> userList = userRepo.getAllUsers();
        List<UserDTO> users = new ArrayList<UserDTO>();
        userList.forEach((u) -> {
            UserDTO user = new UserDTO(
                                        u.getId(), 
                                        u.getConvertedCreationDateToString(), 
                                        u.getCreationDate(), 
                                        u.getFirstName(), 
                                        u.getIsLogged(), 
                                        u.getConvertedLastLoginDateToString(), 
                                        u.getLastLogginDate(), 
                                        u.getLastName()
                                       );
        
            users.add(user);
        });
        
        
        return users;
    }

    @Override
    public UserDTO insertUser(UserDTO userDTO) 
    {
        User savedUser = userRepo.insertUser(userDTO);
        
        UserDTO user = new UserDTO(
                                        savedUser.getId(), 
                                        savedUser.getConvertedCreationDateToString(), 
                                        savedUser.getCreationDate(), 
                                        savedUser.getFirstName(), 
                                        savedUser.getIsLogged(), 
                                        savedUser.getConvertedLastLoginDateToString(), 
                                        savedUser.getLastLogginDate(), 
                                        savedUser.getLastName()
                                  );
        
        return user;
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Long userId) 
    {
        User updatedUser = userRepo.updateUser(userDTO, userId);
        
        /*DONE IN THE REPOSITORY
        //CREATION DATE : Convert LocalDateTime To String
        updatedUser.setConvertedCreationDateToString(updatedUser.getCreationDate());
        //LAST LOGIN DATE : Convert LocalDateTime To String
        updatedUser.setConvertedLastLoginDateToString(updatedUser.getLastLogginDate());
        */
        
        UserDTO user = new UserDTO(
                                        updatedUser.getId(), 
                                        updatedUser.getConvertedCreationDateToString(), 
                                        updatedUser.getCreationDate(), 
                                        updatedUser.getFirstName(), 
                                        updatedUser.getIsLogged(), 
                                        updatedUser.getConvertedLastLoginDateToString(), 
                                        updatedUser.getLastLogginDate(), 
                                        updatedUser.getLastName()
                                  );
        
        return user;
    }

    @Override
    public UserDTO findById(Long userId) 
    {
        User foundUser = userRepo.findById(userId);
        UserDTO user = new UserDTO(
                                    foundUser.getId(), 
                                    foundUser.getConvertedCreationDateToString(), 
                                    foundUser.getCreationDate(), 
                                    foundUser.getFirstName(), 
                                    foundUser.getIsLogged(), 
                                    foundUser.getConvertedLastLoginDateToString(), 
                                    foundUser.getLastLogginDate(), 
                                    foundUser.getLastName()
                                  );
        
        
        return user;
    }

    @Override
    public UserDTO deleteById(Long userId) 
    {
        User deletedUser = userRepo.deleteById(userId);
        UserDTO user = new UserDTO(
                                    deletedUser.getId(), 
                                    deletedUser.getConvertedCreationDateToString(), 
                                    deletedUser.getCreationDate(), 
                                    deletedUser.getFirstName(), 
                                    deletedUser.getIsLogged(), 
                                    deletedUser.getConvertedLastLoginDateToString(), 
                                    deletedUser.getLastLogginDate(), 
                                    deletedUser.getLastName()
                                  );
        
        return user;
    }
    
}
