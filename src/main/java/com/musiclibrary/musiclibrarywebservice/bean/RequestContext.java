/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice.bean;

import com.musiclibrary.musiclibraryapi.dto.MusicDTO;
import javax.annotation.ManagedBean;
import lombok.Data;
import org.springframework.web.context.annotation.RequestScope;

/**
 *
 * @author Mehdi
 */
@ManagedBean
@RequestScope
@Data
public class RequestContext 
{
    private long pathVariable;
    
    private MusicDTO musicDTO;
}
