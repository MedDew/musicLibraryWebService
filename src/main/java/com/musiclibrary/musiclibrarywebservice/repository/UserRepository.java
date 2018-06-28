/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice.repository;

import com.musiclibrary.musiclibraryapi.dto.UserDTO;
import com.musiclibrary.musiclibrarywebservice.entity.User;
import java.util.List;

/**
 *
 * @author Mehdi
 */
public interface UserRepository 
{
    public List<User> getAllUsers();
    
    public User insertUser(UserDTO userDTO);
    
    public User updateUser(UserDTO userDTO, Long userId);
    
    public User findById(Long userId);
    
    public User deleteById(Long userId);
}
