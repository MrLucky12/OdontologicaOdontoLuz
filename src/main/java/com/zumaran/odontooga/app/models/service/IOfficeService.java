package com.zumaran.odontooga.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zumaran.odontooga.app.models.entity.Office;

public interface IOfficeService {

    public List<Office> getAll();
    
    public Page<Office> getAll(Pageable pageable);

    public void save(Office office);

    public Office get(Long id);

    public void delete(Long id);

    public List<Office> findById(Long id);
    

}
