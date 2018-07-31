/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice.service;

import com.musiclibrary.musiclibraryapi.dto.GenreDTO;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Mehdi
 */
public interface GenreManager 
{
    List<GenreDTO> getGenres();
    
    GenreDTO insertGenre(GenreDTO genreDTO);
    
    GenreDTO updateGenre(GenreDTO genreDTO, long id);
    
    GenreDTO deleteGenreByID( long id);
    
    GenreDTO findByID(long id);
    
    List<GenreDTO> findByIDS(Set<Long> ids);
}
