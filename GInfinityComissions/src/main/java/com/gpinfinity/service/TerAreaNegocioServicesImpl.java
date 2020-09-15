/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.service;

import com.gpinfinity.entities.TerAreaNegocio;
import com.gpinfinity.repository.ITerAreaNegocioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author piru876
 */

@Service
public class TerAreaNegocioServicesImpl  implements ITerAreaNegocioServices{

    @Autowired
    ITerAreaNegocioRepository iterAreaNegocioRepository;
    
    @Override
    public List<TerAreaNegocio> listAllAreaNegocio() {
        return iterAreaNegocioRepository.findAll();
    }
    
}
