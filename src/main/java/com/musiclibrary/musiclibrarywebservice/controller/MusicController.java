/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice.controller;

import com.musiclibrary.musiclibraryapi.dto.MusicDTO;
import com.musiclibrary.musiclibrarywebservice.bean.RequestContext;
import com.musiclibrary.musiclibrarywebservice.service.MusicManager;
import java.util.List;
import javax.validation.Valid;
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
public class MusicController 
{
    @Autowired 
    private MusicManager musicService;
    
    @Autowired
    private RequestContext requestContext;
    
    @GetMapping(path = "/musics")
    public List<MusicDTO> getMusics()
    {
        List<MusicDTO> musics = musicService.getMusics();
        
        return musics;
    }
    
    @GetMapping("/musics/{id}")
    public MusicDTO getMusic(@PathVariable(name = "id") long musicId)
    {
        requestContext.setPathVariable(musicId);
        MusicDTO music = musicService.findById(musicId);
        
        return music;
    }
    
    @PostMapping(name = "/musics/create")
    public MusicDTO postMusic(@RequestBody MusicDTO musicDTO)
    {
        MusicDTO createdMusic = musicService.insertMusic(musicDTO);
        
        return createdMusic;
    }
    
    @PutMapping(path = "/musics/update/{id}")
    public MusicDTO putMusic(@RequestBody MusicDTO musicDTO, @PathVariable(name = "id") long musicId)
    {
        requestContext.setPathVariable(musicId);
        requestContext.setMusicDTO(musicDTO);
        MusicDTO updatedMusic = musicService.updateMusic(musicDTO, musicId);
        
        return updatedMusic;
    }
    
    @PostMapping(path = "/musics/update/addGenre/{id}")
    public MusicDTO addGenreMusic(@RequestBody MusicDTO musicDTO, @PathVariable(name = "id") long musicId)
    {
        requestContext.setPathVariable(musicId);
        requestContext.setMusicDTO(musicDTO);
        MusicDTO updatedMusic = musicService.addGenreMusic(musicDTO, musicId);
        
        return updatedMusic;
    }
    
    @DeleteMapping(path = "/musics/delete/removeGenre/{id}")
    public MusicDTO deleteMusic(@RequestBody @Valid MusicDTO musicDTO, @PathVariable long id)
    {
        requestContext.setPathVariable(id);
        requestContext.setMusicDTO(musicDTO);
        MusicDTO deletedMusic = musicService.removeGenreMusic(musicDTO, id);
        
        return deletedMusic;
    }
    
    @DeleteMapping(path = "/musics/delete/{id}")
    public MusicDTO deleteMusic(@PathVariable long id)
    {
        requestContext.setPathVariable(id);
        MusicDTO deletedMusic = musicService.deleteMusicById(id);
        
        return deletedMusic;
    }
}
