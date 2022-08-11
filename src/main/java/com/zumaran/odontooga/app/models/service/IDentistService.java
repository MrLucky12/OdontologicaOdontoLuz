package com.zumaran.odontooga.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zumaran.odontooga.app.models.entity.Dentist;

public interface IDentistService {
    
    //FIND METHOD

    public List<Dentist> getAll();

    public Dentist get(Long id);


    public Page <Dentist> findAll(Pageable pageable);

    public void save(Dentist dentist);

    public void delete(Long id);

}
