/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.musiclibrarywebservice.repository;

import com.musiclibrary.musiclibraryapi.dto.CategoryDTO;
import com.musiclibrary.musiclibrarywebservice.entity.Category;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mehdi
 */
@Repository
public class CategoryRepositoryImpl implements CategoryRepository
{
    @PersistenceContext
    private EntityManager em;
    
    private static final String GET_CATEGORIES = "Select c From Category c";
    
    private static final String GET_CATEGORY_BY_ID_NAMED_PARAMETER = "Select c From Category c Where id = :id";
    private static final String CATEGORY_ID_NAMED_PARAMETER = "id";
    
    private static final String GET_CATEGORY_BY_ID_POSITIONAL_PARAMETER = "Select c From Category c Where id = ?1";
    private static final int CATEGORY_ID_POSITIONAL_PARAMETER = 1;
    
    @Override
    public List<Category> getCategories() 
    {
        Query q = em.createQuery(GET_CATEGORIES);
        List<Category> categoryList = q.getResultList();
        
        return categoryList;
    }

    @Override
    public Category insertCategory(CategoryDTO categoryDTO) 
    {
        Category category = new Category(categoryDTO.getCategoryName());
        em.persist(category);
        
        return category;
    }

    @Override
    public Category updateCategory(CategoryDTO categoryDTO, long id) 
    {
        Category foundCategory = em.find(Category.class, id);
        foundCategory.setCategoryName(categoryDTO.getCategoryName());
        
        em.persist(foundCategory);
        
        return foundCategory;
    }

    @Override
    public Category deleteCategoryById( long id) 
    {
        Category foundCategory = em.find(Category.class, id);
        
        em.remove(foundCategory);
        
        return foundCategory;
    }

    @Override
    public Category findById(long id) 
    {
        Query q = em.createQuery(GET_CATEGORY_BY_ID_POSITIONAL_PARAMETER);
        q.setParameter(CATEGORY_ID_POSITIONAL_PARAMETER, id);
        
        Category foundCategory = (Category) q.getSingleResult();
        
        return foundCategory;
    }
    
}
