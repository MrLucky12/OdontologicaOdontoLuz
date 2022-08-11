package com.zumaran.odontooga.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zumaran.odontooga.app.models.dao.IClientDAO;
import com.zumaran.odontooga.app.models.entity.Client;

@Service
public class ClientServiceImp implements IClientService{
 
    @Autowired
    private IClientDAO clientDAO;

    @Override
    public void delete(Long id) {  }

    @Override
    @Transactional(readOnly = true)
    public Page<Client> findAll(Pageable pageable) { return clientDAO.findAll(pageable); }

    @Override
    @Transactional(readOnly = true)
    public List<Client> getAll() { return (List<Client>) clientDAO.findAll(); }

    @Override
    public void save(Client dentist) { clientDAO.save(dentist); }

    @Override
    public Client get(Long id) { return clientDAO.findById(id).orElse(null); }


}
