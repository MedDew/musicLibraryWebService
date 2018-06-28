/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice.service;

import com.musiclibrary.musiclibraryapi.dto.UserDTO;
import java.util.List;

/**
 *
 * @author Mehdi
 */
public interface UserManager 
{
    public List<UserDTO> getUsers();
    
    public UserDTO insertUser(UserDTO userDTO);
    
    public UserDTO updateUser(UserDTO userDTO, Long userId);
    
    public UserDTO findById(Long userId);
    
    public UserDTO deleteById(Long userId);    
}
