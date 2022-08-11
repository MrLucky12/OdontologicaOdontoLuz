package com.zumaran.odontooga.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.zumaran.odontooga.app.models.entity.Client;

public interface IClientDAO extends PagingAndSortingRepository<Client, Long>{
    
}
