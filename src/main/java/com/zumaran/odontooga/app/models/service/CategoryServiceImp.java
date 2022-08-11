package com.zumaran.odontooga.app.models.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zumaran.odontooga.app.models.entity.Category;

@Repository
public class CategoryServiceImp implements ICategoryService{

    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public void delete(Long id) {
        manager.remove(get(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Category get(Long id) {
        return manager.find(Category.class, id);
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<Category> getAll() {
        return manager.createQuery("from Category").getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Category> getProductByCategory(Long id) {
        return manager.createQuery("select c.products from Category c where c.id = "+id).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public String countProductsByCategory(Long id) {
        return manager.createNamedQuery("select count(c.products) from Category c where c.id = "+id).toString();
    }

    @Override
    @Transactional
    public void save(Category category) {
        if(category.getId() != null && category.getId() > 0) {
            manager.merge(category);
        } else{
            manager.persist(category);
        }
    }
    
}
