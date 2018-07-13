/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice.repository;

import com.musiclibrary.musiclibraryapi.dto.MusicDTO;
import com.musiclibrary.musiclibrarywebservice.entity.Music;
import java.util.List;

/**
 *
 * @author Mehdi
 */
public interface MusicRepository 
{
    List<Music> getMusics();
    
    Music insertMusic(MusicDTO musicDTO);
    
    Music updateMusic(MusicDTO musicDTO, long id);
    
    Music deleteMusicById(long id);
    
    Music findById(long id);
    
}
