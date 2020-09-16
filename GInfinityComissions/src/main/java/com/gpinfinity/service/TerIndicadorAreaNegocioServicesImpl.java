/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.service;

import com.gpinfinity.entities.TerIndicadorAreaNegocio;
import com.gpinfinity.entities.TerIndicadorAreaNegocioPK;
import com.gpinfinity.repository.ITerIndicadorAreaNegocioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nivrist
 */
@Service
public class TerIndicadorAreaNegocioServicesImpl implements ITerIndicadorAreaNegocioServices{

    
    
    @Autowired
    ITerIndicadorAreaNegocioRepository terIndicadorAreaNegocioRepository;
    
    @Override
    public List<TerIndicadorAreaNegocio> listAllIndicadorAreaNegocio() {
        return terIndicadorAreaNegocioRepository.findAll();
    }

    @Override
    public List<TerIndicadorAreaNegocio> findByIdAreaNegocio(int idAreaNegocio) {
        return terIndicadorAreaNegocioRepository.findByIdAreaNegocio(idAreaNegocio);
    }

    
    
}
