/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.service;

import com.gpinfinity.entities.TerParamTextil;
import com.gpinfinity.repository.ITerParamTextilRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nivrist
 */

@Service
public class TerParamTextilServicesImpl implements ITerParamTextilServices{

    
    @Autowired
    ITerParamTextilRepository iTerParamTextilRepository;
    
    @Override
    public List<TerParamTextil> findAll() {
        return iTerParamTextilRepository.findAll();
    }

    @Override
    public void createParameter(TerParamTextil param) {
        iTerParamTextilRepository.save(param);
    }

    @Override
    public void updateParameter(TerParamTextil param) {
        iTerParamTextilRepository.save(param);
    }

    @Override
    public void deleteParamter(TerParamTextil param) {
        iTerParamTextilRepository.delete(param);
    }

    @Override
    public int getNextId() {
        return iTerParamTextilRepository.nextId();
    }
    
}
