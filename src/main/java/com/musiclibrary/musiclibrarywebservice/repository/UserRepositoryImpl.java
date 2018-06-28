/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice.repository;

import com.musiclibrary.musiclibraryapi.dto.UserDTO;
import com.musiclibrary.musiclibrarywebservice.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mehdi
 */
@Repository
public class UserRepositoryImpl implements UserRepository
{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> getAllUsers() 
    {
        Query q = em.createQuery("Select u From User u");
        List<User> users = q.getResultList();
        List<User> userList = new ArrayList<>();
        for(User u : users)
        {
            //CREATION DATE : Convert LocalDateTime date To String
            u.setConvertedCreationDateToString(u.getCreationDate());
            //LAST LOGIN DATE : Convert LocalDateTime date To String
            u.setConvertedLastLoginDateToString(u.getLastLogginDate());
            
            userList.add(u);
        }
        
        return userList;
    }

    @Override
    public User insertUser(UserDTO userDTO) 
    {
        //CREATION DATE : Convert String Date To LocalDateTime Date FOR INSERT OPERATION 
        userDTO.setCreationDateLocalDateTime(userDTO.getCreationDate());
        //LAST LOGIN DATE : Convert String Date To LocalDateTime Date FOR INSERT OPERATION 
        userDTO.setLastLoginDateLocalDateTime(userDTO.getLastLoginDate());
        
        User user = new User(
                              userDTO.getCreationDateLocalDateTime(), 
                              userDTO.getFirstName(), 
                              userDTO.getLastName(), 
                              userDTO.getLastLoginDateLocalDateTime(), 
                              userDTO.getIsLogged()
                            );
        
        //CREATION DATE : Convert String date To LocalDateTime FOR DISPLAY OPERATION 
        user.setConvertedCreationDateToString(userDTO.getCreationDateLocalDateTime());
        //CREATION DATE : Convert String date To LocalDateTime FOR DISPLAY OPERATION 
        user.setConvertedLastLoginDateToString(userDTO.getLastLoginDateLocalDateTime());
        
        em.persist(user);
        em.flush();
        
//        Query q = em.createNativeQuery("SELECT LAST_INSERT_ID()", User.class);
//        User u = (User) q.getSingleResult();
//        System.out.println("LAST INSERTED ID : "+u);
        
        System.err.println("user.getId() : "+user.getId());
        
        return user;
    }

    @Override
    public User updateUser(UserDTO userDTO, Long userId) 
    {
        User foundUser = em.find(User.class, userId);
        
        //CREATION DATE : Convert String date To LocalDateTime FOR INSERT OPERATION 
        userDTO.setCreationDateLocalDateTime(userDTO.getCreationDate());
        
        //LAST LOGIN DATE : Convert String date To LocalDateTime FOR INSERT OPERATION 
        userDTO.setLastLoginDateLocalDateTime(userDTO.getLastLoginDate());
        
        foundUser.setCreationDate(userDTO.getCreationDateLocalDateTime());
        foundUser.setFirstName(userDTO.getFirstName());
        foundUser.setLastLogginDate(userDTO.getLastLoginDateLocalDateTime());
        foundUser.setIsLogged(userDTO.getIsLogged());
        foundUser.setLastName(userDTO.getLastName());
        
        //CREATION DATE : Convert String date To LocalDateTime FOR DISPLAY OPERATION 
        foundUser.setConvertedCreationDateToString(foundUser.getCreationDate());
        
        //CREATION DATE : Convert String date To LocalDateTime FOR DISPLAY OPERATION 
        foundUser.setConvertedLastLoginDateToString(foundUser.getLastLogginDate());
        
        em.persist(foundUser);
        return foundUser;
    }

    @Override
    public User findById(Long userId) 
    {
        User foundUser = em.find(User.class, userId);
        //CREATION DATE : Convert String date To LocalDateTime FOR DISPLAY OPERATION 
        foundUser.setConvertedCreationDateToString(foundUser.getCreationDate());
        
        //CREATION DATE : Convert String date To LocalDateTime FOR DISPLAY OPERATION 
        foundUser.setConvertedLastLoginDateToString(foundUser.getLastLogginDate());
        
        return foundUser;
    }

    @Override
    public User deleteById(Long userId) 
    {
        User foundUser = findById(userId);
        
        em.remove(foundUser);
        
        return foundUser;
    }
    
}
