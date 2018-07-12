/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice.repository;

import com.musiclibrary.musiclibraryapi.dto.CategoryDTO;
import com.musiclibrary.musiclibrarywebservice.entity.Category;
import java.util.List;

/**
 *
 * @author Mehdi
 */
public interface CategoryRepository 
{
    List<Category> getCategories();
    
    Category insertCategory(CategoryDTO categoryDTO);
    
    Category updateCategory(CategoryDTO categoryDTO, long id);
    
    Category deleteCategoryById(long id);
    
    Category findById(long id);
}
