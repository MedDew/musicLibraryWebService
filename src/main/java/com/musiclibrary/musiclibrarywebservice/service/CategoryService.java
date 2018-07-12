/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice.service;

import com.musiclibrary.musiclibraryapi.dto.CategoryDTO;
import com.musiclibrary.musiclibrarywebservice.entity.Category;
import com.musiclibrary.musiclibrarywebservice.repository.CategoryRepository;
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
public class CategoryService implements CategoryManager
{
    @Autowired
    private CategoryRepository categoryRepo;

    @Override
    public List<CategoryDTO> getCategories() 
    {
        List<Category> categoryList = categoryRepo.getCategories();
        List<CategoryDTO> categories = new ArrayList<>();
        
        categoryList.forEach(c ->{
            CategoryDTO categoryDTO = new CategoryDTO(c.getId(), c.getCategoryName());
            categories.add(categoryDTO);
        });
        
        return categories;
    }

    @Override
    public CategoryDTO insertCategory(CategoryDTO categoryDTO) 
    {
        Category createdCategory = categoryRepo.insertCategory(categoryDTO);
        CategoryDTO category = new CategoryDTO(createdCategory.getId(), createdCategory.getCategoryName());
        
        return category;
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, long id) 
    {
        Category updatedCategory = categoryRepo.updateCategory(categoryDTO, id);
        CategoryDTO category = new CategoryDTO(updatedCategory.getId(), updatedCategory.getCategoryName());
        
        return category;
    }

    @Override
    public CategoryDTO deleteCategoryById(long id) 
    {
        Category deletedCategory = categoryRepo.deleteCategoryById(id);
        CategoryDTO category = new CategoryDTO(deletedCategory.getCategoryName());
        
        return category;
    }

    @Override
    public CategoryDTO findById(long id) 
    {
        Category foundCategory = categoryRepo.findById(id);
        CategoryDTO category = new CategoryDTO(foundCategory.getId(), foundCategory.getCategoryName());
        
        return category;
    }
    
}
