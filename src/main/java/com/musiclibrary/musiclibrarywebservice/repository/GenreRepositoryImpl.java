/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice.repository;

import com.musiclibrary.musiclibraryapi.dto.GenreDTO;
import com.musiclibrary.musiclibrarywebservice.entity.Genre;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mehdi
 */
@Repository
public class GenreRepositoryImpl implements GenreRepository
{
    @PersistenceContext
    private EntityManager em;
    
    private static final String GET_GENRES = "Select g From Genre g";
    
    private static final String GET_GENRE_BY_ID_NAMED_PARAMETER = "Select g From Genre g Where id = :id ";
    private static final String GENRE_ID_NAMED_PARAMETER = "id";
    
    private static final String GET_GENRE_BY_ID_POSITIONAL_PARAMETER = "Select g From Genre g Where id = ?1";
    private static final int GENRE_ID_POSITIONAL_PARAMETER = 1;
    
    private static final String GET_GENRES_BY_ID_NAMED_PARAMETER = "Select g From Genre g Where id IN :genredIds";
    private static final String GENRES_ID_NAMED_PARAMETER = "genredIds";
    
    @Override
    public List<Genre> getGenres() 
    {
        Query q = em.createQuery(GET_GENRES);
        List<Genre> genreList =  q.getResultList();
        
        return genreList;
    }

    @Override
    public Genre insertGenre(GenreDTO genreDTO) 
    {
        Genre genre = new Genre(genreDTO.getGenreName());
        
        em.persist(genre);
        
        return genre;
    }

    @Override
    public Genre updateGenre(GenreDTO genreDTO, long id) 
    {
        Genre foundGenre = em.find(Genre.class, id);//genreDTO.getId()
        foundGenre.setGenreName(genreDTO.getGenreName());
        
        em.persist(foundGenre);
        
        return foundGenre;
    }

    @Override
    public Genre deleteGenreById(GenreDTO genreDTO, long id) 
    {
        Genre foundGenre = em.find(Genre.class, id);//genreDTO.getId()
        
        em.remove(foundGenre);
        
        return foundGenre;
    }

    @Override
    public Genre findById(long id) 
    {
        TypedQuery<Genre> foundGenre =  em.createQuery(GET_GENRE_BY_ID_NAMED_PARAMETER, Genre.class);
        foundGenre.setParameter(GENRE_ID_NAMED_PARAMETER, id);
//        TypedQuery<Genre> foundGenreAlt =  em.createQuery(GET_GENRE_BY_ID_POSITIONAL_PARAMETER, Genre.class);
//        foundGenreAlt.setParameter(GENRE_ID_POSITIONAL_PARAMETER, id);
        
        Genre genre = foundGenre.getSingleResult();
        
        return genre;
    }

    @Override
    public Set<Genre> findByIdS(Set<Long> ids) 
    {
        TypedQuery<Genre> genres = em.createQuery(GET_GENRES_BY_ID_NAMED_PARAMETER, Genre.class);
        genres.setParameter(GET_GENRES_BY_ID_NAMED_PARAMETER, ids);
        
        Set<Genre> genresSet = new HashSet<>((Collection<? extends Genre>) genres);
        
        return genresSet;
    }
    
}
