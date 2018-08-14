/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice.controller;

import com.musiclibrary.musiclibraryapi.dto.GenreDTO;
import com.musiclibrary.musiclibrarywebservice.bean.RequestContext;
import com.musiclibrary.musiclibrarywebservice.service.GenreManager;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mehdi
 */
@RestController
public class GenreController 
{
    @Autowired
    private GenreManager genreService;
    
    @Autowired
    private RequestContext requestContext;
    
    @GetMapping(path = "/genres")
    public List<GenreDTO> getGenres()
    {
        List<GenreDTO> genres = genreService.getGenres();
        
        return genres;
    }
    
    @GetMapping(path = "/genres/{id}")
    public GenreDTO getGenre(@PathVariable(name = "id") long genreId)
    {
        requestContext.setPathVariable(genreId);
        GenreDTO genre = genreService.findByID(genreId);
        
        return genre;
    }
    
    @PostMapping("/genres/create")
    public GenreDTO postGenre(@RequestBody GenreDTO genreDTO)
    {
        requestContext.setGenreDTO(genreDTO);
        GenreDTO createdGenre = genreService.insertGenre(genreDTO);
        
        return createdGenre;
    }
    
    @PutMapping("/genres/update/{id}")
    public GenreDTO putGenre(@RequestBody GenreDTO genreDTO, @PathVariable(name = "id") long genreId)
    {
        requestContext.setGenreDTO(genreDTO);
        GenreDTO updatedGenre = genreService.updateGenre(genreDTO, genreId);
        
        return updatedGenre;
    }
    
    @DeleteMapping(path = "/genres/delete/{id}")
    public GenreDTO deleteGenre( @PathVariable(name = "id") long userId)
    {
        requestContext.setPathVariable(userId);
        GenreDTO deletedGenre = genreService.deleteGenreByID( userId);
        
        return deletedGenre;
    }
    
}
