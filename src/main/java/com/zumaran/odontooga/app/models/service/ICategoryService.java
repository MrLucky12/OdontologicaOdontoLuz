package com.zumaran.odontooga.app.models.service;

import java.util.List;

import com.zumaran.odontooga.app.models.entity.Category;

public interface ICategoryService {
    
    public List<Category> getAll();

    public void save(Category category);

    public Category get(Long id);

    public void delete(Long id);

    public List<Category> getProductByCategory(Long id);

    public String countProductsByCategory(Long id);

}
