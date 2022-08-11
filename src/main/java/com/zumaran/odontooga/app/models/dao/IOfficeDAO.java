package com.zumaran.odontooga.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.zumaran.odontooga.app.models.entity.Office;

public interface IOfficeDAO extends PagingAndSortingRepository<Office, Long>{
    
}
