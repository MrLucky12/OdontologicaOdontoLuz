package com.zumaran.odontooga.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zumaran.odontooga.app.models.entity.OfficeDetail;

public interface IOfficeDetailService {
    
    public List<OfficeDetail> getAll();

    public OfficeDetail get(Long id);


    public Page <OfficeDetail> findAll(Pageable pageable);

    public void save(OfficeDetail dentist);

    public void delete(Long id);

}
