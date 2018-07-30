/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice.service;

import com.musiclibrary.musiclibraryapi.dto.GenreDTO;
import com.musiclibrary.musiclibrarywebservice.entity.Genre;
import com.musiclibrary.musiclibrarywebservice.repository.GenreRepository;
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
public class GenreManagerService implements GenreManager
{
    
    @Autowired
    private GenreRepository genreRepo;
    
    @Override
    public List<GenreDTO> getGenres() 
    {
        List<Genre> genreList = genreRepo.getGenres();
        List<GenreDTO> genres = new ArrayList<>();
        
        genreList.forEach(g -> {
            GenreDTO genreDTO = new GenreDTO(g.getId(), g.getGenreName());
            
            genres.add(genreDTO);
        });
        
        return genres;
    }

    @Override
    public GenreDTO insertGenre(GenreDTO genreDTO) 
    {
        Genre createdGenre = genreRepo.insertGenre(genreDTO);
        GenreDTO genre = new GenreDTO(createdGenre.getId(), createdGenre.getGenreName());
        
        return genre;
    }

    @Override
    public GenreDTO updateGenre(GenreDTO genreDTO, long id) 
    {
        Genre updatedGenre = genreRepo.updateGenre(genreDTO, id);
        GenreDTO genre = new GenreDTO(updatedGenre.getId(), updatedGenre.getGenreName());
        
        return genre;
    }

    @Override
    public GenreDTO deleteGenreByID( long id) 
    {
        Genre deletedGenre = genreRepo.deleteGenreById(id);
        GenreDTO genre = new GenreDTO(deletedGenre.getId(), deletedGenre.getGenreName());
        
        return genre;
    }

    @Override
    public GenreDTO findByID(long id) 
    {
        Genre foundGenre = genreRepo.findById(id);
        GenreDTO genre = new GenreDTO(foundGenre.getId(), foundGenre.getGenreName());
        
        return genre;
    }
    
}
