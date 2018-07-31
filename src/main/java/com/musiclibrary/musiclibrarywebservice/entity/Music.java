/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author Mehdi
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Music 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(length = 255)
    private String album;
    
    @Column(length = 255)
    private String band;
    
    private LocalDate releaseYear;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "music_genre", joinColumns = {@JoinColumn(name = "music_id")}, inverseJoinColumns = {@JoinColumn(name = "genre_id")})
    private Set<Genre> genres = new HashSet<Genre>();
    
    @Transient
    private String convertedReleaseYearToString;

    public Music(String album, String band, LocalDate releaseYear, Category category, Set<Genre> genres) 
    {
        this.album = album;
        this.band = band;
        this.releaseYear = releaseYear;
        this.category = category;
        this.genres = genres;
    }
    
    public String getConvertedReleaseYearToString()
    {
        return this.convertedReleaseYearToString;
    }
    
    public void setConvertedReleaseYearToString(LocalDate dateToConvert)
    {
        String convertedDate = convertFromLocalDateToString(dateToConvert);
        this.convertedReleaseYearToString = convertedDate;
    }
    
    private String convertFromLocalDateToString(LocalDate dateToConvert)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String localDate = dateToConvert.format(formatter);
        
        return localDate;
    }
    
}
