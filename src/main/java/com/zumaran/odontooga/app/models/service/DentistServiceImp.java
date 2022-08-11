package com.zumaran.odontooga.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zumaran.odontooga.app.models.dao.IDentistDAO;
import com.zumaran.odontooga.app.models.entity.Dentist;

@Service
public class DentistServiceImp implements IDentistService {

    @Autowired
    private IDentistDAO dentistDAO;

    @Override
    public void delete(Long id) {  }

    @Override
    @Transactional(readOnly = true)
    public Page<Dentist> findAll(Pageable pageable) { return dentistDAO.findAll(pageable); }

    @Override
    @Transactional(readOnly = true)
    public List<Dentist> getAll() { return (List<Dentist>) dentistDAO.findAll(); }

    @Override
    public void save(Dentist dentist) { dentistDAO.save(dentist); }

    @Override
    public Dentist get(Long id) { return dentistDAO.findById(id).orElse(null); }

    
    
}
