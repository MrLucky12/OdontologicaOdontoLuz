package com.zumaran.odontooga.app.models.dao;

import com.zumaran.odontooga.app.models.entity.OfficeDetail;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface IOfficeDetailDAO extends PagingAndSortingRepository<OfficeDetail, Long>{
    
}
