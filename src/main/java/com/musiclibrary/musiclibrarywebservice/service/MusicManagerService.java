/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice.service;

import com.musiclibrary.musiclibraryapi.dto.CategoryDTO;
import com.musiclibrary.musiclibraryapi.dto.GenreDTO;
import com.musiclibrary.musiclibraryapi.dto.MusicDTO;
import com.musiclibrary.musiclibrarywebservice.entity.Music;
import com.musiclibrary.musiclibrarywebservice.repository.MusicRepository;
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
public class MusicManagerService implements MusicManager 
{

    @Autowired
    private MusicRepository musicRepo;
    
    @Autowired
    private CategoryManager categoryService;
    
    @Autowired
    private GenreManager genreService;
    
    @Override
    public List<MusicDTO> getMusics() 
    {
        List<Music> musicList = musicRepo.getMusics();
        List<MusicDTO> musics = new ArrayList<>();
        
        musicList.forEach(m -> {
            
            //CRAPPY
            //FIND THE CORRECT Category ASSOCIATED TO THE Music FOUND 
            //TO RESPECT THE BASE ARCHITECTURE TO JUST RETURNING THE DTO
            CategoryDTO category = categoryService.findById(m.getCategory().getId());
            
            //CRAPPY
            //FIND THE CORRECT Genres ASSOCIATED TO THE Music FOUND
            //TO RESPECT THE BASE ARCHITECTURE TO JUST RETURNING THE DTO
            List<GenreDTO> genres = new ArrayList<>();
            m.getGenres().forEach(g -> {
                GenreDTO genre = genreService.findByID(g.getId());
                
                genres.add(genre);
            });
            
//            MusicDTO music = new MusicDTO(m.getId(), m.getAlbum(), m.getBand(), m.getConvertedReleaseYearToString(), m.getCategory().getId(), m.getGenres(), m.getReleaseYear());
            MusicDTO music = new MusicDTO(m.getId(), m.getAlbum(), m.getBand(), m.getConvertedReleaseYearToString(), category, genres);
            
            musics.add(music);
        });
        
        return musics;
    }

    @Override
    public MusicDTO insertMusic(MusicDTO musicDTO) 
    {
        Music createdMusic = musicRepo.insertMusic(musicDTO);
        
        //CRAPPY
        //FIND THE CORRECT Category ASSOCIATED TO THE Music CREATED 
        //TO RESPECT THE BASE ARCHITECTURE TO JUST RETURNING THE DTO
        CategoryDTO category = categoryService.findById(createdMusic.getCategory().getId());
        
        //CRAPPY
        //FIND THE CORRECT Genres ASSOCIATED TO THE Music CREATED
        //TO RESPECT THE BASE ARCHITECTURE TO JUST RETURNING THE DTO
        List<GenreDTO> genres = new ArrayList<>();
        createdMusic.getGenres().forEach(g -> {
            GenreDTO genre = genreService.findByID(g.getId());
            genres.add(genre);
        });
        
        MusicDTO music = new MusicDTO(
                                        createdMusic.getId(), 
                                        createdMusic.getAlbum(), 
                                        createdMusic.getBand(), 
                                        createdMusic.getConvertedReleaseYearToString(), 
                                        category, 
                                        genres
                                     );
        
        return music;
    }

    @Override
    public MusicDTO updateMusic(MusicDTO musicDTO, long id) 
    {
        Music updatedMusic = musicRepo.updateMusic(musicDTO, id);
        
        //CRAPPY
        //FIND THE CORRECT Category ASSOCIATED TO THE Music UPDATED 
        //TO RESPECT THE BASE ARCHITECTURE TO JUST RETURNING THE DTO
        CategoryDTO category = categoryService.findById(updatedMusic.getCategory().getId());
        
        //CRAPPY
        //FIND THE CORRECT Genres ASSOCIATED TO THE Music UPDATED
        //TO RESPECT THE BASE ARCHITECTURE TO JUST RETURNING THE DTO
        List<GenreDTO> genres = new ArrayList<>();
        updatedMusic.getGenres().forEach(g -> {
            GenreDTO genre = genreService.findByID(g.getId());
            
            genres.add(genre);
        });
        
        
        MusicDTO music = new MusicDTO(
                                        updatedMusic.getId(), 
                                        updatedMusic.getAlbum(), 
                                        updatedMusic.getBand(), 
                                        updatedMusic.getConvertedReleaseYearToString(), 
                                        category,
                                        genres
                                     );
        
        return music;
    }

    @Override
    public MusicDTO addGenreMusic(MusicDTO musicDTO, long id) 
    {
        Music addedGenreMusic = musicRepo.addGenreMusic(musicDTO, id);
        
        //CRAPPY
        //FIND THE CORRECT Category ASSOCIATED TO THE Music WHICH WAS ADDED Genre 
        //TO RESPECT THE BASE ARCHITECTURE TO JUST RETURNING THE DTO
        addedGenreMusic.getCategory().getId();
        CategoryDTO category = categoryService.findById(addedGenreMusic.getCategory().getId());
        
        //CRAPPY
        //FIND THE CORRECT Genres ASSOCIATED TO THE Music WHICH WAS ADDED Genre
        //TO RESPECT THE BASE ARCHITECTURE TO JUST RETURNING THE DTO
        List<GenreDTO> genres = genreService.findByIDS(musicDTO.getGenres());

        MusicDTO music = new MusicDTO(
                                       addedGenreMusic.getId(), 
                                       addedGenreMusic.getAlbum(), 
                                       addedGenreMusic.getBand(), 
                                       addedGenreMusic.getConvertedReleaseYearToString(), 
                                       category, 
                                       genres
                                     );
        
        return music;
    }
    
    @Override
    public MusicDTO deleteMusicById(long id) 
    {
        Music deletedMusic = musicRepo.deleteMusicById(id);
        
        //CRAPPY
        //FIND THE CORRECT Category ASSOCIATED TO THE Music DELETED 
        //TO RESPECT THE BASE ARCHITECTURE TO JUST RETURNING THE DTO
        CategoryDTO category = categoryService.findById(deletedMusic.getCategory().getId());
        
        //CRAPPY
        //FIND THE CORRECT Genres ASSOCIATED TO THE Music DELETED
        //TO RESPECT THE BASE ARCHITECTURE TO JUST RETURNING THE DTO
        List<GenreDTO> genres = new ArrayList<>();
        deletedMusic.getGenres().forEach(g -> {
            GenreDTO genre = genreService.findByID(g.getId());
            
            genres.add(genre);
        });
        
        
        
        MusicDTO music = new MusicDTO(
                                        deletedMusic.getId(), 
                                        deletedMusic.getAlbum(), 
                                        deletedMusic.getBand(), 
                                        deletedMusic.getConvertedReleaseYearToString(), 
                                        category, 
                                        genres
                                     );
        
        return music;
    }

    @Override
    public MusicDTO findById(long id) 
    {
        Music foundMusic = musicRepo.findById(id);
        
        //CRAPPY
        //FIND THE CORRECT Category ASSOCIATED TO THE Music FOUND 
        //TO RESPECT THE BASE ARCHITECTURE TO JUST RETURNING THE DTO
        CategoryDTO category = categoryService.findById(foundMusic.getCategory().getId());
        
        //CRAPPY
        //FIND THE CORRECT Genres ASSOCIATED TO THE Music FOUND
        //TO RESPECT THE BASE ARCHITECTURE TO JUST RETURNING THE DTO
        List<GenreDTO> genres = new ArrayList<>();
        foundMusic.getGenres().forEach(g -> {
            GenreDTO genre = genreService.findByID(g.getId());
            
            genres.add(genre);
        });
        
        MusicDTO music = new MusicDTO(
                                        foundMusic.getId(), 
                                        foundMusic.getAlbum(), 
                                        foundMusic.getBand(), 
                                        foundMusic.getConvertedReleaseYearToString(), 
                                        category, 
                                        genres
                                     );
        
        return music;
    }
    
}
