package com.zumaran.odontooga.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zumaran.odontooga.app.models.dao.IProductDAO;
import com.zumaran.odontooga.app.models.entity.Product;

@Service
public class ProductServiceImp implements IProductService {

    @Autowired
    private IProductDAO productDAO;
    
    @Override
    @Transactional
    public void delete(Long id) { productDAO.deleteById(id); }

    @Override
    @Transactional(readOnly = true)
    public Product get(Long id) { return productDAO.findById(id).orElse(null); }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAll() { return (List<Product>) productDAO.findAll(); }

    @Override
    @Transactional
    public void save(Product product) { productDAO.save(product); }

    @Override
    public List<Product> findById(Long id) {
        return null;
    }

    //GET ALL BY PAGINATOR
    @Override
    @Transactional( readOnly = true)
    public Page<Product> getAll(Pageable pageable) { return productDAO.findAll(pageable); }
    
    


}
