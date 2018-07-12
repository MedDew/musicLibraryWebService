/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice.service;

import com.musiclibrary.musiclibraryapi.dto.CategoryDTO;
import java.util.List;

/**
 *
 * @author Mehdi
 */
public interface CategoryManager 
{
    List<CategoryDTO> getCategories();
    
    CategoryDTO insertCategory(CategoryDTO categoryDTO);
    
    CategoryDTO updateCategory(CategoryDTO categoryDTO, long id);
    
    CategoryDTO deleteCategoryById(long id);
    
    CategoryDTO findById(long id);
}
