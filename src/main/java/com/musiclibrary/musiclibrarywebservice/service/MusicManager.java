/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice.service;

import com.musiclibrary.musiclibraryapi.dto.MusicDTO;
import java.util.List;

/**
 *
 * @author Mehdi
 */
public interface MusicManager 
{
    List<MusicDTO> getMusics();
    
    MusicDTO insertMusic(MusicDTO musicDTO);
    
    MusicDTO updateMusic(MusicDTO musicDTO, long id);
    
    MusicDTO addGenreMusic(MusicDTO musicDTO, long id);
    
    MusicDTO removeGenreMusic(MusicDTO musicDTO, long id);
    
    MusicDTO deleteMusicById(long id);
    
    MusicDTO findById(long id);
}
