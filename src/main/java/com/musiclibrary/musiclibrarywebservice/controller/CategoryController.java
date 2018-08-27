/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice.controller;

import com.musiclibrary.musiclibraryapi.dto.CategoryDTO;
import com.musiclibrary.musiclibrarywebservice.bean.RequestContext;
import com.musiclibrary.musiclibrarywebservice.service.CategoryManager;
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
public class CategoryController 
{
    @Autowired
    private CategoryManager categoryService;
    
    @Autowired
    private RequestContext requestContext;
    
    @GetMapping("/categories")
    public List<CategoryDTO> getCategories()
    {
        return categoryService.getCategories();
    }
    
    @GetMapping("/categories/{id}")
    public CategoryDTO getCategory(@PathVariable(name = "id") long categoryId )
    {
        requestContext.setPathVariable(categoryId);
        requestContext.setUri("/categories/"+categoryId);
        return categoryService.findById(categoryId);
    }
    
    @PostMapping("/categories/create")
    public CategoryDTO postCategory(@RequestBody CategoryDTO categoryDTO)
    {
        requestContext.setCategoryDTO(categoryDTO);
        return categoryService.insertCategory(categoryDTO);
    }
    
    @PutMapping("/categories/update/{id}")
    public CategoryDTO putCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable(name = "id") long categoryId)
    {
        requestContext.setPathVariable(categoryId);
        requestContext.setUri("/categories/update/"+categoryId);
        return categoryService.updateCategory(categoryDTO, categoryId);
    }
    
    @DeleteMapping("/categories/delete/{id}")
    public CategoryDTO deleteCategory(@PathVariable(name = "id") long categoryId)
    {
        requestContext.setPathVariable(categoryId);
        requestContext.setUri("/categories/update/"+categoryId);
        return categoryService.deleteCategoryById(categoryId);
    }
    
}
