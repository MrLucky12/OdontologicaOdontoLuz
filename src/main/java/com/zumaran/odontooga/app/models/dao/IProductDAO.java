package com.zumaran.odontooga.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.zumaran.odontooga.app.models.entity.Product;

public interface IProductDAO extends PagingAndSortingRepository<Product, Long>{
    
}
