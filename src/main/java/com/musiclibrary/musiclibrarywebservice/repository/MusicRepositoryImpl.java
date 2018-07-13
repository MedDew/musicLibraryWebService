/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice.repository;

import com.musiclibrary.musiclibraryapi.dto.MusicDTO;
import com.musiclibrary.musiclibrarywebservice.entity.Category;
import com.musiclibrary.musiclibrarywebservice.entity.Genre;
import com.musiclibrary.musiclibrarywebservice.entity.Music;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mehdi
 */
@Repository
public class MusicRepositoryImpl implements MusicRepository
{
    @Autowired
    private EntityManager em;
    
    @Autowired
    private CategoryRepository categoryRepo;
    
    @Autowired
    private GenreRepository genreRepo;
    
    private static final String GET_MUSICS = "Select m From Music m";
    
    private static final String GET_MUSIC_BY_ID_NAMED_PARAMETER = "Select m From Music m Where id = :id";
    private static final String MUSIC_ID_NAMED_PARAMETER = "id";
    
    private static final String GET_MUSIC_BY_ID_POSITIONAL_PARAMETER = "Select m From Music m Where id = ?1";
    private static final int MUSIC_ID_POSITIONAL_PARAMETER = 1;
    
    @Override
    public List<Music> getMusics() 
    {
        Query q = em.createQuery(GET_MUSICS);
        List<Music> musicList =  q.getResultList();
        
        return musicList;
    }

    @Override
    public Music insertMusic(MusicDTO musicDTO) 
    {
        //CONVERT RELEASE YEAR DATE FROM String TO LocalDate
        musicDTO.setReleaseYearLocalDate(musicDTO.getReleaseYear());
        
        //RECOVER THE Category
        Category foundCategory = categoryRepo.findById(musicDTO.getCategoryId());
        
        Music music = new Music(musicDTO.getAlbum(), musicDTO.getBand(), musicDTO.getReleaseYearLocalDate(), foundCategory);
        em.persist(music);
        
        //CONVERT RELEASE YEAR DATE FROM LocalDate TO String
        music.setConvertedReleaseYearToString(music.getReleaseYear());
        
        return music;
    }

    @Override
    public Music updateMusic(MusicDTO musicDTO, long id) 
    {
        //FIND THE Music TO UPDATE
        Music foundMusic = em.find(Music.class, id);
        
        //FIND THE Category RELATED TO THE Music
        Category foundCategory = categoryRepo.findById(musicDTO.getCategoryId());
        
        //FIND THE Genre RELATED TO THE Music
        Set<Genre> foundGenres = genreRepo.findByIdS(musicDTO.getGenres());
        
        //CONVERT RELEASE YEAR FROM String TO LocalDate
        musicDTO.setReleaseYearLocalDate(musicDTO.getReleaseYear());
        
        
        foundMusic.setAlbum(musicDTO.getAlbum());
        foundMusic.setBand(musicDTO.getBand());
        foundMusic.setCategory(foundCategory);
        foundMusic.setGenres(foundGenres);
        foundMusic.setReleaseYear(musicDTO.getReleaseYearLocalDate());
        
        em.persist(foundMusic);
        
        //CONVERT RELEASE YEAR FROM LocalDate TO String
        foundMusic.setConvertedReleaseYearToString(foundMusic.getReleaseYear());
        
        return foundMusic;
    }

    @Override
    public Music deleteMusicById(long id) 
    {
        //FIND THE Music TO UPDATE
        Music foundMusic = em.find(Music.class, id);
        
        em.remove(foundMusic);
        
        return foundMusic;
    }

    @Override
    public Music findById(long id) 
    {
//        TypedQuery<Music> foundMusic = em.createQuery(GET_MUSIC_BY_ID_POSITIONAL_PARAMETER, Music.class);
//        foundMusic.setParameter(MUSIC_ID_POSITIONAL_PARAMETER, id);
//        Music music = foundMusic.getSingleResult();
        
        Query  q = em.createQuery(GET_MUSIC_BY_ID_POSITIONAL_PARAMETER);
        q.setParameter(MUSIC_ID_POSITIONAL_PARAMETER, id);
        Music music = (Music) q.getSingleResult();
        
        return music;
    }
    
}
