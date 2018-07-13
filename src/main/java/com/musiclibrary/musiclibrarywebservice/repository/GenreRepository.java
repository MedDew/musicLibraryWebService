/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice.repository;

import com.musiclibrary.musiclibraryapi.dto.GenreDTO;
import com.musiclibrary.musiclibrarywebservice.entity.Genre;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Mehdi
 */
public interface GenreRepository 
{
    List<Genre> getGenres();
    
    Genre insertGenre(GenreDTO genreDTO);
    
    Genre updateGenre(GenreDTO genreDTO, long id);
    
    Genre deleteGenreById(GenreDTO genreDTO, long id);
    
    Genre findById(long id);
    
    Set<Genre> findByIdS(Set<Long> ids);
}
