package com.zumaran.odontooga.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zumaran.odontooga.app.models.dao.IOfficeDetailDAO;
import com.zumaran.odontooga.app.models.entity.OfficeDetail;

@Service
public class OfficeDetailServiceImp implements IOfficeDetailService{

    @Autowired
    private IOfficeDetailDAO odDAO;

    @Override
    public void delete(Long id) { /* DELETE */ }

    @Override
    @Transactional(readOnly = true)
    public Page<OfficeDetail> findAll(Pageable pageable) { return odDAO.findAll(pageable); }

    @Override
    @Transactional(readOnly = true)
    public List<OfficeDetail> getAll() { return (List<OfficeDetail>) odDAO.findAll(); }

    @Override
    public void save(OfficeDetail officeDetail) { odDAO.save(officeDetail); }

    @Override
    public OfficeDetail get(Long id) { return odDAO.findById(id).orElse(null); }

    
    
    
}
