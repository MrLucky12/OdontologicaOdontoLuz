package com.zumaran.odontooga.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zumaran.odontooga.app.models.entity.Product;

public interface IProductService {
    
    public List<Product> getAll();

    public Page<Product> getAll(Pageable pageable);

    public void save(Product product);

    public Product get(Long id);

    public void delete(Long id);

    public List<Product> findById(Long id);
    
    //ADD AT LEAST 2 METHODS 

    //PRODUCTS BY ID IS ALREADY IN CATEGORY SECTION :3
    //METHOD 1
    //public List<Product> getProductByCategory(Long id);


}
