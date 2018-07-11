/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Mehdi
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Genre 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    
    @Column(length = 20, unique = true)
    private String genreName;
    
    @ManyToMany(fetch = FetchType.LAZY ,cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "genres")
    private Set<Music> musics = new HashSet<Music>();
}
