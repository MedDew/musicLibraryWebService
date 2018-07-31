/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice.controller;

import com.musiclibrary.musiclibraryapi.dto.MusicDTO;
import com.musiclibrary.musiclibrarywebservice.service.MusicManager;
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
public class MusicController 
{
    @Autowired 
    private MusicManager musicService; 
    
    @GetMapping(path = "/musics")
    public List<MusicDTO> getMusics()
    {
        List<MusicDTO> musics = musicService.getMusics();
        
        return musics;
    }
    
    @GetMapping("/musics/{id}")
    public MusicDTO getMusic(@PathVariable(name = "id") long musicId)
    {
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
        MusicDTO updatedMusic = musicService.updateMusic(musicDTO, musicId);
        
        return updatedMusic;
    }
    
    @PutMapping(path = "/musics/update/addGenre/{id}")
    public MusicDTO addGenreMusic(@RequestBody MusicDTO musicDTO, @PathVariable(name = "id") long musicId)
    {
        MusicDTO updatedMusic = musicService.addGenreMusic(musicDTO, musicId);
        
        return updatedMusic;
    }
    
    @DeleteMapping(path = "/musics/delete/{id}")
    public MusicDTO deleteMusic(@PathVariable long id)
    {
        MusicDTO deletedMusic = musicService.deleteMusicById(id);
        
        return deletedMusic;
    }
}
