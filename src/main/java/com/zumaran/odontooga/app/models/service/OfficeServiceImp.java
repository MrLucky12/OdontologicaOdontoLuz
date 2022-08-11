package com.zumaran.odontooga.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zumaran.odontooga.app.models.dao.IOfficeDAO;
import com.zumaran.odontooga.app.models.entity.Office;

@Service
public class OfficeServiceImp implements IOfficeService{
    
    @Autowired
    private IOfficeDAO office;

    @Override
    @Transactional
    public void delete(Long id) { office.deleteById(id); }

    @Override
    public List<Office> findById(Long id) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Office get(Long id) { return office.findById(id).orElse(null); }

    @Override
    @Transactional(readOnly = true)
    public List<Office> getAll() { return (List<Office>) office.findAll(); }

    @Override
    @Transactional( readOnly = true)
    public Page<Office> getAll(Pageable pageable) { return office.findAll(pageable); }

    @Override
    @Transactional
    public void save(Office office) { this.office.save(office); }

    
}
