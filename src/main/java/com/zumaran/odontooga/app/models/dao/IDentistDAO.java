package com.zumaran.odontooga.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.zumaran.odontooga.app.models.entity.Dentist;

public interface IDentistDAO extends PagingAndSortingRepository<Dentist, Long> {
    
}
