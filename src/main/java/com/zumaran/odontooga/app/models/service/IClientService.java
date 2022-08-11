package com.zumaran.odontooga.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zumaran.odontooga.app.models.entity.Client;

public interface IClientService {
    
    public List<Client> getAll();

    public Client get(Long id);

    public Page <Client> findAll(Pageable pageable);

    public void save(Client dentist);

    public void delete(Long id);

}
